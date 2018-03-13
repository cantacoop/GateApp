package mooc.vandy.java4android.gate.logic;

/**
 * This file defines the Gate class.
 */
public class Gate {

    public static final int IN = 1;
    public static final int OUT = -1;
    public static final int CLOSED = 0;

    private int snails;

    // gate direction
    private int mSwing;

    /**
     * default constructor
     */
    public Gate() {

        // set default swing to closed
        mSwing = CLOSED;
    }

    /**
     * set swing direction
     * @param direction swing in or out our closed
     * @return is set success
     */
    public boolean setSwing(int direction) {

        // check direction
        if (direction == IN || direction == OUT || direction == CLOSED) {

            mSwing = direction;
            return true;
        } else {
            return false;
        }
    }

    /**
     * check swing is not closed
     * @param direction swing
     * @return swing is not closed
     */
    public boolean open(int direction) {

        if (direction == IN || direction == OUT) {
            setSwing(direction);
            return true;
        } else {
            return false;
        }
    }

    /**
     * set swing is closed
     */
    public void close() {
        setSwing(CLOSED);
    }

    /**
     * get swing direction
     * @return
     */
    public int getSwingDirection() {

        return mSwing;
    }

    /**
     * check increase or decrease snails is through
     * @param count number of snails
     * @return increase or decrease snail
     */
    public int thru(int count) {

        if (getSwingDirection() == IN) {
            snails++;
            return count;
        } else if (getSwingDirection() == OUT) {
            snails--;
            return -count;
        } else {
            return 0;
        }
    }

    /**
     * string for information the gate swing status
     * @return string
     */
    public String toString() {

        // get swing direction
        switch (getSwingDirection()) {
            case CLOSED:
                // a gate that is closed
                return "This gate is closed";
            case IN:
                // a gate that has opened to swing IN
                return "This gate is open and swings to enter the pen only";
            case OUT:
                // a gate that been opened swing OUT
                return "This gate is open and swings to exit the pen only";
            default:
                // a gate that has a swing value other than IN, OUT, or CLOSED
                return "This gate has an invalid swing direction";
        }
    }

    public int getSnails() {
        return snails;
    }
}
