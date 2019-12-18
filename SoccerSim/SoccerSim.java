/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  Creates a field that shos the behavior of balls that move on the field
 *  @author       :  Ambuj Bhatnagar
 *  Date written  :  2019-03-13
 *  Description   :  This class uses the ball class to create multiple ball instances which test where they travel to
 *                   and collide. Collisions are logged (when and where) For Homework 4, part 2.
 *  Notes         :  None right now.  I'll add some as they occur.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0    2019-03-13  Ambuj B       unfinished class build, validateArgs() WN, hitPole() SND,
 *  @version 1.0    2019-03-13  Ambuj B       distanceBetween() radius implimentation ??, main() WN COMMENTS NEEDED
 *  @version 2.0    2019-03-15  Ambuj B       Unf Class Build, validatedArgs() updated and untested. Rest WN.
 *  @version 3.0    2019-03-18  Ambuj B       Unf class build, eradication of unneeded static methods and variables
 *  @version 3.0    2019-03-18  Ambuj B       unfinished main method and distanceBetween().
 *  @version 4.0    2019-03-18  Ambuj B       Unf. All tests work except last two and one ball that stops at 4:42.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

public class SoccerSim {
  public final double xSize = 1000;
  public final double ySize = 1000;
  public Ball[] ballArr = null;
  public double timerSlice = 1.0;
  public final double diameter = (4.45/12) * 2;

  public SoccerSim() {
  }

/*
 * @returns nothing; validates all arguments
 * @throws IllegalArgumentException
 */
  public void validateArgs(String args[]) {
    if ( (args.length < 4) || ( (args.length % 4) > 1) ) {
      throw new IllegalArgumentException("Error: Wrong number of arguments.");
    }

    //if there is a timeslice or not, then for loop is executed
    if (args.length % 4 <= 1) {
      ballArr = new Ball[(int)args.length / 4];
      //for loop for every ball instance
      for (int i = 0; i < ballArr.length; i++) {
        //initializes each argument to each necessary value to initialize each ball instance
        double xLocat = Double.parseDouble(args[0 + (4*i) ]);
        double yLocat = Double.parseDouble(args[1 + (4*i) ]);
        double xVel = Double.parseDouble(args[2 + (4*i) ]);
        double yVel = Double.parseDouble(args[3 + (4*i) ]);

        //validates x-location and y-location arguments
        //Anything else needed for validation?
        if (!( (xLocat <= xSize) && (xLocat >= (-1*xSize)))) {
          throw new IllegalArgumentException("Error: X Location must be above/equal to -1000 and below/equal to 1000.");
        }
        if (!( (yLocat <= ySize) && (yLocat >= (-1*ySize)))) {
          throw new IllegalArgumentException("Error: Y Location must be above/equal -1000 and below/equal to 1000.");
        }

        //stores each ball instance in ball array
        ballArr[i] = new Ball(xLocat, yLocat, xVel, yVel);

        //all information about each ball
      } //for

      //if there is a timeSlice argument, the timeSlice values for Timer and Ball class is changed to the argument.
      if (args.length % 4 == 1) {
        //timeSlice validation
        if (Double.parseDouble(args[args.length - 1]) <= 0) {
          throw new IllegalArgumentException("Error: TimeSlice must be a positive number.");
        }
        if (Double.parseDouble(args[args.length - 1]) > 1800) {
          throw new IllegalArgumentException("Error: TimeSlice must be a positive number below or equal to 1800");
        }

        timerSlice = Double.parseDouble(args[args.length -1]);
        Ball.timeSlice = timerSlice;

      } //if2
    } //if1
    //if there are less than four arguments for each ball instance
  }
  public void ballsList() {
    int i = 1;
    for (Ball ball: ballArr) {
      System.out.println("Ball Number: " + (i) );
      System.out.println("X-location: " + ball.xLoc);
      System.out.println("Y-location: " + ball.yLoc);
      System.out.println("X-Velocity: " + ball.xVel);
      System.out.println("Y-Velocity: " + ball.yVel);
      System.out.println("---------------------");
      i++;
    }
  }
/*
 * @returns boolean that says if the ball hit a pole
 * sets x-velocity and y-velocity to 0
 */
  public boolean hitPole(Ball b) {
    boolean returnBool = false;
    if ( (b.xLoc + (4.45/12) == 5) && (b.yLoc + (4.45/12) == 5) ) {
      b.xVel = 0;
      b.yVel = 0;
      returnBool = true;
    }
    return returnBool;
  }



/*
 * @returns the distance between two balls
 * NOTES: test1 (two balls with same y)
 */
  public double distanceBetween(Ball ball1, Ball ball2) {
    //distance between two balls
    double distanceForm = Math.hypot(ball1.xLoc - ball2.xLoc, ball1.yLoc - ball2.yLoc);
    return distanceForm;
  }

  public static void main(String args[]) {
    String collisionString = ""; //collision coordinates
    SoccerSim soccerTest = new SoccerSim();

    soccerTest.validateArgs( args );
    soccerTest.ballsList();
    Timer timer = new Timer(soccerTest.timerSlice);

    boolean collisionBoolean = true;
    //while loop in charge of keeping objects moving
    while (collisionBoolean) {
      timer.tick();
      //for loop for other parts of program
      int stoppedBalls = 0;
      for (Ball item: soccerTest.ballArr) {
        item.move();
        //checks if any of the items hit the stationary object at 5,5
        if (soccerTest.hitPole(item)) {
          System.out.println("Ball hit a pole @ 5,5");
          collisionBoolean = false;
        }
        //Stops program if ball is out of bounds
        if (!item.isInBounds()) {
          System.out.println("Ball went out of bounds!");
          return;
        }
        //checks if balls have stopped
        if (item.isNotInMotion()) {
          stoppedBalls++;
          collisionString += "[" + item.toString() + "]";
        }
      } //for
      //stops program if all balls have stopped
      if (stoppedBalls == soccerTest.ballArr.length) {
        collisionBoolean = false;
        System.out.println(collisionString);
        System.out.println(timer.toString());
        return;
      }

      //two for loops that compare each ball object to eachother
      for (int i = 0; i <= soccerTest.ballArr.length - 2; i++) {
        for (int k = i + 1; k <= soccerTest.ballArr.length - 1; k++) {
          //if the distance between two of any of the ball objects is less than the diameter, a collision occurs
          if (soccerTest.distanceBetween(soccerTest.ballArr[i], soccerTest.ballArr[k]) <= soccerTest.diameter) {
            if (soccerTest.ballArr[i].isInBounds() && soccerTest.ballArr[k].isInBounds()) {
              System.out.println("Collision Detected Between Ball " + (i+1) + " And Ball " + (k+1));
              collisionString = soccerTest.ballArr[k].toString();
              collisionBoolean = false;
              //collisionString is set to ball k coordinates and while loop will be broken
            }
          }
        } //for2
      } //for1
      //timer ticks
    } //while
    System.out.println(collisionString);
    System.out.println(timer.toString());
    System.out.println("--------------------");
  }
}
