/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-9-26 14:59:08
 * description: define the action that a Player can move along with the object
 **/

public interface Shiftable {
    /**
     * make the Player move along with the object
     * @param player    the Player that move along with the object
     * @param tileSpeed the speed that the object is moving
     * */
    void shift(Player player, float tileSpeed);
}
