package DataSaving;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import pkg2048.Tile;

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
    private HashSet<Integer> milestonesReached;

    //Record information
    private int bestScore;
    private int totalScore;
    private int topTile;

    static public class MilestoneTile {

        int gamesReached;
        int shortestTime;
        int fewestMoves;

        public MilestoneTile() {
            gamesReached = 0;
            shortestTime = Integer.MAX_VALUE;
            fewestMoves = Integer.MAX_VALUE;
        }

        public MilestoneTile(int gamesReached, int shortestTime, int fewestMoves) {
            this.gamesReached = gamesReached;
            this.shortestTime = shortestTime;
            this.fewestMoves = fewestMoves;
        }

        public String getShortestTimeDisplay() {
            return String.format("%02d:%02d:%02d", this.shortestTime / 3600, (this.shortestTime / 60) % 60, this.shortestTime % 60);
        }

        public int getGamesReached() {
            return gamesReached;
        }

        public void setGamesReached(int gamesReached) {
            this.gamesReached = gamesReached;
        }

        public int getShortestTime() {
            return shortestTime;
        }

        public void setShortestTime(int shortestTime) {
            this.shortestTime = shortestTime;
        }

        public int getFewestMoves() {
            return fewestMoves;
        }

        public void setFewestMoves(int fewestMoves) {
            this.fewestMoves = fewestMoves;
        }

    }

    private Map<Integer, MilestoneTile> milestones;

    /**
     *
     */
    public Information() {
        this.score = 0;
        this.numOfMoves = 0;
        this.time = 0;

        this.bestScore = 0;
        this.totalScore = 0;

        this.milestones = new TreeMap<>();
        this.milestones.put(512, new MilestoneTile());
        this.milestones.put(1024, new MilestoneTile());
        this.milestones.put(2048, new MilestoneTile());
        this.milestonesReached = new HashSet();

        this.gameState = new Tile[4][4];
        this.gameState[(int) (Math.random() * 3 + 0)][(int) (Math.random() * 3 + 0)] = new Tile();
    }

    public Information(Tile[][] gameState, int score, int time, int numOfMoves, HashSet<Integer> milestonesReached, int bestScore, int totalScore, int topTile, Map<Integer, MilestoneTile> milestones) {
        this.gameState = gameState;
        this.score = score;
        this.time = time;
        this.numOfMoves = numOfMoves;
        this.milestonesReached = milestonesReached;
        this.bestScore = bestScore;
        this.totalScore = totalScore;
        this.topTile = topTile;
        this.milestones = milestones;
    }

    /**
     *
     * @param bestScore
     * @param totalScore
     * @param gameReached512
     * @param milestones
     */
    public Information(int bestScore, int totalScore, int gameReached512, TreeMap<Integer, MilestoneTile> milestones) {
        this.score = 0;
        this.numOfMoves = 0;
        this.time = 0;
        this.bestScore = bestScore;
        this.totalScore = totalScore;

        this.milestones = milestones;

    }

    public MilestoneTile getMilestones(int tileValue) {
        return this.milestones.get(tileValue);
    }

    public void setMiletones(Map<Integer, MilestoneTile> miletones) {
        this.milestones = miletones;
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
        if (amount > topTile) {
            topTile = amount;
        }
        this.score += amount;
        this.totalScore += amount;
        //Major tiles reached:
        //512
        MilestoneTile milestone = this.milestones.get(amount);
        if (!milestonesReached.contains(amount)) {
            milestonesReached.add(amount);

            if (milestone != null) {
                milestone.setGamesReached(milestone.getGamesReached() + 1);
                if (this.time < milestone.getShortestTime()) {
                    milestone.setShortestTime(this.time);
                }
                if (this.numOfMoves < milestone.getFewestMoves()) {
                    milestone.setFewestMoves(this.numOfMoves);
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

    public HashSet<Integer> getMilestonesReached() {
        return milestonesReached;
    }

    public void setMilestonesReached(HashSet<Integer> milestonesReached) {
        this.milestonesReached = milestonesReached;
    }

    /**
     *
     * @return
     */
    public String getTimeDisplay() {
        return String.format("%02d:%02d:%02d", this.time / 3600, this.time / 60, this.time % 60);
    }

    /**
     *
     * @param timeString
     * @return
     */
    public static int convertTime(String timeString) {
        String time[] = timeString.split(":");
        return Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
    }

    public void incrementNoOfMoves() {
        this.numOfMoves++;
    }

    public Information createCopy() {
        return new Information(gameState, score, time, numOfMoves, milestonesReached, bestScore, totalScore, topTile, milestones);
    }
}
