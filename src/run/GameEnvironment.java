
package run;

import geometry.Line;
import geometry.Point;
import spritesandcollidables.Collidable;
import spritesandcollidables.CollisionInfo;
import spritesandcollidables.Paddle;

import java.util.List;

/**
 * @author Tomer .
 * The Game environment holds a collection of all the collidable objects of the game.
 */
public class GameEnvironment {

    //A list of all the collidable objects of the game.
    private java.util.List<Collidable> collidables;

    //The paddle of the game.
    private Paddle paddle;

    /**
     * A constructor method to GameEnvironment class. It creates a instance of a game environment without a paddle.
     */
    public GameEnvironment() {
        this.collidables = new java.util.ArrayList<Collidable>();
        this.paddle = null;
    }

    /**
     * A getter method for the collidables field of the game environment.
     *
     * @return a list of the collidable objects of the game.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * A getter method for the paddle field of the game environment.
     *
     * @return the paddle of the game.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * A setter method which sets a paddle for the game environment.
     *
     * @param newPaddle - the new paddle of the game environment.
     */
    public void setPaddle(Paddle newPaddle) {
        this.paddle = newPaddle;
    }

    /**
     * The method adds a colidable object to the list of collidable objects of the game environment.
     *
     * @param c - the collidable object to be added to the game environment.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * The method removes a collidable object from the list of collidable objects of the game environment.
     *
     * @param c - the collidable object to be removed from the game environment.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * The method returns information about the closest collision in the trajectory. The information consists of the
     * collision point and a reference to the collidable object that was hit.
     *
     * @param trajectory - the trajectory of the hitting object.
     * @return information about the closest collision in the trajectory. Null is returned if there is no collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        /*
         * Creating list of the objects which might get hit in the trajectory and list of the colliding points. Each
         *  hitting point weill be stored in matching index with the hitted object.
         */
        java.util.List<Collidable> collidingWith = new java.util.ArrayList<Collidable>();
        java.util.List<Point> collidingPoints = new java.util.ArrayList<Point>();

        /*
         * The loop goes over the collidable objects in the game environment and checking if they intersects with
         * the trajectory of the hitting object. If there is a collision the hitted collidable object and the
         * collision point are added to the matching list.
         */
        for (int i = 0; i < this.collidables.size(); i++) {
            Point possibleCollisoinPoint =
                    trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle());
            if (possibleCollisoinPoint != null) {
                collidingWith.add(collidables.get(i));
                collidingPoints.add(possibleCollisoinPoint);
            }
        }

        //null is returned if there is no collision.
        if (collidingWith.isEmpty()) {
            return null;
        }
        int j = 0;

        //Storing information about the first collision which for now is "the closest" collision.
        Collidable closestCollision = collidingWith.get(j);
        Point closestCollisionPoint = collidingPoints.get(j);
        double closestDistance = trajectory.start().distance(closestCollisionPoint);

        /*
         * The loop goes over all the collision points in the trajectory and checks which one of them is the closest
         *  to the start point of the trajectory.
         */
        for (j = 1; j < collidingWith.size(); j++) {
            double distance = trajectory.start().distance(collidingPoints.get(j));
            if (distance < closestDistance) {
                closestDistance = distance;
                closestCollision = collidingWith.get(j);
                closestCollisionPoint = collidingPoints.get(j);
            }
        }

        //After finding the closest collision the information about it is returned.
        return new CollisionInfo(closestCollisionPoint, closestCollision);
    }
}
