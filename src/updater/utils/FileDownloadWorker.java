package updater.utils;

import javax.swing.SwingWorker;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloadWorker extends SwingWorker<Void, Void> {

    private String fileUrl;
    private File destinationFile;

    public FileDownloadWorker(String fileUrl, File destinationFile) {
        this.fileUrl = fileUrl;
        this.destinationFile = destinationFile;
    }

    @Override
    protected Void doInBackground() throws Exception {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        }

        return null;
    }
}
