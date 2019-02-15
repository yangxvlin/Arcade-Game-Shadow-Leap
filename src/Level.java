import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.PlayerInteractable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-11 22:09
 * description: level that stores all tiles
 **/

public final class Level {
    /** the tileKeys that its tile is stored in levels in the order of tiles to be rendered */
    public static final String[] TILE_LAYER = new String[]{TileFactory.GRASS,
												            TileFactory.WATER,
												            TileFactory.BUS,
												            TileFactory.TREE,
												            TileFactory.BULLDOZER,
												            TileFactory.SHORTLOG,
												            TileFactory.LONGLOG,
												            TileFactory.BIKE,
												            TileFactory.RACECAR,
												            TileFactory.TURTLE,
												            TileFactory.COMPLETED_PLAYER,
												            TileFactory.EXTRA_LIFE};
    /** initial level to start the game */
    public static final int GAME_INITIAL_LEVEL = 0;
    /** the number of frogs that the player has to made in order to pass one level */
    public static final int TOTAL_FROG_TO_COMPLETE = 5;
    /** initial number player's completion */
    public static final int NO_PROGRESS = 0;
    /** x-coordinates of 5 place player needs to move frog to */
    public static final int[] HOLE_X = new int[]{120, 312, 504, 696, 888};
    /** y-coordinate of 5 place player needs to move frog to */
    public static final int HOLE_Y = 48;
    /** extra life object's initial direction */
    public static final boolean EXTRALIFE_INITIAL_RIGHT = true;

    /** store Tiles based on their sub types.
     *  map: {key: String tileType, value: Tile[*]} */
    private HashMap<String, ArrayList<Tile>> tileMap = new HashMap<>();

    /** construct level */
    public Level() throws SlickException {
        /* create 5 place that player needs to complete */
        for (int x:HOLE_X) {
            this.addTile(TileFactory.COMPLETED_PLAYER, new CompletedPlayer(Player.PLAYER_IMAGE_PATH, x, HOLE_Y));
        }

        /* create extraLife object */
        this.addTile(TileFactory.EXTRA_LIFE, new ExtraLife(ExtraLife.EXTRALIVES_IMAGE_PATH, EXTRALIFE_INITIAL_RIGHT));
    }

    /**
     * update status of all tiles in the level
     * @param input the Slick game container object's input
     * @param delta number of milliseconds since the last frame is
     *               passed as the argument
     * */
    public void update(Input input, int delta) {
        tileMap.values().forEach(t -> t.forEach(i -> i.update(input, delta)));
    }

    /** render all tiles in level in order */
    public void render() {
        renderInLayer();
    }

    /* ************************* helper function ************************* */
    /**
     * render a list of Tile
     * @param tileList a list of tile to be rendered
     * */
    private static void renderTileList(ArrayList<Tile> tileList) {
    	if (tileList != null) {
    		tileList.forEach(Tile::render);
    	}
    }

    /** render all the tiles in array in order */
    private void renderInLayer() {
        for (String tileKey:TILE_LAYER) {
            renderTileList(tileMap.get(tileKey));
        }
    }

    /** filter out all PlayerInteractable tiles and perform interact action against player */
    public void interactWithPlayer() {
    	/* an easier version of this method but with less efficiency */
//        this.tileMap.values().forEach(t -> t.stream()
//                                            .filter(PlayerInteractable.class::isInstance)
//                                            .map(PlayerInteractable.class::cast)
//                                            .forEach(PlayerInteractable::interactPlayer));
        
    	/* rideableObject need to interact player before water */
    	for (int i = TILE_LAYER.length-1; i > 0; i--) {
    		Player player = World.getPlayer(this.getClass());
    	    int playerCurLives = player.getLives();

            ArrayList<Tile> list= this.tileMap.get(TILE_LAYER[i]);

            /* do nothing if list has no tiles */
            if (list != null) {
                for (Tile t:list) {
                	/* skip non playerInteractable tiles */
                    if (!(t instanceof PlayerInteractable)) {
                        break;
                    } else {
                        ((PlayerInteractable) t).interactPlayer();

                        /* no need to interact rest interactable tiles in cur tileType
                           once player lost its life */
                        if (player.getLives() != playerCurLives) {
                            break;
                        }
                    }
                }
            }

            /* no need to interact rest interactable tiles once player has lost its life */
            if (player.getLives() != playerCurLives) {
                break;
            }
        }
    }

    /* ************************ getters and setters ************************ */
    /**
     * add tile to specific key's value
     * @param tileKey the key of tile
     * @param tile    the value
     * */
    public void addTile(String tileKey, Tile tile) {
        Tools.addToHashMap(this.tileMap, tileKey, tile);
    }

    /**
     * return Log[] of the level
     * @return ArrayList<Log> where Tile are Log type
     * */
    public ArrayList<Log> getLogList() {
        return Stream.concat(tileMap.get(TileFactory.SHORTLOG).stream(),
                             tileMap.get(TileFactory.LONGLOG).stream())
                        .map(Log.class::cast)
                        .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * return Tile<Solid>[] in level
     * @return ArrayList<Tile> where Tile implements Solid
     * */
    public ArrayList<Tile> getSolidList() {
        /* Here might use .stream().filter() to filter out all Tile<Solid> and
         * .cast() and then return the list. In this case, we know Tree and
         * Bulldozer are Solid. We can directly get them from HashMap and concat
         * them with O(1) in stead of O(n) */
        return Stream.concat(tileMap.get(TileFactory.BULLDOZER).stream(),
                             tileMap.get(TileFactory.TREE).stream())
                        .map(Tile.class::cast)
                        .collect(Collectors.toCollection(ArrayList::new));
    }
}

/* java is fun! */