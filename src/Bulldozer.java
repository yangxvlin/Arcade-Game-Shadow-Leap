import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.Solid;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-08 21:54
 * description: represent concrete bulldozer tile
 **/

public final class Bulldozer extends Vehicles implements Shiftable, Solid {
    /** image paths for Bulldozer */
    public static final String BULLDOZER_IMAGE_PATH = "assets/bulldozer.png";
    /** all bulldozers have constant speed 0.05 pixels per ms */
    public static final float BULLDOZER_SPEED       = 0.05f;

    /**
     * construct bulldozer
     * @param imageSrc image path
     * @param x        x-coordinate for bulldozer tile
     * @param y        y-coordinate for bulldozer tile
     * @param isRight  indicates sprite is moving right
     * */
    public Bulldozer(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y, isRight);
    }

    /**
     * update the position of one bulldozer with its speed
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta, BULLDOZER_SPEED);
    }

    /* ************************ implement Shiftable ************************ */
    /**
     * make the Player move along with the bulldozer when player contact it on the same direction with its velocity
     * @param player    the Player that move along with the object
     * @param tileSpeed the speed that the object is moving
     * */
    @Override
    public void shift(Player player, float tileSpeed) {
        /* player only interacts with bulldozer when it can be shifted by bulldozer */
        if (this.contactTile(player, Tile.EDGES_EXCLUSIVE)) {
            player.setX(this.getX() + TILE_BLOCK_SIZE);
        }
    }

    /* ******************** implement PlayerInteractable ******************** */
    /** perform the action that the object can shift Player and possibly hurt player's lives */
    @Override
    public void interactPlayer() {
        Player player = World.getPlayer(this.getClass());
        this.shift(player, BULLDOZER_SPEED);

        /* bulldozer is dangerous when player is going to be pushed out of screen by it */
        if (this.contactTile(player, Tile.EDGES_INCLUSIVE) && World.isOutOfWorld(player)) {
            player.resetPosition();
            player.loseLife();
        }
    }
}

/* java is fun! */