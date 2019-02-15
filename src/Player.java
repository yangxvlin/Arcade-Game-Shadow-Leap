import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;
import utilities.NextTileMovable;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @date 2018-08-25 19:34
 * @description concrete class to specify Player type is a Sprite
 **/

public final class Player extends Sprite implements NextTileMovable {
    /** image paths for Player */
    public static final String PLAYER_IMAGE_PATH       = "assets/frog.png";
    /** player tile's initial position (x, y) */
    public static final int[] PLAYER_START_XY          = new int[]{512, 720};
    /** initial player's lives */
    public static final int PLAYER_INITIAL_LIVES       = 3;
    /** player's lives threshold to terminate the game */
    public static final int NO_LIFE                    = 0;
    /** player's lives image */
    public static final String LIFE_IMAGE_PATH         = "assets/lives.png";
    /** player's life image's initial xy-coordinates */
    public static final int[] LIFE_XY                  = new int[]{24, 744};
    /** player's life image's separation */
    public static final int LIFE_SEPARATION            = 32;
    /** player's initial progress(completion) in one level */
    public static final int NO_PROGRESS                = 0;
    /** player doesn't contact a rideable object */
    public static final boolean NOT_ON_RIDEABLE_OBJECT = false;
    /** player contacts a rideable tile */
    public static final boolean ON_RIDEABLE_OBJECT     = true;
    /** player contacts a solid tile */
    public static final boolean CONTACT_SOLID_TILES    = false;

    /** player's lives's image */
    private Image lifeImage;
    /** player's lives left */
    private int lives;
    /** the number of hole that the player has completed */
    private int levelProgress;
    /** whether player contacts a rideable tile */
    private boolean isOnRideableObject;

    /**
     * construct player instance
     * @param imageSrc image path
     * @param x        x-coordinate for player tile
     * @param y        y-coordinate for player tile
     * */
    public Player(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
        this.lifeImage = new Image(LIFE_IMAGE_PATH);
        this.lives = PLAYER_INITIAL_LIVES;
        this.levelProgress = NO_PROGRESS;
        this.isOnRideableObject = NOT_ON_RIDEABLE_OBJECT;
    }

    /**
     * update player's position as well as its boundingBox
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    @Override
    public void update(Input input, int delta) {
        /* move leftward */
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            move(MOVE_LEFTWARD * TILE_BLOCK_SIZE, NO_MOVE);
        /* move rightward */
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            move(MOVE_RIGHTWARD * TILE_BLOCK_SIZE, NO_MOVE);
        /* move upward */
        } else if (input.isKeyPressed((Input.KEY_UP))) {
            move(NO_MOVE,MOVE_UPWARD * TILE_BLOCK_SIZE);
        /* move downward */
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            move(NO_MOVE, MOVE_DOWNWARD * TILE_BLOCK_SIZE);
        }
    }

    /** render Player as well as its remaining lives */
    @Override
    public void render() {
    	for (int i = 0; i < this.lives; i++) {
    		this.lifeImage.drawCentered(LIFE_XY[0] + i * LIFE_SEPARATION, LIFE_XY[1]);
    	}

    	super.render();
    }
    
    /* ************************* implement Movable ************************* */
    /**
     * change player's coordinates
     * @param dx number of pixels to be moved in x-axis
     * @param dy number of pixels to be moved in y-axis
     * */
    @Override
    public void move(float dx, float dy) {
    	/* player makes a move when it is still in screen */
        if (moveInWorld(this.getX() + dx, this.getY() + dy)) {
            this.setX(this.getX() + dx);
            this.setY(this.getY() + dy);
        }
    }

    /* ********************* implement NextTileMovable ********************* */
    /**
     * check whether Player's target tile is in Game World (screen) and won't contact solid tiles
     * @param toX Sprite's target x coordinate
     * @param toY Sprite's target y coordinate
     * @return true if player's movable area contains target tilePosition
     * */
    @Override
    public boolean moveInWorld(float toX, float toY) {
        /* the target tile */
        BoundingBox target = new BoundingBox(this.getImage(), toX, toY);

        /* check contact solid tiles */
        for (Tile t:World.getLevelSolidTileList(this.getClass())) {
            if (target.intersects(t.getBoundingBox(), Sprite.EDGES_EXCLUSIVE)) {
                return CONTACT_SOLID_TILES;
            }
        }

        /* check if out side of screen */
        return !World.isOutOfWorld(target);
    }

    /* ************************ getters and setters ************************ */
    /** increase player's lives by 1 */
    public void gainLife() {
        this.lives ++;
    }

    /** decrease player's lives by 1 and terminate game if player run out of lives */
    public void loseLife() {
    	this.lives --;


    }

    /**
     * return true if player run out of lives
     * return true if game cannot continuous
     * */
    public boolean isDead() {
    	return this.lives < NO_LIFE;
    }

    /** reset player's xy-coordinates to its initial position */
    public void resetPosition() {
        this.setX(PLAYER_START_XY[0]);
        this.setY(PLAYER_START_XY[1]);
    }

    /** set player on rideable object */
    public void setOnRideableObject() {
        this.isOnRideableObject = ON_RIDEABLE_OBJECT;
    }

    /** set player not on rideable object */
    public void resetStatus() {
        this.isOnRideableObject = NOT_ON_RIDEABLE_OBJECT;
    }

    /** player has made one completion */
    public void increaseLevelProgress() {
    	this.levelProgress ++;
    }

    /**
     * return the number of safe frog player has made
     * @return the number of frogs in holes
     * */
    public int getLevelProgress() {
    	return this.levelProgress;
    }

    /** start a new level for player */
    public void resetLevelProgress() {
    	this.levelProgress = NO_PROGRESS;
    }

    /**
     * return true if player intersects a rideableObject
     * @return player.isOnRideableObject
     * */
    public boolean isOnRideableObject() {
        return this.isOnRideableObject;
    }

    /**
     * return player's remaining lives
     * @return player.lives
     * */
    public int getLives() {
        return this.lives;
    }
}

/* java is fun! */