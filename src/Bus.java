import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-08-29 15:29
 * @description represent concrete bus tile
 **/

public final class Bus extends KnockDownableVehicle{
    /** image paths for Bus */
    public static final String BUS_IMAGE_PATH = "assets/bus.png";
    /** all buses have constant speed 0.15 pixels per ms */
    public static final float BUS_SPEED       = 0.15f;

    /**
     * construct bus instance and assign which row the bus is in
     * @param imageSrc image path
     * @param x        x-coordinate for bus tile
     * @param y        y-coordinate for bus tile
     * */
    public Bus(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y, isRight);
    }

    /**
     * update the position of one bus with its speed
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta, BUS_SPEED);
    }
}

/* java is fun! */