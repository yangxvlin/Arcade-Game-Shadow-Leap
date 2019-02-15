import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-08 22:01
 * description: represent concrete bike tile
 **/

public final class Bike extends KnockDownableVehicle {
    /** image paths for Bike */
    public static final String BIKE_IMAGE_PATH = "assets/bike.png";
    /** all bikes have constant speed 0.2 pixels per ms */
    public static final float BIKE_SPEED       = 0.2f;

    /** the bound for bike's x-coordinate [24, 1000] to reverse its direction when it is out side of it */
    public static final BoundingBox BIKE_BOUND = new BoundingBox(App.SCREEN_CENTER_X,                App.SCREEN_CENTER_Y,
                                                                 App.SCREEN_WIDTH - TILE_BLOCK_SIZE * 2, App.SCREEN_HEIGHT);

    /**
     * construct bike
     * @param imageSrc image path
     * @param x        x-coordinate for bike tile
     * @param y        y-coordinate for bike tile
     * @param isRight  indicates sprite is moving right
     * */
    public Bike(String imageSrc, float x, float y, boolean isRight) throws SlickException {
        super(imageSrc, x, y, isRight);
    }

    /**
     * update the position of one bike with its speed
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta, BIKE_SPEED);
    }

    /* ************************* implement Movable ************************* */
    /**
     * change bike's coordinates and move to opposite direction if it is out of BIKE_BOUND
     * @param dx number of pixels to be moved in x-axis
     * @param dy number of pixels to be moved in y-axis
     * */
    @Override
    public void move(float dx, float dy) {

        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);

        reappear();
    }

    /* ******************* implement MarginReappearable ******************* */
    /** bike's reappear is to change bike's direction if it is outside of the bound */
    @Override
    public void reappear() {
        /* Can Override at here because Bike's reappear is different from Bus, RaceCar
         * and Bulldozer. And might be Override in future update which is unlike the
         * knockDown() case. */
        if (!this.isMovingInBound(BIKE_BOUND)) {
            super.alternateDirection();
        }
    }
}

/* java is fun! */