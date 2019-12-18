/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  Provides a way to keep track of time for SoccerSim and Ball class
 *  @author       :  Ambuj Bhatnagar
 *  Date written  :  2019-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim and Ball class
 *                   for Homework 4, part 2.
 *  Notes         :  None right now.  I'll add some as they occur.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0    2019-03-13  Ambuj B       Finished class build, untested, toString representation incorrect COMMENTS NEEDED
 *  @version 2.0    2019-03-18  Ambuj B       Finished class build, unt, toString incorrect, no static variables
 *  @version 3.0    2019-03-18  Ambuj B       Final Version
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

public class Timer{
  public double timeSlice = 1.0;
  public double totalSeconds = 0.0;

  public Timer(double timeSlice) {
    this.timeSlice = timeSlice;
  }

/*
 * @returns a double of total seconds
 */
  public double tick() {
    totalSeconds += timeSlice;
    return totalSeconds;
  }

/*
 * @returns string representation of total seconds that have passed.
 * NOTES: WRONG format
 */
  public String toString() {
    int hours = (int)Math.floor(totalSeconds/3600);
    int minutes = (int)Math.floor((totalSeconds - hours * 3600)/60);
    int seconds = (int)totalSeconds - (hours * 3600 + minutes * 60);
    return hours + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
  }
}
