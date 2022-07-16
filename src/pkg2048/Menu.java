/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2048;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author ASUS
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();

        this.setLocationRelativeTo(null);
        subMenu.setSize(995,730);
        

        this.setResizable(false);//Don't allow user to resize window
        HTP_MoTa.setVisible(false);
        Panel2048.setVisible(true);
        AU_Panel.setVisible(false);


        this.setTitle("2048");//Set title to 2048
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        MainMenu = new javax.swing.JPanel();
        PlayPanel = new javax.swing.JPanel();
        Play = new javax.swing.JLabel();
        HTP_Panel = new javax.swing.JPanel();
        HTP = new javax.swing.JLabel();
        AboutUs_Panel = new javax.swing.JPanel();
        AboutUs = new javax.swing.JLabel();
        QuitPanel = new javax.swing.JPanel();
        Quit = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        subMenu = new javax.swing.JPanel();
        AU_Panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Panel2048 = new javax.swing.JLabel();
        HTP_MoTa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 153));

        MainMenu.setBackground(new java.awt.Color(255, 153, 0));

        PlayPanel.setBackground(new java.awt.Color(0, 0, 0));
        PlayPanel.setBorder(new javax.swing.border.MatteBorder(null));
        PlayPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayPanelMouseClicked(evt);
            }
        });

        Play.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 48)); // NOI18N
        Play.setForeground(new java.awt.Color(255, 255, 255));
        Play.setText("Play");
        Play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PlayPanelLayout = new javax.swing.GroupLayout(PlayPanel);
        PlayPanel.setLayout(PlayPanelLayout);
        PlayPanelLayout.setHorizontalGroup(
            PlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayPanelLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(Play)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        PlayPanelLayout.setVerticalGroup(
            PlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Play, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        HTP_Panel.setBackground(new java.awt.Color(0, 0, 0));
        HTP_Panel.setBorder(new javax.swing.border.MatteBorder(null));
        HTP_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HTP_PanelMouseClicked(evt);
            }
        });

        HTP.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 48)); // NOI18N
        HTP.setForeground(new java.awt.Color(255, 255, 255));
        HTP.setText("How to play?");
        HTP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HTPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout HTP_PanelLayout = new javax.swing.GroupLayout(HTP_Panel);
        HTP_Panel.setLayout(HTP_PanelLayout);
        HTP_PanelLayout.setHorizontalGroup(
            HTP_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HTP_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HTP)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        HTP_PanelLayout.setVerticalGroup(
            HTP_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HTP_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HTP)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AboutUs_Panel.setBackground(new java.awt.Color(0, 0, 0));
        AboutUs_Panel.setBorder(new javax.swing.border.MatteBorder(null));
        AboutUs_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AboutUs_PanelMouseClicked(evt);
            }
        });

        AboutUs.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 48)); // NOI18N
        AboutUs.setForeground(new java.awt.Color(255, 255, 255));
        AboutUs.setText("About us");
        AboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AboutUsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout AboutUs_PanelLayout = new javax.swing.GroupLayout(AboutUs_Panel);
        AboutUs_Panel.setLayout(AboutUs_PanelLayout);
        AboutUs_PanelLayout.setHorizontalGroup(
            AboutUs_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutUs_PanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(AboutUs)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        AboutUs_PanelLayout.setVerticalGroup(
            AboutUs_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutUs_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AboutUs)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        QuitPanel.setBackground(new java.awt.Color(0, 0, 0));
        QuitPanel.setBorder(new javax.swing.border.MatteBorder(null));
        QuitPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuitPanelMouseClicked(evt);
            }
        });

        Quit.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 48)); // NOI18N
        Quit.setForeground(new java.awt.Color(255, 255, 255));
        Quit.setText("Quit");

        javax.swing.GroupLayout QuitPanelLayout = new javax.swing.GroupLayout(QuitPanel);
        QuitPanel.setLayout(QuitPanelLayout);
        QuitPanelLayout.setHorizontalGroup(
            QuitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuitPanelLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(Quit)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        QuitPanelLayout.setVerticalGroup(
            QuitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuitPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Quit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Home");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout MainMenuLayout = new javax.swing.GroupLayout(MainMenu);
        MainMenu.setLayout(MainMenuLayout);
        MainMenuLayout.setHorizontalGroup(
            MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuLayout.createSequentialGroup()
                .addGroup(MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainMenuLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(PlayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(QuitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(HTP_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AboutUs_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(MainMenuLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel9)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        MainMenuLayout.setVerticalGroup(
            MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139)
                .addComponent(PlayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(HTP_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(AboutUs_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(QuitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subMenu.setBackground(new java.awt.Color(0, 153, 153));
        subMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subMenuMouseClicked(evt);
            }
        });
        subMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AU_Panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel4.setText("-FPT University Can Tho - SE1605 - SU22 - CSD201 ");

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel5.setText("-Game made by : Group 3");

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel13.setText("-Mentor: Vo Hong Khanh");

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel11.setText("+CE170036 Pham Nhat Quang: algorithm design");

        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel10.setText("+CE161130 Nguyen Le Quang Thinh: interface design");

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel12.setText("+CE161130 Nguyen The Lu: tester ");

        jLabel17.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel17.setText("Members of group 3:");

        javax.swing.GroupLayout AU_PanelLayout = new javax.swing.GroupLayout(AU_Panel);
        AU_Panel.setLayout(AU_PanelLayout);
        AU_PanelLayout.setHorizontalGroup(
            AU_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AU_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AU_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13)
                    .addComponent(jLabel5))
                .addGroup(AU_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AU_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(AU_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addContainerGap())
                    .addGroup(AU_PanelLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel17)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        AU_PanelLayout.setVerticalGroup(
            AU_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AU_PanelLayout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap())
            .addGroup(AU_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subMenu.add(AU_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 970, 130));

        Panel2048.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2048_Icon.png"))); // NOI18N
        subMenu.add(Panel2048, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, 234));

        HTP_MoTa.setBackground(new java.awt.Color(255, 255, 255));
        HTP_MoTa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel1.setText("The rules of how to play 2048 are simple. You have four ways to move the tiles: left, right, up, and down.  ");
        HTP_MoTa.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        HTP_MoTa.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 107, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel8.setText("A low number such as a 2 or 4 will ");
        HTP_MoTa.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 298, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel3.setText("You combine numbers like 2 and 2 to make a 4 tile, 4 and 4 to make an 8 tile, and so on and so forth. ");
        HTP_MoTa.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 46, -1, 21));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel6.setText("and so on and so forth.");
        HTP_MoTa.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 259, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel7.setText("A low number such as a 2 or 4 will appear in a random spot with every move you make.");
        HTP_MoTa.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 74, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel14.setText("make a 4 tile, 4 and 4 to make an 8 tile ");
        HTP_MoTa.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 226, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel15.setText("move you make. ");
        HTP_MoTa.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 364, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel16.setText("appear in a random spot with every ");
        HTP_MoTa.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 331, -1, -1));

        subMenu.add(HTP_MoTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 970, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(subMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HTPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HTPMouseClicked
  //nothing here
   // TODO add your handling code here:
        HTP_Panel.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        HTP_MoTa.setVisible(true);
        Panel2048.setVisible(false);
        AU_Panel.setVisible(false);

        
        HTP.setForeground(Color.black);
        HTP_Panel.setBackground(Color.white);
        
        AboutUs.setForeground(Color.white);
        AboutUs_Panel.setBackground(Color.black);
    }//GEN-LAST:event_HTPMouseClicked

    private void PlayPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayPanelMouseClicked
        
        /* Create and display the form */
        PlayPanel.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        Play.setForeground(Color.black);
        PlayPanel.setBackground(Color.white);

        HTP.setForeground(Color.white);
        HTP_Panel.setBackground(Color.black);
        
        AboutUs.setForeground(Color.white);
        AboutUs_Panel.setBackground(Color.black);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }//GEN-LAST:event_PlayPanelMouseClicked

    private void HTP_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HTP_PanelMouseClicked
        // TODO add your handling code here:
        HTP_Panel.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        
        HTP_MoTa.setVisible(true);
        Panel2048.setVisible(false);
        AU_Panel.setVisible(false);
        
        HTP.setForeground(Color.black);
        HTP_Panel.setBackground(Color.white);
        
        AboutUs.setForeground(Color.white);
        AboutUs_Panel.setBackground(Color.black);
    }//GEN-LAST:event_HTP_PanelMouseClicked

    private void AboutUs_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AboutUs_PanelMouseClicked
        // TODO add your handling code here:
        AboutUs_Panel.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        HTP_MoTa.setVisible(false);
        AU_Panel.setVisible(true);
        Panel2048.setVisible(false);
        
        HTP.setForeground(Color.white);
        HTP_Panel.setBackground(Color.black);
        
        AboutUs.setForeground(Color.black);
        AboutUs_Panel.setBackground(Color.white);
    }//GEN-LAST:event_AboutUs_PanelMouseClicked

    private void QuitPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuitPanelMouseClicked
        // TODO add your handling code here:
        Quit.setForeground(Color.white);
        QuitPanel.setBackground(Color.black);
        System.exit(0);
    }//GEN-LAST:event_QuitPanelMouseClicked

    private void subMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subMenuMouseClicked
        // TODO add your handling code here:      
        subMenu.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        
        Panel2048.setVisible(true);
        HTP_MoTa.setVisible(false);
        AU_Panel.setVisible(false);
        
        HTP.setForeground(Color.white);
        HTP_Panel.setBackground(Color.black);
        
        AboutUs.setForeground(Color.white);
        AboutUs_Panel.setBackground(Color.black);
    }//GEN-LAST:event_subMenuMouseClicked

    private void PlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayMouseClicked
          /* Create and display the form */
        Play.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        Play.setForeground(Color.black);
        PlayPanel.setBackground(Color.white);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });

    }//GEN-LAST:event_PlayMouseClicked

    private void AboutUsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AboutUsMouseClicked
        // TODO add your handling code here:
         // TODO add your handling code here:
        AboutUs.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        HTP_MoTa.setVisible(false);
        AU_Panel.setVisible(true);
        Panel2048.setVisible(false);
        
        HTP.setForeground(Color.white);
        HTP_Panel.setBackground(Color.black);
        
        AboutUs.setForeground(Color.black);
        AboutUs_Panel.setBackground(Color.white);
    }//GEN-LAST:event_AboutUsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AU_Panel;
    private javax.swing.JLabel AboutUs;
    private javax.swing.JPanel AboutUs_Panel;
    private javax.swing.JLabel HTP;
    private javax.swing.JPanel HTP_MoTa;
    private javax.swing.JPanel HTP_Panel;
    private javax.swing.JPanel MainMenu;
    private javax.swing.JLabel Panel2048;
    private javax.swing.JLabel Play;
    private javax.swing.JPanel PlayPanel;
    private javax.swing.JLabel Quit;
    private javax.swing.JPanel QuitPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel subMenu;
    // End of variables declaration//GEN-END:variables
}
