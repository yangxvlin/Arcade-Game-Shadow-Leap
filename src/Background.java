import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.ImmutablePosition;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-06 19:28
 * description: abstract class for background tile which is parent of all non-movable tiles
 **/

public abstract class Background extends Tile {
    /**
     * construct background instance
     * @param imageSrc image path
     * @param x        x-coordinate for background tile
     * @param y        y-coordinate for background tile
     * */
    public Background(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, new ImmutablePosition(x, y, new Image(imageSrc)));
    }

    /** default background tile has no update action
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    @Override
    public void update(Input input, int delta){}
}

/* java is fun! */