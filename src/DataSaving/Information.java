package DataSaving;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import pkg2048.Tile;

/**
 * Store information in a game
 *
 * @author Pham Nhat Quang
 */
public class Information {

    //Current game information
    private Tile[][] gameState;
    private int score; //Current game

    private int time; //Time in seconds
    private int numOfMoves;

    //Record information
    private int bestScore;
    private int totalScore;
    private int topTile;

    /**
     * Information for milestone tiles reached in all games
     */
    static public class MilestoneTile {

        int gamesReached;//Number of games reached
        int shortestTime;//Shortest time to reach
        int fewestMoves;//Fewest moves to reach

        /**
         * Create new MilestoneTile with no information
         */
        public MilestoneTile() {
            gamesReached = 0;
            shortestTime = Integer.MAX_VALUE;
            fewestMoves = Integer.MAX_VALUE;
        }

        /**
         * Create new MilestoneTile with information
         *
         * @param gamesReached Number of games reached
         * @param shortestTime Shortest time to reach
         * @param fewestMoves Fewest moves to reach
         */
        public MilestoneTile(int gamesReached, int shortestTime, int fewestMoves) {
            this.gamesReached = gamesReached;
            this.shortestTime = shortestTime;
            this.fewestMoves = fewestMoves;
        }

        /**
         * Get String representation of shortest time (in hh:mm:ss format)
         *
         * @return the String representation
         */
        public String getShortestTimeDisplay() {
            return String.format("%02d:%02d:%02d", this.shortestTime / 3600, (this.shortestTime / 60) % 60, this.shortestTime % 60);
        }

        /**
         * Get number of games reached
         *
         * @return Number of games reached for this milestone tile
         */
        public int getGamesReached() {
            return gamesReached;
        }

        /**
         * Set number of games reached
         *
         * @param gamesReached The number
         */
        public void setGamesReached(int gamesReached) {
            this.gamesReached = gamesReached;
        }

        /**
         * Get shortest time to reach this milestone tile
         *
         * @return The shortest time (in seconds)
         */
        public int getShortestTime() {
            return shortestTime;
        }

        /**
         * Set shortest time to reach this milestone tile
         *
         * @param shortestTime Time to set
         */
        public void setShortestTime(int shortestTime) {
            this.shortestTime = shortestTime;
        }

        /**
         * Get the fewest number of moves to reach this milestone tile
         *
         * @return Fewest number of moves
         */
        public int getFewestMoves() {
            return fewestMoves;
        }

        /**
         * Set the fewest number of moves to reach this milestone tile
         *
         * @param fewestMoves Number of moves to set
         */
        public void setFewestMoves(int fewestMoves) {
            this.fewestMoves = fewestMoves;
        }

        /**
         * Check if this MilestoneTile object has been modified (contains more
         * than default information
         *
         * @return false if information is the same as no parameter constructor,
         * true if not
         */
        public boolean isEmpty() {
            return gamesReached == 0 && shortestTime == Integer.MAX_VALUE && fewestMoves == Integer.MAX_VALUE;
        }

        /**
         * Create a copy of this MilestoneTile object
         *
         * @return
         */
        public MilestoneTile createCopy() {
            return new MilestoneTile(gamesReached, shortestTime, fewestMoves);
        }
    }
    private HashSet<Integer> milestonesReached; //Milestones reached in current games
    private Map<Integer, MilestoneTile> milestones;//Information of milestones

    /**
     * Default constructor for Information with no parameters
     */
    public Information() {
        //Current game information is 0 (no moves played yet)
        this.score = 0;
        this.numOfMoves = 0;
        this.time = 0;

        //Best and total score is 0 (no moves played yet)
        this.bestScore = 0;
        this.totalScore = 0;

        //Initialize milestones with default 512, 1024, 2048 MilestoneTiles constructor
        this.milestones = new TreeMap<>();
        this.milestones.put(512, new MilestoneTile());
        this.milestones.put(1024, new MilestoneTile());
        this.milestones.put(2048, new MilestoneTile());

        //Milestones reached for current game is 0
        this.milestonesReached = new HashSet();

        //Current game state is blank (no tiles yet)
        this.gameState = new Tile[4][4];
        //Generate random tile in random position
        this.gameState[(int) (Math.random() * 3 + 0)][(int) (Math.random() * 3 + 0)] = new Tile();
    }

    /**
     * Create Information object with existing information
     *
     * @param gameState Current game state
     * @param score Current score
     * @param time Current time played
     * @param numOfMoves Current number of moves
     * @param milestonesReached Milestones reached
     * @param bestScore All time best score
     * @param totalScore Total score of all games
     * @param topTile Top tile reached
     * @param milestones Milestones information
     */
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
     * Get information of a milestone
     *
     * @param tileValue Tile value of the milestone
     * @return Information of a the milestone retrieved from milestones map
     */
    public MilestoneTile getMilestoneInfo(int tileValue) {
        return this.milestones.get(tileValue);
    }

    /**
     * Set milestones map
     *
     * @param miletones Map to set with
     */
    public void setMiletones(Map<Integer, MilestoneTile> miletones) {
        this.milestones = miletones;
    }

    /**
     * Get score of current game
     *
     * @return Current game score
     */
    public int getScore() {
        return score;
    }

