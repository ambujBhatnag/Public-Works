/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *  1.2.0  2019-04-18  B.J. Johnson  Fixed bug in add() method that was causing errors in Collatz
 *                                     sequence.  Added some tests into the main() method at the bottom
 *                                     of the file to test construction.  Also added two tests there to
 *                                     test multiplication by three and times-3-plus-1 operations
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BrobInt {
  public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
  public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
  public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
  public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
  public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
  public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
  public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
  public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
  public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
  public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
  public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
  /// These are the internal fields
   public  String internalValue = "";        // internal String representation of this BrobInt
   public  byte   sign          = 0;         // "0" is positive, "1" is negative
   public int chunks = 0;
  /// You can use this or not, as you see fit.  The explanation was provided in class
   private String reversed      = "";        // the backwards version of the internal String representation
   public int[] brokIntArr = null;


   private static BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
   private static final boolean DEBUG_ON = false;
   private static final boolean INFO_ON  = false;
  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
     // replace this with the appropriate code to accomplish what is in the javadoc text
     this.internalValue = value;
     validateDigits();
     //determines how many integers in ra array
     if (value.charAt(0) == '-') {
       sign = 1;
       value = value.replace("-","");
     }
     else if (value.charAt(0) == '+') {
       value = value.replace("+","");
     }
     chunks = (int)Math.ceil(value.length()/9.0); //statistically more remainder cases; just have a specific method to handle evens
     brokIntArr = new int[chunks];
     //indexes for determine indexes for start and stop value for each integer
     int stopInd = value.length();
     int startInd = stopInd - 9;
     if (startInd < 0) {
       startInd = 0;
     }
     for (int i = 0; i < chunks; i++) {
       brokIntArr[i] = Integer.parseInt(value.substring(startInd,stopInd));
       stopInd -= 9;
       if (startInd - 9 < 0) {
         startInd = 0;
       } else {
         startInd -=9;
       }
     }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() throws IllegalArgumentException {
     boolean returnBool = true;
     for (int i = 0; i < this.internalValue.length(); i++) {
       int digit = Character.getNumericValue(this.internalValue.charAt(i));
       if ((digit < 9) || (digit >= 0)) {
         returnBool = true;
       }
       else {
         returnBool = false;
         throw new IllegalArgumentException("Error: Non-valid number/character in internalValue.");
       }
     }
     return returnBool;
   }

   public int digitCompareTo(BrobInt bint) {
     int thisStartIndex = 0;
     int bintStartIndex = 0;

     if (this.internalValue.charAt(0) == '-' || this.internalValue.charAt(0) == '+') {
       thisStartIndex = 1;
     }
     if (bint.internalValue.charAt(0) == '-' || bint.internalValue.charAt(0) == '+') {
       bintStartIndex = 1;
     }
     String thisString = this.internalValue.substring(thisStartIndex, this.internalValue.length());
     String bintString =  bint.internalValue.substring(bintStartIndex, bint.internalValue.length());

     if (thisString.length() > bintString.length()) {
       //if this is bigger
       return 1;
     } else if (thisString.length() < bintString.length()){
       //if bints is bigger
       return 0;
     } else {
       return -1;
     }

   }
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt bint ) {
     int carryOver = 0;
     int a = 0;
     int b =0;
     StringBuilder reverseString = new StringBuilder();
     String returnString = "";
     int longerValue = 0;
     int transferValue = 0;
     boolean thisLonger = true;
     BrobInt returnBrob = null;

     if (this.digitCompareTo(bint) == 1) {
       a = this.chunks;
       b = bint.chunks;
     } else if (this.digitCompareTo(bint) == 0) {
       a = bint.chunks;
       b = this.chunks;
       thisLonger = false;
     } else if (this.digitCompareTo(bint) == -1) {
       if (this.compareTo(bint) == 1) {
         a = this.chunks;
         b = bint.chunks;
       } else {
         a = bint.chunks;
         b = this.chunks;
         thisLonger = false;
       }
     }

     if ( ((this.sign == 0) && (bint.sign == 0)) || ((this.sign == 1) && (bint.sign == 1)) ) { //same signs needed

       for (int i = 0; i < a; i++) {

         if (thisLonger) {
           longerValue = Math.abs(this.brokIntArr[i]);
           if (i<b){transferValue = Math.abs(bint.brokIntArr[i]);} else {transferValue = 0;}
         }
         else {
           longerValue = Math.abs(bint.brokIntArr[i]);
           if (i<b){transferValue = Math.abs(this.brokIntArr[i]);} else {transferValue = 0;}
         }
         if (longerValue + transferValue + carryOver > 999999999) {
           int total = (Math.abs(longerValue) + (Math.abs(transferValue) + carryOver)) - 1000000000;
           if ( (i != a-1) && ((Integer.toString(total).length() < 9))) {
             reverseString = reverseString.append(this.addedZeroes(Integer.toString(total).length())+ Integer.toString(total));
           } else {
             reverseString = reverseString.append(total);
           } //change to prospective elements
           returnString += reverseString.reverse().toString();
           carryOver = 1;
           reverseString.delete(0, reverseString.toString().length());

         } else {
           int total = (Math.abs(longerValue) + (Math.abs(transferValue) + carryOver));
           if ( (i != a-1) && ((Integer.toString(total).length() < 9))) {
             reverseString = reverseString.append(this.addedZeroes(Integer.toString(total).length())+ Integer.toString(total));
           } else {
             reverseString = reverseString.append(total);
           } //change to prospective elements
           returnString += reverseString.reverse().toString();
           carryOver = 0;
           reverseString.delete(0, reverseString.toString().length());
        }
      }

      if ((this.sign == 1) && (bint.sign == 1)) {
        reverseString = reverseString.append(returnString).reverse();
        returnString = "-";
        returnString += reverseString.toString();
      } else if ((this.sign == 0) && (bint.sign == 0)) {
        reverseString.delete(0,reverseString.toString().length());
        reverseString = reverseString.append(returnString).reverse();
        returnString = reverseString.toString();
      }
     returnBrob = new BrobInt(returnString);

   } else if (thisLonger && (this.sign == 0) && (bint.sign == 1)) {
     bint.sign = 0;
     returnBrob = this.subtract(bint);
   } else if (thisLonger && (this.sign == 1) && (bint.sign == 0)) {
     this.sign = 0;
     returnString = "-" + this.subtract(bint).internalValue;
     returnBrob = new BrobInt(returnString);
   } else if (!thisLonger && (this.sign == 0) && (bint.sign == 1)) {
     bint.sign = 0;
     returnString = "-" + bint.subtract(this).internalValue;
     returnBrob = new BrobInt(returnString);
   } else if (!thisLonger && (this.sign == 1) && (bint.sign == 0)) {
     this.sign = 0;
     returnBrob = bint.subtract(this);
   }
   return returnBrob;
 }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt bint ) {
     int carryOver = 0;
     int a = 0;
     int b = 0;
     boolean thisLonger = true;
     StringBuffer reverseString = new StringBuffer();
     String changeString = "";
     String returnString = "";
     int longerValue = 0;
     int transferValue = 0;

     if (this.digitCompareTo(bint) == 1) {
       a = this.chunks;
       b = bint.chunks;
     } else if (this.digitCompareTo(bint) == 0) {
       a = bint.chunks;
       b = this.chunks;
       thisLonger = false;
     } else if (this.digitCompareTo(bint) == -1) {
       if (this.compareTo(bint) == 1) {
         a = this.chunks;
         b = bint.chunks;
       } else {
         a = bint.chunks;
         b = this.chunks;
         thisLonger = false;
       }
     }

     if ( ((this.sign == 0) && (bint.sign == 0)) || ((this.sign == 1) && (bint.sign == 1)) ) {

       for (int i = 0; i < a; i++) {
         if (thisLonger) {
           longerValue = this.brokIntArr[i];
           if (i<b){transferValue = bint.brokIntArr[i];} else {transferValue = 0;}
         }
         else {
           longerValue = bint.brokIntArr[i];
           if (i<b){transferValue = this.brokIntArr[i];} else {transferValue = 0;}
         }
         if (Math.abs(longerValue) - Math.abs(transferValue) - carryOver < 0) {
           int addedVals = (Math.abs(longerValue) + 1000000000) - Math.abs(transferValue) - carryOver;

           if ( (i != a-1) && ((Integer.toString(addedVals).length() < 9))) {
             reverseString = reverseString.append(this.addedZeroes(Integer.toString(addedVals).length())+ Integer.toString(addedVals));
           } else {
             reverseString = reverseString.append(addedVals);
           }

           changeString = reverseString.reverse().toString();
           returnString = changeString + returnString;
           carryOver = 1;
         }
         else {

           int addedVals = Math.abs(longerValue) - Math.abs(transferValue) - carryOver;
           if ( (i != a-1) && ((Integer.toString(addedVals).length() < 9))) {
             reverseString = reverseString.append(this.addedZeroes(Integer.toString(addedVals).length())+ Integer.toString(addedVals));
           } else {
             reverseString = reverseString.append(addedVals);
           } //change to prospective elements
           returnString += reverseString.reverse().toString();
           carryOver = 0;
         }


         reverseString.delete(0,reverseString.toString().length());
       }
       reverseString = reverseString.append(returnString).reverse();
       returnString = "";
       returnString += reverseString.toString();
       if (thisLonger && (this.sign == 1) && (bint.sign == 1)) {
         returnString = "-" + returnString;
       }
       else if (!thisLonger && (this.sign == 0) && (bint.sign == 0)) {
         returnString = "-" + returnString;
       }
     } else if (thisLonger && (this.sign == 0) && (bint.sign == 1)) {
       bint.sign = 0;
       returnString = this.add(bint).internalValue;
     } else if (!thisLonger && (this.sign == 1) && (bint.sign == 0)) {
       this.sign = 0;
       returnString = "-" + this.add(bint);
     } else if (!thisLonger && (this.sign == 0) && (bint.sign == 1)) {
       bint.sign = 0;
       returnString = this.add(bint).internalValue;
     } else if (thisLonger && (this.sign == 1) && (bint.sign == 0)) {
       this.sign = 0;
       returnString = "-" + this.add(bint).internalValue;
     }
     return new BrobInt(returnString);
   }

   public static String addedZeroes(int numOfDigits) {
     int numOfZ = 9 - numOfDigits;
     String zeroString = "";
     for (int i = 0; i < numOfZ; i++) {
       zeroString += "0";
     }
     return zeroString;
   }
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to multiply this by
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt bint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  bint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt bint ) {
     BrobInt newVal = this.subtract(bint);
     int lastDig = bint.internalValue.charAt(bint.internalValue.length() - 1);
     int dividedAmts = 0;
     String returnString = "";
     if (bint.equals(ZERO)) {
       throw new IllegalArgumentException("Denominator cannot be zero.");
     }
     if (bint.internalValue.length() > this.internalValue.length()) {
       return ZERO;
     } else {
       if ( (this.sign == 1) && (bint.sign == 0)) {

         this.sign = 0;
         returnString = "-";

       } else if ((this.sign == 1) && (bint.sign == 1)) {
         this.sign = 0;
         bint.sign = 0;
       } else if ((this.sign == 0) && (bint.sign == 1)) {
         bint.sign = 0;
         returnString = "-";
       } else if ((this.sign == 1) && (bint.sign == 0)) {
         this.sign = 0;
       }
       if ( (lastDig == '0') || (lastDig == '2') || (lastDig =='4') || (lastDig =='6') || (lastDig =='8')) {
         dividedAmts += 2;
       } else {
         dividedAmts += 1;
       }

       while (newVal.compareTo(ZERO) == 1) {
         newVal = newVal.subtract(bint);
         dividedAmts++;

         if ((newVal.subtract(bint).compareTo(ZERO) == -1) || (newVal.subtract(bint).compareTo(ZERO) == 0) ) {
           break;
         }
       }
       returnString += dividedAmts;
       return new BrobInt(returnString);
     }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  bint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt bint ) {
     BrobInt newVal = this.subtract(bint);
     int lastDig = bint.internalValue.charAt(bint.internalValue.length() - 1);
     int dividedAmts = 0;
     String returnString = "";
     if (((this.sign == 1) && (bint.sign == 0)) || ((this.sign == 0) && (bint.sign == 1))) {
       returnString += "-";
       if (this.sign == 1) {
         this.internalValue = this.internalValue.substring(1, this.internalValue.length());
       } else if (bint.sign == 1) {
         bint.internalValue = bint.internalValue.substring(1, bint.internalValue.length());
       }
     }
     this.sign = 0;
     bint.sign = 0;
     if (bint.equals(ZERO)) {
       throw new IllegalArgumentException("Denominator cannot be zero.");
     }
     if (bint.digitCompareTo(this) == 1) {
       return ZERO;
     } else {
       while (newVal.compareTo(ZERO) == 1) {
         newVal = newVal.subtract(bint);

         if ((newVal.subtract(bint).compareTo(ZERO) == -1) || (newVal.subtract(bint).compareTo(ZERO) == 0) ) {
           break;
         }
       }
       return newVal;
     }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  bint  BrobInt to compare to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt bint ) {

     // remove any leading zeros because we will compare lengths
       String me  = removeLeadingZeros( this ).toString();
       String arg = removeLeadingZeros( bint ).toString();

     // handle the signs here
      if( 1 == sign && 0 == bint.sign ) {
         return -1;
      } else if( 0 == sign && 1 == bint.sign ) {
         return 1;
      } else if( 0 == sign && 0 == bint.sign ) {
        // the signs are the same at this point ~ both positive
        // check the length and return the appropriate value
        //   1 means this is longer than bint, hence larger positive
        //  -1 means bint is longer than this, hence larger positive
         if( me.length() != arg.length() ) {
            return (me.length() > arg.length()) ? 1 : -1;
         }
      } else {
        // the signs are the same at this point ~ both negative
         if( me.length() != arg.length() ) {
            return (me.length() > arg.length()) ? -1 : 1;
         }
      }

     // otherwise, they are the same length, so compare absolute values
      for( int i = 0; i < me.length(); i++ ) {
         Character a = Character.valueOf( me.charAt(i) );
         Character b = Character.valueOf( arg.charAt(i) );
         if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
            return 1;
         } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
            return (-1);
         }
      }
      return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  bint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt bint ) {
      return ( (sign == bint.sign) && (this.toString().equals( bint.toString() )) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value    long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt bi = null;
      try { bi = new BrobInt( Long.valueOf( value ).toString() ); }
      catch( NumberFormatException nfe ) { throw new NumberFormatException( "\n  Sorry, the value must be numeric of type long." ); }
      return bi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
     return internalValue;

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to remove leading zeros from a BrobInt passed as argument
   *  @param  bint     BrobInt to remove zeros from
   *  @return BrobInt that is the argument BrobInt with leading zeros removed
   *  Note that the sign is preserved if it exists
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt removeLeadingZeros( BrobInt bint ) {
      Character sign = null;
      String returnString = bint.toString();
      int index = 0;

      if( allZeroDetect( bint ) ) {
         return bint;
      }
      if( ('-' == returnString.charAt( index )) || ('+' == returnString.charAt( index )) ) {
         sign = returnString.charAt( index );
         index++;
      }

      if( returnString.charAt( index ) != '0' ) {
         return bint;
      }

      // while( returnString.charAt( index ) == '0' ) {
      //    index++;
      // }
      returnString = bint.toString().substring( index, bint.toString().length() );
      if( sign != null ) {
         returnString = sign.toString() + returnString;
      }
      return new BrobInt( returnString );

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a boolean if a BrobInt is all zeros
   *  @param  bint     BrobInt to compare to this
   *  @return boolean  that is true if the BrobInt passed is all zeros, false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean allZeroDetect( BrobInt bint ) {
      for( int i = 0; i < bint.toString().length(); i++ ) {
         if( bint.toString().charAt(i) != '0' ) {
            return false;
         }
      }
      return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  @param   d  byte array from which to display the contents
   *  NOTE: may be changed to int[] or some other type based on requirements in code above
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( byte[] d ) {
      System.out.println( "Array contents: " + Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display a prompt for the user to press 'ENTER' and wait for her to do so
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void pressEnter() {
      String inputLine = null;
      try {
         System.out.print( "      [Press 'ENTER' to continue]: >> " );
         inputLine = input.readLine();
      }
      catch( IOException ioe ) {
         System.out.println( "Caught IOException" );
      }

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  NOTE:  we don't really care about these, since we test the BrobInt class with the BrobIntTester
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
     BrobInt bi1 = new BrobInt(args[0]);
     BrobInt bi2 = new BrobInt(args[1]);
     System.out.println(bi1.divide(bi2));
   }
}
