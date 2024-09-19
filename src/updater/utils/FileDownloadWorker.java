package updater.utils;

import javax.swing.SwingWorker;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloadWorker extends SwingWorker<Void, Integer> {

    private String fileUrl;
    private File destinationFile;
    private java.util.function.Consumer<Integer> progressUpdater;

    public FileDownloadWorker(String fileUrl, File destinationFile, java.util.function.Consumer<Integer> progressUpdater) {
        this.fileUrl = fileUrl;
        this.destinationFile = destinationFile;
        this.progressUpdater = progressUpdater;
    }

    @Override
    protected Void doInBackground() throws Exception {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream(); FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytesRead = 0;
            long fileSize = connection.getContentLengthLong();

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                int progress = (int) (totalBytesRead * 100 / fileSize);
                publish(progress);
            }
        }

        return null;
    }

    @Override
    protected void process(java.util.List<Integer> chunks) {
        for (Integer progress : chunks) {
            progressUpdater.accept(progress);
        }
    }
}
