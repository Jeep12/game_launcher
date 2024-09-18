package updater.manager;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdaterManager {

    private File selectedFolder;
    private JButton btnCheckFiles;
    private JButton btnSelectFolder;
    private JFileChooser folderChooser;
    private JTextField showPath;
    private String lastPath; // Variable para almacenar la última ruta

    private static final String CONFIG_FILE = "config.properties"; // Archivo de configuración

    public UpdaterManager(JButton btnSelectFolder, JButton btnCheckFiles, JTextField showPath) {
        this.btnSelectFolder = btnSelectFolder;
        this.btnCheckFiles = btnCheckFiles;
        this.showPath = showPath;
        this.folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Lee la última ruta del archivo de configuración
        lastPath = loadLastPath();
        if (lastPath != null && !lastPath.isEmpty()) {
            selectedFolder = new File(lastPath); // Restaurar la última carpeta seleccionada
            showPath.setText(lastPath); // Mostrar la ruta en el JTextField
            btnCheckFiles.setVisible(true); // Mostrar el botón de comprobar archivos
            btnSelectFolder.setVisible(false); // Ocultar el botón de selección de carpeta
        } else {
            selectedFolder = null;
            btnCheckFiles.setVisible(false); // Ocultar el botón de comprobar archivos
            btnSelectFolder.setVisible(true); // Mostrar el botón de selección de carpeta
        }

        setupListeners();
    }

    private void setupListeners() {
        btnSelectFolder.addActionListener(e -> selectFolder());
        btnCheckFiles.addActionListener(e -> checkFiles());
    }

    public void selectFolder() {
        // Si hay una ruta previamente guardada, establece esa como el directorio inicial del JFileChooser
        if (lastPath != null && !lastPath.isEmpty()) {
            folderChooser.setCurrentDirectory(new File(lastPath));
        } else {
            // Si no hay una ruta guardada, inicia en el directorio de usuario por defecto
            folderChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        }

        int returnValue = folderChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFolder = folderChooser.getSelectedFile();
            if (selectedFolder != null) {
                // Actualiza la última ruta y muestra la ruta en el JTextField
                lastPath = selectedFolder.getAbsolutePath();
                showPath.setText(lastPath);

                // Muestra el botón de comprobar archivos y oculta el botón de selección de carpeta
                btnCheckFiles.setVisible(true);
                btnSelectFolder.setVisible(false);

                // Guarda la ruta actual en el archivo de configuración
                saveLastPath(lastPath);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna carpeta.");
            }
        }
    }

    private void checkFiles() {
        if (selectedFolder != null) {
            // Llamada al método para recorrer directorios y archivos
            System.out.println("Comprobando archivos en la carpeta: " + selectedFolder.getAbsolutePath());
            listFilesAndDirectories(selectedFolder);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna carpeta.");
        }
    }

    private void listFilesAndDirectories(File folder) {
        // Verifica si el archivo o directorio existe
        if (folder.exists()) {
            // Obtiene la lista de archivos y directorios dentro de la carpeta
            File[] files = folder.listFiles();
            if (files != null) {
                // Recorre cada archivo y directorio
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println("Directorio: " + file.getAbsolutePath());
                        // Llamada recursiva para recorrer subdirectorios
                        listFilesAndDirectories(file);
                    } else {
                        System.out.println("Archivo: " + file.getAbsolutePath());
                        // Aquí puedes añadir la lógica para procesar cada archivo
                    }
                }
            } else {
                System.out.println("No se pudo acceder a los archivos en el directorio.");
            }
        } else {
            System.out.println("El directorio no existe: " + folder.getAbsolutePath());
        }
    }

    private void saveLastPath(String path) {
        Properties properties = new Properties();
        properties.setProperty("lastPath", path);
        try {
            properties.store(Files.newOutputStream(Paths.get(CONFIG_FILE)), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String loadLastPath() {
        try {
            Path path = Paths.get(CONFIG_FILE);
            if (Files.exists(path)) {
                Properties props = new Properties();
                try (var in = Files.newInputStream(path)) {
                    props.load(in);
                    return props.getProperty("lastPath");
                }
            } else {
                // El archivo no existe, crea uno vacío o inicializa `selectedFolder` a `null`
                Files.createFile(path);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores, como mostrar un mensaje al usuario o establecer valores predeterminados
            return null;
        }
    }

    public void runClient() {
        if (selectedFolder != null) {
            File clientExecutable = new File(selectedFolder, "system/l2.exe");
            if (clientExecutable.exists()) {
                try {
                    // Usa ProcessBuilder para ejecutar el cliente
                    executeWithElevation(clientExecutable.getAbsolutePath());
                } catch (Exception ex) {
                    Logger.getLogger(UpdaterManager.class.getName()).log(Level.SEVERE, "Error al iniciar el cliente", ex);
                    JOptionPane.showMessageDialog(null, "No se pudo iniciar el cliente. Verifique la ruta del archivo.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo 'l2.exe' no se encuentra en la carpeta seleccionada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna carpeta.");
        }
    }

    private void executeWithElevation(String exePath) {
        // Obtiene la ruta al directorio donde se encuentra el archivo JAR
        String jarDir = new File(System.getProperty("user.dir")).getAbsolutePath();

        // Construye la ruta al archivo runElevated.bat en la carpeta dist
        String batFilePath = new File(jarDir, "dist/runElevated.bat").getAbsolutePath();

        try {
            // Llama al script para ejecutar el programa con privilegios elevados
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", batFilePath, exePath);
            processBuilder.redirectErrorStream(true); // Combina stdout y stderr
            Process process = processBuilder.start();
            // Espera a que el proceso termine
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
