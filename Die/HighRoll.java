/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  MainProgLoopDemo.java
 *  Purpose       :  Demonstrates the use of input from a command line for use with Yahtzee
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-14
 *  Description   :
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-14  B.J. Johnson  Initial writing and release
 *  @version 2.0.0  2019-02-14  AJ   B        Final Version
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{

   public static void main( String args[] ) {
      int numberOfDice = 0;
      int numberOfSides = 0;
      int highScore = 0;

      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );

     // This line uses the two classes to assemble an "input stream" for the user to type
     // text into the program

      while( true ) {
        System.out.println( "\n   Welcome to  HighRoll!\n" );
        System.out.println( "     Press the '1' key to roll all the dice" );
        System.out.println( "     Press the '2' key to roll a single die." );
        System.out.println( "     Press the '3' key to calculate the score for this set." );
        System.out.println( "     Press the '4' key to save this score as a high score." );
        System.out.println( "     Press the '5' key to display the high score." );
        System.out.println( "     Press the '6' key to quit the program." );
        System.out.println("\n");

         String inputLine = null;
         DiceSet ds = null;
        try {
            inputLine = input.readLine();
            numberOfDice = Integer.parseInt(args[0]);
            numberOfSides = Integer.parseInt(args[1]);

            ds = new DiceSet(numberOfDice,numberOfSides);
            if( 0 == inputLine.length() ) {
               System.out.println("enter some text!:");
            }
            System.out.println( "   You typed: " + inputLine );
            if( '1' == inputLine.charAt(0) ) {
               ds.roll();
              System.out.println(ds.toString());
            }
            if( '2' == inputLine.charAt(0) ) {
              System.out.println("Enter a Dice Number from 1 to " + numberOfDice);
              int chosenDieIndex = Integer.parseInt( input.readLine() );
              if ( (chosenDieIndex < 1) && (chosenDieIndex > numberOfDice) ) {
                throw new IllegalArgumentException("Error: Dice Number must be between 1 and " + numberOfDice);
              }
              ds.rollIndividual(chosenDieIndex - 1);
              System.out.println(ds.toString());
            }
            if( '3' == inputLine.charAt(0) ) {
              ds.sum();
              System.out.println("Your high score has been calculated.");
            }
            if( '4' == inputLine.charAt(0) ) {
              highScore = ds.sum();
              System.out.println("Your high score has been saved.");
            }
            if( '5' == inputLine.charAt(0) ) {
              System.out.println("Your current high score is: " + highScore);
            }
            if( '6' == inputLine.charAt(0) ) {
              System.out.println("Now exiting the program.");
               break;
            }
          }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
      }
   }
}
