
package run;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.Counter;
import listeners.ScoreTrackingListener;
import physics.Velocity;
import spritesandcollidables.Ball;
import spritesandcollidables.Block;
import spritesandcollidables.Collidable;
import spritesandcollidables.LevelIndicator;
import spritesandcollidables.Paddle;
import spritesandcollidables.ScoreIndicator;
import spritesandcollidables.Sprite;
import spritesandcollidables.SpriteCollection;

import java.util.List;


/**
 * @author Tomer .
 * The class GameLevel is in charge of initializing the levels of the game, hold their sprites and run them.
 */
public class GameLevel implements Animation {

    //The width of the gui window of the game.
    static final int GUI_WIDTH = 800;

    //The height of the gui window of the game.
    static final int GUI_HEIGHT = 600;

    //The height of the the score counter displayed while playing.
    static final int SCORE_HEIGHT = 20;

    //The width of the vertical bound blocks of the game or The height of the horizontal bound blocks of the game.
    static final int BOUND_SIZE = 25;

    //Initial x,y value for the top left point of the game animation GUI.
    static final int INITIAL_POINT = 0;

    //The environment of the game which holds all the collidable objects of the level.
    private GameEnvironment environment;

    //Holds all the sprites of the level.
    private SpriteCollection sprites;

    //The GUI in which the game level is animated and displayed on the screen.
    private GUI gui;

    //The keyboard of the game;
    private KeyboardSensor keyboard;

    //Counts the number of blocks in the level.
    private Counter blocksCounter;

    //Counts the number of balls in the level.
    private Counter ballsCounter;

    //Keeps the score of the player.
    private Counter score;

    //The information needed to run the level.
    private LevelInformation info;

    //Running the animation of the game level.
    private AnimationRunner runner;

    //Tells the game level if it should run or stop running.
    private boolean running;

    //The number of blocks at the beggining of the level.
    private int initialNumberOfBlocks;

    //The number of blocks that should be removed to clear the level.
    private int numberOfBlocksToRemove;

    /**
     * A constructor method of Game class. It used to create a new game instance.
     *
     * @param levelInformation - the information needed to run the current level.
     * @param runner           - the animation runner that will run the animation of the level.
     * @param keyboard         - the keyboard sensor of the game.
     * @param score            - the score of the game at the point that the level begins.
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner runner,
                     KeyboardSensor keyboard, Counter score) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.gui = runner.getGui();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = score;
        this.runner = runner;
        this.keyboard = keyboard;
        this.info = levelInformation;
        this.initialNumberOfBlocks = levelInformation.blocks().size();
        this.numberOfBlocksToRemove = levelInformation.numberOfBlocksToRemove();
    }

    /**
     * A getter method for the environment field.
     *
     * @return game environment of the game.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * A getter method for the sprites field.
     *
     * @return sprites collection of the level.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * The method returns the numbers of balls remained in the level.
     *
     * @return the numbers of balls remained in the level.
     */
    public int getNumberOfBallsInLevel() {
        return this.ballsCounter.getValue();
    }

    /**
     * A getter method for the gui field.
     *
     * @return the GUI object of the game.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * A setter method which sets a GUI object for the game level.
     *
     * @param newGui - the new GUI of the game level.
     */
    public void setGui(GUI newGui) {
        this.gui = newGui;
    }

    /**
     * The method adds a colidable object to the game environment of the level.
     *
     * @param c - the collidable object to be added to the game environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The method adds a sprite object to the sprite collection of the level.
     *
     * @param s - the sprite object to be added to the sprite collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The method creates instances of all the blocks of the level.
     */
    public void createBlocks() {

        //Creating the bound blocks of the game.
        Block upperBound = new Block(new Rectangle(new Point(SCORE_HEIGHT, SCORE_HEIGHT), GUI_WIDTH, BOUND_SIZE));
        Block leftBound = new Block(new Rectangle(new Point(0, SCORE_HEIGHT), BOUND_SIZE, GUI_HEIGHT));
        Block rightBound = new Block(new Rectangle(new Point(GUI_WIDTH - BOUND_SIZE, SCORE_HEIGHT),
                BOUND_SIZE, GUI_HEIGHT));

        //This block will be the death-region block, hitting it will mean that the ball is out of the game.
        Block deathBound = new Block(new Rectangle(new Point(INITIAL_POINT, GUI_HEIGHT),
                GUI_WIDTH, BOUND_SIZE));
        Block[] bounds = {upperBound, leftBound, rightBound, deathBound};

        //Adding the bound blocks to the game.
        for (int i = 0; i < bounds.length; i++) {
            bounds[i].addToGame(this);
        }

        //Adding a ball remover listener to the death block so balls will be removed when hitting it.
        deathBound.addHitListener(new BallRemover(this, this.ballsCounter));
        List<Block> blocksList = this.info.blocks();


        //Creating the listeners to be added to the blocks. They add points to the score and remove a block when hit.
        BlockRemover remover = new BlockRemover(this, this.blocksCounter);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);

