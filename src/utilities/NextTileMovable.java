package utilities;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-08-29 16:44
 * description: interface represent an object can move in to next tile (48 *
 *              48) pixels
 **/

public interface NextTileMovable extends Movable {
    /**
     * check whether cur tile'sWorld contains its target tilePosition
     * @param toX Sprite's target x coordinate
     * @param toY Sprite's target y coordinate
     * @return true if cur tile's movable area(World) contains target tilePosition
     * */
    boolean moveInWorld(float toX, float toY);
}

/* java is fun! */