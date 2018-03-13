package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This class uses your Gate class to fill the corral with snails.  We
 * have supplied you will the code necessary to execute as an app.
 * You must fill in the missing logic below.
 */
public class FillTheCorral {
    /**
     * Reference to the OutputInterface.
     */
    private OutputInterface mOut;

    /**
     * Constructor initializes the field.
     */
    FillTheCorral(OutputInterface out) {
        mOut = out;
    }

    /**
     * Initial corral swing status
     * @param gate corral
     * @param selectDirection direction
     */
    public void setCorralGates(Gate[] gate, Random selectDirection) {

        for (int i = 0; i < gate.length; i++) {
            // randomly direction
            gate[i].setSwing(selectDirection.nextInt(3) - 1);
        }
    }

    /**
     * Check corral available least one gate
     * @param corral gate
     * @return least once gate
     */
    public boolean anyCorralAvailable(Gate[] corral) {

        for (int i = 0; i < corral.length; i++) {

            // check status gate is IN
            if (corral[i].getSwingDirection() == Gate.IN) {
                return true;
            }
        }

        return false;
    }

    /**
     * Simulate corral snail
     * @param corral gate
     * @param rand random object
     * @return attempts
     */
    public int corralSnails(Gate[] corral, Random rand) {

        // display initial gate setup
        displayInitialGateSetup(corral);

        // initial snail in the pasture
        int pasture = 5;

        // initial attempts
        int attempts = 0;

        do {
            // randomly gate
            int selectGate = rand.nextInt(corral.length);

            // select current gate
            Gate currentGate = corral[selectGate];

            // randomly snails into the pen
            int snails = rand.nextInt(pasture) + 1;

            // snails into the pen and update number snail in the pasture
            pasture -= currentGate.thru(snails);

            // update attempts
            attempts++;

            // display attempts status
            mOut.println(snails + " are trying to move through corral " + selectGate);

        } while (pasture > 0); // check number snail in pasture is empty

        // display attempts
        mOut.println("It took " + attempts + " attempts to corral all of the snails.");

        // return number attempts
        return attempts;
    }

    /**
     * Display the initial gate setup
     *
     * @param corral gates
     */
    private void displayInitialGateSetup(Gate[] corral) {

        // display gate initial info
        mOut.println("Initial gate setup:");

        // loop for each corral status
        for (int i = 0; i < corral.length; i++) {

            if (corral[i].getSwingDirection() == Gate.IN) {
                mOut.println("Gate " + i + ": This gate is open and swings to open the pen only");
            } else if (corral[i].getSwingDirection() == Gate.OUT) {
                mOut.println("Gate " + i + ": This gate is open and swings to exit the pen only");
            } else {
                mOut.println("Gate " + i + ": This gate is closed");
            }
        }
    }
}
