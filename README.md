# Arkanoid game
## About
The final version of a 5 parts project from the second semester of my first year as a computer science student. The project was made as part of intro to OOP course.

This is an implementation of the famous Arkanoid arcade game in Java.

The project doesn’t make use of standard Java GUI frameworks. It uses a GUI implementation supplied to the students by the staff of the course (the biuoop-1.4.jar).

The game has four different levels. The first two levels are easy and the others are more challenging.

The paddle is split into five parts and each part gives the ball a different velocity and direction when hitting it.

## Running the game
Compiling and running the game is made with apache ant.

 - Download the project or clone it using the command: `git clone https://github.com/TOMER-77/Arkanoid.git`
 - Navigate to the game folder.
 - compile with `ant compile`
 - There are two ways to run the game:
 - 1. Running with `ant run` will run the four levels      by their order.
 - 2. You can customize the order of the levels being played.
 - - - For example: using `ant -Dargs="4" run` will run only the fourth level.
 - - - Using `ant -Dargs="1 4 3 4 3" run` will play the levels 1,4,3,4,3 in this exact order.
## Playing the game

- The left and right arrow keys are used to control the movement of the paddle.
- Pressing `p` will pause the game.

<img width="600" alt="ark" src="https://user-images.githubusercontent.com/92518651/156892641-94db4fcc-cbd9-400e-9f03-4293ced758ca.png">



