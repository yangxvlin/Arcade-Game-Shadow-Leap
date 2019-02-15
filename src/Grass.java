import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @date 2018-08-25 22:50
 * @description concrete class to specify Grass type is a background tile
 **/

public final class Grass extends Background {
    /** image paths for Grass */
    public static final String GRASS_IMAGE_PATH = "assets/grass.png";

    /**
     * construct grass instance
     * @param imageSrc image path
     * @param x        x-coordinate for grass tile
     * @param y        y-coordinate for grass tile
     */
    public Grass(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    /** grass tile has no update action */
    @Override
    public void update(Input input, int delta) {}
}

/* java is fun! */