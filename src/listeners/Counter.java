
package listeners;

/**
 * @author Tomer .
 * The class Counter is used to count things.
 */
public class Counter {

    //Keeps the count.
    private int count;

    /**
     * A constructor method of Counter class. It creates a new instance of a counter.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * The method increases the counter by the passed parameter.
     *
     * @param number - the number that the counter will be increased by.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * The method increases the counter by One.
     */
    public void increaseByOne() {
        this.count++;
    }

    /**
     * The method decreases the counter by the passed parameter.
     *
     * @param number - the number that the counter will be decreased by.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * The method decreases the counter by One.
     */
    public void decreaseByOne() {
        this.count--;
    }

    /**
     * A getter method for the value field of Counter.
     *
     * @return the value of the counter.
     */
    public int getValue() {
        return this.count;
    }
}
