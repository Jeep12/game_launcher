package updater.manager;

import updater.model.Component;
import updater.model.DirectoryComposite;
import updater.model.FileLeaf;
import updater.utils.ZipExtractor;
import updater.utils.FileDownloadWorker;
import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.Properties;

public class UpdaterManager {

    private static final int PROGRESS_UPDATE_INTERVAL = 1024 * 10; // Actualizar cada 10 KB

    private File selectedFolder;
    private JButton btnCheckFiles;
    private JButton btnSelectFolder;
    private JFileChooser folderChooser;
    private JTextField showPath;
    private String lastPath;
    private static final String CONFIG_FILE = System.getProperty("user.home") + "/L2Terra/config/config.properties";
    private JProgressBar loadingIndicatorCheckFiles;

    public UpdaterManager(JButton btnSelectFolder, JButton btnCheckFiles, JTextField showPath, JProgressBar loadingIndicatorCheckFiles) {
        this.btnSelectFolder = btnSelectFolder;
        this.btnCheckFiles = btnCheckFiles;
        this.showPath = showPath;
        this.folderChooser = new JFileChooser();
        this.loadingIndicatorCheckFiles = loadingIndicatorCheckFiles;
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        lastPath = loadLastPath();
        if (lastPath != null && !lastPath.isEmpty()) {
            selectedFolder = new File(lastPath);
            showPath.setText(lastPath);
            btnCheckFiles.setVisible(true);
            btnSelectFolder.setVisible(false);
        } else {
            selectedFolder = null;
            btnCheckFiles.setVisible(false);
            btnSelectFolder.setVisible(true);
        }

        setupListeners();
        if (!isUpdateAvailable()) {
            loadingIndicatorCheckFiles.setValue(100);
            loadingIndicatorCheckFiles.setString("El cliente esta actualizado");
        } else {
            loadingIndicatorCheckFiles.setValue(0);
            loadingIndicatorCheckFiles.setString("Hay una actualizacion disponible");
        }
    }

    private void setupListeners() {
        btnSelectFolder.addActionListener(e -> selectFolder());
        btnCheckFiles.addActionListener(e -> checkFiles());
    }

