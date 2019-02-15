import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-11 22:33
 * description: represent vehicle object
 **/

public abstract class Vehicles extends DirectedSprite {
    /**
     * construct vehicle
     * @param imageSrc image path
     * @param x        x-coordinate for the tile
     * @param y        y-coordinate for the tile
     * @param isRight  indicates sprite is moving right
     * */
    public Vehicles(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y, isRight);
    }

    /** update vehicle with specific speed
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    public void update(Input input, int delta, float vehicleSpeed) {
       move(this.getDirection() * vehicleSpeed * delta, NO_MOVE);
    }
}

/* java is fun! */