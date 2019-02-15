package utilities;

/**
 * @project SWEN20003: Object Oriented Software Development 2018 S1 project1
 * @author Xulin Yang, 904904
 * @create 2018-10-3 21:43:35
 * description: represent a clock and will give response if clock runs out of time
 **/

public class MyTimer{
    /** store the remained milliseconds in the clock */
    private long timer;
    
    /** indicate an empty clock */
    public static final long NO_TIME = 0;

    /**
     * create a clock with specified time
     * @param time the remained time in the clock
     * */
    public MyTimer(long time) {
        this.timer = time;
    }
    
    /** create an empty clock */
    public MyTimer() {
        this.timer = NO_TIME;
    }

    /**
     * decrease the remained time in the clock with specified time passed
     * @param delta the time has passed
     * */
    public void updateTimer(int delta) {
        this.timer -= delta;
    }

    /**
     * load a new time for the clock
     * @param newTime a new period time for the clock
     * */
    public void setTimer(long newTime) {
        this.timer += newTime;
    }

    /** clear all the remained time in the clock */
    public void clearTimer() {
        this.timer = NO_TIME;
    }

    /**
     * return True if the clock is alarmed
     * @return True if the clock is alarmed
     * */
    public boolean isAlarmed() {
        return this.timer <= NO_TIME;
    }
}

/* java is fun! */