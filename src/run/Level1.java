
package run;

import geometry.Point;
import geometry.Rectangle;
import physics.Velocity;
import spritesandcollidables.Background;
import spritesandcollidables.Block;
import spritesandcollidables.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomer .
 * The First level of the game. This is an easy level with only one brick.
 */
public class Level1 implements LevelInformation {

    //The width of the gui window of the game.
    static final int GUI_WIDTH = 800;

    //The height of the gui window of the game.
    static final int GUI_HEIGHT = 600;

    //The width of the regular blocks of the game.
    static final int BLOCK_WIDTH = 30;

    //The height of the regular blocks of the game.
    static final int BLOCK_HEIGHT = 30;

    //Initial x value from where blocks will be drawn.
    static final int INITIAL_BLOCKS_Y = 200;

    //number of blocks in the level.
    static final int NUMBER_OF_BLOCKS = 1;

    //The radius of the game balls.
    static final int RADIUS = 4;

    //The speed of the game balls.
    static final int SPEED = 6;

    //Initial direction angle of the velocity of the balls.
    static final int INITIAL_BALL_ANGLE = 0;

    //Initial x,y value for the top left point of the game animation GUI.
    static final int INITIAL_POINT = 0;

    //The number of balls in this level.
    static final int NUMBER_OF_BALLS = 1;

    //The horizontal speed of the paddle per frame when the right or left key is pressed.
    static final int PADDLE_SPEED = 7;

    //The width of the paddle rectangle.
    static final int PADDLE_WIDTH = 75;

    //The height of the paddle rectangle.
    static final int PADDLE_HEIGHT = 10;

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public int paddleHeight() {
        return PADDLE_HEIGHT;
    }

    @Override
    public String levelName() {
        return "just hit it";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Point(INITIAL_POINT, INITIAL_POINT), GUI_WIDTH, GUI_HEIGHT, java.awt.Color.BLACK);
    }

    @Override
    public List<Block> blocks() {

        //Creating the only block of the level.
        Rectangle blockRectangle = new Rectangle(new Point((int) (GUI_WIDTH / 2 - BLOCK_WIDTH / 2), INITIAL_BLOCKS_Y),
                BLOCK_WIDTH, BLOCK_HEIGHT);
        Block lonelyBlock = new Block(blockRectangle, java.awt.Color.WHITE);
        ArrayList<Block> blocksList = new ArrayList<Block>();
        blocksList.add(lonelyBlock);
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }

    @Override
    public int ballsRadius() {
        return RADIUS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocitiesList = new ArrayList<Velocity>();
        velocitiesList.add(Velocity.fromAngleAndSpeed(INITIAL_BALL_ANGLE, SPEED));
        return velocitiesList;
    }
}
