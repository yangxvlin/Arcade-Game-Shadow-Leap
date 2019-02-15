/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-9-26 14:59:08
 * description: define the action that an object can be dangerous to Player
 **/

public interface KnockDownable {
    /**
     * perform the action that decrease the player's life
     * @param player the Player to be knocked down
     * */
    void knockDown(Player player);
}
