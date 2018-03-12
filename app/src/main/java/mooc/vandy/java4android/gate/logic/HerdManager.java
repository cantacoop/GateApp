package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This class uses your Gate class to manage a herd of snails.  We
 * have supplied you will the code necessary to execute as an app.
 * You must fill in the missing logic below.
 */
public class HerdManager {
    /**
     * Reference to the output.
     */
    private OutputInterface mOut;

    /**
     * The input Gate object.
     */
    private Gate mEastGate;

    /**
     * The output Gate object.
     */
    private Gate mWestGate;

    /**
     * Maximum number of iterations to run the simulation.
     */
    private static final int MAX_ITERATIONS = 20;

    public static final int HERD = 24;

    /**
     * Constructor initializes the fields.
     */
    public HerdManager(OutputInterface out,
                       Gate westGate,
                       Gate eastGate) {
        mOut = out;

        mWestGate = westGate;
        mWestGate.open(Gate.IN);

        mEastGate = eastGate;
        mEastGate.open(Gate.OUT);
    }

    public void simulateHerd(Random rand) {

        int snailInPen = HERD;
        int snailInPasture = 0;

        // set seed value for the random method
        rand.setSeed(24);

        for (int i = 0; i < MAX_ITERATIONS; i++) {

            if (snailInPen == 0) {
                break;
            }

            // randomly select a gate
            if (rand.nextBoolean()) { // West Gate
                snailInPen += mWestGate.thru(rand.nextInt(snailInPasture + 1));
            } else { // East Gate
                snailInPen += mEastGate.thru(rand.nextInt(snailInPen + 1));
            }

            snailInPasture = HERD - snailInPen;

            displayResult(snailInPen);
        }
    }

    public void displayResult(int snailInPen) {
        mOut.println("There currently " + snailInPen + " snails in the pen and " +
                (HERD - snailInPen) + " snails in the pasture");
    }
}
