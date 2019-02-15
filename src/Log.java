import org.newdawn.slick.SlickException;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-08 21:54
 * description: abstract log object
 **/

public abstract class Log extends RideableObject {
    /**
     * construct and assign attributes
     * @param imageSrc image path
     * @param x        x-coordinate for log tile
     * @param y        y-coordinate for log tile
     * @param isRight  indicates sprite is moving right
     * */
    public Log(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y, isRight);
    }
}

/* java is fun! */