
package animation;

import biuoop.DrawSurface;
import listeners.Counter;

/**
 * @author Tomer .
 * An animation screen which is shown when the player loses the game.
 */
public class GameOverScreen implements Animation {


    //The size of the text.
    static final int TEXT_SIZE = 45;

    //The current score of the game.
    private Counter score;

    /**
     * A constructor method of GameOverScreen class. It creates a new instance of a game over screen animation.
     *
     * @param score - the score at the current point in the game.
     */
    public GameOverScreen(Counter score) {
        this.score = score;
    }


    @Override
    public void doOneFrame(DrawSurface d) {

        //Drawing the background of the screen.
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Writing the corresponding text message with the score of the player.
        d.setColor(java.awt.Color.RED);
        d.drawText(d.getWidth() / 8, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue()
                + ".", TEXT_SIZE);
        d.drawText(d.getWidth() / 8, d.getHeight() / 2 + TEXT_SIZE, "Press space to exit...",
                TEXT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
