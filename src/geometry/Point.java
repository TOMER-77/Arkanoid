
package geometry;

/**
 * @author Tomer .
 * The class Point represents a two-dimensional point with x and y value.
 */
public class Point {

    //EPSILON will be used to overcome precision bugs.
    static final double EPSILON = Math.pow(10, -8);

    //x value of the coordinate of the point.
    private double x;

    //y value of the coordinate of the point.
    private double y;

    /**
     * A constructor method for Point class.It creates a new instance of Point.
     *
     * @param x - the x value of the point.
     * @param y - the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A getter method for the y value of the point.
     *
     * @return the x value of a point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * A getter method for the y value of the point.
     *
     * @return the y value of a point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * A setter method which sets a new x value for a point.
     *
     * @param newX - the new value for the x field of the Point.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * A setter method which sets a new y value for a point.
     *
     * @param newY - the new value for the y field of the Point.
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * The method checks if two Points have the same x and y values.
     *
     * @param other - other instance of Point.
     * @return true if the points have the same x and y values (the points are equal), false if not.
     */
    public boolean equals(Point other) {

        /*
         * The values of x and y are checked to be less than epsilon and not compared directly with '==' operator to
         *  avoid bugs caused by precision inaccuracies.
         */
        if ((Math.abs(this.x - other.getX()) < EPSILON) && (Math.abs(this.y - other.getY()) < EPSILON)) {
            return true;
        }
        return false;
    }

    /**
     * The method calculates and returns the distance between two points.
     *
     * @param other - other instance of Point.
     * @return the distance between the Point and the other Point (a double value).
     */
    public double distance(Point other) {

        //The distance is calculated with the formula: d = sqrt((x1-x2)^2 + (y1-y2)^2)) .
        double distanceX = (this.x - other.getX()) * (this.x - other.getX());
        double distanceY = (this.y - other.getY()) * (this.y - other.getY());
        return Math.sqrt(distanceX + distanceY);
    }

    /**
     * The method checks if a Point is located on both lines sent as parameters.
     *
     * @param l1 - an instance of Line object.
     * @param l2 - another instance of Line object.
     * @return true if the point is on both lines, else false is returned.
     */
    boolean isOnTheLines(Line l1, Line l2) {

        //Calling a method to check if the point is on each of the lines.
        return (this.isOnTheLine(l1) && this.isOnTheLine(l2));
    }

    /**
     * The method checks if a Point is located on the line sent as a parameter.
     *
     * @param l - an instance of Line object.
     * @return true if the point is on the line, false is returned if not.
     */
    public boolean isOnTheLine(Line l) {

        /*
         * crating an instance of the line where the start point is the edge point with the lowest x value (or lowest
         * y value if the x value of the start and end are the same, It is done to make the calculations easier.
         */
        Line copyOfLine = l.copyForCalculations();

        /*
         * The next two if conditions are checking if the x value and the y value of the point are on the line.
         * epsilon is added or subtracted in order to avoid bugs caused by precision inaccuracies.
         */
        if (this.getX() < copyOfLine.start().getX() - EPSILON || copyOfLine.end().getX() + EPSILON < this.getX()) {
            return false;
        }
        if ((copyOfLine.getLowestPoint().getY() - EPSILON <= this.getY() && this.getY()
                <= copyOfLine.getHighestPoint().getY() + EPSILON)) {
            return true;
        }
        return false;
    }
}
