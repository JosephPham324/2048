package pkg2048;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Pham Nhat Quang
 */
public class Game extends javax.swing.JFrame {

    TileMap map;
    DrawingPanel panel;
    static boolean isDisabled = false;
    Color movementButton = Color.gray;
    Color moveMentButtonWhenUsed = Color.white;
    Color functionColor = Color.decode("#f1691d");
    Color functionColorWhenUsed = Color.decode("#ff321d");

    /**
     * Creates new form Game
     */
    public Game() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) screenSize.getHeight(), (int) screenSize.getHeight());
        this.setLocationRelativeTo(null);

        this.setResizable(false);
        this.setTitle("2048");

        try {
            map = new TileMap();
        } catch (Exception e) {

        }
        map.generateNewTile();
        panel = new DrawingPanel(map);

        gamePanel.setPreferredSize(new Dimension(720, 720));
        gamePanel.setLayout(new BorderLayout());

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {

                if (e.getID() == KeyEvent.KEY_RELEASED && !isDisabled) {

                    int keyCode = e.getKeyCode();

                    if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D
                            || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                        setFunctionButtonsColor('N');
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_W:
                            case KeyEvent.VK_UP:
                                panel.processMovement(TileMap.Movement.UP);
                                setMovementButtonsColor('u');

                                break;
                            case KeyEvent.VK_S:
                            case KeyEvent.VK_DOWN:
                                panel.processMovement(TileMap.Movement.DOWN);
                                setMovementButtonsColor('d');
                                break;
                            case KeyEvent.VK_A:
                            case KeyEvent.VK_LEFT:
                                panel.processMovement(TileMap.Movement.LEFT);
                                setMovementButtonsColor('l');
                                break;
                            case KeyEvent.VK_D:
                            case KeyEvent.VK_RIGHT:
                                panel.processMovement(TileMap.Movement.RIGHT);
                                setMovementButtonsColor('r');
                                break;
                        }
                        score.setText(map.getScore() + "");
                        e.consume();
                        gameOver();
                    } else {
                        setMovementButtonsColor('F');
                        if (e.isControlDown()) {
                            switch (keyCode) {
                                case KeyEvent.VK_Z:
                                    panel.Undo();
                                    setFunctionButtonsColor('U');
                                    break;
                                case KeyEvent.VK_R:
                                    panel.resetGame();
                                    setFunctionButtonsColor('R');
                                    break;
                            }
                        }
                        if (e.isAltDown()) {
                            if (keyCode == KeyEvent.VK_F4) {
                                System.exit(0);
                            }
                        }
                    }
                }
                return true;
            }
        });
        gamePanel.add(panel, BorderLayout.CENTER);
    }

    private void gameOver() throws HeadlessException {
        if (panel.isGameOver()) {
            isDisabled = true;
            if (JOptionPane.showConfirmDialog(null, "Game Over!\nOK to reset; Cancel to undo", "Game Over", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                isDisabled = false;
                panel.resetGame();
            } else {
                panel.Undo();
                isDisabled = false;
            }
        }
    }
    

    void setMovementButtonsColor(char button) {
        UP.setBackground(button == 'u' ? moveMentButtonWhenUsed : movementButton);
        DOWN.setBackground(button == 'd' ? moveMentButtonWhenUsed : movementButton);
        LEFT.setBackground(button == 'l' ? moveMentButtonWhenUsed : movementButton);
        RIGHT.setBackground(button == 'r' ? moveMentButtonWhenUsed : movementButton);
    }

    void setFunctionButtonsColor(char button) {
        Undo.setBackground(button == 'U' ? functionColorWhenUsed : functionColor);
        RESET.setBackground(button == 'R' ? functionColorWhenUsed : functionColor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gamePanel = new javax.swing.JLayeredPane();
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        jPanel1 = new javax.swing.JPanel();
        score = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Undo = new javax.swing.JButton();
        RESET = new javax.swing.JButton();
        UP = new javax.swing.JButton();
        RIGHT = new javax.swing.JButton();
        DOWN = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        LEFT = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        score1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        score.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        score.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        score.setText("0");
        score.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SCORE");

        Undo.setBackground(new java.awt.Color(241, 105, 29));
        Undo.setFont(new java.awt.Font("sansserif", 1, 22)); // NOI18N
        Undo.setForeground(new java.awt.Color(255, 255, 255));
        Undo.setText("UNDO");
        Undo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoActionPerformed(evt);
            }
        });

        RESET.setBackground(new java.awt.Color(241, 105, 29));
        RESET.setFont(new java.awt.Font("sansserif", 1, 22)); // NOI18N
        RESET.setForeground(new java.awt.Color(255, 255, 255));
        RESET.setText("RESET");
        RESET.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETActionPerformed(evt);
            }
        });

        UP.setBackground(new java.awt.Color(204, 204, 204));
        UP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/up.png"))); // NOI18N
        UP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPActionPerformed(evt);
            }
        });

        RIGHT.setBackground(new java.awt.Color(204, 204, 204));
        RIGHT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/right.png"))); // NOI18N
        RIGHT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RIGHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RIGHTActionPerformed(evt);
            }
        });

        DOWN.setBackground(new java.awt.Color(204, 204, 204));
        DOWN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/down.png"))); // NOI18N
        DOWN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DOWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOWNActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 206, 75));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("2048");
        jButton1.setToolTipText("");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        LEFT.setBackground(new java.awt.Color(204, 204, 204));
        LEFT.setForeground(new java.awt.Color(255, 255, 255));
        LEFT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/left.png"))); // NOI18N
        LEFT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LEFT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LEFTActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BEST");

        score1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        score1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        score1.setText("0");
        score1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Undo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(score)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(score1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(RESET))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LEFT, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(UP, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RIGHT, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DOWN, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(LEFT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(UP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RIGHT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(DOWN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(score1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Undo)
                            .addComponent(RESET))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gamePanel))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(gamePanel))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LEFTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LEFTActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.LEFT);
            score.setText(map.getScore() + "");
            setMovementButtonsColor('l');
            gameOver();
        }
    }//GEN-LAST:event_LEFTActionPerformed

    private void RIGHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RIGHTActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.RIGHT);
            score.setText(map.getScore() + "");
            setMovementButtonsColor('r');
            gameOver();
        }
    }//GEN-LAST:event_RIGHTActionPerformed

    private void UPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.UP);
            score.setText(map.getScore() + "");
            setMovementButtonsColor('u');
            gameOver();
        }
    }//GEN-LAST:event_UPActionPerformed

    private void UndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoActionPerformed
        this.panel.Undo();
        setFunctionButtonsColor('U');
        setMovementButtonsColor('U');
    }//GEN-LAST:event_UndoActionPerformed

    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        this.panel.resetGame();
        score.setText(map.getScore() + "");
        setFunctionButtonsColor('R');
        setMovementButtonsColor('R');
    }//GEN-LAST:event_RESETActionPerformed

    private void DOWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOWNActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.DOWN);
            score.setText(map.getScore() + "");
            setMovementButtonsColor('d');
            gameOver();
        }
    }//GEN-LAST:event_DOWNActionPerformed

    private void scoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scoreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void score1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score1ActionPerformed

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
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DOWN;
    private javax.swing.JButton LEFT;
    private javax.swing.JButton RESET;
    private javax.swing.JButton RIGHT;
    private javax.swing.JButton UP;
    private javax.swing.JButton Undo;
    private javax.swing.JLayeredPane gamePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField score;
    private javax.swing.JTextField score1;
    // End of variables declaration//GEN-END:variables
}
