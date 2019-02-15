import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-08 21:56
 * description: represent concrete LongLog tile
 **/

public final class LongLog extends Log{
    /** image paths for LongLog */
    public static final String LONGLOG_IMAGE_PATH = "assets/longlog.png";
    /** all LongLog have constant speed 0.07 pixels per ms */
    public static final float LONGLOG_SPEED       = 0.07f;

    /**
     * construct LongLog and assign attributes
     * @param imageSrc image path
     * @param x        x-coordinate for LongLog tile
     * @param y        y-coordinate for LongLog tile
     * @param isRight  indicates sprite is moving right
     * */
    public LongLog(String imageSrc, float x, float y, boolean isRight) throws SlickException {
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
        super.update(input, delta, LONGLOG_SPEED);
    }

    /* ******************** implement PlayerInteractable ******************** */
    /** perform the action that the object can shift Player */
    @Override
    public void interactPlayer() {
        Player player = World.getPlayer(this.getClass());
        super.shift(player, LONGLOG_SPEED);
    }
}

/* java is fun! */