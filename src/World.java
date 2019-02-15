import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @date 2018-08-25 18:31
 * @description concrete class World specify every thing in Game World (screen)
 *                Singleton pattern applied
 *
 *                Although singleton pattern might have the drawback that make
 *                all tiles' data in game global. But I can solve this by adding
 *                a condition to only return the data when the class calling getter
 *                in World is the class we want to call.
 *
 *                Although Input input is only need in Player's update(), in future,
 *                we might need Input input in future in other tile's leaf class.
 *                For example, me press 's' to destroy all vehicles. 'k' to stop
 *                all RideableObject for 2 seconds.
 **/

public final class World {
    /** directory of level files */
    public static final String LEVEL_DIR = "assets/levels/";
    /** file type of level file */
    public static final String FILE_TYPE = ".lvl";
    /** class that can get Solid Tiles from singleton world */
    private static final Class<Player> SOLID_TILE_LIST_ACCESSIBLE_CLASS = Player.class;
    /** class that can get Log Tiles from singleton world */
    private static final Class<ExtraLife> LOG_TILE_LIST_ACCESSIBLE_CLASS = ExtraLife.class;
    /** classes that can get Player Tile from singleton world */
    private static final Class<?>[] PLAYER_TILE_ACCESSIBLE_CLASSES = new Class[]{Bike.class,
            Bulldozer.class, Bus.class, CompletedPlayer.class, ExtraLife.class,
            KnockDownableVehicle.class, LongLog.class, RaceCar.class, ShortLog.class,
            Turtles.class, Water.class, Level.class};
    /** classes that can get singleton world instance */
    private static final Class<?>[] WORLD_INSTANCE_ACCESSIBLE_CLASSES = new Class[] {
    	World.class, App.class};
    
    /** Player tile */
	private Player player;
    /** the index of level */
    private int curLevelIndex = Level.GAME_INITIAL_LEVEL;
    /** world associate a level */
    private Level curLevel;

    /** Game World(screen) boundingBox used to check whether tiles are out of screen */
    public static final BoundingBox SCREEN = new BoundingBox(App.SCREEN_CENTER_X, App.SCREEN_CENTER_Y,
                                                             App.SCREEN_WIDTH,    App.SCREEN_HEIGHT);

    /* ************************ implement Singleton ************************ */
    /** Singleton World */
    private static World world;

    /**
     * return the Singleton world
     * @return	the singleton World instance we are access this method
     *          from World or App class else return null
     * */
    public static <T> World getInstance(Class<T> c){
        if (world == null) {
            try {
                world = new World();
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        
        if (Arrays.asList(WORLD_INSTANCE_ACCESSIBLE_CLASSES).contains(c)) {
        	return world;
        } else {
        	return null;
        }
    }

    /** construct World instance */
    private World() throws SlickException{
        /* initialize one player */
        this.player = new Player(Player.PLAYER_IMAGE_PATH,
                                 Player.PLAYER_START_XY[0],
                                 Player.PLAYER_START_XY[1]);
        /* load first level */
        this.newLevel();
	}

    /* ********************************************************************** */
    /**
     * update status of all tiles in Game World (screen)
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
	public void update(Input input, int delta) throws SlickException {
		/* terminate Game */
        if (player.isDead()) {
            Tools.endGame();
        }

        /* player's Move update */
        player.update(input, delta);

        /* update all tiles in the level */
        curLevel.update(input, delta);

        /* check whether player is drown in water and make tiles interact with player */
        player.resetStatus();
        curLevel.interactWithPlayer();

        /* if curLevel completed load next level or terminate if no more level */
        if (player.getLevelProgress() == Level.TOTAL_FROG_TO_COMPLETE) {
            curLevelIndex ++;

            this.newLevel();
        }
	}

	/**
     * render all tiles in game
     * @param g The Slick graphics object, used for drawing.
     * */
	public void render(Graphics g) {
        curLevel.render();

        this.player.render();
    }

    /* ************************* helper function ************************* */
    /**
     * check whether tile is outside of screen
     * @param tile the tile to check with
     * @return true if the tile's BoundingBox has any part outside of the screen
     * */
    public static boolean isOutOfWorld(Tile tile) {
        return !SCREEN.contains(tile.getBoundingBox(), Tile.EDGES_EXCLUSIVE);
    }

    /**
     * check whether a boundingBox is outside of screen
     * @param bb the tile's boundingBox to check with
     * @return true if the BoundingBox has any part outside of the screen
     * */
    public static boolean isOutOfWorld(BoundingBox bb) {
        return !SCREEN.contains(bb, Tile.EDGES_EXCLUSIVE);
    }

    /** load new level and reset player */
    private void newLevel() throws SlickException {
    	/* read next level */
        curLevel = ReadCsv.readCsv(LEVEL_DIR + curLevelIndex + FILE_TYPE);

        assert curLevel != null;

        /* reset player's game status */
        player.resetPosition();
        player.resetLevelProgress();
    }

    /* ************************ getters and setters ************************ */
    /**
     * return Tile<Solid>[] in level
     * @param <T> generic type for Class Object
     * @param c the Class Object of the class you are accessing this method
     * @return ArrayList<Tile> where Tile implements Solid if we are access this method
     *          from ExtraLife.class, otherwise a null is returned
     * */
    public static <T> ArrayList<Tile> getLevelSolidTileList(Class<T> c) {
        if (Objects.equals(c, SOLID_TILE_LIST_ACCESSIBLE_CLASS)) {
            return World.getInstance(World.class).curLevel.getSolidList();
        } else {
            return null;
        }
    }

    /**
     * return Log[] in level
     * @param <T> generic type for Class Object
     * @param c the Class Object of the class you are accessing this method
     * @return ArrayList<Log> where Tile are Log type if we are access this method
     *          from ExtraLife.class and Player.class, otherwise a null is returned
     * */
    public static <T> ArrayList<Log> getLevelLogList(Class<T> c) {
        if (Objects.equals(LOG_TILE_LIST_ACCESSIBLE_CLASS, c)) {
            return World.getInstance(World.class).curLevel.getLogList();
        } else {
            return null;
        }
    }

    /**
     * return player in singleton world
     * @param <T> generic type for Class Object
     * @param c the Class Object of the class you are accessing this method
     * @return Player in world if we are access this method from the class are
     *          PlayerInteractable, otherwise a null is returned
     * */
    public static <T> Player getPlayer(Class<T> c) {
        if (Arrays.asList(PLAYER_TILE_ACCESSIBLE_CLASSES).contains(c)) {
            return World.getInstance(World.class).player;
        } else {
            return null;
        }
    }
}

/* java is fun! */