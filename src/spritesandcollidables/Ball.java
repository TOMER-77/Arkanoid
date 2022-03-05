
package spritesandcollidables;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import listeners.HitListener;
import physics.Velocity;
import run.GameLevel;
import run.GameEnvironment;

import java.util.List;

/**
 * @author Tomer .
 * The class Ball represents a two-dimensional colored ball. It will be used to represent balls that will be drawn
 * on the screen and played with in this game.
 */
public class Ball implements Sprite {

    static final java.awt.Color DEFAULT_COLOR = java.awt.Color.WHITE;

    //The game environment in which the ball moves.
    private GameEnvironment gameEnvironment;

    //The center point (the x and y coordinate) of the ball.
    private Point center;

    //The color of the ball.
    private java.awt.Color color;

    //The velocity of the ball. The pace and the direction the ball changes it's location.
    private Velocity velocity;

    //A list of the listener objects that are registered to the ball.
    private List<HitListener> hitListeners;

    //The radius of the ball.
    private int size;

    /**
     * A constructor method to Ball class. It used to create a new instance of Ball.
     *
     * @param center - the central point of the ball.
     * @param r      - the radius of the ball.
     * @param color  - the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = null;
        this.hitListeners = new java.util.ArrayList<HitListener>();
    }

    /**
     * A constructor method to Ball class. It creates a instance of ball with default color.
     *
     * @param center - the central point of the ball.
     * @param r      - the radius of the ball.
     */
    public Ball(Point center, int r) {
        this(center, r, DEFAULT_COLOR);
    }


    /**
     * A getter method for x field of the center of the ball.
     *
     * @return the x value of the center point of a ball.integer (according to the instructions).
     */
    public int getX() {
        return (int) this.getCenter().getX();
    }

    /**
     * A getter method for y field of the center of the ball.
     *
     * @return the y value of the center point of a ball as an integer (according to the instructions).
     */
    public int getY() {
        return (int) this.getCenter().getY();
    }

    /**
     * A getter method for size field of the ball.
     *
     * @return the radius (size) of the ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * A getter method for color field of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * A getter method for the center field of the ball.
     *
     * @return the center Point of the ball.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * A getter method for the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * A getter method for the game environment of the ball.
     *
     * @return the game environment of the ball.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * A setter method which sets a new Velocity for a point.
     *
     * @param v - the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * A setter method which sets a new Velocity for a point with dx and dy parameters.
     *
     * @param dx - the new pace of movement on x axis.
     * @param dy - the new pace of movement on y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * A setter method which sets a new game environment for the ball.
     *
     * @param newGameEnvironment - the new game environment.
     */
    public void setGameEnvironment(GameEnvironment newGameEnvironment) {
        this.gameEnvironment = newGameEnvironment;
    }

    /**
     * The method removes the ball from the sprites collection of the game.
     *
     * @param g - the game we remove the ball from.
     */
    public void removeFromGame(GameLevel g) {
        g.getSprites().removeSprite(this);
    }

    /**
     * The method handles a collision of the ball with the upper or bottom parts of an other object.
     *
     * @param collision - the information about the collision point and the other colliding object.
     */
    public void hitUpperOrBottomBound(CollisionInfo collision) {

        /*
         * If the ball is moving up (negative speed) it will be put under the colliding object.
         * If the ball is moving down (positive speed) it will be put above the colliding object.
         */
        if (this.velocity.getDy() < 0) {
            this.center.setY(this.size + collision.collisionPoint().getY());
        } else {
            this.center.setY(collision.collisionPoint().getY() - this.size);
        }
    }

    /**
     * The method handles a collision of the ball with the left or right parts of an other object.
     *
     * @param collision - the information about the collision point and the other colliding object.
     */
    public void hitLeftOrRightBound(CollisionInfo collision) {

        /*
         * If the ball is moving left (negative speed) it will be put to the left of the colliding object.
         * If the ball is moving right (positive speed) it will be put to the right of the colliding object.
         */
        if (this.getVelocity().getDx() < 0) {
            this.center.setX(this.size + collision.collisionPoint().getX());
        } else {
            this.center.setX(collision.collisionPoint().getX() - this.size);
        }
    }

