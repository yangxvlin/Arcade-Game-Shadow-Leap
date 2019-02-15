import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-17 19:39
 * description: some useful tools
 **/

public final class Tools {
    /**
     * return a random int belongs to [min, max]
     * @return a random integer in specified range
     * */
    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /** terminates game */
    public static void endGame() {
        System.exit(0);
    }

    /**
     * add a value to the map's specific key's ArrayList
     * @param map   map to store values
     * @param key   key of the value
     * @param value data to store
     * */
    public static <K, V> void addToHashMap(HashMap<K, ArrayList<V>> map, K key, V value) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(value);
    }
}

/* java is fun! */