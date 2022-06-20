package pkg2048;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
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

    /**
     * Creates new form Game
     */
    public Game() {
        initComponents();

        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setTitle("2048");
        try {
            map = new TileMap(720);
        } catch (Exception e) {

        }
        map.generateNewTile();
        panel = new DrawingPanel(map.getMapWidth(), map);
        
        gamePanel.setPreferredSize(new Dimension(720, 720));
        gamePanel.setLayout(new BorderLayout());

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && !isDisabled) {
                }
                if (e.getID() == KeyEvent.KEY_RELEASED && !isDisabled) {

                    int keyCode = e.getKeyCode();

                    if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D
                            || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_W:
                            case KeyEvent.VK_UP:
                                panel.processMovement(TileMap.Movement.UP);
                                break;
                            case KeyEvent.VK_S:
                            case KeyEvent.VK_DOWN:
                                panel.processMovement(TileMap.Movement.DOWN);
                                break;
                            case KeyEvent.VK_A:
                            case KeyEvent.VK_LEFT:
                                panel.processMovement(TileMap.Movement.LEFT);
                                break;
                            case KeyEvent.VK_D:
                            case KeyEvent.VK_RIGHT:
                                panel.processMovement(TileMap.Movement.RIGHT);
                                break;
                        }
                        score.setText(map.getScore()+"");
                        e.consume();
                        if (panel.isGameOver()) {
                            isDisabled = true;
                            if (JOptionPane.showConfirmDialog(null, "Game Over!", "Game Over", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                                isDisabled = false;
                            } else {
                                panel.Undo();
                                isDisabled = false;
                            }
                        }
                    }
                }
                return false;
            }
        });
        System.out.println(panel.getSize());
        gamePanel.add(panel, BorderLayout.CENTER);
        System.out.println(panel.getSize());
        System.out.println(gamePanel.getSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        Undo = new javax.swing.JButton();
        RESET = new javax.swing.JButton();
        UP = new javax.swing.JButton();
        RIGHT = new javax.swing.JButton();
        LEFT = new javax.swing.JButton();
        DOWN = new javax.swing.JButton();
        gamePanel = new javax.swing.JLayeredPane();
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        jLabel1 = new javax.swing.JLabel();
        score = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Undo.setBackground(new java.awt.Color(241, 105, 29));
        Undo.setFont(new java.awt.Font("sansserif", 1, 22)); // NOI18N
        Undo.setForeground(new java.awt.Color(255, 255, 255));
        Undo.setText("UNDO");
        Undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoActionPerformed(evt);
            }
        });

        RESET.setBackground(new java.awt.Color(241, 105, 29));
        RESET.setFont(new java.awt.Font("sansserif", 1, 22)); // NOI18N
        RESET.setForeground(new java.awt.Color(255, 255, 255));
        RESET.setText("RESET");
        RESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETActionPerformed(evt);
            }
        });

        UP.setText("UP");
        UP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPActionPerformed(evt);
            }
        });

        RIGHT.setText("RIGHT");
        RIGHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RIGHTActionPerformed(evt);
            }
        });

        LEFT.setText("LEFT");
        LEFT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LEFTActionPerformed(evt);
            }
        });

        DOWN.setText("DOWN");
        DOWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOWNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 753, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Score:");

        score.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        score.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gamePanel)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Undo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RESET)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DOWN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LEFT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RIGHT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UP)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addGap(452, 452, 452))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Undo)
                    .addComponent(RESET)
                    .addComponent(UP)
                    .addComponent(RIGHT)
                    .addComponent(LEFT)
                    .addComponent(DOWN)
                    .addComponent(jLabel1)
                    .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(gamePanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LEFTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LEFTActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.LEFT);
            score.setText(map.getScore()+"");
        }
    }//GEN-LAST:event_LEFTActionPerformed

    private void RIGHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RIGHTActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.RIGHT);
            score.setText(map.getScore()+"");
        }
    }//GEN-LAST:event_RIGHTActionPerformed

    private void UPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.UP);
            score.setText(map.getScore()+"");
        }
    }//GEN-LAST:event_UPActionPerformed

    private void UndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoActionPerformed
        this.panel.Undo();
    }//GEN-LAST:event_UndoActionPerformed

    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        this.panel.resetGame();
        score.setText(map.getScore()+"");
    }//GEN-LAST:event_RESETActionPerformed

    private void DOWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOWNActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.DOWN);
            score.setText(map.getScore()+"");
        }
    }//GEN-LAST:event_DOWNActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField score;
    // End of variables declaration//GEN-END:variables
}
