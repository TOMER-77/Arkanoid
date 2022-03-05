
package listeners;

import run.GameLevel;
import spritesandcollidables.Ball;
import spritesandcollidables.Block;

/**
 * @author Tomer .
 * The BallRemover class is in charge of removing balls when they arrive to the death-region and updating the
 * counter of the available balls in the game.
 */
public class BallRemover implements HitListener {

    //The game that the balls will be removed from.
    private GameLevel game;

    //The counter of the remaining balls in the game.
    private Counter remainingBalls;

    /**
     * A constructor method of BallRemover class. It creates a new instance of ball remover.
     *
     * @param game         - the game that the balls will be removed from.
     * @param ballsCounter - counter of the remaining balls in the game.
     */
    public BallRemover(GameLevel game, Counter ballsCounter) {
        this.game = game;
        this.remainingBalls = ballsCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        //Updating the number of balls in the game and removing the listener from the ball that will be removed.
        hitter.removeFromGame(this.game);
        this.remainingBalls.decreaseByOne();
    }
}
