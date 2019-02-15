package utilities;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-08 23:30
 * description: Position interface
 **/

public interface Position {
    /**
     * return x-coordinate
     * @return the value of x-coordinate
     * */
    float getX();

    /**
     * return y-coordinate
     * @return the value of y-coordinate
     * */
    float getY();

    /**
     * modify x-coordinate
     * @param x new x-coordinate
     * */
    void setX(float x);

    /**
     * modify y-coordinate
     * @param y new y-coordinate
     * */
    void setY(float y);

    /**
     * return the copy of BoundingBox
     * @return the copy of BoundingBox
     * */
    BoundingBox getBoundingBox();

    /**
     * return the copy of Position
     * @return the copy of Position
     * */
    Position copyOf();

    /**
     * set the x-coordinate of the BoundingBox
     * @param x new x-coordinate
     * */
    void setBoundingBoxX(float x);

    /**
     * set the y-coordinate of the BoundingBox
     * @param y new y-coordinate
     * */
    void setBoundingBoxY(float y);
}

/* java is fun! */