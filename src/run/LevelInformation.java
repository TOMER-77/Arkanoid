
package run;

import physics.Velocity;
import spritesandcollidables.Block;
import spritesandcollidables.Sprite;

import java.util.List;

/**
 * @author Tomer .
 * The LevelInformation interfacenspecifies the information required to fully execute and run a level in the game.
 */
public interface LevelInformation {

    /**
     * Gets the initial number of balls of the level.
     *
     * @return the number of balls at the beginning of the level.
     */
    int numberOfBalls();


    /**
     * Gets a list with the initial velocity of each ball. The length of the list should correspond to the number of
     * balls of the level.
     *
     * @return a list with the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Gets the horizontal speed of the paddle per frame when the right or left key is pressed.
     *
     * @return the horizontal speed of the paddle.
     */
    int paddleSpeed();

    /**
     * Gets the width of the paddle.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * Gets the height of the paddle.
     *
     * @return the height of the paddle.
     */
    int paddleHeight();

    /**
     * Gets the name of the level, which will be displayed at the top of the screen.
     *
     * @return a string of the name of the level.
     */
    String levelName();

    /**
     * Gets a sprite with the background of the level.
     *
     * @return the background of the level.
     */
    Sprite getBackground();


    /**
     * Gets a list of the Blocks that make up the level.
     *
     * @return a list of the Blocks of the level.
     */
    List<Block> blocks();


    /**
     * Gets the number of blocks that should be removed before the level is considered to be cleared.
     *
     * @return the number of blocks that should be removed to clear the level.
     */
    int numberOfBlocksToRemove();

    /**
     * Gets the radius of the balls of the level.
     *
     * @return the radius of the balls of the level.
     */
    int ballsRadius();
}