    /**
     * Set score of current game
     *
     * @param score Score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Increase score of current game
     *
     * @param amount Amount to increase with (should increase an amount equal to
     * a tile value)
     */
    public void increaseScore(int amount) {
        if (amount > topTile) {//If amount is more than the current top tile
            topTile = amount;//Update top tile value
        }

        //Increase score and total score
        this.score += amount;
        this.totalScore += amount;

        //Update information of major tiles reached
        if (!milestonesReached.contains(amount)) {//If current game has not reached the tile with this amount yet
            milestonesReached.add(amount);//Add to milestonesReached
            MilestoneTile milestone = this.milestones.get(amount);//Get reference of the MilestoneTile to update information
            if (milestone != null) {
                milestone.setGamesReached(milestone.getGamesReached() + 1);//Increase number of games reached

                if (this.time < milestone.getShortestTime()) {//If reached in shorter time than current shortest time, update shortest time
                    milestone.setShortestTime(this.time);
                }
                if (this.numOfMoves < milestone.getFewestMoves()) {//If reached in fewer moves than current fewest moves, update fewest moves
                    milestone.setFewestMoves(this.numOfMoves);
                }
            }
        }
    }

    /**
     * Get the best score ever reached in all games
     *
     * @return Best score recorded
     */
    public int getBestScore() {
        return bestScore;
    }

    /**
     * Set best score
     *
     * @param bestScore Amount to set
     */
    public void setBestScore(int bestScore) {
        if (bestScore >= this.bestScore) {
            this.bestScore = bestScore;
        }
    }

    /**
     * Get total score in all games
     *
     * @return Amount of total score
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Set total score
     *
     * @param totalScore Amount to set
     */
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * Get number of moves in current games
     *
     * @return Number of moves
     */
    public int getNumOfMoves() {
        return numOfMoves;
    }

    /**
     * Set number of moves in current game
     *
     * @param numOfMoves Quantity to set
     */
    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
    }

    /**
     * Get time since current game started
     *
     * @return Time in seconds
     */
    public int getTime() {
        return time;
    }

    /**
     * Set time for current game
     *
     * @param time Time in seconds to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Get top tile ever reached
     *
     * @return Top tile recorded
     */
    public int getTopTile() {
        return topTile;
    }

    /**
     * Set top tile
     *
     * @param topTile Value of top tile to set
     */
    public void setTopTile(int topTile) {
        if (topTile >= this.topTile) {
            this.topTile = topTile;
        }
    }

    /**
     * Get current game state
     *
     * @return Current game state
     */
    public Tile[][] getGameState() {
        return gameState;
    }

    /**
     * Set state of current game
     *
     * @param gameState State to set
     */
    public void setGameState(Tile[][] gameState) {
        this.gameState = gameState;
    }

    /**
     * Get HashSet of values of milestones reached in this game
     *
     * @return milestonesReached HashSet
     */
    public HashSet<Integer> getMilestonesReached() {
        return milestonesReached;
    }

    /**
     * Set milestonesReached HashSet
     *
     * @param milestonesReached HashSet to set
     */
    public void setMilestonesReached(HashSet<Integer> milestonesReached) {
        this.milestonesReached = milestonesReached;
    }

    /**
     * Get time of current game in String representation
     * @return String representation of time (hh:mm:ss format)
     */
    public String getTimeDisplay() {
        return String.format("%02d:%02d:%02d", this.time / 3600, this.time / 60, this.time % 60);
    }

    /**
     * Convert a String in format hh:mm:ss into time in seconds
     * @param timeString String to convert
     * @return Time in seconds
     */
    public static int convertTime(String timeString) {
        String time[] = timeString.split(":");
        return Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
    }

    /**
     * Increment the number of moves
     */
    public void incrementNoOfMoves() {
        this.numOfMoves++;
    }

    /**
     * Create copy of this Information object
     * @return Copy of this object (not a reference to this object)
     */
    public Information createCopy() {
        return new Information(gameState, score, time, numOfMoves, createMilestoneReachedCopy(), bestScore, totalScore, topTile, createMilestonesCopy());
    }

    /**
     * Create copy of milestones Map
     * @return A copy of milestones Map (not a reference to current milestones map)
     */
    public Map<Integer, MilestoneTile> createMilestonesCopy() {
        TreeMap<Integer, MilestoneTile> result = new TreeMap<>();
        this.milestones.forEach((k, v) -> result.put(k, v.createCopy()));
        return result;
    }

    /**
     * Create copy of milestonesReached HashSet
     * @return A copy of milestonesReached (not a reference to current milestonesReached)
     */
    public HashSet<Integer> createMilestoneReachedCopy() {
        HashSet<Integer> result = new HashSet<>();
        this.milestonesReached.forEach(insert -> {
            result.add(insert);
        });
        return result;
    }

    /**
     * Reset milestonesReached with new HashSet
     */
    public void resetMilestonesReached() {
        this.milestonesReached = new HashSet<>();
    }

    /**
     * Calculate milestonesReached based on maxTile of game state
     * @param maxTile The highest value of tiles in the game state
     */
    public void calculateMilestonesReached(int maxTile) {
        resetMilestonesReached();
        int milestone = 512;
        int pow = 0;
        while (milestone <= maxTile) {
            milestone = milestone * (int) Math.pow((double) 2, (double) pow);
            this.milestonesReached.add(milestone);
            ++pow;
        }
    }
}
