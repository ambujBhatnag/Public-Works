/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobIntTester.java
 * Purpose    :  Test Harness for the BrobInt java class
 * @author    :  B.J. Johnson
 * Date       :  2017-04-05
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-05  B.J. Johnson  Initial writing and release
 *  1.1.0  2017-04-13  B.J. Johnson  Added new BrobInt instances to check addition; refactored to
 *                                     check the new versions of compareTo and equals; verified that all
 *                                     additions work for both small and large numbers, as well as for
 *                                     values of different lengths and including same-sign negative value
 *                                     additions; ready to start subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.text.DecimalFormat;

public class BrobIntTester {

   private static String g01String = "1200567890"; //add
   private static String g02String = "34000000"; //add
   private static String g03String = "-2324330000000";//a
   private static String g04String = "-5557893";//a
   private static String g05String = "-679076";//a
   private static String g06String = "365567697087";//a
   private static String g07String = "45678902345";//a
   private static String g08String = "-1023456543456543456";//a
   private static String g09String = "32345432253422";//s
   private static String g10String = "354354354353";//s
   private static String g11String = "-234567";//s
   private static String g12String = "-12345678765432";//s
   private static String g13String = "2345434543565435643";//s
   private static String g14String = "-12343234323432"; //s
   private static String g15String = "-23578954789";//s
   private static String g16String = "-6698987987089";//s
   private static String g17String = "23232"; //d
   private static String g18String = "2"; //d
   private static String g19String = "345432345432345432345"; //d
   private static String g20String = "23234323432344343243"; //d
   private static String g21String = "-11234567890"; //d
   private static String g22String = "68780"; //d
   private static String g23String = "45659608687"; //d
   private static String g24String = "-89787"; //d
   private static String g25String = "765"; //r
   private static String g26String = "3"; //r
   private static String g27String = "56789"; //r
   private static String g28String = "37"; //r
   private static String g29String = "22222222"; //r
   private static String g30String = "22222"; //r
   private static String g31String = "1000000001"; //r
   private static String g32String = "100000"; //r

   private static BrobInt g1 = null;
   private static BrobInt g2 = null;

   private static int testNumber = 1;
   private static DecimalFormat df = new DecimalFormat( "000" );


   public BrobIntTester() {
   }

   public static void main( String[] args ) {
      BrobIntTester git = new BrobIntTester();
      System.out.println("WELCOME TO BROBINT TEST.");

      System.out.println("ADD ----------------------");

      System.out.println("-->Test 1: ");
      g1 = new BrobInt(g01String);
      g2 = new BrobInt(g02String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Sum should be 1234567890: " + g1.add(g2).toString());

      System.out.println("-->Test 2: ");
      g1 = new BrobInt(g03String);
      g2 = new BrobInt(g04String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Sum should be -2324335557893: " + g1.add(g2).toString());

      System.out.println("-->Test 3: ");
      g1 = new BrobInt(g05String);
      g2 = new BrobInt(g06String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Sum should be 365567018011: " + g1.add(g2).toString());

      System.out.println("-->Test 4: ");
      g1 = new BrobInt(g07String);
      g2 = new BrobInt(g08String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Sum should be -1023456497777641111: " + g1.add(g2).toString());

      System.out.println("SUBTRACT -----------------");

      System.out.println("-->Test 1: ");
      g1 = new BrobInt(g09String);
      g2 = new BrobInt(g10String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Product should be 31991077899069: " + g1.subtract(g2).toString());

      System.out.println("-->Test 2: ");
      g1 = new BrobInt(g11String);
      g2 = new BrobInt(g12String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Product should be 12345678530865: " + g1.subtract(g2).toString());

      System.out.println("-->Test 3: ");
      g1 = new BrobInt(g13String);
      g2 = new BrobInt(g14String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Product should be 2345446886799759075: " + g1.subtract(g2).toString());

      System.out.println("-->Test 4: ");
      g1 = new BrobInt(g15String);
      g2 = new BrobInt(g16String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Product should be 6675409032300: " + g1.subtract(g2).toString());

      System.out.println("**Could not get multiply to work :( **");
      System.out.println("DIVIDE ------------------");

      System.out.println("-->Test 1: ");
      g1 = new BrobInt(g17String);
      g2 = new BrobInt(g18String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Quotient should be 11616: " + g1.divide(g2).toString());
      System.out.println("One Less, I do not know why.");

      System.out.println("-->Test 2: ");
      g1 = new BrobInt(g19String);
      g2 = new BrobInt(g20String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Quotient should be 14: " + g1.divide(g2).toString());

      // System.out.println("-->Test 3: ");
      // g1 = new BrobInt(g21String);
      // g2 = new BrobInt(g22String);
      // System.out.println("Value 1: " + g1.toString());
      // System.out.println("Value 2: " + g2.toString());
      // System.out.println("Quotient should be -163340: " + g1.divide(g2).toString());
      //
      // System.out.println("-->Test 4: ");
      // g1 = new BrobInt(g23String);
      // g2 = new BrobInt(g24String);
      // System.out.println("Value 1: " + g1.toString());
      // System.out.println("Value 2: " + g2.toString());
      // System.out.println("Quotient should be -508532: " + g1.divide(g2).toString());

      System.out.println("REMAINDER ------------------");

      System.out.println("-->Test 1: ");
      g1 = new BrobInt(g25String);
      g2 = new BrobInt(g26String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Quotient Remainder should be 6: " + g1.remainder(g2).toString());

      System.out.println("-->Test 2: ");
      g1 = new BrobInt(g27String);
      g2 = new BrobInt(g28String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Quotient Remainder should be 31: " + g1.remainder(g2).toString());

      System.out.println("-->Test 3: ");
      g1 = new BrobInt(g29String);
      g2 = new BrobInt(g30String);
      System.out.println("Value 1: " + g1.toString());
      System.out.println("Value 2: " + g2.toString());
      System.out.println("Quotient Remainder should be 222: " + g1.remainder(g2).toString());

      System.out.println("Only Positives");



   }
}
