
package spritesandcollidables;

import biuoop.DrawSurface;
import listeners.Counter;
import run.GameLevel;

/**
 * @author Tomer .
 * The ScoreIndicator class is in charge of displaying the score of the game.
 */
public class ScoreIndicator implements Sprite {

    //The color of the background of the score indicator.
    static final java.awt.Color COLOR = java.awt.Color.WHITE;

    //The initial x and y values from where the score indicator is drawn.
    static final int INITIAL_PLACEMENT = 0;

    //The size of the text written in the indicator.
    static final int TEXT_SIZE = 15;

    //A counter that counts the score of the game.
    private Counter score;

    //The width of the drawn indicator.
    private int width;

    //The height of the drawn indicator.
    private int height;

    /**
     * A constructor method of ScoreIndicator class. It creates a new instance of a Score Indicator.
     *
     * @param score  -  the score counter of the game.
     * @param width  - The width of the drawn indicator.
     * @param height - The height of the drawn indicator.
     */
    public ScoreIndicator(Counter score, int width, int height) {
        this.score = score;
        this.width = width;
        this.height = height;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {

        //Drawing and filling the inside of the score indicator with it's color.
        d.setColor(COLOR);
        d.fillRectangle(INITIAL_PLACEMENT, INITIAL_PLACEMENT, width, height);

        //Drawing the black frame around the indicator.
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle(INITIAL_PLACEMENT, INITIAL_PLACEMENT, width, height);

        //Writing the score.
        d.drawText(width / 2 - TEXT_SIZE * 2, height - (height - TEXT_SIZE) / 2, "Score:" + this.score.getValue(),
                TEXT_SIZE);
    }

    @Override
    public void timePassed() {
        return;
    }
}
