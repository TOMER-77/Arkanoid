
package physics;

import geometry.Point;

/**
 * @author Tomer .
 * The class Velocity is used in order to represent and apply the rate of change in the position of moving objects.
 * The velocity is based on the change in the x and y values of the object.
 */
public class Velocity {

    /*
     * The rate of change of the position on the x axis.
     * Positive value means the object is moving to the right, negative value means movement to the left.
     */
    private double dx;

    /*
     * The rate of change of the position on the y axis.
     * Positive value means the object is moving down, negative value means movement up. This is because the y axis
     * in the java system is pointing down.
     */
    private double dy;

    /**
     * A constructor method to Ball class. It used to create a new instance of Ball.
     *
     * @param dx - the velocity on the x axis.
     * @param dy - the velocity on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * A method that returns new velocity instance based on a speed and angle of movement instead of separated
     * velocities for x and y. The method is static as was stated in the instructions.
     *
     * @param angle - the angle in which the object moves
     * @param speed - the speed of the object.
     * @return a new Velocity instance.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        /*
         * The y axis in the java system points down and not up, for this reason dx is multiplied by sinus and dy is
         * multiplied by -cosinus in order to make speed with angle 0 move up as written in the instructions.
         */
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));

        //The velocities of the x and y movement are sent to the constructor of Velocity.
        return new Velocity(dx, dy);
    }

    /**
     * A getter method for dx field of the velocity.
     *
     * @return the velocity of the object on the x axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * A getter method for dy field of the velocity.
     *
     * @return the velocity of the object on the y axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * The method return the speed of an object which is the magnitude of the change of it's position without taking
     * the direction into account.
     *
     * @return a speed clculated from the velocity.
     */
    public double getSpeed() {

        //speed = sqrt(dx^2+dy^2).
        return Math.sqrt(Math.abs(this.getDx() * this.getDx()) + Math.abs(this.getDy() * this.getDy()));
    }

    /**
     * The method is changing the position of a point according to it's velocity.
     *
     * @param p - the point that the velocity will be applied to.
     * @return a point with the updated coordinates after applying the velocity.
     */
    public Point applyToPoint(Point p) {

        //the returned point is the new coordinate of the point p.
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}