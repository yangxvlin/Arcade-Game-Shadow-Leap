/**
 * Sample Project for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {
    /** screen width, in pixels */
    public static final int SCREEN_WIDTH  = 1024;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;
    /** screen left starts */
    public static final int SCREEN_LEFT   = 0;
    /** x-coordinate of center of screen */
    public static final float SCREEN_CENTER_X = (float)SCREEN_WIDTH / 2;
    /** y-coordinate of center of screen */
    public static final float SCREEN_CENTER_Y = (float)SCREEN_HEIGHT / 2;

    /** game world instance */
    private World world;

    /** initialize an app */
    public App() {
        super("Shadow Leap");
    }

    /** initialize the game
     * @param gc The Slick game container object.
     * */
    @Override
    public void init(GameContainer gc)
            throws SlickException {
        world = World.getInstance(this.getClass());
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        world.update(input, delta);
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        world.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
//        app.setShowFPS(true);
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}