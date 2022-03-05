
package spritesandcollidables;

import biuoop.DrawSurface;
import run.GameLevel;

/**
 * @author Tomer .
 * The class is an indicator which shows to the player the name of the played level.
 */
public class LevelIndicator implements Sprite {

    //The size of the text written by the indicator.
    static final int TEXT_SIZE = 15;

    //The name of the level.
    private String levelName;

    //The width of the drawn indicator.
    private int width;

    //The height of the drawn indicator.
    private int height;

    /**
     * A constructor method of the LevelIndicator class. It creates a new instance of a level indicator.
     *
     * @param levelName - the name of the level.
     * @param width     - the width of the drawn indicator.
     * @param height    -The height of the drawn indicator.
     */
    public LevelIndicator(String levelName, int width, int height) {
        this.levelName = levelName;
        this.width = width;
        this.height = height;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);

        //Writing the score.
        d.drawText(width * 2 / 3, height - (height - TEXT_SIZE) / 2, "Level Name:" + this.levelName,
                TEXT_SIZE);
    }


    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