    /**
     * The method checks if the ball is trapped inside a paddle.
     *
     * @param paddle - The paddle in which the ball might be stuck.
     * @return true if the ball is inside the paddle ,false if it isn't.
     */
    public boolean isTrapped(Collidable paddle) {

        /*
         * If the x value of the center of the ball is between the most left and most right x values of the paddle
         * and if the y value of the center of the ball is between the highest and lowest y values of the paddle it
         * means it is trapped inside the paddle.
         */
        if ((this.getX() > (paddle.getCollisionRectangle().getUpperLeft().getX()))
                && (this.getX() < (paddle.getCollisionRectangle().getUpperLeft().getX()
                + paddle.getCollisionRectangle().getWidth()))
                && (this.getY() > (paddle.getCollisionRectangle().getUpperLeft().getY()))
                && (this.getY() < (paddle.getCollisionRectangle().getUpperLeft().getY()
                + paddle.getCollisionRectangle().getHeight()))) {
            return true;
        }
        return false;
    }

    /**
     * the method gets the ball out of the paddle it is trapped inside.
     *
     * @param paddle - The paddle in which the ball is trapped.
     */
    public void getOutOfPaddle(Paddle paddle) {
        Velocity momentalVelocity = this.velocity;

        //This will be
        double newdx = this.velocity.getDx();

        //The ball moves to the left.
        if (this.velocity.getDx() < 0) {

            /*
             * If the paddle is moving right we put the ball to the right of the paddle. Else, the paddle moves to the
             * left we put the ball to the left of the paddle. Then we let the ball move faster than the
             * paddle for a short instance in the x axis to let it escape the paddle's speed. After that the sign of
             *  the future horizontal speed is decided. If the ball and paddle move in opposite directions the
             * direction iss changed, if they move in the same direction then we do not change the sign.
             */
            if (paddle.getCollisionRectangle().getUpperLeft().getX() > paddle.getLastLocationX()) {
                this.center.setX(paddle.getCollisionRectangle().getUpperLeft().getX()
                        + paddle.getCollisionRectangle().getWidth() + this.size);
                momentalVelocity = new Velocity(paddle.getStepSize() - this.velocity.getDx(), 0);
                newdx = -this.velocity.getDx();
            }
            if (paddle.getCollisionRectangle().getUpperLeft().getX() < paddle.getLastLocationX()) {
                this.center.setX(paddle.getCollisionRectangle().getUpperLeft().getX() - this.size);
                momentalVelocity = new Velocity(-paddle.getStepSize() + this.velocity.getDx(), 0);
                newdx = this.velocity.getDx();
            }

            //The ball moves to the right.
        } else {

            /*
             * If the paddle is moving right we put the ball to the right of the paddle. Else, the paddle moves to the
             * left we put the ball to the left of the paddle. Then we let the ball move faster than the
             * paddle for a short instance in the x axis to let it escape the paddle's speed. After that the sign of
             *  the future horizontal speed is decided. If the ball and paddle move in opposite directions the
             * direction iss changed, if they move in the same direction then we do not change the sign.
             */
            if (paddle.getCollisionRectangle().getUpperLeft().getX() > paddle.getLastLocationX()) {
                this.center.setX(paddle.getCollisionRectangle().getUpperLeft().getX()
                        + paddle.getCollisionRectangle().getWidth() + this.size);
                momentalVelocity = new Velocity(paddle.getStepSize() + this.velocity.getDx(), 0);
                newdx = this.velocity.getDx();
            }
            if (paddle.getCollisionRectangle().getUpperLeft().getX() < paddle.getLastLocationX()) {
                this.center.setX(paddle.getCollisionRectangle().getUpperLeft().getX() - this.size);
                momentalVelocity = new Velocity(-paddle.getStepSize() - this.velocity.getDx(), 0);
                newdx = -this.velocity.getDx();
            }
        }

        //Applying high speed to the ball to let it get away from the paddle.
        this.center = momentalVelocity.applyToPoint(this.center);

        //Setting the new horizontal speed,
        this.setVelocity(new Velocity(newdx, this.velocity.getDy()));
    }