        //Adding the blocks of the level to the game with their listeners.
        for (Block block : blocksList) {
            block.addToGame(this);
            block.addHitListener(remover);
            block.addHitListener(scoreTracker);
        }

        //Updating the number of blocks in the game.
        this.blocksCounter.increase(blocksList.size());
    }

    /**
     * The method creates instances of all the balls of the level.
     */
    public void createBalls() {

        Ball ball;

        //The distance above the paddle that the balls will start from.
        int distanceFromPaddle = 10;

        //The default center point of the balls.
        Point center = new Point(this.GUI_WIDTH / 2,
                this.getEnvironment().getPaddle().getPaddleY() - distanceFromPaddle);
        List<Velocity> velocityList = info.initialBallVelocities();
        int radius = info.ballsRadius();

        //Creating the game's balls,setting their velocity and game environment and adding them to the game.
        for (int i = 0; i < info.numberOfBalls(); i++) {
            ball = new Ball(center, radius);
            ball.setGameEnvironment(this.environment);
            ball.setVelocity(velocityList.get(i));
            ball.addToGame(this);
        }

        //Updating the number of balls in the game
        this.ballsCounter.increase(info.numberOfBalls());
    }


    /**
     * The method adds 100 points to the score of the player. It will be used when the player clears a level.
     */
    private void levelCleared() {
        final int levelClearedPoint = 100;
        this.score.increase(levelClearedPoint);
    }

    /**
     * The method initialize a new game. It creates the GUI, blocks, balls and paddle and adding them to the game.
     */
    public void initialize() {

        //Adding the background of the level to the game.
        info.getBackground().addToGame(this);

        //Setting the paddle of the level.
        Paddle paddle = new Paddle(GUI_HEIGHT, GUI_WIDTH, BOUND_SIZE, this.info.paddleSpeed(),
                this.info.paddleWidth(), keyboard);
        paddle.addToGame(this);
        this.environment.setPaddle(paddle);

        //Creating the balls of the game.
        createBalls();

        //Creating the blocks of the game and adding them to the game.
        createBlocks();

        //Creating the score indicator which will display the score of the player and adding it to the game.
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, GUI_WIDTH, SCORE_HEIGHT);
        scoreIndicator.addToGame(this);

        //Creating the level indicator which will display the name of the current level and adding it to the game.
        LevelIndicator levelIndicator = new LevelIndicator(this.info.levelName(), GUI_WIDTH, SCORE_HEIGHT);
        levelIndicator.addToGame(this);
    }

    /**
     * The method runs the animation of the game.
     */
    public void run() {

        //The number of seconds that the pre game countdown will be shown.
        int numberOfSeconds = 2;

        //The number that the countdown will start count from.
        int countFrom = 3;

        //Showing the pre game countdown and running the animation of the level.
        this.running = true;
        CountdownAnimation countdown = new CountdownAnimation(numberOfSeconds, countFrom, this.getSprites(),
                GUI_HEIGHT / 2, GUI_WIDTH / 2);
        this.runner.run(countdown);
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        //Exiting the game when all blocks were hit.
        if ((this.initialNumberOfBlocks - this.blocksCounter.getValue() == numberOfBlocksToRemove)
                || (this.ballsCounter.getValue() == 0)) {

            //Adding extra points when all the blocks are removed.
            if (this.blocksCounter.getValue() == 0) {
                levelCleared();
            }
            this.running = false;
        }

        //The keys that will be used pause the game and stop the pause screen.
        String pauseString = "p";
        String stopPauseString = "space";

        //Pausing the game if the player pressing the pause key.
        if (this.keyboard.isPressed(pauseString)) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, stopPauseString,
                    new PauseScreen(this.keyboard)));
        }

        //All the sprites of the game are drawn on the drawing surface.
        this.sprites.drawAllOn(d);

        //The balls and paddle are moved to their next location.
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
