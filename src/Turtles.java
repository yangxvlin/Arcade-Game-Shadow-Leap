import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.MyTimer;

import java.util.concurrent.TimeUnit;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-08 22:01
 * description: represent concrete turtles tile
 **/

public final class Turtles extends RideableObject {
    /** image paths for Turtles */
    public static final String TURTLES_IMAGE_PATH = "assets/turtles.png";
    /** all Turtles have constant speed 0.085 pixels per ms */
    public static final float TURTLES_SPEED       = 0.085f;
    /** turtles can be displayed cause it is on water */
    public static final boolean ON_WATER          = true;
    /** turtles can't be displayed cause it is under water */
    public static final boolean UNDER_WATER       = false;
    /** one set of turtles has 7 seconds time on water */
    public static final long ON_WATER_TIME        = TimeUnit.SECONDS.toMillis(7);
    /** one set of turtles has 2 seconds time under water after it is on water */
    public static final long UNDER_WATER_TIME     = TimeUnit.SECONDS.toMillis(2);

    /** indicate whether the set of turtles is on water */
    private boolean isOnWater;
    /** set timed action timer for on water 7s and under water 2s */
    private MyTimer displayTimer;

    /**
     * construct and assign attributes
     * @param imageSrc image path
     * @param x        x-coordinate for Turtles tile
     * @param y        y-coordinate for Turtles tile
     * @param isRight  indicates sprite is moving right
     * */
    public Turtles(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y, isRight);
        /* turtles are initially on water */
        this.isOnWater = ON_WATER;
        this.displayTimer= new MyTimer(ON_WATER_TIME);
    }

    /**
     * update the position and timed action of one set of turtles
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta, TURTLES_SPEED);

        this.displayTimer.updateTimer(delta);

        /* turtles timed action */
        if (this.displayTimer.isAlarmed()) {
        	/* turtles start the under water 2s phase */
            if (isOnWater) {
                this.setUnderWater();
                this.displayTimer.setTimer(UNDER_WATER_TIME);
            /* turtles start the above water 7s phase */
            } else {
                this.setOnWater();
                this.displayTimer.setTimer(ON_WATER_TIME);
            }
        }
    }

    /** render the set of turtles when it is in on water 7s */
    @Override
    public void render() {
        if (this.isOnWater) {
            super.render();
        }
    }

    /* ******************** implement PlayerInteractable ******************** */
    /** perform the action that the turtles can shift Player and prevent from the danger of drown in water */
    @Override
    public void interactPlayer() {
        Player player = World.getPlayer(this.getClass());

        /* shift player when it is on water */
        if (this.isOnWater) {
            super.shift(player, TURTLES_SPEED);
        }
    }

    /* ************************ getters and setters ************************ */
    /** set turtles to on water phase */
    private void setOnWater() {
        this.isOnWater = ON_WATER;
    }

    /** set turtles to under water phase */
    private void setUnderWater() {
        this.isOnWater = UNDER_WATER;
    }
}

/* java is fun! */