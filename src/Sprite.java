import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.MutablePosition;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @date 2018-08-25 18:31
 * @description class Sprite abstract All movable tiles in game
 **/

public abstract class Sprite extends Tile {
	/** instance moving left */
	public static final int MOVE_LEFTWARD  = -1;
	/** instance moving up */
	public static final int MOVE_UPWARD    = -1;
	/** instance moving right */
	public static final int MOVE_RIGHTWARD = 1;
	/** instance moving down */
	public static final int MOVE_DOWNWARD  = 1;
    /** zero change in x, y coordinates */
    public static final int NO_MOVE        = 0;

    /**
     * construct and assign attributes
     * @param imageSrc image path
     * @param x        x-coordinate for player tile
     * @param y        y-coordinate for player tile
     * */
	public Sprite(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, new MutablePosition(x, y, new Image(imageSrc)));
	}

	/**
	 * construct Sprite instance and assign attributes
	 * @param imageSrc image path
	 * */
	public Sprite(String imageSrc) throws SlickException {
		super(imageSrc);
	}

	/** default sprite has no update */
	public void update(Input input, int delta) {}
}

/* java is fun! */