import org.newdawn.slick.SlickException;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-12 16:39
 * description: represent vehicle has knock down operation
 **/

public abstract class KnockDownableVehicle extends Vehicles implements KnockDownable {
    /**
     * construct vehicle
     * @param imageSrc image path
     * @param x        x-coordinate for the tile
     * @param y        y-coordinate for the tile
     * @param isRight  indicates sprite is moving right
     * */
    public KnockDownableVehicle(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y, isRight);
    }

    /* *********************** implement KnockDownable *********************** */
    /**
     * perform the action that decrease the player's life
     * @param player the Player to be knocked down
     * */
    public void knockDown(Player player) {
        /* knock down player when contacts player */
        if (this.contactTile(player, Tile.EDGES_EXCLUSIVE)) {
            player.loseLife();
            player.resetPosition();
        }
    }

    /* ******************** implement PlayerInteractable ******************** */
    /** perform the action that the vehicle can knock down Player */
    @Override
    public void interactPlayer() {
        Player player = World.getPlayer(this.getClass());
        /* Cannot put this interactPlayer() in parent class: Vehicle. Cause Bulldozer
         * won't has super.interactPlayer() which is knockDown player in Bulldozer.class */
        this.knockDown(player);
    }
}

/* java is fun! */