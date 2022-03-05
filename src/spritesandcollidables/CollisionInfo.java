
package spritesandcollidables;

import geometry.Point;

/**
 * @author Tomer .
 * The class CollisionInfo will be used to represent information about collision of two objects. The information
 * consists of the collision point and a reference to the collidable object that was hit.
 */
public class CollisionInfo {

    //The point where the collision occurred.
    private Point collisionPoint;

    //The collidable object that was hit.
    private Collidable collisionObject;

    /**
     * A constructor method of CollisionInfo class. It used to create a new instance of CollisionInfo.
     *
     * @param collisionPoint  - the point where the collision occurred.
     * @param collisionObject - the collidable object that was hit.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * A getter method for the collisionPoint field.
     *
     * @return the point where the collision occurred.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * A getter method for the collisionObject field.
     *
     * @return the collidable object that was hit.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
