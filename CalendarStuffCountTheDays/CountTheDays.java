public class CountTheDays {
  public static void main (String[] args) {
    try {
      long month1 = Long.parseLong(args[0]);
      long day1 = Long.parseLong(args[1]);
      long year1 = Long.parseLong(args[2]);
      long month2 = Long.parseLong(args[3]);
      long day2 = Long.parseLong(args[4]);
      long year2 = Long.parseLong(args[5]);
      if (CalendarStuff.isValidDate(month1,day1,year1) && CalendarStuff.isValidDate(month2,day2,year2)) {
        System.out.println("The number of days between " + month1 + "/" + day1 + "/" + year1 + " and " + month2 + "/" + day2 + "/" + year2 + " is:" + CalendarStuff.daysBetween(month1,day1,year1,month2,day2,year2));
      }
    }
    catch (NumberFormatException e) {
      System.out.println("Invalid Arguements. Arguments must be type long.");
    }
  }
}
