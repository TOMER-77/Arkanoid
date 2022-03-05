
package listeners;

import run.GameLevel;
import spritesandcollidables.Ball;
import spritesandcollidables.Block;

/**
 * @author Tomer .
 * The BlockRemover class is in charge of removing blocks from the game, and keeping the count of the remaining
 * blocks in the game.
 */
public class BlockRemover implements HitListener {

    //The game that the blocks will be removed from.
    private GameLevel game;

    //A Counter of the remaining blocks in the game.
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          - the game that the blocks will be removed from.
     * @param removedBlocks - counter of the remaining blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        //Removing the listeners from the block that was hit and will be removed from the game.
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);

        //Updating the number of remaining blocks.
        this.remainingBlocks.decreaseByOne();
    }
}
