
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Tomer .
 * A decorator class which adds a "waiting-for-key" behavior to an existing animation so the animation will stop
 * when pressing a certain key.
 */
public class KeyPressStoppableAnimation implements Animation {

    //The keyboard which will be used by the class.
    private KeyboardSensor sensor;

    //The key that should be pressed to stop the animation.
    private String key;

    //The animation which will get the "waiting-for-key" behavior.
    private Animation animation;

    //Holds a false value until the animation should be stopped.
    private boolean stop;

    //Used in to prevent a bug which occurs when the key  was already pressed before the animation started running.
    private boolean isAlreadyPressed;

    /**
     * A constructor method of KeyPressStoppableAnimation class. It creates a new instance of a key stoppable
     * animation.
     *
     * @param sensor    - the keyboard which will be used by the class.
     * @param key       - the key that will be pressed to stop the animation.
     * @param animation - the animation which will get the "waiting-for-key" behavior.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;

        //Gets an initial true value to prevent a bug which occurs when the key pressed before the animation began.
        this.isAlreadyPressed = true;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);

        //If the key is pressed now and wasn't pressed before the animation began the animation stops.
        if ((sensor.isPressed(key)) && (!isAlreadyPressed)) {
            this.stop = true;
        }

        //After the animation began we know that the bug won't happen.
        if (!sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
