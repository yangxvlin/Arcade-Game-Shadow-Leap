import org.newdawn.slick.SlickException;
import utilities.PlayerInteractable;

/**
 /**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-12 19:31
 * description: concrete class for the completed tile for player
 *              if it is not completed and player interacts, reset player and make CompletedPlayer completed
 *              if it is completed and player interacts, reset player and decrease player's lives
 **/

public final class CompletedPlayer extends Background implements PlayerInteractable {
    /** player has no completion at this tile */
    private static final boolean UNCOMPLETED = false;
    /** player has completion at this tile */
    private static final boolean COMPLETED = true;

    /** indicate whether a player has complete this tile */
    private boolean isCompleted = UNCOMPLETED;

    /**
     * construct and assign attributes for CompletedPlayer
     * @param imageSrc image path
     * @param x        x-coordinate for CompletedPlayer tile
     * @param y        y-coordinate for CompletedPlayer tile
     * */
    public CompletedPlayer(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    /** only render it on screen when player has already interacted with it */
    @Override
    public void render() {
        if (this.isCompleted) {
            super.render();
        }
    }

    /* ******************** implement PlayerInteractable ******************** */
    /**
     * if it is not completed and player interacts, reset player's position and make CompletedPlayer completed
     * if it is completed and player interacts, reset player's position and decrease player's lives
     * */
    @Override
    public void interactPlayer() {
        Player player = World.getPlayer(this.getClass());

        if (this.contactTile(player, Tile.EDGES_EXCLUSIVE)) {
            /* player can't complete at this tile again */
        	if (this.isCompleted) {
        		player.loseLife();

            /* made a completion */
            } else {
                player.increaseLevelProgress();
                this.isCompleted = COMPLETED;
            }

            player.resetPosition();
        }
    }
}
