package pkg2048;

import DataSaving.Information;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;

/**
 *
 * @author Pham Nhat Quang
 */
public class StatDisplay extends javax.swing.JFrame {

    DataSaving.Information info;

    /**
     * Creates new form Information
     *
     * @param info
     */
    public StatDisplay(DataSaving.Information info) {
        initComponents();

        this.info = info;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//Get size of screen

        //Set size of JFRAME as a square proportional to screen height
        this.setSize((int) screenSize.getHeight(), (int) screenSize.getHeight());
        this.setLocationRelativeTo(null);

        this.setResizable(false);//Don't allow user to resize window

        this.setTitle("2048");//Set title to 2048

        this.bestScore.setText(this.info.getBestScore() + "");
        this.totalScore.setText(this.info.getTotalScore() + "");
        this.topTile.setText(this.info.getTopTile() + "");
        //512
//        this.gamesReached512.setText(this.info.getGameReached512() + "");
//        this.shortestTime512.setText(this.info.getShortestTime512Display());
//        this.FewestMoves512.setText(this.info.getFewestMoves512() + "");
        setLabel(_512reached, gamesReached512, this.info.getGameReached512() + "");
        setLabel(_512shortestTime, shortestTime512, this.info.getShortestTime512Display());
        setLabel(_512fewestMoves, FewestMoves512, this.info.getFewestMoves512() + "");
        //1024
//        this.gamesReached1024.setText(this.info.getGameReached1024() + "");
//        this.shortestTime1024.setText(this.info.getShortestTime1024Display()+ "");
//        this.fewestMoves1024.setText(this.info.getFewestMoves1024() + "");
        setLabel(_1024reached, gamesReached1024, this.info.getGameReached1024() + "");
        setLabel(_1024shortestTime, shortestTime1024, this.info.getShortestTime1024Display());
        setLabel(_1024fewestMoves, fewestMoves1024, this.info.getFewestMoves1024() + "");
        System.out.println(this.info.getFewestMoves1024());
        if (this.info.getGameReached1024() == 0) {
            this._1024.setText("");
        }
        if (this.info.getGameReached512() == 0) {
            this._512.setText("");
        }
    }

    private void setLabel(JLabel title, JLabel value, String valueString) {
        if (valueString.equals("0")
                || valueString.equals(Integer.MAX_VALUE + "")
                || valueString.equals("596523:35791394:07")) {
            title.setText("");
            value.setText("");
        } else {
            value.setText(valueString);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        _512fewestMoves = new javax.swing.JLabel();
        _512shortestTime = new javax.swing.JLabel();
        _512 = new javax.swing.JLabel();
        _512reached = new javax.swing.JLabel();
        _1024fewestMoves = new javax.swing.JLabel();
        _1024shortestTime = new javax.swing.JLabel();
        _1024 = new javax.swing.JLabel();
        _1024reached = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bestScore = new javax.swing.JLabel();
        totalScore = new javax.swing.JLabel();
        topTile = new javax.swing.JLabel();
        gamesReached512 = new javax.swing.JLabel();
        shortestTime512 = new javax.swing.JLabel();
        FewestMoves512 = new javax.swing.JLabel();
        gamesReached1024 = new javax.swing.JLabel();
        shortestTime1024 = new javax.swing.JLabel();
        fewestMoves1024 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(720, 720));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setText("All play");

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel2.setText("Best Score");

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel3.setText("Total Score");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel4.setText("Top Tile");

        _512fewestMoves.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        _512fewestMoves.setText("Fewest Moves");

        _512shortestTime.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        _512shortestTime.setText("Shortest Time");

        _512.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        _512.setText("512");

        _512reached.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        _512reached.setText("Games reached");

        _1024fewestMoves.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        _1024fewestMoves.setText("Fewest Moves");

        _1024shortestTime.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        _1024shortestTime.setText("Shortest Time");

        _1024.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        _1024.setText("1024");

        _1024reached.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        _1024reached.setText("Games Reached");

        jLabel13.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Statistics");

        bestScore.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        bestScore.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        bestScore.setText("0");

        totalScore.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        totalScore.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalScore.setText("0");

        topTile.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        topTile.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        topTile.setText("0");

        gamesReached512.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        gamesReached512.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gamesReached512.setText("0");

        shortestTime512.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        shortestTime512.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        shortestTime512.setText("0");

        FewestMoves512.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        FewestMoves512.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        FewestMoves512.setText("0");

        gamesReached1024.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        gamesReached1024.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gamesReached1024.setText("0");

        shortestTime1024.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        shortestTime1024.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        shortestTime1024.setText("0");

        fewestMoves1024.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        fewestMoves1024.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fewestMoves1024.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(_512)
                            .addComponent(_512reached)
                            .addComponent(_512shortestTime)
                            .addComponent(_512fewestMoves)
                            .addComponent(_1024)
                            .addComponent(_1024reached)
                            .addComponent(_1024shortestTime)
                            .addComponent(_1024fewestMoves))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(FewestMoves512, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(shortestTime512, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bestScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(topTile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(gamesReached512, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(gamesReached1024, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(shortestTime1024, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fewestMoves1024, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(_512)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_512reached)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_512shortestTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_512fewestMoves)
                        .addGap(18, 18, 18)
                        .addComponent(_1024)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_1024reached)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_1024shortestTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_1024fewestMoves))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bestScore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalScore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(topTile)
                        .addGap(56, 56, 56)
                        .addComponent(gamesReached512)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shortestTime512)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FewestMoves512)
                        .addGap(56, 56, 56)
                        .addComponent(gamesReached1024)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shortestTime1024)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fewestMoves1024)))
                .addContainerGap(178, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(StatDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StatDisplay(new Information()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FewestMoves512;
    private javax.swing.JLabel _1024;
    private javax.swing.JLabel _1024fewestMoves;
    private javax.swing.JLabel _1024reached;
    private javax.swing.JLabel _1024shortestTime;
    private javax.swing.JLabel _512;
    private javax.swing.JLabel _512fewestMoves;
    private javax.swing.JLabel _512reached;
    private javax.swing.JLabel _512shortestTime;
    private javax.swing.JLabel bestScore;
    private javax.swing.JLabel fewestMoves1024;
    private javax.swing.JLabel gamesReached1024;
    private javax.swing.JLabel gamesReached512;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel shortestTime1024;
    private javax.swing.JLabel shortestTime512;
    private javax.swing.JLabel topTile;
    private javax.swing.JLabel totalScore;
    // End of variables declaration//GEN-END:variables
}
