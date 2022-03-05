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
 * The Second level of the game. An easy level with a wide paddle.
 */
public class Level2 implements LevelInformation {

    //The width of the gui window of the game.
    static final int GUI_WIDTH = 800;

    //The height of the gui window of the game.
    static final int GUI_HEIGHT = 600;

    //The width of the vertical bound blocks of the game or The height of the horizontal bound blocks of the game.
    static final int BOUND_SIZE = 25;

    //The width of the regular blocks of the game.
    static final int BLOCK_WIDTH = 50;

    //The height of the regular blocks of the game.
    static final int BLOCK_HEIGHT = 30;

    //Initial x value from where blocks will be drawn.
    static final int INITIAL_BLOCKS_Y = 200;

    //number of blocks in the level.
    static final int NUMBER_OF_BLOCKS = 15;

    //The radius of the game balls.
    static final int RADIUS = 4;

    //The speed of the game balls.
    static final int SPEED = 6;

    //Initial y value of the center of the balls.
    static final int INITIAL_BALLS_Y = 250;

    //Initial direction angle of the velocity of the balls.
    static final int INITIAL_BALL_ANGLE = 0;

    //Initial x,y value for the top left point of the game animation GUI.
    static final int INITIAL_POINT = 0;

    //The number of balls in this level.
    static final int NUMBER_OF_BALLS = 10;

    //The horizontal speed of the paddle per frame when the right or left key is pressed.
    static final int PADDLE_SPEED = 3;

    //The width of the paddle rectangle.
    static final int PADDLE_WIDTH = 600;

    //The height of the paddle rectangle.
    static final int PADDLE_HEIGHT = 10;

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int angleToAdd = 10;
        ArrayList<Velocity> velocitiesList = new ArrayList<Velocity>();
        for (int i = 0; i <= NUMBER_OF_BALLS / 2; i++) {
            velocitiesList.add(Velocity.fromAngleAndSpeed(INITIAL_BALL_ANGLE + i * angleToAdd, SPEED));
            velocitiesList.add(Velocity.fromAngleAndSpeed(-(INITIAL_BALL_ANGLE + i * angleToAdd), SPEED));
        }
        return velocitiesList;
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
        return "wide level";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Point(INITIAL_POINT, INITIAL_POINT), GUI_WIDTH, GUI_HEIGHT, java.awt.Color.WHITE);
    }

    @Override
    public List<Block> blocks() {

        //The array holds the colors of the blocks.
        java.awt.Color[] colors = {java.awt.Color.RED, java.awt.Color.ORANGE, java.awt.Color.YELLOW,
                java.awt.Color.GREEN, java.awt.Color.BLUE, java.awt.Color.PINK, java.awt.Color.CYAN,
                java.awt.Color.CYAN};
        ArrayList<Block> blocksList = new ArrayList<Block>();
        Block block;
        Rectangle rectangle;
        int x = BOUND_SIZE;

        //Creating one row of blocks
        for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
            rectangle = new Rectangle(new Point(x, INITIAL_BLOCKS_Y), BLOCK_WIDTH, BLOCK_HEIGHT);
            block = new Block(rectangle, colors[i / 2]);
            blocksList.add(block);

            //Setting the x value of the next block by adding the width of the blocks.
            x += BLOCK_WIDTH;
        }
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
}
