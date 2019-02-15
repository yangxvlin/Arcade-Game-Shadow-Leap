package utilities;

import org.newdawn.slick.Image;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-06 19:18
 * description: mutable 2D (x, y) coordinates class associate a mutable BoundingBox
 **/

public final class MutablePosition implements Position{
    /** (x, y) coordinates */
    private float x, y;
    /** a position associate a boundingBox */
    private BoundingBox boundingBox;

    /**
     * construct and assign attributes
     * @param x x-coordinate
     * @param y y-coordinate
     * */
    public MutablePosition(float x, float y, Image image) {
        this.x = x;
        this.y = y;
        boundingBox = new BoundingBox(image, x, y);
    }

    /**
     * construct and assign attributes
     * @param position new Position to copy
     * */
    public MutablePosition(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.boundingBox = position.getBoundingBox();
    }

    /* ************************ getters and setters ************************ */
    /**
     * return x-coordinate
     * @return the value of x-coordinate
     * */
    @Override
    public float getX() {
        return x;
    }

    /**
     * return y-coordinate
     * @return the value of y-coordinate
     * */
    @Override
    public float getY() {
        return y;
    }

    /**
     * modify x-coordinate
     * @param x new x-coordinate
     * */
    @Override
    public void setX(float x) {
        this.x = x;
        this.boundingBox.setX(x);
    }

    /**
     * modify y-coordinate
     * @param y new y-coordinate
     * */
    @Override
    public void setY(float y) {
        this.y = y;
        this.boundingBox.setY(y);
    }

    /**
     * return the copy of BoundingBox
     * @return the copy of BoundingBox
     * */
    @Override
    public BoundingBox getBoundingBox(){
        return this.boundingBox.copyOf();
    }


    /**
     * return the copy of Position
     * @return the copy of Position
     * */
    @Override
    public Position copyOf() {
        return new MutablePosition(this);
    }

    /**
     * set the x-coordinate of the BoundingBox
     * @param x new x-coordinate
     * */
    @Override
    public void setBoundingBoxX(float x) {
        this.boundingBox.setX(x);
    }

    /**
     * set the y-coordinate of the BoundingBox
     * @param y new y-coordinate
     * */
    @Override
    public void setBoundingBoxY(float y) {
        this.boundingBox.setY(y);
    }
}

/* java is fun! */