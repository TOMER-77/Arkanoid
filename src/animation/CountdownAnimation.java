
package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import spritesandcollidables.SpriteCollection;

/**
 * @author Tomer .
 * The class represents a countdown which is shown before the beginning of each level.
 */
public class CountdownAnimation implements Animation {


    //The size of the text.
    static final int TEXT_SIZE = 45;


    //Represents 1 second in millisecond units.
    static final int MILLISECONDS = 1000;

    //The height of the gui window of the game.
    private int height;

    //The width of the gui window of the game.
    private int width;

    //The seconds that remained for the countdown.
    private int remainingTime;

    //The time each number in the countdown will be shown on the screen.
    private double timeForDigit;

    //The sprites that should be animated in the background of the countdown.
    private SpriteCollection gameScreen;

    //Tells the animation if it should run or stop running.
    private boolean running;

    /**
     * A constructor method of CountdownAnimation class. It creates a new instance of a countdown animation.
     *
     * @param numOfSeconds - the number of seconds that the animation should be shown.
     * @param countFrom    - the number the countdown starts from.
     * @param gameScreen   - the sprites that should be animated in the background of the countdown.
     * @param height       - the height of the gui window of the game.
     * @param width        - the width of the gui window of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, int height, int width) {
        this.gameScreen = gameScreen;
        this.height = height;
        this.width = width;
        this.timeForDigit = numOfSeconds / countFrom;
        this.remainingTime = countFrom;
        this.running = true;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        //If the remaining time for the animation has ended we let the program know that it should stop running.
        if (remainingTime == 0) {
            running = false;
        }
        biuoop.Sleeper sleeper = new Sleeper();

        //Drawing the balls, blocks and paddle in the background.
        gameScreen.drawAllOn(d);

        //Writing countdown itself.
        d.setColor(java.awt.Color.RED);
        d.drawText(width - TEXT_SIZE / 3, height, "" + remainingTime, TEXT_SIZE);

        //Letting the program sleep for the time between each change of digit in the countdown.
        long sleeperTime = (long) (MILLISECONDS * timeForDigit);
        sleeper.sleepFor(sleeperTime);

        //The remaining seconds left for the countdown is reduced by one.
        remainingTime--;
    }
}
