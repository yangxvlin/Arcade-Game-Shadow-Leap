import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.Solid;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-08 22:01
 * description: concrete class to specify Tree type is a background tile and it is solid
 **/

public final class Tree extends Background implements Solid {
    /** image paths for Tree */
    public static final String TREE_IMAGE_PATH = "assets/tree.png";

    /**
     * construct tree instance
     * @param imageSrc image path
     * @param x        x-coordinate for Tree tile
     * @param y        y-coordinate for Tree tile
     */
    public Tree(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    /** tree tile has no update action */
    @Override
    public void update(Input input, int delta) {}
}
