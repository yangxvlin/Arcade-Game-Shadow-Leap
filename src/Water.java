import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.PlayerInteractable;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @date 2018-08-25 19:19
 * @description concrete class to specify Water type is an Environment tile
 **/

public final class Water extends Background implements PlayerInteractable {
    /** image paths for Water */
    public static final String WATER_IMAGE_PATH = "assets/water.png";

    /**
     * construct water instance
     * @param imageSrc image path
     * @param x        x-coordinate for Water tile
     * @param y        y-coordinate for Water tile
     * */
    public Water(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    /** water has no further update action */
    @Override
    public void update(Input input, int delta){}

    /* ******************** implement PlayerInteractable ******************** */
    /** player can on water but it might be dangerous */
    @Override
    public void interactPlayer() {
        Player player = World.getPlayer(this.getClass());
        
    	/* no need to check player interacts water when player is on log or turtles */
        if (!player.isOnRideableObject() && this.contactTile(player, Tile.EDGES_EXCLUSIVE)) {
            player.loseLife();
            player.resetPosition();
        }
    }
}

/* java is fun! */