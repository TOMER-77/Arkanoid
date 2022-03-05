
package run;

import animation.AnimationRunner;
import animation.GameOverScreen;
import animation.KeyPressStoppableAnimation;
import animation.YouWinScreen;
import biuoop.KeyboardSensor;
import listeners.Counter;

import java.util.List;

/**
 * @author Tomer .
 * The Gameflow class is in charge of running the different levels of the game, and moving from one level to the next.
 */
public class GameFlow {

    //The object that will run the animation of the game levels.
    private AnimationRunner animationRunner;

    //The keyboard which will be used by the class.
    private KeyboardSensor keyboardSensor;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar - The animation runner that will run the animation of the game levels.
     * @param ks - The keyboard which will be used by the class.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * The method runs the different levels of the game one by one.
     *
     * @param levels - a list of the levels which will be played one after another in the game.
     */
    public void runLevels(List<LevelInformation> levels) {

        //The key that should be pressed to exit the end screen of the game.
        String stopEndScreen = "space";

        //A counter for the levels which were played by the player.
        Counter numberOfLevel = new Counter();

        //A counter for the score that the player scored throughout the game.
        Counter score = new Counter();

        //The loop runs the levels in the levels list one by one by their order.
        for (LevelInformation levelInformation : levels) {
            numberOfLevel.increaseByOne();

            //Creating the level object for the current level,initializing it and then running the level.
            GameLevel level = new GameLevel(levelInformation, this.animationRunner, this.keyboardSensor, score);
            level.initialize();
            level.run();

            //If no balls were left in the game it means the player lost so the game over screen is shown.
            if (level.getNumberOfBallsInLevel() == 0) {
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, stopEndScreen,
                        new GameOverScreen(score)));
                this.animationRunner.getGui().close();
            } else if (numberOfLevel.getValue() == levels.size()) {

                //If the player cleared all the levels the winning screen is shown.
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, stopEndScreen,
                        new YouWinScreen(score)));
                this.animationRunner.getGui().close();
            }
        }
    }
}