    public void selectFolder() {
        if (lastPath != null && !lastPath.isEmpty()) {
            folderChooser.setCurrentDirectory(new File(lastPath));
        } else {
            folderChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        }

        int returnValue = folderChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFolder = folderChooser.getSelectedFile();
            if (selectedFolder != null) {
                lastPath = selectedFolder.getAbsolutePath();
                showPath.setText(lastPath);
                btnCheckFiles.setVisible(true);
                btnSelectFolder.setVisible(false);
                saveLastPath(lastPath);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna carpeta.");
            }
        }
    }

    private void checkFiles() {
        if (selectedFolder != null) {
            // Reset y mostrar la barra de progreso para la verificación de archivos
            SwingUtilities.invokeLater(() -> {
                // Inicializa la barra de progreso a 0 y visible
                loadingIndicatorCheckFiles.setValue(0);
                loadingIndicatorCheckFiles.setString("Verificando archivos...");
                loadingIndicatorCheckFiles.setVisible(true);

                // Crear y ejecutar la tarea de verificación
                new Thread(() -> {
                    DirectoryComposite rootDirectory = new DirectoryComposite(selectedFolder.getAbsolutePath());
                    populateComponents(rootDirectory, selectedFolder);

                    // Verificar versiones
                    rootDirectory.checkVersion();

                    String clientVersion = getClientVersion();
                    String serverVersion = getServerVersionFromServer();

                    if (clientVersion.equals(serverVersion)) {
                        // Verificación completa, oculta la barra de progreso
                        SwingUtilities.invokeLater(() -> {
                            loadingIndicatorCheckFiles.setValue(100);
                            loadingIndicatorCheckFiles.setString(" La versión del cliente está actualizada.");
                            try {

                                Thread.sleep(200); // Pausa para que el usuario vea el mensaje
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });
                    } else {
                        // Progreso para la descarga de archivos
                        SwingUtilities.invokeLater(() -> {
                            loadingIndicatorCheckFiles.setString("Descargando archivos...");
                        });

                        // Resetear la barra de progreso para la descarga
                        downloadFilesFromServer();
                        updateClientVersion(serverVersion);
                    }
                }).start();
            });
        } else {
            System.out.println("No se ha seleccionado ninguna carpeta.");
        }
    }

    private void simulateProgress() {
        final int TOTAL_STEPS = 100;
        new Thread(() -> {
            for (int i = 0; i <= TOTAL_STEPS; i++) {
                try {
                    Thread.sleep(50); // Ajusta el tiempo para que la simulación sea más o menos lenta
                    int progress = i; // Progreso actual
                    SwingUtilities.invokeLater(() -> updateProgressBar(progress));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void populateComponents(DirectoryComposite directoryComposite, File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    DirectoryComposite subDirectory = new DirectoryComposite(file.getAbsolutePath());
                    directoryComposite.addComponent(subDirectory);
                    populateComponents(subDirectory, file);
                } else {
                    String version = getFileVersion(file); // Implementar según sea necesario
                    FileLeaf fileLeaf = new FileLeaf(file.getAbsolutePath(), version);
                    directoryComposite.addComponent(fileLeaf);
                }
            }
        }
    }

    private String getFileVersion(File file) {
        // Usa la versión del cliente para todos los archivos
        return getClientVersion();
    }

    private String getClientVersion() {
        File versionFile = new File(selectedFolder, "version.txt");
        if (versionFile.exists()) {
            try {
                List<String> lines = Files.readAllLines(versionFile.toPath());
                if (!lines.isEmpty()) {
                    return lines.get(0).trim();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "v0.0";
    }

    private void updateClientVersion(String newVersion) {
        File versionFile = new File(selectedFolder, "version.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(versionFile.toPath())) {
            writer.write(newVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getServerVersionFromServer() {
        try {
            URL url = new URL("http://localhost/l2terraupdater/version.txt");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
                return in.readLine().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "v0.0";
        }
    }

    private void saveLastPath(String path) {
        Properties properties = new Properties();
        properties.setProperty("lastPath", path);

        // Crea la carpeta config si no existe
        File configFile = new File(CONFIG_FILE);
        File configDir = configFile.getParentFile();
        if (!configDir.exists()) {
            configDir.mkdirs(); // Crear la carpeta si no existe
        }

        // Guarda el archivo config.properties dentro de la carpeta config
        try (OutputStream out = new FileOutputStream(configFile)) {
            properties.store(out, null);
            System.out.println("Archivo de configuración guardado en: " + configFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String loadLastPath() {
        File configFile = new File(CONFIG_FILE);
        if (configFile.exists()) {
            try (InputStream in = new FileInputStream(configFile)) {
                Properties props = new Properties();
                props.load(in);
                return props.getProperty("lastPath");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void showLoadingIndicator(boolean show, String message) {
        SwingUtilities.invokeLater(() -> {
            loadingIndicatorCheckFiles.setVisible(show);

            if (show) {
                loadingIndicatorCheckFiles.setValue(0); // Reinicia el progreso a 0
                loadingIndicatorCheckFiles.setString(message);
            } else {
                loadingIndicatorCheckFiles.setString("");
            }
        });
    }

    private void downloadFilesFromServer() {
        try {
            // Descargar el archivo version.txt del servidor
            String versionUrl = "http://localhost/l2terraupdater/version.txt";
            File versionFile = new File(selectedFolder, "version.txt");
            System.out.println("Descargando " + versionUrl);
            downloadFile(versionUrl, versionFile);

            // Leer el archivo version.txt para obtener la lista de archivos ZIP
            List<String> lines = Files.readAllLines(versionFile.toPath());
            if (lines.isEmpty()) {
                System.out.println("El archivo version.txt está vacío.");
                return;
            }

            // Obtener la lista de archivos ZIP desde el version.txt
            List<String> zipFiles = lines.subList(1, lines.size()); // Asumimos que la primera línea es la versión
            if (zipFiles.isEmpty()) {
                System.out.println("No se encontraron archivos ZIP en version.txt.");
                return;
            }

            System.out.println("Archivos ZIP a descargar: " + zipFiles);

            // Descargar y descomprimir cada archivo ZIP
            for (String zipFileName : zipFiles) {
                String zipFileUrl = "http://localhost/l2terraupdater/" + zipFileName;
                File zipFile = new File(selectedFolder, zipFileName);

                System.out.println("Descargando " + zipFileUrl);
                // Descargar cada archivo ZIP
                downloadFile(zipFileUrl, zipFile);

                System.out.println("Descomprimiendo " + zipFile.getAbsolutePath());
                // Descomprimir el archivo ZIP
                ZipExtractor.unzip(zipFile.getAbsolutePath(), selectedFolder.getAbsolutePath());
            }

            loadingIndicatorCheckFiles.setString("Actualización completada.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al descargar o descomprimir archivos.");
        }
    }

    private void updateProgressBar(final int progress) {
        SwingUtilities.invokeLater(() -> {
            loadingIndicatorCheckFiles.setValue(progress);
            loadingIndicatorCheckFiles.setString(progress + "%");
        });
    }

    // Método para descargar un archivo dado una URL y destino
    private void downloadFile(String fileUrl, File destinationFile) {
        try (InputStream inputStream = new URL(fileUrl).openStream(); FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytesRead = 0;
            long fileSize = new URL(fileUrl).openConnection().getContentLengthLong(); // Obtén el tamaño del archivo

            long lastUpdateTime = System.currentTimeMillis();
            long minUpdateInterval = 10; // Mínimo intervalo de actualización en milisegundos

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;

                long currentTime = System.currentTimeMillis();
                if (currentTime - lastUpdateTime > minUpdateInterval) {
                    int progress = (int) (totalBytesRead * 100 / fileSize);
                    updateProgressBar(progress);
                    lastUpdateTime = currentTime;
                }
                // Simulación de progreso más lento
                Thread.sleep(1); // Ajusta el tiempo de simulación
            }

            // Actualizar barra de progreso al final para asegurar el 100%
            updateProgressBar(100);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error al descargar el archivo: " + fileUrl);
        }
    }

    public void checkForUpdates() {
        if (isUpdateAvailable()) {
            System.out.println("Actualización disponible. Iniciando proceso de actualización...");
            downloadFilesFromServer();
            // Puedes añadir otros pasos aquí, como notificar al usuario o reiniciar la aplicación
        } else {
            System.out.println("No hay actualizaciones disponibles.");
        }
    }

    private boolean isUpdateAvailable() {
        try {
            URL versionUrl = new URL("http://localhost/l2terraupdater/version.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(versionUrl.openStream()));
            String serverVersion = in.readLine().trim();
            in.close();

            String clientVersion = getClientVersion();
            return serverVersion.compareTo(clientVersion) > 0; // Devuelve true si la versión del servidor es mayor
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Si hay error, asumimos que no hay actualización disponible
        }
    }

    public void runClient() {
        if (isUpdateAvailable()) {
            // Si hay una actualización disponible, mostrar un mensaje y no ejecutar el cliente
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null,
                        "Hay una nueva versión disponible. Por favor, actualiza el cliente antes de ejecutar.",
                        "Actualización Disponible",
                        JOptionPane.INFORMATION_MESSAGE);
            });
        } else {
            // Si no hay actualizaciones disponibles, proceder a ejecutar el cliente
            if (selectedFolder != null) {
                File clientExecutable = new File(selectedFolder, "system/l2.exe");
                if (clientExecutable.exists()) {
                    try {
                        executeWithElevation(clientExecutable.getAbsolutePath());
                    } catch (Exception ex) {
                        System.err.println("No se pudo ejecutar el cliente.");
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("El archivo ejecutable no se encuentra en la ruta especificada.");
                }
            } else {
                System.out.println("No se ha seleccionado ninguna carpeta.");
            }
        }
    }

    private void executeWithElevation(String command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", command);
        processBuilder.inheritIO().start();
    }
}
