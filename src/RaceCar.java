import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-08 22:01
 * description: represent concrete race car tile
 **/

public final class RaceCar extends KnockDownableVehicle {
    /** image paths for RaceCar */
    public static final String RACECAR_IMAGE_PATH = "assets/racecar.png";
    /** all race cars have constant speed 0.5 pixels per ms */
    public static final float RACECAR_SPEED       = 0.5f;

    /**
     * construct RaceCar
     * @param imageSrc image path
     * @param x        x-coordinate for RaceCar tile
     * @param y        y-coordinate for RaceCar tile
     * @param isRight  indicates sprite is moving right
     * */
    public RaceCar(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y, isRight);
    }

    /**
     * update the position of one race car with its speed
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta, RACECAR_SPEED);
    }
}

/* java is fun! */