
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Tomer .
 * An animation screen which is shown when the player pauses the game.
 */
public class PauseScreen implements Animation {

    //The size of the text.
    static final int TEXT_SIZE = 32;

    //The keyboard which will be used by the class.
    private KeyboardSensor keyboard;

    //Holds a false value until the animation should be stopped.
    private boolean stop;

    /**
     * A constructor method of PauseScreen class. It creates a new instance of a pause screen animation.
     *
     * @param k - the keyboard which will be used by the class.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }


    @Override
    public void doOneFrame(DrawSurface d) {

        //Writing the text of the pause screen.
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, "paused -- press space to continue", TEXT_SIZE);
    }
}
