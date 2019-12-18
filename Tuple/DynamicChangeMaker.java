/**
* File name  :  DynamicChangeMaker.java
* Purpose    :  Program to give the correct amount of change back for a certain number of dollars
* @author    :  Ambuj Bhatnagar
* Date       :  2017-04-19
* Description:  Coins and their values are given, as well as a dollar amount. The least amount of coins
*               that provide change is returned
* Notes      :  None
* Warnings   :  None
*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class DynamicChangeMaker {

  /**Stores all coin denominations*/
  public int[] coinArray = null;
  /**Two-dimensional tuple array that stores and determines coins possibilities*/
  public Tuple[][] possArray = null;
  /**number of rows of possArray*/
  public int rows = 0;
  /**number of columns of possArray*/
  public int columns = 0;
  /** boolean to determine valid arguments (false if missing or malformed
   * arguments, denominations that aren't positive,duplicated denominations,
   * and negative amounts.)
   */
  public boolean goodArgs = true;

  public DynamicChangeMaker(){
  }

  /** sets up coin denominations and dollar amount for makeChange methods and
   * validates all arguments
   * @param args[] String array of coin denominations and dollar amount
   * @throws IllegalArgumentException if there are not enough arguments
   */
  public void validateArgs(String args[]) {

    String[] coinStringArray = args[0].split(",");
    this.coinArray = new int[coinStringArray.length];
    int rows = 0;
    int columns = 0;

    //arg length handling
    if (args.length < 2) {
      goodArgs = false;
    } else {

          //args[0] handling

          //for-loop to translate string args into coin array
          for (int i = 0; i < coinStringArray.length; i++) {
            if (Integer.parseInt(coinStringArray[i]) < 1) {
              //if coin is negative, goodArgs is false
              this.goodArgs = false;
            } else {
              //else, integer value of coin String is saved into coin array
              this.coinArray[i] = Integer.parseInt(coinStringArray[i]);
            }
          }

          //repeats and zeroes in coin array
          for (int i = 0; i < this.coinArray.length-1; i++) {
            if (this.coinArray[i] == 0) {
              this.goodArgs = false;
            }
            for (int k = i+1; k < this.coinArray.length; k++) {
              if (this.coinArray[i] == this.coinArray[k]) {
                this.goodArgs = false;
              }
            }
          }

          //args[1] handling
          if (Integer.parseInt(args[1]) < 0) {
            // if arg 2 is less than zero
            this.goodArgs = false;
          }

          //possibilities tuple array creation
          rows = this.coinArray.length;
          columns = Math.abs(Integer.parseInt(args[1]) + 1);
          this.rows = rows;
          this.columns = columns;
          possArray = new Tuple[rows][columns];

          //initializes each spot in possArr with new tuple
          for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
              possArray[i][k] = new Tuple(rows);
              //set every Tuple to zero
              for (int j = 0; j < rows; j++) {
                possArray[i][k].setElement(j,0);
              }
            }
          }
    }

  }

  /** Adds one to typle if the column number is divisable by the rowValue
   * @param rowNumber row Number of tuple possibilities array
   * @param rowValue value of coin denomination at coinArray[rowNumber]
   * @param columnNumber column Number of tuple possibilities array
   */
  public void divisable(int rowNumber, int rowValue, int columnNumber) {
    //does not check first column
    if (columnNumber > 0) {
      if ((columnNumber / rowValue) >= 1) {
        //makes sure columnNum/rowVal > 0
        this.possArray[rowNumber][columnNumber].setElement(rowNumber,1);
      } else {
        this.possArray[rowNumber][columnNumber] = Tuple.IMPOSSIBLE;
      }
    }
  }

  /** Checks tuple spot backward to see if there is a valid/impossible solution
   *  if there is, copy it over and add/replace the one that is there
   * @param rowNumber row Number of tuple possibilities array
   * @param rowValue value of coin denomination at coinArray[rowNumber]
   * @param columnNumber column Number of tuple possibilities array
   */
  public void rowCheck(int rowNumber, int rowValue, int columnNumber) {
    //makes sure backwards spot exists
    if ( (columnNumber - rowValue) >= 0) {
      //if the spot behind is equal to IMPOSSIBLE, this one is equal to IMPOSSIBLE as well.
      if (this.possArray[rowNumber][columnNumber - rowValue].equals(Tuple.IMPOSSIBLE)) {
        this.possArray[rowNumber][columnNumber] = Tuple.IMPOSSIBLE;
      }
      else {
        //otherwise, the two tuples are added together
        this.possArray[rowNumber][columnNumber] = this.possArray[rowNumber][columnNumber].add(this.possArray[rowNumber][columnNumber - rowValue]);
      }
    }
  }
 /** Check tuple spot above to see if there is a better/non-impossible solution; if so, copy it down
  * @param rowNumber row Number of tuple possibilities array
  * @param rowValue value of coin denomination at coinArray[rowNumber]
  * @param columnNumber column Number of tuple possibilities array
  */
  public void columnCheck(int rowNumber, int rowValue, int columnNumber) {
    //if this spot is impossible
    if (this.possArray[rowNumber][columnNumber].equals(Tuple.IMPOSSIBLE)) {
      //if the spot above is not impossible
      if (!this.possArray[rowNumber-1][columnNumber].equals(Tuple.IMPOSSIBLE)) {
        //sets this spot to spot above
        this.possArray[rowNumber][columnNumber] = this.possArray[rowNumber-1][columnNumber];
      }
    //compares this spot and above spot if both are defined (!impossible)
    } else if ((!this.possArray[rowNumber][columnNumber].equals(Tuple.IMPOSSIBLE)) && (!this.possArray[rowNumber-1][columnNumber].equals(Tuple.IMPOSSIBLE))) {
      //if the above spot has less coins
      if (this.possArray[rowNumber-1][columnNumber].total() < this.possArray[rowNumber][columnNumber].total()) {
        //then this spot is now the above spot
        this.possArray[rowNumber][columnNumber] = this.possArray[rowNumber-1][columnNumber];
      }
    }
  }

  /** Method that determines that least amount of coins that make change for a dollar amount.
   * (collection of three different parts made into one)
   * @return a Tuple that gives specified arrangement of coins
   */
  public Tuple makeChange() {
    if (goodArgs) {
      for (int i = 0; i < this.rows; i++) {
        for (int k = 0; k < this.columns; k++) {
          //checks every spot to make sure it is divisable
          divisable(i,this.coinArray[i],k);

          //if this spot is equal to IMPOSSIBLE, then there is no point checking spots behind
          if (!(this.possArray[i][k].equals(Tuple.IMPOSSIBLE))) {
            rowCheck(i,this.coinArray[i],k);
          }

          //spot above checked always if the row index is greater than zero.
          if (i > 0) {
            columnCheck(i,this.coinArray[i],k);
          }
        }
      }
      return this.possArray[this.rows-1][this.columns-1]; //returns best & final possibility
    } else {
      //if there are bad arguments, then IMPOSSIBLE is returned
      return Tuple.IMPOSSIBLE;
    }
  }

  /** Static method that calculates the least number of coins required to make change for a certain dollar amount.
   * @param coinDen coin denominations put into an int Array
   * @param dollarAmt total dollar amount
   * @return Tuple that indicates how many coins are needed for change.
   */
  public static Tuple makeChangeWithDynamicProgramming(int[] coinDen, int dollarAmt) {
    String[] argsString = new String[2];

    argsString[0] = "";
    //sets args[0] to coinDenominations in String form
    for (int i = 0; i < coinDen.length; i++) {
      if (i == coinDen.length - 1) {
        argsString[0] += Integer.toString(coinDen[i]);
      } else {
        argsString[0] += Integer.toString(coinDen[i]) +",";
      }
    }
    //args[1] = dollar amount
    argsString[1] = Integer.toString(dollarAmt);

    DynamicChangeMaker changeMaker = new DynamicChangeMaker();
    changeMaker.validateArgs(argsString);
    return changeMaker.makeChange();
  }

  public static void main(String args[]) {
    DynamicChangeMaker testDCM = new DynamicChangeMaker();
    testDCM.validateArgs(args);
    System.out.println(testDCM.makeChange());

  }
}
