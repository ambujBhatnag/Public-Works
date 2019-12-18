
/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Ambuj Bhatnagar
 *  Date          :  01/28/19
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 */
public class CalendarStuff {
  
  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
     boolean leapYear = false;
     if(year % 4 == 0) {
       if (year % 400 == 0) {
           leapYear = true;
       }
       else if (!(year % 100 == 0)) {
         leapYear = true;

       }
     }

      return leapYear;
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
      boolean yearIsLeap = isLeapYear(year);
      month--;
      int monthDays = days[(int)month];
      if ((month == FEBRUARY) && yearIsLeap) {
         monthDays++;
      }
      return monthDays;
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     if ( (month1 == month2) && (day1 == day2) && (year1 == year2)) {
       return true;
     }
     else {
      return false;
    }
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   //IS THIS METHOD ASSUMING THAT THE DATES ARE VALID AND REAL
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     int compareDateValue;
     if (year1 > year2) {
       compareDateValue = 1;
     } else if (year1 < year2) {
       compareDateValue = -1;
     } else {
       if (month1 > month2) {
         compareDateValue = 1;
       } else if (month1 < month2) {
         compareDateValue = -1;
       } else {
         if (day1 > day2) {
           compareDateValue = 1;
         } else if (day1 < day2) {
           compareDateValue = -1;
         } else {
           compareDateValue = 0;
         }
       }
     }

      return compareDateValue;
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
     //WHY DO THE NOTES PARTS, seems unnecessary?
     boolean validReturnDate = false;
     month--;
     if (month == FEBRUARY) {
       if ( (isLeapYear(year)) && ( (day <= 29) && (day > 0) ) ) {
         validReturnDate = true;

       }
       else if ( (!isLeapYear(year)) && ( (day <= 28) && (day > 0) ) ) {
         validReturnDate = true;
       }
     }
     else if ( (month >= JANUARY) && (month <= JUNE) ) {
       if ( (month % 2 == 0) && ( (day <= 31) && (day > 0) ) ) {
         validReturnDate = true;
       }
       else if ( (month % 2 == 1) && ( (day <= 30) && (day > 0) ) ) {
         validReturnDate = true;
       }
     }
     else if (month >= JULY && month <= DECEMBER) {
       if ( (month % 2 == 1) && ( (day <= 31) && (day > 0) ) ) {
         validReturnDate = true;
       }
       else if ( (month % 2 == 0) && ( (day <= 30) && (day > 0) ) ) {
         validReturnDate = true;
       }
     }

      return validReturnDate;
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
     String monthReturn;
     switch( month - 1 ) {
        case JANUARY: monthReturn = "January";
        break;
        case FEBRUARY: monthReturn = "February";
        break;
        case MARCH: monthReturn =  "March";
        break;
        case APRIL: monthReturn =  "April";
        break;
        case MAY: monthReturn =  "May";
        break;
        case JUNE: monthReturn =  "June";
        break;
        case JULY: monthReturn =  "July";
        break;
        case AUGUST: monthReturn =  "August";
        break;
        case SEPTEMBER: monthReturn =  "September";
        break;
        case OCTOBER: monthReturn =  "October";
        break;
        case NOVEMBER: monthReturn =  "November";
        break;
        case DECEMBER: monthReturn =  "December";
        break;
        default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
      return monthReturn;
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
     String dayReturn;
      switch( day - 1 ) {
        case SUNDAY: dayReturn = "Sunday";
        break;
        case MONDAY: dayReturn = "Monday";
        break;
        case TUESDAY: dayReturn = "Tuesday";
        break;
        case WEDNESDAY: dayReturn = "Wednesday";
        break;
        case THURSDAY: dayReturn = "Thursday";
        break;
        case FRIDAY: dayReturn = "Friday";
        break;
        case SATURDAY: dayReturn = "Saturday";
        break;
        default : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
      return dayReturn;
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      int dayCount = 0;
      int dateOneCount = (int)day1;
      int dateTwoCount = (int)day2;
      long tempMonth;
      long tempYear;
      long tempDay;
      if (year2 < year1) {
        tempYear = year2;
        year2 = year1;
        year1 = tempYear;
        tempMonth = month2;
        month2 = month1;
        month1 = tempMonth;
        tempDay = day2;
        day2 = day1;
        day1 = tempDay;
      }
      if (year2 > year1) {
        for (int i = (int)month1; i < 12; i++) {
          if ( (i - 1 == FEBRUARY) && (isLeapYear(year1)) ) {
            dayCount += days[i] + 1;
          }
          else {
            dayCount += days[i];
          }
        }
        for (int i = (int)month2 - 2; i >= 0; i--) {
          if ( (i == FEBRUARY) && (isLeapYear(year2)) ) {
            dayCount += days[i] + 1;
          }
          else {
            dayCount += days[i];
          }
        }
        if ( (month1 - 1 == FEBRUARY) && (isLeapYear(year1)) ) {
          dayCount += (int)day2 + (days[(int)month1-1] + 1 - day1);
        }
        else {
          dayCount += (int)day2 + (days[(int)month1-1] - day1);
        }
        for (int i = (int)year1 + 1; i < year2; i++) {
          if(isLeapYear(i)) {
            dayCount += 366;
          }
          else {
            dayCount += 365;
          }
        }
      }
      else if (year1 == year2) {
        for (int i = (int)month1 - 2; i >= 0; i--) {
          dateOneCount += days[i];

        }
        for (int i = (int)month2 - 2; i >= 0; i--) {
          dateTwoCount += days[i];

        }
        dayCount = Math.abs(dateOneCount - dateTwoCount);
      }
      return dayCount;
   }
}
