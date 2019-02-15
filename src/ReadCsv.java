import org.newdawn.slick.SlickException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-08 20:36
 * description: read level from csv files
 **/

public final class ReadCsv {
    /** the character that the data are split by */
    public static final String csvSplitBy = ",";

    /**
     * return the level with Tiles read
     * @param csvFilePath the path of the csv file
     * @return the Level with Tiles created
     * */
    public static Level readCsv(String csvFilePath) throws SlickException {
        String line, cleaned;

        Level level = new Level();

        /* terminate game when no more levels can be read */
        try (Scanner scan = new Scanner(new FileReader(csvFilePath))) {
            /* create all tiles from file */
            while (scan.hasNextLine()) {
            	line = scan.nextLine();
                cleaned = line.toLowerCase();

                /* use comma as separator */
                String[] parameters = cleaned.split(csvSplitBy);

                TileFactory.createTile(level, parameters);
            }

            scan.close();
            
            return level;
        } catch (IOException e) {
            Tools.endGame();
        }
        
        return null;
    }
}

/* java is fun! */