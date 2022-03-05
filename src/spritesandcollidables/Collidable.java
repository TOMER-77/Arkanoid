
package spritesandcollidables;

import geometry.Point;
import geometry.Rectangle;
import physics.Velocity;

/**
 * @author Tomer .
 * The interface Collidable will represent the colidable objects of the game which are the blocks and paddle.
 */
public interface Collidable {

    /**
     * A getter method for the CollisionRectangle field of the object.
     *
     * @return the rectangle shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * This method is used to "notify" other object which has velocity that it collided with a colidable object.
     *
     * @param hitter          - the ball that hit the collidable object
     * @param collisionPoint  - the collision point of the objects.
     * @param currentVelocity - the current velocity of the hitting object.
     * @return the new velocity expected for the hitting object after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}