    /**
     * The method checks if the ball is out of the left and right boundaries of the game and if so brings it back to
     * the screen.
     */
    public void getInIfOutOfLeftOrRightBounds() {

        /*
         * First we check if the ball is out of the left bound and then if it's out of the right bound. If it does,
         * the method brings the ball back to the bounds.
         */
        if (this.getX() <= this.gameEnvironment.getPaddle().getLeftBound()) {
            this.center.setX(this.gameEnvironment.getPaddle().getLeftBound() + this.size);
            this.center.setY(this.gameEnvironment.getPaddle().getPaddleY() - this.size);

        }
        if (this.getX() >= this.gameEnvironment.getPaddle().getRightBound()) {
            this.center.setX(this.gameEnvironment.getPaddle().getRightBound() - this.size);
            this.center.setY(this.gameEnvironment.getPaddle().getPaddleY() - this.size);
        }
    }


    /**
     * The method moves the center of the ball according to the ball's velocity while keeping the ball in the
     * boundaries of the game and considering it's collisions with other objects of the game.
     */
    public void moveOneStep() {

        /*
         * The trajectory of the ball in this move is calculated and we get the collision information about the
         * closest collision the ball is facing in this move.
         */
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);

        //If the ball doesn't collide we move it as expected in it's trajectory.
        if (collision == null) {
            this.center = this.velocity.applyToPoint(this.center);
            return;
        }

        /*
         * If the ball do collide, we recieve it's new velocity after the collision. Then we check in which axis his
         *  speed changed and changing his position accordingly. After that we set the ball's ne velocity.
         */
        Velocity newVelocity = collision.collisionObject().hit(this, collision.collisionPoint(), this.velocity);
        if ((newVelocity.getDx() != this.velocity.getDx())
                && ((newVelocity.getDy() != this.velocity.getDy()))) {
            this.hitUpperOrBottomBound(collision);
            this.hitLeftOrRightBound(collision);
        } else if ((newVelocity.getDx() != this.velocity.getDx())) {
            this.hitLeftOrRightBound(collision);
            this.center.setY(collision.collisionPoint().getY());
        } else if ((newVelocity.getDy() != this.velocity.getDy())) {
            this.hitUpperOrBottomBound(collision);
            this.center.setX(collision.collisionPoint().getX());
        }
        this.setVelocity(newVelocity);

        /*
         * When the Ball and paddle move together and collide on the left or right side of the paddle the ball might
         * get trapped in the paddle because both objects move together horizontally and in the next move the ball
         * might get inside the paddle even though in this move it wasn't anticipated. We check if it happens and if
         * so we get the ball out of the paddle.
         */
        if ((this.gameEnvironment.getPaddle() != null) && (this.isTrapped(this.gameEnvironment.getPaddle()))) {
            this.getOutOfPaddle((Paddle) this.gameEnvironment.getPaddle());

            //Handling and edge case when the ball went out of bounds we we got him out of the paddle.
            this.getInIfOutOfLeftOrRightBounds();
        }
    }

    /**
     * The method draws a ball on the screen. This method is implemented in the Sprite interface.
     *
     * @param d - the surface on which the ball will be drawn. DrawSurface class is part of the biuoop package.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle(this.getX(), this.getY(), this.size);
        d.setColor(java.awt.Color.BLACK);
        d.drawCircle(this.getX(), this.getY(), this.size);
    }

    /**
     * The method notify the ball that an animation loop has passed and moves it to it's next location. This method
     * is implemented in the Sprite interface.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * The method adds the ball as a sprite to the game that it's part of. This method is implemented in the Sprite
     * interface.
     *
     * @param g - the game to which the object is added.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
