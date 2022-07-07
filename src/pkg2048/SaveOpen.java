package pkg2048;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Pham Nhat Quang
 */
public class SaveOpen {

    private Information info;
    private final String savePath = "2048.sav";
    private final File saveFile;

    public SaveOpen() {
        this.saveFile = new File(savePath);
        if (!(saveFile.exists() && saveFile.isFile())) {
            try {
                saveFile.createNewFile();
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        this.open();
    }

    public void save() {

    }

    public void open() {
        this.info = new Information();

        try (BufferedReader br = new BufferedReader(new FileReader(saveFile))){
            String line;
            for (int i = 0; i < 10; i++) {
                
            }
        } catch (IOException ioe){
            System.err.println(ioe);
        }
    }

}
