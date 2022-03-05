
package spritesandcollidables;

import biuoop.DrawSurface;
import run.GameLevel;

/**
 * @author Tomer .
 * The interface Sprite will represent all the sprites in the game which are object that can be drawn to the screen
 * and which are not just a background image. These objects are the paddle, the balls and the blocks.
 */
public interface Sprite {
    /**
     * The method draws the object to the screen.
     *
     * @param d - the surface on which the object will be drawn. DrawSurface class is part of the biuoop package.
     */
    void drawOn(DrawSurface d);

    /**
     * The method notify the object that an animation loop has passed.
     */
    void timePassed();

    /**
     * The method adds the object to the game that it's part of.
     *
     * @param g - the game to which the object is added.
     */
    void addToGame(GameLevel g);
}
