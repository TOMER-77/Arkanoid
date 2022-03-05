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
 * The Third level of the game.
 */
public class Level3 implements LevelInformation {
    //The width of the gui window of the game.
    static final int GUI_WIDTH = 800;

    //The height of the gui window of the game.
    static final int GUI_HEIGHT = 600;

    //The width of the regular blocks of the game.
    static final int BLOCK_WIDTH = 50;

    //The height of the regular blocks of the game.
    static final int BLOCK_HEIGHT = 25;

    //The number of the block rows in the game.
    static final int BLOCK_ROWS = 5;

    //Initial x value from where blocks will be drawn.
    static final int INITIAL_BLOCKS_Y = 170;

    //Initial x value from where blocks will be drawn.
    static final int INITIAL_BLOCKS_X = 725;

    //Maximal number of blocks in one row.
    static final int MAXIMAL_NUM_OF_BLOCKS = 10;

    //The radius of the game balls.
    static final int RADIUS = 4;

    //The speed of the game balls.
    static final int SPEED = 7;

    //Initial direction angle of the velocity of the first ball.
    static final int INITIAL_BALL1_ANGLE = 40;

    //Initial direction angle of the velocity of the second ball.
    static final int INITIAL_BALL2_ANGLE = -40;

    //Initial x,y value for the top left point of the game animation GUI.
    static final int INITIAL_POINT = 0;

    //The number of balls in this level.
    static final int NUMBER_OF_BALLS = 2;

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
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocitiesList = new ArrayList<Velocity>();
        velocitiesList.add(Velocity.fromAngleAndSpeed(INITIAL_BALL1_ANGLE, SPEED));
        velocitiesList.add(Velocity.fromAngleAndSpeed(INITIAL_BALL2_ANGLE, SPEED));
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
        return "getting the bricks";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Point(INITIAL_POINT, INITIAL_POINT), GUI_WIDTH, GUI_HEIGHT,
                new java.awt.Color(0, 0, 150));
    }

    @Override
    public List<Block> blocks() {

        //The array holds the colors of each blocks row.
        java.awt.Color[] colors = {java.awt.Color.GRAY, java.awt.Color.RED, java.awt.Color.YELLOW,
                java.awt.Color.PINK, java.awt.Color.green};
        ArrayList<Block> blocksList = new ArrayList<Block>();
        Block block;
        int x;
        int y = INITIAL_BLOCKS_Y;
        int numberOfBlocks = MAXIMAL_NUM_OF_BLOCKS;

        //Creating the rows of the blocks in the loop.
        for (int r = 0; r < BLOCK_ROWS; r++) {
            x = INITIAL_BLOCKS_X;
            for (int c = 0; c < numberOfBlocks; c++) {
                block = new Block(new Rectangle(new Point(x, y), BLOCK_WIDTH, BLOCK_HEIGHT), colors[r]);
                blocksList.add(block);

                //Setting the x value of the next block by subtracting the width of the blocks.
                x -= BLOCK_WIDTH;
            }

            //Setting the y value of the next row by adding the height of the blocks.
            y += BLOCK_HEIGHT;

            //In each row the number of blocks decreased by one.
            numberOfBlocks--;
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public int ballsRadius() {
        return RADIUS;
    }
}
