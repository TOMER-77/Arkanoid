
package listeners;

import spritesandcollidables.Ball;
import spritesandcollidables.Block;

/**
 * @author Tomer .
 * The interface HitListener indicates that objects that implement it should be notified of hits in the game.
 */
public interface HitListener {

    /**
     * The method does all the updates that has to be done when the beingHit block is hit.
     *
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
