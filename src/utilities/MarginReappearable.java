package utilities;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-09-17 17:04
 * description: define the action that an object can reappear if it is going to be outside if the bound
 **/

public interface MarginReappearable {
    /** reappear the object */
    void reappear();

    /**
     * if instance is moving left/right check whether it has any remain image
     * (right/left bound of its image) in bound
     * @param bound the bound that the object is moving in
     * */
    boolean isMovingInBound(BoundingBox bound);
}

/* java is fun! */