import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-08 21:59
 * description: represent concrete ShortLog tile
 **/

public final class ShortLog extends Log {
    /** image paths for ShortLog */
    public static final String SHORTLOG_IMAGE_PATH = "assets/log.png";
    /** all ShortLog have constant speed 0.1 pixels per ms */
    public static final float SHORTLOG_SPEED       = 0.1f;

    /**
     * construct ShortLog and assign attributes
     * @param imageSrc image path
     * @param x        x-coordinate for ShortLog tile
     * @param y        y-coordinate for ShortLog tile
     * @param isRight  indicates sprite is moving right
     * */
    public ShortLog(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y, isRight);
    }

    /**
     * update the position of one bus and its boundingBox and move left
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta, SHORTLOG_SPEED);
    }

    /* ******************** implement PlayerInteractable ******************** */
    /** perform the action that the object can shift Player */
    @Override
    public void interactPlayer() {
        Player player = World.getPlayer(this.getClass());
        super.shift(player, SHORTLOG_SPEED);
    }
}

/* java is fun! */