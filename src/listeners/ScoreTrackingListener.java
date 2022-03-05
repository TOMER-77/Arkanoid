
package listeners;

import spritesandcollidables.Ball;
import spritesandcollidables.Block;

/**
 * @author Tomer .
 * The ScoreTrackingListener is in charge of keep track of the score of the game.
 */
public class ScoreTrackingListener implements HitListener {

    //The number of points that being added when a block is being hit.
    static final int BLOCK_POINT = 5;

    //The score counter of the game.
    private Counter currentScore;

    /**
     * A constructor method of ScoreTrackingListener class. It creates a new instance of a score tracking listener.
     *
     * @param scoreCounter - the score counter of the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        /*
         * increasing the score of the game and removing itself of the block that was hit and will be removed from
         * the game.
         */
        this.currentScore.increase(BLOCK_POINT);
        beingHit.removeHitListener(this);
    }
}
