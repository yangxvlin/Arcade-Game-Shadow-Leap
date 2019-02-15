import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;
import utilities.MutablePosition;
import utilities.MyTimer;
import utilities.NextTileMovable;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-12 20:58
 * description: represent concrete ExtraLife tile
 **/

public final class ExtraLife extends DirectedSprite implements NextTileMovable {
    /** image paths for ExtraLife */
    public static final String EXTRALIVES_IMAGE_PATH = "assets/extralife.png";
    /** extra life appears at most per 35s */
    public static final int MAX_REAPPEARANCE_TIME    = 35;
    /** extra life appears at least per 25s */
    public static final int MIN_REAPPEARANCE_TIME    = 25;
    /** a random frequency for extra life to appear */
    private long randomReappearanceTime;
    /** extra life disappears at 14s after it appears */
    public static final long EXTRALIFE_EXIST_TIME    = TimeUnit.SECONDS.toMillis(14);
    /** extra life moves per 2s after it appears */
    public static final long ONE_MOVE_TIME           = TimeUnit.SECONDS.toMillis(2);
    /** extra life is not available to interact with player */
    public static final boolean UNAVAILABLE          = false;
    /** extra life is available to interact with player */
    public static final boolean AVAILABLE            = true;
    /** extra life automatically moves to right after associated with a log */
    public static final boolean INITIAL_RIGHT        = true;
    /** extra life initially appears at center of log */
    public static final float LOG_CENTER             = 0.0f;

    /** the log associated with extra life for it to move on */
    private Log associateLog;
    /** indicate the extra life can interact with player */
    private boolean isAvailable;    
    /** the timer alternate between random(25s, 35s) and 14s */
    private MyTimer displayTimer;
    /** timer for move per 2s */
    private MyTimer moveTimer;

    /**
     * construct ExtraLife and assign attributes
     * @param imageSrc image path
     * @param isRight  indicates sprite is moving right
     * */
    public ExtraLife(String imageSrc, boolean isRight) throws SlickException {
        super(imageSrc, isRight);
        /* extraLife has an relative position w.r.t. the log */
        this.setPosition(new MutablePosition(LOG_CENTER, LOG_CENTER, this.getImage()));

        /* extraLife is not available initially */
        this.isAvailable = UNAVAILABLE;
        /* set random disappear time */
        this.setRandomReappearanceTime();
        /* initialize a disappear timer */
        this.displayTimer = new MyTimer(this.randomReappearanceTime);
        /* empty move timer */
        this.moveTimer = new MyTimer();
    }

    /** 
     * update the position and timed action of one extraLife
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *              passed as the argument */
    @Override
    public void update(Input input, int delta) {
        this.displayTimer.updateTimer(delta);

        if (this.displayTimer.isAlarmed()) {
            /* 14s(appear) phase end */
            if (this.isAvailable) {

                this.setUnAvailable();
                /* start a new disappear phase */
                this.setRandomReappearanceTime();
                this.displayTimer.setTimer(this.randomReappearanceTime);

            /* 25-35s(disappear) phase end */
            } else {
                /* start a new appear phase */
                this.setAvailable();
                this.displayTimer.setTimer(EXTRALIFE_EXIST_TIME);

                /* initialize the 2s move timer */
                this.moveTimer.clearTimer();
                this.moveTimer.setTimer(ONE_MOVE_TIME);

                /* assign new random associated log */
                ArrayList<Log> logList = World.getLevelLogList(this.getClass());
                this.registerLog(logList.get(Tools.randomInt(0, logList.size())));

                this.setDirection(INITIAL_RIGHT);
            }
        }

        /* one move per 2s and update its boundingBox */
        if (this.isAvailable) {
            this.moveTimer.updateTimer(delta);

            /* one move has made and start a new 2s wait timer for next move */
            if (this.moveTimer.isAlarmed()) {
                this.move(TILE_BLOCK_SIZE, NO_MOVE);
                this.moveTimer.setTimer(ONE_MOVE_TIME);
            }

            /* update boundingBox */
            this.setBoundingBoxX(associateLog.getX() + this.getX());
            this.setBoundingBoxY(associateLog.getY() + this.getY());
        }
    }

    /** draw extra life's image and update its boundingBox */
    @Override
    public void render() {
        /* only draw it when it is available */
        if (this.isAvailable) {
            assert associateLog != null;
        	assert this.getImage() != null;

        	/* extra life real position is the associated log's position + its relative position to log */
            this.getImage().drawCentered(associateLog.getX() + this.getX(), associateLog.getY() + this.getY());
        }
    }

    /* ******************** implement PlayerInteractable ******************** */
    /** player gain one more life when interacts with available extra life */
    @Override
    public void interactPlayer() {
        Player player = World.getPlayer(this.getClass());

        if (this.isAvailable && this.contactTile(player, Tile.EDGES_EXCLUSIVE)) {

            player.gainLife();

            /* player is not available after contact player */
            this.isAvailable = UNAVAILABLE;
        	/* start a new 25-35s(disappear) phase immediately */
            this.displayTimer.clearTimer();
            this.setRandomReappearanceTime();
            this.displayTimer.setTimer(this.randomReappearanceTime);
        }
    }

    /* ************************* implement Movable ************************* */
    /**
     * change player's coordinates
     * @param dx number of pixels to be moved in x-axis
     * @param dy number of pixels to be moved in y-axis
     * */
    @Override
    public void move(float dx, float dy) {
    	if (this.isAvailable) {
    	    /* if moves out of log alter direction */
            if (!moveInWorld(this.getX() + associateLog.getX() + this.getDirection() * dx,
                             this.getY() + associateLog.getY() + dy)) {
                this.alternateDirection();
            }

            /* update coordinates */
            this.setX(this.getX() + this.getDirection() * dx);
            this.setY(this.getY() + dy);
        }
    }

    /* ********************* implement NextTileMovable ********************* */
    /**
     * check whether extra life's target tile is in associated log
     * ExtraLife's world is its associated log
     * @param toX Sprite's target x coordinate
     * @param toY Sprite's target y coordinate
     * @return true if extraLife interacts its associated Log
     * */
    @Override
    public boolean moveInWorld(float toX, float toY) {
        /* the target tile */
        BoundingBox target = new BoundingBox(this.getImage(), toX, toY);

        return (associateLog.getBoundingBox().intersects(target, Sprite.EDGES_EXCLUSIVE));
    }

    /* ************************ getters and setters ************************ */
    /** set extra life can be interacted */
    private void setAvailable() {
        this.isAvailable = AVAILABLE;
    }

    /** set extra life can't be interacted */
    private void setUnAvailable() {
        this.isAvailable = UNAVAILABLE;
    }

    /**
     * associate a new log to extra life and set its position to center of log
     * @param log log that extra life to move on
     * */
    public void registerLog(Log log){
        this.associateLog = log;
        super.setPosition(new MutablePosition(LOG_CENTER, LOG_CENTER, this.getImage()));
    }
    
    /** assign a random int between [25, 35] for disappear phase time
     * +1 to make it inclusive */
    private void setRandomReappearanceTime() {
        randomReappearanceTime = TimeUnit.SECONDS.toMillis(Tools.randomInt(MIN_REAPPEARANCE_TIME, MAX_REAPPEARANCE_TIME + 1));
    }
}

/* java is fun! */