import org.newdawn.slick.SlickException;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-11 21:37
 * description: simple factory pattern for Tile
 **/

public final class TileFactory{
    /** key for Grass Tile */
    public static final String GRASS = "grass";
    /** key for Water Tile */
    public static final String WATER = "water";
    /** key for Bus Tile */
    public static final String BUS = "bus";
    /** key for Tree Tile */
    public static final String TREE = "tree";
    /** key for Bulldozer Tile */
    public static final String BULLDOZER = "bulldozer";
    /** key for ShortLog Tile */
    public static final String SHORTLOG = "log";
    /** key for LongLog Tile */
    public static final String LONGLOG = "longlog";
    /** key for Bike Tile */
    public static final String BIKE = "bike";
    /** key for RaceCar Tile */
    public static final String RACECAR = "racecar";
    /** key for Turtle Tile */
    public static final String TURTLE = "turtle";
    /** key for CompletedPlayer Tile */
    public static final String COMPLETED_PLAYER = CompletedPlayer.class.getName().toLowerCase();
    /** key for ExtraLife Tile */
    public static final String EXTRA_LIFE = ExtraLife.class.getName().toLowerCase();

    /**
     * create specific key according the input parameter
     * @param level      place where tiles are stored
     * @param parameters String[4] or String[3]
     *                    0: Tile Type
     *                    1: x-coordinate for Tile
     *                    2: y-coordinate for Tile
     *                    3: Tile's direction
     * */
    public static void createTile(Level level, String[] parameters) throws SlickException {
        switch (parameters[0]) {
            case BUS:
                level.addTile(BUS, new Bus(Bus.BUS_IMAGE_PATH, toInt(parameters[1]), toInt(parameters[2]), toBoolean(parameters[3])));
                return;
            case BULLDOZER:
                level.addTile(BULLDOZER, new Bulldozer(Bulldozer.BULLDOZER_IMAGE_PATH, toInt(parameters[1]), toInt(parameters[2]), toBoolean(parameters[3])));
                return;
            case SHORTLOG:
                level.addTile(SHORTLOG, new ShortLog(ShortLog.SHORTLOG_IMAGE_PATH, toInt(parameters[1]), toInt(parameters[2]), toBoolean(parameters[3])));
                return;
            case LONGLOG:
                level.addTile(LONGLOG, new LongLog(LongLog.LONGLOG_IMAGE_PATH, toInt(parameters[1]), toInt(parameters[2]), toBoolean(parameters[3])));
                return;
            case BIKE:
                level.addTile(BIKE, new Bike(Bike.BIKE_IMAGE_PATH, toInt(parameters[1]), toInt(parameters[2]), toBoolean(parameters[3])));
                return;
            case RACECAR:
                level.addTile(RACECAR, new RaceCar(RaceCar.RACECAR_IMAGE_PATH, toInt(parameters[1]), toInt(parameters[2]), toBoolean(parameters[3])));
                return;
            case TURTLE:
                level.addTile(TURTLE, new Turtles(Turtles.TURTLES_IMAGE_PATH, toInt(parameters[1]), toInt(parameters[2]), toBoolean(parameters[3])));
                return;
            case GRASS:
                level.addTile(GRASS, new Grass(Grass.GRASS_IMAGE_PATH, toInt(parameters[1]), toInt(parameters[2])));
                return;
            case WATER:
                level.addTile(WATER, new Water(Water.WATER_IMAGE_PATH, toInt(parameters[1]), toInt(parameters[2])));
                return;
            case TREE:
                level.addTile(TREE, new Tree(Tree.TREE_IMAGE_PATH, toInt(parameters[1]), toInt(parameters[2])));
                return;
            default:
                return;
        }
    }

    /**
     * convert a string of integer to int
     * @return int value of a variable that is stored in String
     * */
    private static int toInt(String s){
        return Integer.parseInt(s);
    }

    /**
     * convert a string of boolean to boolean
     * @return boolean value of a variable that is stored in String
     * */
    private static boolean toBoolean(String s) {
        return Boolean.parseBoolean(s);
    }
}

/* java is fun! */