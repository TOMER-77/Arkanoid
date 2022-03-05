
package spritesandcollidables;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import physics.Velocity;
import run.GameLevel;

/**
 * @author Tomer .
 * The class represents the Paddle which is the player in the game. It is a rectangle which controlled by the arrow
 * keys, and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {

    //The height of the paddle rectangle.
    static final int PADDLE_HEIGHT = 10;

    //A default color of the paddle.
    static final java.awt.Color DEFAULT_COLOR = java.awt.Color.ORANGE;

    //The rectangle that represents the paddle.
    private Rectangle collisionRectangle;

    //The y value of the upper side of the paddle.
    private int paddleY;

    //The most left x value the paddle is allowed to reach.
    private int leftBound;

    //The most right x value the paddle is allowed to reach.
    private int rightBound;

    //The horizontal speed of the paddle per frame when the right or left key is pressed.
    private int stepSize;

    //The width of the paddle's rectangle.
    private int paddleWidth;

    //The x value of the left side of the paddle in the last animation loop.
    private double lastLocationX;

    //The keyboard the paddle will be moving accordingly to.
    private biuoop.KeyboardSensor keyboard;

    /**
     * A constructor method of Paddle class. It creates a new instance of Paddle.
     *
     * @param guiHeight   - the height of the gui windows of the game.
     * @param guiWidth    - the width of the gui windows of the game.
     * @param boundSize   - The width of the vertical bound blocks of the game or The height of the horizontal bound
     *                    blocks of the game.
     * @param stepSize    - he horizontal speed of the paddle.
     * @param paddleWidth - The width of the paddle's rectangle.
     * @param keyboard    - The keyboard the paddle will be moving accordingly to.
     */
    public Paddle(int guiHeight, int guiWidth, int boundSize, int stepSize,
                  int paddleWidth, biuoop.KeyboardSensor keyboard) {

        this.paddleWidth = paddleWidth;
        //Putting the paddle in it's place on the bottom of the screen.
        this.paddleY = guiHeight - boundSize - PADDLE_HEIGHT;

        //The paddle will start on the middle of the screen.
        Point upperLeft = new Point((int) ((guiWidth / 2) - paddleWidth / 2), this.paddleY);
        this.collisionRectangle = new Rectangle(upperLeft, paddleWidth, PADDLE_HEIGHT);
        this.leftBound = boundSize;
        this.rightBound = guiWidth - boundSize;
        this.stepSize = stepSize;
        this.keyboard = keyboard;

        //The current x value of the left side of the rectangle will be the initial last location.
        this.lastLocationX = this.collisionRectangle.getUpperLeft().getX();
    }


    /**
     * A getter method for the paddleY field of the paddle.
     *
     * @return The y value of the upper side of the paddle.
     */
    public int getPaddleY() {
        return this.paddleY;
    }

    /**
     * A getter method for the leftBound field of the paddle.
     *
     * @return The most left x value the paddle is allowed to reach.
     */
    public int getLeftBound() {
        return this.leftBound;
    }

    /**
     * A getter method for the rightBound field of the paddle.
     *
     * @return The most right x value the paddle is allowed to reach.
     */
    public int getRightBound() {
        return this.rightBound;
    }

    /**
     * A getter method for the step size of the paddle.
     *
     * @return The horizontal speed of the paddle per frame.
     */
    public int getStepSize() {
        return stepSize;
    }

    /**
     * A getter method for the lastLocationX field of the paddle.
     *
     * @return The x value of the left side of the paddle in the last animation loop.
     */
    public double getLastLocationX() {
        return this.lastLocationX;
    }

    /**
     * A getter method for the CollisionRectangle field of the paddle. This method is implemented in the Collidable
     * interface.
     *
     * @return the rectangle shape of the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * This method is used to "notify" other object which has velocity that it collided with the paddle.
     * This method is implemented in the Collidable interface.
     *
     * @param hitter          - the ball that hit the paddle.
     * @param collisionPoint  - the collision point of the objects.
     * @param currentVelocity - the current velocity of the hitting object.
     * @return the new velocity expected for the hitting object after the collision with the paddle.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        /*
         * According to the instructions the paddle is split in to 5 equally spaced regions.he left most region is 1
         * and the rightmost is 5. if we hit region 1, the ball should bounce back with an angle of 300 degrees,
         * regardless of where it came from. Similarly, for region 2 is should bounce back 330 degrees, for region 4
         * it should bounce in 30 degrees, and for region 5 in 60 degrees. If the ball hit region 3 (the middle of
         * the paddle) it will bounce lit it hit a regular block.
         */
        int numberOfAreas = 5;
        int middleArea = 3;
        double[] angles = {0, 300, 330, 0, 30, 60};

        //These will be the new vertical and horizontal speeds of the ball.
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        //If the ball hits the sides of the paddle it's horizontal direction is flipped.
        if ((collisionPoint.isOnTheLine(this.collisionRectangle.getLeftSide()))
                || (collisionPoint.isOnTheLine(this.collisionRectangle.getRightSide()))) {
            newDx = -newDx;
        }

        /*
         * The loop checks which region of the paddle the ball hit and give it a bouncing angle according to the
         * region. If the ball hits the middle region of the paddle it's vertical direction is flipped just like in
         * a collision with a block.
         */
        if ((collisionPoint.isOnTheLine(this.collisionRectangle.getUpperSide()))) {
            for (int i = 1; i <= numberOfAreas; i++) {
                if (collisionPoint.getX() <= ((this.collisionRectangle.getUpperLeft().getX()
                        + (i * this.collisionRectangle.getWidth()) / 5))) {
                    if (i == middleArea) {
                        newDy = -newDy;
                        return new Velocity(newDx, newDy);
                    }
                    return Velocity.fromAngleAndSpeed(angles[i], currentVelocity.getSpeed());
                }
            }
        }
        return new Velocity(newDx, newDy);
    }

    /**
     * The method adds the paddle to the game as a sprite and collidable object. This method is implemented in the
     * Sprite interface.
     *
     * @param g - the game to which the paddle is added.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * The method moves the paddle left.
     */
    public void moveLeft() {
        Point newUpperLeft;

        /*
         * If moving the paddle left will make it cross the bound it put right next to the bound. Else the paddle is
         * moved left according to the size of the paddle's step.
         */
        if (this.collisionRectangle.getUpperLeft().getX() - stepSize < this.leftBound) {
            newUpperLeft = new Point(this.leftBound, this.paddleY);
        } else {
            newUpperLeft = new Point(this.collisionRectangle.getUpperLeft().getX() - stepSize, this.paddleY);
        }
        this.collisionRectangle = new Rectangle(newUpperLeft, paddleWidth, PADDLE_HEIGHT);
    }

    /**
     * The method moves the paddle right.
     */
    public void moveRight() {
        Point newUpperLeft;

        /*
         * If moving the paddle right will make it cross the bound it put right next to the bound. Else the paddle is
         * moved right according to the size of the paddle's step.
         */
        if (this.collisionRectangle.getUpperLeft().getX() + paddleWidth + stepSize > this.rightBound) {
            newUpperLeft = new Point(this.rightBound - paddleWidth, this.paddleY);
        } else {
            newUpperLeft = new Point(this.collisionRectangle.getUpperLeft().getX() + stepSize,
                    this.paddleY);
        }
        this.collisionRectangle = new Rectangle(newUpperLeft, paddleWidth, PADDLE_HEIGHT);
    }

    /**
     * The method notify the paddle that an animation loop has passed and moves it to it's next location according
     * to the key the player presses. This method is implemented in the Sprite interface.
     */
    @Override
    public void timePassed() {

        //Storing the x value of the left side of the paddle in the current animation loop.
        this.lastLocationX = this.collisionRectangle.getUpperLeft().getX();

        /*
         *The paddle will move according to the key the player presses. If no key is pressed the paddle will stay in
         *  it's current location.
         */
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * The method draws a paddle on the screen. This method is implemented in the Sprite interface.
     *
     * @param d - the surface on which the paddle will be drawn. DrawSurface class is part of the biuoop package.
     */
    @Override
    public void drawOn(DrawSurface d) {

        //Drawing the rectangle of the paddle with it's color.
        d.setColor(DEFAULT_COLOR);
        d.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(), (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());

        //Drawing the black frame of the rectangle of the paddle.
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(), (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
    }
}
