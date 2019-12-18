/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Creates a Ball instance that behaves like a ball on a field
 *  @author       :  Ambuj Bhatnagar
 *  Date written  :  2019-03-13
 *  Description   :  This class creates a ball instance that adheres to the physics specified
 *                   in Homework 4, part 2. Used in SoccerSim.
 *  Notes         :  None right now.  I'll add some as they occur.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0    2019-03-13  Ambuj B       Finished class build, untested, main argument needed?
 *  @version 2.0    2019-03-18  Ambuj B       Finished class build, main test, (works), got rid of unneeded static variables and methods
 *  @version 3.0    2019-03-18  Ambuj B       Final version
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

public class Ball {
  public double xLoc = 0;
  public double yLoc = 0;
  public double xVel = 0;
  public double yVel = 0;
  public double radius = 4.45;
  public static double timeSlice = 1.0;

/*
 * Constructor for Ball.java
 * Initializes each value to overarching variable in each Ball instance
 * SoccerSim arguments are parsed and the created in this instance
 * PROB: how to update timeslice ?
 */
   public Ball(double xLoc, double yLoc, double xVel, double yVel) {
     //how do I impliment radius and weight? are they important factors?
     this.xLoc = xLoc;
     this.yLoc = yLoc;
     this.xVel = xVel;
     this.yVel = yVel;
   }

/*
 * @returns nothing
 * Moves ball within field
 * ball moves once and and then velocity is updated
 */
   public void move() {
     xLoc += xVel * timeSlice;
     yLoc += yVel * timeSlice;
     xVel = updateVel(xVel);
     yVel = updateVel(yVel);
   }

/*
 * @returns Updated velocity for X-Vel and Y-Vel
 * called twice (probably in for loop)
 */
   public double updateVel(double vel) {
     vel *= Math.pow(0.99,timeSlice);
     if ( (vel < 1/12) && (vel >1/12) ) {vel= 0;}
     return vel;
   }

/*
 * @returns a boolean whether or not ball is still in motion
 */
   public boolean isNotInMotion() {
     return ( (xVel == 0) && (yVel == 0) );
   }

/*
 * @returns net velocity
 * distance formula = (( (x^2) +(y^2) )^0.5)
 * needed?
 */
   public double getVelocity() {
     return (double)Math.sqrt( (xVel*xVel) + (yVel*yVel) );
   }

/*
 * @returns string representation of x and y location
 */
   public String toString() {
     return xLoc + "," + yLoc;
   }

/*
 * @returns a boolean if ball is within the bounds of the field
 */
   public boolean isInBounds() {
     boolean inBounds = false;
     if ( (xLoc <= 1000) && (xLoc >= -1000) ) {
       if ( (yLoc <= 1000) && (yLoc >= -1000) ) {
         inBounds = true;
       }
     }
     return inBounds;
   }

   public static void main(String args[]) {
     Ball testBall = new Ball(5,5,100,-3);
     System.out.println(testBall.toString());
     System.out.println(testBall.xVel + ":" + testBall.yVel);
     testBall.move();
     System.out.println(testBall.toString());
     System.out.println(testBall.xVel + ":" + testBall.yVel);
   }
}
