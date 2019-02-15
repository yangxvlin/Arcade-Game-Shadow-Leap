import org.newdawn.slick.SlickException;
import utilities.BoundingBox;
import utilities.MarginReappearable;
import utilities.Movable;
import utilities.PlayerInteractable;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-11 22:35
 * description: class DirectedSprite abstract All movable tiles in game with a direction
 **/

public abstract class DirectedSprite extends Sprite implements Movable, MarginReappearable, PlayerInteractable {
    /** indicate sprite's direction */
    private boolean isRight;

    /**
     * construct and assign attributes
     * @param imageSrc image path
     * @param x        x-coordinate for the tile
     * @param y        y-coordinate for the tile
     * @param isRight  indicates sprite is moving right
     * */
    public DirectedSprite(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y);

        this.isRight = isRight;
    }

    /**
     * construct and assign attributes
     * @param imageSrc image path
     * @param isRight  indicates sprite is moving right
     * */
    public DirectedSprite(String imageSrc, boolean isRight) throws SlickException {
        super(imageSrc);

        this.isRight = isRight;
    }

    /* ******************* implement MarginReappearable ******************* */
    /**
     * if instance is moving left/right check whether it has any remain image
     * (right/left bound of its image) in bound
     * @param bound the bound that the object is moving in
     * @return true if a Sprite cannot continuous to move inside the screen
     * */
    @Override
    public boolean isMovingInBound(BoundingBox bound) {
        if (!this.isRight) {
            return this.getBoundingBox().atRight(bound, EDGES_INCLUSIVE);
        } else {
            return this.getBoundingBox().atLeft(bound, EDGES_INCLUSIVE);
        }
    }

    /** change obstacle's coordinates to opposite side when it is moving and outside of screen */
    @Override
    public void reappear() {
        alterPosition();
    }

    /* ************************* implement Movable ************************* */
    /**
     * change obstacle's coordinates and move to opposite side if it is out of Game World (screen)
     * @param dx number of pixels to be moved in x-axis
     * @param dy number of pixels to be moved in y-axis
     * */
    @Override
    public void move(float dx, float dy) {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);

        reappear();
    }

    /* ************************ getters and setters ************************ */
    /**
     * return instance's numeric direction
     * @return 1:movingRight -1: movingLeft
     * */
    public int getDirection(){
        return (this.isRight) ? MOVE_RIGHTWARD : MOVE_LEFTWARD;
    }

    /** reverse the object's direction */
    public void alternateDirection() {
        this.isRight = !this.isRight;
    }

    /**
     * set object with specific direction
     * @param isRight indicates sprite is moving right
     * */
    public void setDirection(boolean isRight) {
        this.isRight = isRight;
    }

    /** move object to the other side of the screen */
    private void alterPosition() {
        if (!this.isMovingInBound(World.SCREEN)) {
            this.setX(this.getX() - this.getDirection() * (App.SCREEN_WIDTH + this.getImage().getWidth()));
        }
    }
}

/* java is fun! */