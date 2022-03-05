
package animation;

import biuoop.DrawSurface;
import listeners.Counter;

/**
 * @author Tomer .
 * An animation screen which is shown when the player wins the game.
 */
public class YouWinScreen implements Animation {

    //The size of the text.
    static final int TEXT_SIZE = 45;

    //The current score of the game.
    private Counter score;

    /**
     * A constructor method of YouWinScreen class. It creates a new instance of winning screen animation.
     *
     * @param score - the score at the current point in the game.
     */
    public YouWinScreen(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        //Drawing the background of the screen.
        d.setColor(java.awt.Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Writing the corresponding text message with the score of the player.
        d.setColor(java.awt.Color.RED);
        d.drawText(d.getWidth() / 8, d.getHeight() / 2, "You Win! Your score is  " + this.score.getValue()
                        + ".", TEXT_SIZE);
        d.drawText(d.getWidth() / 8, d.getHeight() / 2 + TEXT_SIZE, "Press space to exit...",
                TEXT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
