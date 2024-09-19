/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package updater.igu;

import updater.manager.UpdaterManager;
import updater.backgrounds.BackgroundPanel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import updater.fonts.Fuentes;
import java.awt.Font;
import updater.manager.UpdaterManager;
/**
 *
 * @author Administrador
 */
public class Updater extends javax.swing.JFrame {

    int yMouse, xMouse;
    private UpdaterManager updaterManager;
    private Fuentes fuenteL2;

    public Updater() {
        initComponents();
        setupContentPanel();

        fuenteL2 = new Fuentes();
        Font fuentePersonalizada = fuenteL2.fuente(fuenteL2.RIO, Font.PLAIN, 40);
        this.logo.setFont(fuentePersonalizada);
        this.logo.setForeground(Color.WHITE); // 

    }

    private void setupContentPanel() {
        BackgroundPanel backgroundPanel = new BackgroundPanel("/updater/imgupdater/bgupdater.jpg");
        content.setLayout(new BorderLayout());
        content.add(backgroundPanel, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();

        Color transparentBlack = new Color(0, 0, 0, 200);
        Color transparent = new Color(0, 0, 0, 0);

        wrapper_news.setBackground(transparentBlack);
        this.wrapper_loading.setBackground(transparentBlack);
        wrapper_tops.setBackground(transparentBlack);
        this.panelBtns.setBackground(transparent);

        btnCheckFiles.setVisible(false);

        showPath.setEditable(false);
        showPath.setColumns(30);
        showPath.setHorizontalAlignment(JTextField.LEFT);
        showPath.setPreferredSize(new Dimension(content.getWidth() - jLabel3.getPreferredSize().width - 20, showPath.getPreferredSize().height));
        showPath.setToolTipText("Ruta de la carpeta seleccionada");

        // Configura las barras de progreso
        ProgresBarCheckFiles.setMinimum(0);
        ProgresBarCheckFiles.setMaximum(100);
        ProgresBarCheckFiles.setStringPainted(true);
        ProgresBarCheckFiles.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ProgresBarCheckFiles.setForeground(Color.BLUE);

     

        updaterManager = new UpdaterManager(btnSelectFolder, btnCheckFiles, showPath, ProgresBarCheckFiles);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUpConfig = new javax.swing.JPopupMenu();
        container = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        btnExit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        btnSingUp = new javax.swing.JButton();
        wrapper_news = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        wrapper_tops = new javax.swing.JPanel();
        wrapper_loading = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        showPath = new javax.swing.JTextField();
        ProgresBarCheckFiles = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        panelBtns = new javax.swing.JPanel();
        btnSelectFolder = new javax.swing.JButton();
        btnCheckFiles = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnMenu = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        container.setBackground(new java.awt.Color(0, 255, 51));
        container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 0, 0));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(153, 204, 0));
        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnExit.setText("X");
        btnExit.setAlignmentY(2.0F);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel1.setText("Launcher L2Terra ");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 835, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1))
        );

        container.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 30));

        content.setBackground(new java.awt.Color(255, 51, 51));

        btnLogin.setBackground(new java.awt.Color(0, 0, 0));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("INGRESAR");
        btnLogin.setBorder(null);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnSingUp.setBackground(new java.awt.Color(0, 0, 0));
        btnSingUp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSingUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSingUp.setText("REGISTRARSE");
        btnSingUp.setBorder(null);
        btnSingUp.setBorderPainted(false);
        btnSingUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSingUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSingUpActionPerformed(evt);
            }
        });

        wrapper_news.setBackground(new java.awt.Color(6, 0, 11));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ULTIMAS NOTICIAS");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper_newsLayout = new javax.swing.GroupLayout(wrapper_news);
        wrapper_news.setLayout(wrapper_newsLayout);
        wrapper_newsLayout.setHorizontalGroup(
            wrapper_newsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );
        wrapper_newsLayout.setVerticalGroup(
            wrapper_newsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper_newsLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 321, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout wrapper_topsLayout = new javax.swing.GroupLayout(wrapper_tops);
        wrapper_tops.setLayout(wrapper_topsLayout);
        wrapper_topsLayout.setHorizontalGroup(
            wrapper_topsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );
        wrapper_topsLayout.setVerticalGroup(
            wrapper_topsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

        jLabel3.setText("Carpeta seleccionada:");

        showPath.setBackground(new java.awt.Color(0, 0, 0));
        showPath.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        showPath.setForeground(new java.awt.Color(255, 255, 255));
        showPath.setText(" ");
        showPath.setBorder(null);

        jLabel5.setText("Estado");

        javax.swing.GroupLayout wrapper_loadingLayout = new javax.swing.GroupLayout(wrapper_loading);
        wrapper_loading.setLayout(wrapper_loadingLayout);
        wrapper_loadingLayout.setHorizontalGroup(
            wrapper_loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper_loadingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wrapper_loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, wrapper_loadingLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPath, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ProgresBarCheckFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        wrapper_loadingLayout.setVerticalGroup(
            wrapper_loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper_loadingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wrapper_loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(showPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProgresBarCheckFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtns.setBackground(new java.awt.Color(51, 51, 51));
        panelBtns.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSelectFolder.setBackground(new java.awt.Color(0, 0, 0));
        btnSelectFolder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSelectFolder.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectFolder.setText("SELECCIONAR CARPETA");
        btnSelectFolder.setBorder(null);
        btnSelectFolder.setBorderPainted(false);
        btnSelectFolder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelBtns.add(btnSelectFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 50));

        btnCheckFiles.setBackground(new java.awt.Color(0, 0, 0));
        btnCheckFiles.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCheckFiles.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckFiles.setText("COMPROBAR ARCHIVOS");
        btnCheckFiles.setBorder(null);
        btnCheckFiles.setBorderPainted(false);
        btnCheckFiles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCheckFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCheckFilesMouseClicked(evt);
            }
        });
        btnCheckFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckFilesActionPerformed(evt);
            }
        });
        panelBtns.add(btnCheckFiles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 50));

        btnPlay.setBackground(new java.awt.Color(0, 0, 0));
        btnPlay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPlay.setForeground(new java.awt.Color(255, 255, 255));
        btnPlay.setText("JUGAR");
        btnPlay.setBorder(null);
        btnPlay.setBorderPainted(false);
        btnPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayMouseClicked(evt);
            }
        });
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        panelBtns.add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 250, 50));

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/updater/imgupdater/iconpng.png"))); // NOI18N
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMenuMouseClicked(evt);
            }
        });

        logo.setText("Lineage II Terra");

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(contentLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(wrapper_tops, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSingUp, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMenu)))
                        .addGap(26, 26, 26)
                        .addComponent(wrapper_news, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(wrapper_loading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBtns, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(137, 137, 137))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMenu)
                            .addGroup(contentLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSingUp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wrapper_tops, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(wrapper_news, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(wrapper_loading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBtns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        container.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1010, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setOpaque(true); // Asegura que el fondo sea visible
        btnExit.setBackground(Color.red); // Cambia el fondo a rojo
        btnExit.repaint(); // Fuerza la actualización visual
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(Color.black); // Vuelve al color original
        btnExit.repaint(); // Fuerza la 
    }//GEN-LAST:event_btnExitMouseExited

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnCheckFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckFilesActionPerformed
    }//GEN-LAST:event_btnCheckFilesActionPerformed

    private void btnMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuMouseClicked
        btnCheckFiles.setVisible(true);

        // Crear el JPopupMenu
        JPopupMenu popUpConfig = new JPopupMenu();

        // Configurar el color de fondo del popup, eliminar bordes y añadir padding
        popUpConfig.setBackground(Color.BLACK);
        popUpConfig.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Padding: top, left, bottom, right

        // Crear los elementos del menú
        JMenuItem item1 = new JMenuItem("CAMBIAR CARPETA");
        JMenuItem item2 = new JMenuItem("CAMBIAR CONTRASEÑA");

        // Personalizar apariencia de los JMenuItem
        Font itemFont = new Font("Arial", Font.PLAIN, 14);  // Fuente personalizada para los elementos del menú
        Color itemBackground = Color.BLACK;
        Color itemForeground = Color.WHITE;

        // Configurar los JMenuItem
        item1.setBackground(itemBackground);
        item1.setForeground(itemForeground);
        item1.setFont(itemFont);
        item1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));  // Padding: top, left, bottom, right
        item1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cursor de mano

        item2.setBackground(itemBackground);
        item2.setForeground(itemForeground);
        item2.setFont(itemFont);
        item2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));  // Padding: top, left, bottom, right
        item2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cursor de mano

        // Crear el UpdaterManager
        UpdaterManager updaterManager = new UpdaterManager(btnSelectFolder, btnCheckFiles, showPath, ProgresBarCheckFiles);

        // Añadir ActionListener al JMenuItem
        item1.addActionListener(e -> updaterManager.selectFolder());
        item2.addActionListener(e -> {
            // Acción para cambiar la contraseña
            // Implementar la lógica para cambiar la contraseña aquí
        });

        // Añadir los elementos al popup
        popUpConfig.add(item1);
        popUpConfig.add(item2);

        // Mostrar el JPopupMenu en la ubicación del clic del botón
        popUpConfig.show(btnMenu, evt.getX(), evt.getY());
    }//GEN-LAST:event_btnMenuMouseClicked

    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseClicked
        this.updaterManager.runClient();
    }//GEN-LAST:event_btnPlayMouseClicked
    private void openWebpage(String url) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(url));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            // Manejo de errores, como mostrar un mensaje al usuario
            JOptionPane.showMessageDialog(null, "No se pudo abrir la página web.");
        }
    }
    private void btnSingUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSingUpActionPerformed
        String url = "https://l2genesis.online/app/register";
        openWebpage(url);
    }//GEN-LAST:event_btnSingUpActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String url = "https://l2genesis.online/app/login";
        openWebpage(url);
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnCheckFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCheckFilesMouseClicked
    }//GEN-LAST:event_btnCheckFilesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar ProgresBarCheckFiles;
    private javax.swing.JButton btnCheckFiles;
    private javax.swing.JLabel btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel btnMenu;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnSelectFolder;
    private javax.swing.JButton btnSingUp;
    private javax.swing.JPanel container;
    private javax.swing.JPanel content;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel panelBtns;
    private javax.swing.JPopupMenu popUpConfig;
    private javax.swing.JTextField showPath;
    private javax.swing.JPanel wrapper_loading;
    private javax.swing.JPanel wrapper_news;
    private javax.swing.JPanel wrapper_tops;
    // End of variables declaration//GEN-END:variables
}
