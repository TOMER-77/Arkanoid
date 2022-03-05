

package spritesandcollidables;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;
import physics.Velocity;
import run.GameLevel;

import java.util.List;

/**
 * @author Tomer.
 * The class Block represents rectangle shaped blocks the the ball will be colliding with in the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {


    //A default color for the blocks.
    static final java.awt.Color DEFAULT_COLOR = java.awt.Color.GRAY;

    //The rectangle that the block represents.
    private Rectangle collisionRectangle;

    //The color of the block.
    private java.awt.Color color;

    //A list of the listener objects that are registered to the block.
    private List<HitListener> hitListeners;

    /**
     * A constructor method of Block class. It used to create a new instance of Ball.
     *
     * @param collisionRectangle - the rectangle shape of the block.
     * @param color              - the color of the block.
     */
    public Block(Rectangle collisionRectangle, java.awt.Color color) {
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        this.hitListeners = new java.util.ArrayList<HitListener>();
    }

    /**
     * A constructor method of Block class. It used to create a new instance of Ball with default color.
     *
     * @param collisionRectangle - the rectangle shape of the block.
     */
    public Block(Rectangle collisionRectangle) {
        this.collisionRectangle = collisionRectangle;
        this.color = DEFAULT_COLOR;
        this.hitListeners = new java.util.ArrayList<HitListener>();
    }

    /**
     * A getter method for the color field of the block.
     *
     * @return the color of the block.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * A getter method for the CollisionRectangle field of the object. This method is implemented in the Collidable
     * interface.
     *
     * @return the rectangle shape of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * This method is used to "notify" other object which has velocity that it collided with a colidable object.
     * This method is implemented in the Collidable interface.
     *
     * @param hitter          - the ball that hit the block.
     * @param collisionPoint  - the collision point of the objects.
     * @param currentVelocity - the current velocity of the hitting object.
     * @return the new velocity expected for the hitting object after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        /*
         * If the ball hits the left side of a block it will move right, if it hits the right side of a block it
         * will move left.
         */
        if (collisionPoint.isOnTheLine(this.collisionRectangle.getLeftSide())) {
            newDx = -Math.abs(newDx);
        } else if (collisionPoint.isOnTheLine(this.collisionRectangle.getRightSide())) {
            newDx = Math.abs(newDx);
        }

        /*
         * If the ball hits the bottom side of a block it will move up, if it hits the upper side of a block it
         * will move down.
         */
        if (collisionPoint.isOnTheLine(this.collisionRectangle.getBottomSide())) {
            newDy = Math.abs(newDy);
        } else if (collisionPoint.isOnTheLine(this.collisionRectangle.getUpperSide())) {
            newDy = -Math.abs(newDy);
        }

        //Notify all the listeners registered to the block that a hit occurred.
        this.notifyHit(hitter);
        return new Velocity(newDx, newDy);
    }

    /**
     * The method notify all of the registered HitListener objects that a hit was made.
     *
     * @param hitter - the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {

        //Creating a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new java.util.ArrayList<HitListener>(this.hitListeners);

        //Notifying all the listeners about the hit that occurred.
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The method draws the block to the screen. This method is implemented in the Sprite interface.
     *
     * @param d - the surface on which the block will be drawn. DrawSurface class is part of the biuoop package.
     */
    @Override
    public void drawOn(DrawSurface d) {

        //drawing the inside of the block with it's color.
        d.setColor(this.color);
        d.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(), (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());

        //drawing the black frame around the block.
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(), (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
    }

    /**
     * The method adds the block to the game that it's part of as a sprite and collidable object. This method is
     * implemented in the Sprite interface.
     *
     * @param g - the game to which the block is added.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * The method removes the block from the game environment and sprites collection of the game.
     *
     * @param game - the game we remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.getEnvironment().removeCollidable(this);
        game.getSprites().removeSprite(this);
    }

    /**
     * according to the instructions currently this method does nothing for the block class. This method is
     * implemented in the Sprite interface.
     */
    @Override
    public void timePassed() {
        return;
    }
}
