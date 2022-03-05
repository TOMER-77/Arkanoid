
package animation;

import biuoop.DrawSurface;

/**
 * @author Tomer .
 * The Animation interface represents animations and screens that should be animated .
 */
public interface Animation {
    /**
     * The method draws a frame of the animated object.
     *
     * @param d - the surface on which the game objects will be drawn.
     */
    void doOneFrame(DrawSurface d);

    /**
     * The method checks if the animation should be stopped.
     *
     * @return true if the animation should be stopped ,else false is returned.
     */
    boolean shouldStop();
}
