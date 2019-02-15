import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;
import utilities.MutablePosition;
import utilities.Position;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-06 19:27
 * description: abstract a tile entity in game has [Sprite, Background] type
 **/

public abstract class Tile{
    /** include edges in boundingBox */
    public static final boolean EDGES_INCLUSIVE = true;
    /** exclude edges in boundingBox */
    public static final boolean EDGES_EXCLUSIVE = false;
    /** tile block (square) size: 48 * 48 and center position: (24, 24) */
    public static final int TILE_BLOCK_SIZE     = 48;

    /** image attribute to be rendered */
    private Image image;
    /** a tile associates a position includes: (x, y) coordinated and boundingBox */
    private Position position;

    /**
     * construct Tile instance and assign attributes
     * @param imageSrc image path
     * @param position tile's position
     * */
    public Tile(String imageSrc, Position position) throws SlickException {
        this.image    = new Image(imageSrc);
        this.position = position;
    }

    /**
     * construct Tile instance and assign attributes
     * @param image Image object for tile
     * @param position tile's position
     * */
    public Tile(Image image, Position position) {
        this.image    = image;
        this.position = position;
    }

    /**
     * construct Tile instance and assign attributes position will be assigned later
     * @param imageSrc image path
     * */
    public Tile(String imageSrc) throws SlickException{
        this.image = new Image(imageSrc);
    }

    /** update action
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    public abstract void update(Input input, int delta);

    /** draw all the tile's image at its center (x, y) */
    public void render() {
        this.getImage().drawCentered(this.getX(), this.getY());
    }

    /**
     * return True if current Tile covers the other Tile
     * @param other another Tile to compare with current Tile
     * @param edgesInclusive boolean whether to check tile's edges contact
     * @return true if two tiles' boundingBox intersects
     * */
    public boolean contactTile(Tile other, boolean edgesInclusive) {
        return this.getBoundingBox().intersects(other.getBoundingBox(), edgesInclusive);
    }

    /* ************************ getters and setters ************************ */
    /**
     * return the x-coordinate
     * @return a tile's x-coordinate
     * */
    public float getX() {
        return this.position.getX();
    }

    /**
     * return the y-coordinate
     * @return a tile's y-coordinate
     * */
    public float getY() {
        return this.position.getY();
    }

    /**
     * return tile's boundingBox attribute's copy
     * @return a tile's boundingBox
     * */
    public BoundingBox getBoundingBox() {
        return this.position.getBoundingBox();
    }

    /**
     * return tile's Image attribute's copy
     * @return a tile's image
     * */
    public Image getImage() {
        return this.image.copy();
    }

    /**
     * return the copy of tile's copy
     * @return a tile's position
     * */
    public Position getPosition() {
        return this.position.copyOf();
    }

    /** modify instance's x-coordinate */
    public void setX(float x) {
        this.position.setX(x);
    }

    /** modify instance's y-coordinate */
    public void setY(float y) {
        this.position.setY(y);
    }

    /** only modify the position if tile has no position or the position is mutable */
    public void setPosition(Position position) {
        if ((this.position == null) || (this.position instanceof MutablePosition)) {
            this.position = position;
        }
    }

    /** modify instance's BoundingBox x-coordinate */
    public void setBoundingBoxX(float x) {
        this.position.setBoundingBoxX(x);
    }

    /** modify instance's BoundingBox y-coordinate */
    public void setBoundingBoxY(float y) {
        this.position.setBoundingBoxY(y);
    }
}

/* java is fun! */