package DataSaving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import pkg2048.Tile;
import java.util.Map;
import java.util.TreeMap;
import DataSaving.Information.MilestoneTile;

/**
 * Class for saving and retrieving information from save file
 *
 * @author Pham Nhat Quang
 *
 */
public class SaveOpen {

    private Information info;
    private final String savePath = "2048.sav";//Save file path
    private final File saveFile; //Save file
    private SecretKeySpec secretKey; //Secret
    private byte[] key;
    private static final String SECRET = "2048GameGroup3CSD201";

    /**
     *
     */
    public SaveOpen() {
        this.saveFile = new File(savePath);
        if (!(saveFile.exists() && saveFile.isFile())) {
            try {
                saveFile.createNewFile();
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        this.getSavedInfo();
    }

    /**
     *
     */
    public void saveInfo() {
        try (FileWriter fw = new FileWriter(this.saveFile)) {
            String line;
            for (int i = 0; i < 4; i++) {
                line = "";
                for (int j = 0; j < 3; j++) {
                    if (info.getGameState()[i][j] != null) {
                        line += info.getGameState()[i][j] + " ";
                    } else {
                        line += "0 ";
                    }
                }
                if (info.getGameState()[i][3] != null) {
                    line += info.getGameState()[i][3];
                } else {
                    line += "0";
                }
                line = encrypt(line, SECRET);
                fw.append(line + "\n");
            }
            //Score
            line = encrypt(info.getScore() + "", SECRET);
            fw.append(line + "\n");
            //Time
            line = encrypt(info.getTime() + "", SECRET);
            fw.append(line + "\n");
            //Num moves
            line = encrypt(info.getNumOfMoves() + "", SECRET);
            fw.append(line + "\n");

            //Record
            //Score
            line = encrypt(info.getBestScore() + "", SECRET);
            fw.append(line + "\n");
            //Total score
            line = encrypt(info.getTotalScore() + "", SECRET);
            fw.append(line + "\n");
            //Top tile
            line = encrypt(info.getTopTile() + "", SECRET);
            fw.append(line + "\n");

            int starting = 512;
            int power = 0;
            Information.MilestoneTile milestone;

            do {
                milestone = info.getMilestoneInfo(starting * (int) Math.pow((double) 2, (double) power));
                if (milestone == null) {
                    break;
                }
                line = encrypt(milestone.getGamesReached() + "", SECRET);
                fw.append(line + "\n");
                line = encrypt(milestone.getShortestTime() + "", SECRET);
                fw.append(line + "\n");
                line = encrypt(milestone.getFewestMoves() + "", SECRET);
                fw.append(line + "\n");
                power++;
            } while (true);

        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    /**
     *
     */
    public void getSavedInfo() {
        this.info = new Information();
        if (saveFile.length() == 0) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(saveFile))) {
            String line;
            Tile[][] tiles = new Tile[4][4];
            int maxTile = Integer.MIN_VALUE;
            //Game state
            for (int i = 0; i < 4; i++) {
                line = br.readLine();
                line = decrypt(line, SECRET);
//                System.out.println(line);
                String tileData[] = line.split(" ");
                for (int j = 0; j < 4; j++) {
                    maxTile = Math.max(maxTile,Integer.parseInt(tileData[j]));
                    tiles[i][j] = new Tile(Integer.parseInt(tileData[j]), false);
                }
            }
            this.info.calculateMilestonesReached(maxTile);

            //Current game information
            //State
            this.info.setGameState(tiles);

            //Score
            line = decrypt(br.readLine(), SECRET);
            this.info.setScore(Integer.parseInt(line));

            //Time
            line = decrypt(br.readLine(), SECRET);
            this.info.setTime(Integer.parseInt(line));

            //Num moves
            line = decrypt(br.readLine(), SECRET);
            this.info.setNumOfMoves(Integer.parseInt(line));

            //Record information
            //Best score
            line = decrypt(br.readLine(), SECRET);
            this.info.setBestScore(Integer.parseInt(line));
            //Total score
            line = decrypt(br.readLine(), SECRET);
            this.info.setTotalScore(Integer.parseInt(line));
            //Top tile
            line = decrypt(br.readLine(), SECRET);
            this.info.setTopTile(Integer.parseInt(line));

            int starting = 512;
            int power = 0;
            MilestoneTile milestone;
            Map<Integer, MilestoneTile> milestones = new TreeMap<>();

            do {
                milestone = new MilestoneTile();
                try {
                    line = decrypt(br.readLine(), SECRET);
                    milestone.setGamesReached(Integer.parseInt(line));
                    //Shortest time reached
                    line = decrypt(br.readLine(), SECRET);
                    milestone.setShortestTime(Integer.parseInt(line));
                    //Fewest moves reached
                    line = decrypt(br.readLine(), SECRET);
                    milestone.setFewestMoves(Integer.parseInt(line));
                    milestones.put(starting * (int) Math.pow((double) 2, (double) power), milestone);
                    power++;
                } catch (Exception e) {
                    break;
                }
            } while (true);
            this.info.setMiletones(milestones);

        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    /**
     * Generate secret key based on key String
     *
     * @param myKey key String
     */
    private void setKey(final String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypt a String using a secret String to generate the key
     *
     * @param strToEncrypt String to be encrypted
     * @param secret Secret used for generating key
     * @return Encrypted String
     */
    private String encrypt(final String strToEncrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.err.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     * Decrypt a String using a secret String to generate the key
     *
     * @param strToDecrypt String to be decrypted
     * @param secret Secret used for generating key
     * @return Decrypted String
     */
    private String decrypt(final String strToDecrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
        } catch (Exception e) {
            System.err.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    /**
     * Get info field containing information for 2048 game
     *
     * @return info
     */
    public Information getInfo() {
        return info;
    }

    public void setInfo(Information info) {
        this.info = info;
    }

}
