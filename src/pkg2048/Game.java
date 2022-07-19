package pkg2048;

import DataSaving.Information;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 * Panel to display game and for user to play game
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
    Clock clock;
    StatDisplay infoDisplay;
    pkg2048.Menu parent;

    /**
     * Creates new form Game
     *
     * @param parent parent of this form
     */
    public Game(pkg2048.Menu parent) {
        this.parent = parent;
        parent.setVisible(false);
        initComponents();
        isDisabled = false;
        this.requestFocus();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();        //Get size of screen
        //Set size of JFRAME as a square proportional to screen height
        this.setSize((int) screenSize.getHeight(), (int) screenSize.getHeight());
        this.setLocationRelativeTo(null);

        this.setResizable(false);//Don't allow user to resize window

        this.setTitle("2048");//Set title to 2048
        map = new TileMap(); //Create new TileMap

        panel = new DrawingPanel(map);//Create new DrawingPanel (to draw tiles when playing)
//        System.out.println(panel.getInformation().getInfo().getTimeDisplay());

        this.Time.setText(panel.getInformation().getInfo().getTimeDisplay());//Get time to display
        this.clock = new Clock(this.Time);//Create new clock to display time
        this.clock.resume();//Resume timer got from saved information

        gamePanel.setLayout(new BorderLayout());//Make game panel visible

        //FOR GAMEPLAY CONTROL
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_RELEASED && !isDisabled) {

                    int keyCode = e.getKeyCode();

                    if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S
                            || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D
                            || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN
                            || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {//Movement buttons
                        setFunctionButtonsColor('N');
                        switch (keyCode) {
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
                        saveTime();//Save time when perform movement
                        setScore();//Set score after moving
                        e.consume();
                        try {
                            gameOver();
                        } catch (HeadlessException he) {
                            System.err.println(he);
                        }
                    } else {//Function buttons
                        setMovementButtonsColor('F');
                        if (e.isControlDown()) {//Ctrl +
                            switch (keyCode) {
                                case KeyEvent.VK_Z://Z: Undo
                                    panel.Undo();
                                    setScore();
                                    setFunctionButtonsColor('U');
                                    break;
                                case KeyEvent.VK_R://R: Reset
                                    panel.resetGame();
                                    clock.reset();
                                    setScore();
                                    setFunctionButtonsColor('R');
                                    break;
                            }
                        }
                        if (e.isAltDown()) {//Alt + 
                            if (keyCode == KeyEvent.VK_F4) {//F4: Close
                                askClose();
                            }
                        }
                    }
                }
                return true;
            }
        });

        this.addWindowListener(new java.awt.event.WindowAdapter() {//Window listener for closing operation
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                askClose();//Ask if user really wants to close before closing
            }
        });

        this.addWindowFocusListener(new java.awt.event.WindowAdapter() {//Window focus listener to pause and resume game based on focus

            //To check window gained focus
            public void windowGainedFocus(WindowEvent e) {//Pause game when not focused
                resumeGame();
            }

            //To check window lost focus
            public void windowLostFocus(WindowEvent e) {//Resume game when focused

                pauseGame();
            }
        });
        gamePanel.add(panel, BorderLayout.CENTER);//Add to game panel
        setScore();//Set current game score on display
        this.score1.setText(panel.getInformation().getInfo().getBestScore() + "");//Set best score on display
    }

    /**
     * Ask the user if they want to close the game
     */
    public void askClose() {
        pauseGame();//Pause game before asking
        if (JOptionPane.showConfirmDialog(null,
                "Are you sure you want to close this window?", "Close Window?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {//If user wants to close window
            //Get information to save:
            panel.getInformation().getInfo().setGameState(panel.getGameState());//Game state
            panel.getInformation().getInfo().setScore(map.getScore());//Current score
            saveTime();//Time
            panel.getInformation().saveInfo();//Save information into file

            KeyboardFocusManager.setCurrentKeyboardFocusManager(null);//Delete current KeyboardFocusManager
            parent.setVisible(true);//Set parent visible
            this.dispose();
        } else {//If user does not want to close
            resumeGame();//Resume game
        }
    }

    /**
     * pause the game and stop the clock
     */
    public void pauseGame() {
        isDisabled = true;
        clock.pause();
    }

    /**
     * pause the game and the clock
     */
    public void resumeGame() {
        isDisabled = false;
        clock.resume();
    }

    /**
     * Show game over notification
     *
     * @throws HeadlessException Thrown when code that is dependent on a
     * keyboard, display, or mouse is called in an environment that does not
     * support a keyboard, display, or mouse.
     */
    private void gameOver() throws HeadlessException {
        if (panel.isGameOver()) {
            pauseGame();
            if (JOptionPane.showConfirmDialog(this, "Game Over!\nOK to reset; Cancel to undo", "Game Over", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                this.requestFocus();
                isDisabled = false;
                panel.resetGame();
                setScore();
                clock.start();
            } else {
                this.requestFocus();
                panel.Undo();
                resumeGame();
            }
        }
    }

    /**
     * Set score for display
     */
    void setScore() {
        score.setText(map.getScore() + "");
        if (map.getScore() > panel.getInformation().getInfo().getBestScore()) {
            score1.setText(map.getScore() + "");
            panel.getInformation().getInfo().setBestScore(map.getScore());
        }
    }

    /**
     * Save time
     */
    void saveTime() {
        panel.getInformation().getInfo().setTime(Information.convertTime(Time.getText()));
    }

    /**
     * Set color for movement button
     *
     * @param button (pressed by user)
     */
    void setMovementButtonsColor(char button) {
        UP.setBackground(button == 'u' ? moveMentButtonWhenUsed : movementButton);
        DOWN.setBackground(button == 'd' ? moveMentButtonWhenUsed : movementButton);
        LEFT.setBackground(button == 'l' ? moveMentButtonWhenUsed : movementButton);
        RIGHT.setBackground(button == 'r' ? moveMentButtonWhenUsed : movementButton);
    }

    /**
     * Set color for function button
     *
     * @param button
     */
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

        jLabel3 = new javax.swing.JLabel();
        gamePanel = new javax.swing.JLayeredPane();
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        jPanel1 = new javax.swing.JPanel();
        UP = new javax.swing.JButton();
        RIGHT = new javax.swing.JButton();
        DOWN = new javax.swing.JButton();
        info = new javax.swing.JButton();
        LEFT = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        score1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        score = new javax.swing.JTextField();
        RESET = new javax.swing.JButton();
        Undo = new javax.swing.JButton();
        clockPanel = new javax.swing.JPanel();
        Time = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

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

        info.setBackground(new java.awt.Color(255, 206, 75));
        info.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36)); // NOI18N
        info.setForeground(new java.awt.Color(255, 255, 255));
        info.setText("2048");
        info.setToolTipText("");
        info.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoActionPerformed(evt);
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

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SCORE");

        score.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        score.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        score.setText("0");
        score.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(score)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(score1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(RESET)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Undo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(score1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RESET)
                    .addComponent(Undo))
                .addGap(21, 21, 21))
        );

        clockPanel.setBackground(new java.awt.Color(255, 255, 255));

        Time.setBackground(new java.awt.Color(255, 255, 255));
        Time.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Time.setText("00:00:00");

        javax.swing.GroupLayout clockPanelLayout = new javax.swing.GroupLayout(clockPanel);
        clockPanel.setLayout(clockPanelLayout);
        clockPanelLayout.setHorizontalGroup(
            clockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Time, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );
        clockPanelLayout.setVerticalGroup(
            clockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Time, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LEFT, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(UP, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(RIGHT, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DOWN, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clockPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RIGHT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addComponent(LEFT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(UP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(3, 3, 3)))
                                .addComponent(DOWN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clockPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gamePanel)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            saveTime();
            setScore();
            setMovementButtonsColor('l');
            gameOver();
        }
    }//GEN-LAST:event_LEFTActionPerformed

    private void RIGHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RIGHTActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.RIGHT);
            saveTime();
            setScore();
            setMovementButtonsColor('r');
            gameOver();
        }
    }//GEN-LAST:event_RIGHTActionPerformed

    private void UPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.UP);
            saveTime();
            setScore();
            setMovementButtonsColor('u');
            gameOver();
        }
    }//GEN-LAST:event_UPActionPerformed

    private void UndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoActionPerformed
        this.panel.Undo();
        setScore();
        setFunctionButtonsColor('U');
        setMovementButtonsColor('U');
    }//GEN-LAST:event_UndoActionPerformed
    public void resetGame(){
        this.panel.resetGame();
        this.clock.reset();
        score.setText(map.getScore() + "");
        setFunctionButtonsColor('R');
        setMovementButtonsColor('R');
    }
    
    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        this.panel.resetGame();
        this.clock.reset();
        score.setText(map.getScore() + "");
        setFunctionButtonsColor('R');
        setMovementButtonsColor('R');
    }//GEN-LAST:event_RESETActionPerformed

    private void DOWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOWNActionPerformed
        if (!isDisabled) {
            panel.processMovement(TileMap.Movement.DOWN);
            saveTime();
            setScore();
            setMovementButtonsColor('d');
            gameOver();
        }
    }//GEN-LAST:event_DOWNActionPerformed

    private void scoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scoreActionPerformed

    private void infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoActionPerformed

        new pkg2048.StatDisplay(panel.getInformation().getInfo(),this).setVisible(true);
    }//GEN-LAST:event_infoActionPerformed

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
                new Menu().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DOWN;
    private javax.swing.JButton LEFT;
    private javax.swing.JButton RESET;
    private javax.swing.JButton RIGHT;
    private javax.swing.JLabel Time;
    private javax.swing.JButton UP;
    private javax.swing.JButton Undo;
    private javax.swing.JPanel clockPanel;
    private javax.swing.JLayeredPane gamePanel;
    private javax.swing.JButton info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField score;
    private javax.swing.JTextField score1;
    // End of variables declaration//GEN-END:variables
}
