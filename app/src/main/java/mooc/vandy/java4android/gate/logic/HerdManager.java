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
    private static final int MAX_ITERATIONS = 10;

    public static final int HERD = 24;

    /**
     * Constructor initializes the fields.
     */
    public HerdManager(OutputInterface out,
                       Gate westGate,
                       Gate eastGate) {
        mOut = out;

        // initial west gate swing
        mWestGate = westGate;
        mWestGate.open(Gate.IN);

        // initial east gate swing
        mEastGate = eastGate;
        mEastGate.open(Gate.OUT);
    }

    /**
     * Simulation snail into or out to the pen
     * @param rand random object
     */
    public void simulateHerd(Random rand) {

        // initial number of snail in pen
        int pen = HERD;

        // initial number of snail in pasture
        int pasture = HERD - pen;

        // display configuration status
        displayResult(pen);

        // simulation with maximum iterations
        for (int i = 0; i < MAX_ITERATIONS; i++) {

            if (pen == 0) { // check snail in pen is empty
                // randomly snail into pen
                pen += mWestGate.thru(rand.nextInt(pasture) + 1);
            } else if (pasture == 0) { // check snail in paster is empty
                // randomly snail into pasture
                pen += mEastGate.thru(rand.nextInt(pen) + 1);
            } else if (rand.nextBoolean()) { // randomly select gate
                // randomly snail into pasture
                pen += mEastGate.thru(rand.nextInt(pen) + 1);
            } else {
                // randomly snail into pen
                pen += mWestGate.thru(rand.nextInt(pasture) + 1);
            }

            // update snail in the pasture
            pasture = HERD - pen;

            // display status result
            displayResult(pen);
        }
    }

    /**
     * Display result status
     * @param pen snails in the pen
     */
    public void displayResult(int pen) {
        mOut.println("There are currently " + pen + " snails in the pen and " +
                (HERD - pen) + " snails in the pasture");
    }
}
