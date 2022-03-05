
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Tomer .
 * The AnimationRunner class is in charge of running the animation loops of the game.
 */
public class AnimationRunner {

    //Represents 1 second in millisecond units.
    static final int MILLISECONDS = 1000;

    //The GUI in which the game is animated and displayed on the screen.
    private GUI gui;

    //The number of frames that shown per second by the animation.
    private int framesPerSecond;

    //The sleeper that will be used by the animation.
    private Sleeper sleeper;

    /**
     * A constructor method of AnimationRunner class. It creates a new instance of animation runner.
     *
     * @param gui             - the GUI in which the game is animated and displayed on the screen.
     * @param framesPerSecond - the number of frames that shown per second by the animation.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * A getter method for the gui field of the animation runner.
     *
     * @return the gui of the animation runner.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * The method runs a animation loop to create an animation.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = MILLISECONDS / this.framesPerSecond;

        //Each iteration of the loop will draw the current animation frame.
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            //The animation frame is drawn and shown.
            animation.doOneFrame(d);
            gui.show(d);

            //We subtract the time it takes to do the animation work from the sleep time of the animation.
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
