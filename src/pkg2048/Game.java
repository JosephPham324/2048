package pkg2048;

import java.awt.BorderLayout;
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
        this.controlButtons = new JPanel();
        this.controlButtons.setSize(200, 200);
        map.generateNewTile();
        panel = new DrawingPanel(map.getMapWidth(), map);
        jPanel1.setSize(panel.getWidth(), panel.getWidth());
        jPanel1.setLayout(new BorderLayout());
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
                        e.consume();
                        if (panel.gameOver) {
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
        
        jPanel1.add(controlButtons);
        jPanel1.add(panel, BorderLayout.CENTER);
//        jPanel1.add(jLayeredPane1);
//        this.controlButtons.add(UP,BorderLayout.NORTH);
//        this.controlButtons.add(LEFT,BorderLayout.WEST);
//        this.controlButtons.add(RIGHT,BorderLayout.EAST);
//        this.controlButtons.add(DOWN,BorderLayout.SOUTH);
//        this.controlButtons.setSize(200,200);
//        controlButtons.setVisible(true);
//        JPanel container = new JPanel();
//        container.add(controlButtons,BorderLayout.CENTER);
//        container.setVisible(true);
//        this.jPanel1.add(container,BorderLayout.WEST);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        controlButtons = new javax.swing.JPanel();
        LEFT = new javax.swing.JButton();
        UP = new javax.swing.JButton();
        RIGHT = new javax.swing.JButton();
        DOWN = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        Undo = new javax.swing.JButton();
        RESET = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 0, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LEFT.setText("LEFT");
        LEFT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LEFTActionPerformed(evt);
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

        DOWN.setText("DOWN");
        DOWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOWNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlButtonsLayout = new javax.swing.GroupLayout(controlButtons);
        controlButtons.setLayout(controlButtonsLayout);
        controlButtonsLayout.setHorizontalGroup(
            controlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlButtonsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(LEFT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RIGHT))
            .addGroup(controlButtonsLayout.createSequentialGroup()
                .addGroup(controlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlButtonsLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(UP))
                    .addGroup(controlButtonsLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(DOWN)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlButtonsLayout.setVerticalGroup(
            controlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LEFT)
                    .addComponent(RIGHT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DOWN)
                .addContainerGap())
        );

        jLayeredPane1.setLayer(controlButtons, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(controlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(controlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 443, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(Undo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RESET)
                .addGap(292, 569, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addGap(452, 452, 452))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Undo)
                    .addComponent(RESET))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LEFTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LEFTActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.LEFT);
        }
    }//GEN-LAST:event_LEFTActionPerformed

    private void RIGHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RIGHTActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.RIGHT);
        }
    }//GEN-LAST:event_RIGHTActionPerformed

    private void UPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.UP);
        }
    }//GEN-LAST:event_UPActionPerformed

    private void UndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoActionPerformed
        this.panel.Undo();
    }//GEN-LAST:event_UndoActionPerformed

    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        this.panel.resetGame();
    }//GEN-LAST:event_RESETActionPerformed

    private void DOWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOWNActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.DOWN);
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
    private javax.swing.JPanel controlButtons;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
