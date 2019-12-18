/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Riemann.java
 *  Purpose       :  Calculates area under the curve for an equation
 *  @author       :  Ambuj Bhatnagar
 *  Date written  :  2019-03-13
 *  Description   :  Uses Midpoint Riemann sum approximations to determine the integral of an equation.
 *  Notes         :  None right now.  I'll add some as they occur.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0    2019-04-02  Ambuj B       No comments; integral calculation works; while loop is broken; handleArgs NW
 *  @version 2.0    2019-04-02  Ambuj B       Comments put in; integral calculation works; while loop fixed;
 *  @version 2.0    2019-04-02  Ambuj B       handleArgs NW; runTests method NEEDED
 *  @version 3.0    2019-04-03  Ambuj B       Added second validateArgs "handleMiddleZero" method that handles coefficient
 *  @version 3.0    2019-04-03  Ambuj B       initialization if there are no terms;
 *  @version 4.0    2019-04-03  Ambuj B       Created a runTests method for pretty much no reason; not sure if I even created it
 *  @version 4.0    2019-04-03  Ambuj B       correctly but whatever; created another method that does the overall integration.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class Riemann {
  public double[] termsArr = null;
  public int lastTermIndex = 0;
  public double percent = 1;
  public double[] boundsArr = new double[2];
  public String EqArg = "";

  public Riemann(){

  }

  public void runTests() {
    System.out.println("===== Welcome to the Reimann java class test ===== ");
    System.out.println("Tests will be done on individual methods and the entire program.");
    System.out.println("=================================================================");

    System.out.println("--> First test will be with polynomial equation 4x^2 + 8x from interval 4.83 to 9.4.");
    System.out.println("--> Default percentage is used. Approximation should be near 1217.33.");
    String[] polyArr = {"poly","0","8","4","4.83","9.4"};
    System.out.println("Approximation: " + finalSum(polyArr));
    System.out.println("=================================================================");

    System.out.println("--> Second test will be with sin equation 2.3x^4 + 23x - 2 from interval -3.12 to 0.3.");
    System.out.println("--> Percentage will be 0.001%. Approximation should be near 0.160049.");
    String[] sinArr = {"sin","-2","23","0","0","2.3","-3.12","0.3","0.001%"};
    System.out.println("Approximation: " + finalSum(sinArr));
    System.out.println("=================================================================");

    System.out.println("--> Third test will be with cos equation 2.3x^4 + 23x - 2 from interval -3.12 to 0.3.");
    System.out.println("--> Percentage will be 0.001%. Approximation should be near 0.262872.");
    String[] cosArr = {"cos","-2","23","0","0","2.3","-3.12","0.3","0.001%"};
    System.out.println ("Approximation: " + finalSum(cosArr));
    System.out.println("=================================================================");

    System.out.println("--> Fourth test will be with tan x from interval 0 to 1");
    System.out.println("--> Percentage will be 0.001%. Approximation should be near 0.61563.");
    String[] tanArr = {"tan","0","1","0.001%"};
    System.out.println ("Approximation: " + finalSum(tanArr));
    System.out.println("=================================================================");

    System.out.println("--> Fifth test will be with log x^2 from interval 1.3 to 6.9");
    System.out.println("--> Percentage will be 1e-6%. Approximation should be near 14.7728.");
    String[] logArr = {"log","0","0","1","1.3","6.9","1e-6%"};
    System.out.println ("Approximation: " + finalSum(logArr));
    System.out.println("=================================================================");

    System.out.println("--> Sixth test will be with e^(3x^2) from interval -1.03 to 1.21");
    System.out.println("--> Percentage will be 1e-6%. Approximation should be near 18.0734.");
    String[] expArr = {"exp","0","0","3","-1.03","1.21","1e-6%"};
    System.out.println ("Approximation: " + finalSum(expArr));
    System.out.println("=================================================================");

    System.out.println("--> Seventh test will be with sqrt(6.32x^4-34x^2+213) from interval 12 to 14.23");
    System.out.println("--> Percentage will be 1e-15%. Approximation should be near 951.9577.");
    String[] sqrtArr = {"sqrt","213","0","-34","0","6.32","12","14.23","1e-15%"};
    System.out.println ("Approximation: " + finalSum(sqrtArr));
    System.out.println("=================================================================");
  }


  /*
   * @returns nothing
   * @throws IllegalArgumentException when there are not enough arguments
   */
   //poly lb up %
   //poly lb ub
  public void enoughArgs(String[] args) {
    if ( (args[0].equals("poly") ) && (args.length <= 4) && hasPercent(args) ) {
      throw new IllegalArgumentException("Error: There should be at least one term in polynomial equations in the following order: "
      + "poly [term 1] ... [termN] [lowerBound] [upperBound] <optionalPercentage>");

    }
    else if ( (args[0].equals("poly") ) && (!hasPercent(args)) && (args.length <= 3) ) {
      throw new IllegalArgumentException("Error: There should be at least one term in polynomial equations in the following order: "
      + "[EquationType] [term 1] ... [termN] [lowerBound] [upperBound] <optionalPercentage>");

    }
    else if (args.length < 3) {
      throw new IllegalArgumentException("Error: There should be at least three arguments for non-polynomial equations in the following order: "
      + "[EquationType] [term 1] [term2] ... [termN] [lowerBound] [upperBound] <optionalPercentage>");

    }

  }

  /*
   * @returns nothing
   * @throws IllegalArgumentException if percent is below 0 or above 100 percent
   * NOTES: checks if there is a percent given
   * If percent is given, then this.percent is changed.
   */
  public void handlePercent(String s, String args[]) {

    if (s.endsWith("%")) {

      this.lastTermIndex = args.length - 4;
      char[] percentArr = s.toCharArray();
      String percentString = Character.toString(percentArr[0]);
      for (int i = 1; i < percentArr.length - 1; i++) {
        percentString += percentArr[i];
      }

      this.percent = Double.parseDouble(percentString);
      if ( (this.percent > 100) || (this.percent <= 0) ) {
        throw new IllegalArgumentException("Percent must be above 0 and below 100.");
      }
    }
    else {
      this.lastTermIndex = args.length - 3;
    }
  }

  /*
   * @returns nothing
   * NOTES: handles middle part if there are not terms and only ranges &| percent given
   */
  public void handleMiddleZero(String[] args) {
    this.termsArr = new double[2];
    this.termsArr[0] = 0;
    this.termsArr[1] = 1;
    if (hasPercent(args)) {
      this.boundsArr[0] = Double.parseDouble(args[args.length - 3]);
      this.boundsArr[1] = Double.parseDouble(args[args.length - 2]);
    }
    else {
      this.boundsArr[0] = Double.parseDouble(args[args.length - 2]);
      this.boundsArr[1] = Double.parseDouble(args[args.length - 1]);
    }
  }

  /*
   * @returns nothing
   * @throws IllegalArgumentException if upper bound is less than lower bound
   * NOTES: handles all the terms (co-efficients) and stores them into termsArr
   */
  public void handleMiddle(String args[]) {
    if (hasPercent(args)) {
      this.termsArr = new double[args.length - 4];
      this.boundsArr[0] = Double.parseDouble(args[args.length - 3]);
      this.boundsArr[1] = Double.parseDouble(args[args.length - 2]);
    }
    else {
      this.termsArr = new double[args.length - 3];
      this.boundsArr[0] = Double.parseDouble(args[args.length - 2]);
      this.boundsArr[1] = Double.parseDouble(args[args.length - 1]);
    }
    if (this.boundsArr[0] > this.boundsArr[1]) {
      throw new IllegalArgumentException("Uh oh. It seems like your upper bound is less than your lower bound.");
    }
    for (int i = 1; i <= this.lastTermIndex ; i++) {
      this.termsArr[i-1] = Double.parseDouble(args[i]);
    }
  }

  /*
   * @returns double value of polynomial inside of equation
   * EX. sin(10x^2) => (solves 10x^2)
   *
   */
  public double yCalculationIns( double x) {
    double returnSum = 0;
    for (int i = 0; i < this.termsArr.length ; i++) {
      returnSum += this.termsArr[i]*Math.pow(x,i);
    }
    return returnSum;
  }

  /*
   * @returns boolean to see if the args contain a percent (which changes indexes, etc)
   *
   */
  public static boolean hasPercent(String args[]) {
    if (args[args.length -1].endsWith("%")) {
      return true;
    }
    else {
      return false;
    }
  }

  /*
   * @returns double that handles outer part of Equation
   * Ex. sin(10x^2); after call yCalcIns(10x^2) (which returns #), then solves value of sin(#)
   */
  public double yCalculationOut(String EqArg, double returnSum) {
    double finalSum = 0;
    switch (EqArg)
    {
      //jfdk;lsa
      case "poly" :
        finalSum = returnSum;
        break;
      case "sin" :
        finalSum = Math.sin(returnSum);
        break;
      case "cos" :
        finalSum = Math.cos(returnSum);
        break;
      case "tan":
        finalSum = Math.tan(returnSum);
        break;
      case "log":
        finalSum = Math.log(returnSum);
        break;
      case "exp":
        finalSum = Math.exp(returnSum);
        break;
      case "sqrt":
        finalSum = Math.sqrt(returnSum);
        break;
      default:
        throw new IllegalArgumentException("Equation type must be a cos, sin, tan, logarithmic, exponent, or polynomial function.");
    }
    return finalSum;
  }

 /*
  * @returns the integral number
  *
  */
  public double SumCalc(int n) {
    double sumCalc = 0;
    int UBIndex = 1;
    int LBIndex = 0;
    double width = (boundsArr[UBIndex] - boundsArr[LBIndex])/n;
    double midPoint = boundsArr[LBIndex] + (width/2);
    while (midPoint < this.boundsArr[UBIndex]) {
      sumCalc += yCalculationOut(this.EqArg, yCalculationIns(midPoint));
      midPoint += width;
    }
    sumCalc *= width;
    return sumCalc;
  }

  /*
   * @returns the final summation calculation of the integral
   *  NOTES
   * lastIndex() <--can also initialize termsArr
   * handleMiddle()
   * add all term to the array
   * pretty much finding y values, which is pretty important
   * ok so the actual summation calculation but that's pretty easy
   * first rectangle x-coordinates: a + (b-a)/2n
   * then get y by plugging x in
   * then for loop to find sum of y's
   * then multiply by width which is (b-a)/n
   * after this store in an array
   * then calculate again but n += 1
   * compare to first value which is stored in array
   * while loop (until ratio between <= this.percent)
   */
  public double finalSum(String[] args) {
    int numOfRects = 1;
    double ratio = 0;
    this.enoughArgs(args); //test for enough arguments

    //handles percent and saves first argument to var
    this.handlePercent(args[args.length-1], args);
    this.EqArg = args[0];

    //check for terms and initializes differently depending on how many there are
    if ( (hasPercent(args) && (args.length == 4) ) || ( (!hasPercent(args)) && (args.length == 3) ) ) {
    this.handleMiddleZero(args);
    }
    else {
      this.handleMiddle(args);
    }
    double firstSum = this.SumCalc(1);
    double secondSum = this.SumCalc(++numOfRects);
    //actual riemann summ approximations
    do {
      firstSum = secondSum;
      secondSum = this.SumCalc(++numOfRects);
      ratio = 1-(secondSum/firstSum);
    } while ( Math.abs(ratio) > (this.percent/100) );
    return secondSum;
  }

    public static void main(String args[]){
      if (args[0].equals("runTests")) {
        Riemann RiemTest = new Riemann();
        RiemTest.runTests();
      }
      else {
        Riemann testR = new Riemann();
        System.out.println("Approximation: " + testR.finalSum(args) );;
      }


    }

}
