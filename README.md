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
 - compile with `ant compile`
 - There are two ways to run the game:
 - Running with `ant run` will run the four levels      by their order.
 - You can customizethe order of the levels being played.
 - - For example: using `ant -Dargs="4" run` will run only the fourth level.
 - - Using `ant -Dargs="1 4 3 4 3" run` will play the level 1,4,3,4,3 in this exact order.
## Playing the game

- The left and right arrow keys are used to control the movement of the paddle.
- Pressing `p` will pause the game.


