import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-11 22:42
 * description: class abstract all tile that player can ride on
 **/

public abstract class RideableObject extends DirectedSprite implements Shiftable {
    /** store the delta(ms) has passed since last frame */
    private int delta;

    /**
     * construct and assign attributes
     * @param imageSrc image path
     * @param x        x-coordinate for the tile
     * @param y        y-coordinate for the tile
     * @param isRight  indicates sprite is moving right
     * */
    public RideableObject(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y, isRight);
    }

    /** update rideableObject's position with specific speed
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    public void update(Input input, int delta, float logSpeed) {
        move(this.getDirection() * logSpeed * delta, NO_MOVE);
        this.delta = delta;
    }

    /* ************************ implement Shiftable ************************ */
    /**
     *     make the Player move along with the rideableObject when player
     * contact it and player has no worry about the danger of drown in water
     * @param player    the Player that move along with the object
     * @param tileSpeed the speed that the object is moving
     * */
    @Override
    public void shift(Player player, float tileSpeed) {
        /* player contact rideableObject */
        if (this.contactTile(player, Tile.EDGES_EXCLUSIVE)) {
            player.setOnRideableObject();

            float playerShiftToX = player.getX() + tileSpeed * this.delta * this.getDirection();

            /* no shift when player is at edge of screen */
            if (player.moveInWorld(playerShiftToX, player.getY())) {
                player.setX(playerShiftToX);
            }
        }
    }
}

/* java is fun! */