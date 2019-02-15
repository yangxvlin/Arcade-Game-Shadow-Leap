package utilities;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @date 2018-08-25 22:39
 * @description interface represent an object can move
 **/

@FunctionalInterface
public interface Movable {
    /**
     * change object's coordinates
     * @param dx number of pixels to be moved in x-axis
     * @param dy number of pixels to be moved in y-axis
     * */
    void move(float dx, float dy);
}

/* java is fun! */