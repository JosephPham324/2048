package pkg2048;

/**
 *
 * @author Pham Nhat Quang Store information in a game
 */
public class Information {

    //Current game information
    private Tile[][] gameState;
    private int score; //Current game

    private int time; //Time in seconds
    private int numOfMoves;
    //512
    private int time512;
    private int movesReached512;
    private boolean reached512;
    //1024
    private int movesReached1024;
    private int time1024;
    private boolean reached1024;

    //Record information
    private int bestScore;
    private int totalScore; 
    private int topTile;
    //512
    private int gameReached512;
    private int shortestTime512;
    private int fewestMoves512;

    //1024
    private int gameReached1024;
    private int shortestTime1024;
    private int fewestMoves1024;



    /**
     *
     */
    public Information() {
        this.score = 0;
        this.time512 = 0;
        this.time1024 = 0;
        this.numOfMoves = 0;
        this.time = 0;
        this.reached512 = false;
        this.reached1024 = false;
        this.movesReached512 = -1;
        this.movesReached1024 = -1;
        this.bestScore = 0;
        this.totalScore = 0;
        this.gameReached512 = 0;
        this.shortestTime512 = Integer.MAX_VALUE;
        this.fewestMoves512 = Integer.MAX_VALUE;
        this.gameReached1024 = 0;
        this.shortestTime1024 = Integer.MAX_VALUE;
        this.fewestMoves1024 = Integer.MAX_VALUE;
        this.gameState = new Tile[4][4];
    }

    /**
     *
     * @param bestScore
     * @param totalScore
     * @param gameReached512
     * @param shortestTime512
     * @param fewestMoves512
     * @param gameReached1024
     * @param shortestTime1024
     * @param fewestMoves1024
     */
    public Information(int bestScore, int totalScore, int gameReached512, int shortestTime512, int fewestMoves512, int gameReached1024, int shortestTime1024, int fewestMoves1024) {
        this.score = 0;
        this.time512 = 0;
        this.time1024 = 0;
        this.numOfMoves = 0;
        this.time = 0;
        this.reached512 = false;
        this.reached1024 = false;
        this.movesReached512 = -1;
        this.movesReached1024 = -1;
        this.bestScore = bestScore;
        this.totalScore = totalScore;
        this.gameReached512 = gameReached512;
        this.shortestTime512 = shortestTime512;
        this.fewestMoves512 = fewestMoves512;
        this.gameReached1024 = gameReached1024;
        this.shortestTime1024 = shortestTime1024;
        this.fewestMoves1024 = fewestMoves1024;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @param amount
     */
    public void increaseScore(int amount) {
        this.score += amount;

        //Major tiles reached: 
        //512
        if (amount == 512) {
            this.gameReached512++;
            if (!this.reached512) { //First time getting 512 in a game
                this.reached512 = true;
                this.time512 = this.time;
                this.movesReached512 = this.numOfMoves;
                if (this.shortestTime512 > this.time512) {
                    this.shortestTime512 = this.time512;
                }
                if (this.fewestMoves512 > this.movesReached512) {
                    this.fewestMoves512 = this.movesReached512;
                }
            }
        }

        //1024
        if (amount == 1024) {
            this.gameReached1024++;
            if (!this.reached1024) { //First time getting 1024 in a game
                this.reached1024 = true;
                this.time1024 = this.time;
                if (this.shortestTime1024 > this.time1024) {
                    this.shortestTime1024 = this.time1024;
                }
                if (this.fewestMoves1024 > this.movesReached1024) {
                    this.fewestMoves1024 = this.movesReached1024;
                }
            }
        }

        //2048
    }

    /**
     *
     * @return
     */
    public int getBestScore() {
        return bestScore;
    }

    /**
     *
     * @param bestScore
     */
    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    /**
     *
     * @return
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     *
     * @param totalScore
     */
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    /**
     *
     * @return
     */
    public int getGameReached512() {
        return gameReached512;
    }

    /**
     *
     * @param gameReached512
     */
    public void setGameReached512(int gameReached512) {
        this.gameReached512 = gameReached512;
    }

    /**
     *
     * @return
     */
    public int getShortestTime512() {
        return shortestTime512;
    }

    /**
     *
     * @param shortestTime512
     */
    public void setShortestTime512(int shortestTime512) {
        this.shortestTime512 = shortestTime512;
    }

    /**
     *
     * @return
     */
    public int getFewestMoves512() {
        return fewestMoves512;
    }

    /**
     *
     * @param fewestMoves512
     */
    public void setFewestMoves512(int fewestMoves512) {
        this.fewestMoves512 = fewestMoves512;
    }

    /**
     *
     * @return
     */
    public int getTime512() {
        return time512;
    }

    /**
     *
     * @param time512
     */
    public void setTime512(int time512) {
        this.time512 = time512;
    }

    /**
     *
     * @return
     */
    public int getGameReached1024() {
        return gameReached1024;
    }

    /**
     *
     * @param gameReached1024
     */
    public void setGameReached1024(int gameReached1024) {
        this.gameReached1024 = gameReached1024;
    }

    /**
     *
     * @return
     */
    public int getShortestTime1024() {
        return shortestTime1024;
    }

    /**
     *
     * @param shortestTime1024
     */
    public void setShortestTime1024(int shortestTime1024) {
        this.shortestTime1024 = shortestTime1024;
    }

    /**
     *
     * @return
     */
    public int getFewestMoves1024() {
        return fewestMoves1024;
    }

    /**
     *
     * @param fewestMoves1024
     */
    public void setFewestMoves1024(int fewestMoves1024) {
        this.fewestMoves1024 = fewestMoves1024;
    }

    /**
     *
     * @return
     */
    public int getTime1024() {
        return time1024;
    }

    /**
     *
     * @param time1024
     */
    public void setTime1024(int time1024) {
        this.time1024 = time1024;
    }

    /**
     *
     * @return
     */
    public int getNumOfMoves() {
        return numOfMoves;
    }

    /**
     *
     * @param numOfMoves
     */
    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
    }

    /**
     *
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     *
     * @param movesReached512
     */
    public void setMovesReached512(int movesReached512) {
        this.movesReached512 = movesReached512;
    }

    /**
     *
     * @param reached512
     */
    public void setReached512(boolean reached512) {
        this.reached512 = reached512;
    }

    /**
     *
     * @param movesReached1024
     */
    public void setMovesReached1024(int movesReached1024) {
        this.movesReached1024 = movesReached1024;
    }

    /**
     *
     * @param reached1024
     */
    public void setReached1024(boolean reached1024) {
        this.reached1024 = reached1024;
    }

    /**
     *
     * @return
     */
    public int getTopTile() {
        return topTile;
    }

    /**
     *
     * @param topTile
     */
    public void setTopTile(int topTile) {
        this.topTile = topTile;
    }

    /**
     *
     * @return
     */
    public Tile[][] getGameState() {
        return gameState;
    }

    /**
     *
     * @param gameState
     */
    public void setGameState(Tile[][] gameState) {
        this.gameState = gameState;
    }

    /**
     *
     * @return
     */
    public int getMovesReached512() {
        return movesReached512;
    }

    /**
     *
     * @return
     */
    public boolean isReached512() {
        return reached512;
    }

    /**
     *
     * @return
     */
    public int getMovesReached1024() {
        return movesReached1024;
    }

    /**
     *
     * @return
     */
    public boolean isReached1024() {
        return reached1024;
    }

    
}
