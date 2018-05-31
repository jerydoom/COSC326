import java.util.*;
import java.io.*;
/**
 * The Arithmetic program will take 2 input lines as outlined by the etude guidelines and check if the requested 
 * goal number from line 2 can be reached with the input numbers in the first line using the requested method.
 * If it can, the output of how will be printed in the requested format, if not a suitable message will appear. 
 * Guidlines are found at this link http://www.cs.otago.ac.nz/cosc326/PDF/Arithmetic.pdf
 * 
 * Note, the limitation of this program is the size of int that can be represented by the Integer class
 * in java. 
 * 
 * @author  Jerry Kumar
 * @version 5.1
 * @since   2018-05-06
 */
public class Arithmetic{
  static boolean normal = false;
  static boolean stop = false;
  static String path = "";
  static String pathX = "";
  /**
   * This method takes and input string, removes the last string via returning a substring
   * one less then the original string and returns it. 
   * 
   * @param str is the String to remove the last character from. 
   * @return String the new string with the last character removed.
   **/
  private static String removeLastChar(String str) {
    return str.substring(0, str.length() - 1);
  }
  /**
   * This method takes an array list of integers, and using the + and * operators, creates the largest int possible.
   * This means multiplying always except in the case of a 1 when where it adds.
   * 
   * After getting thiss values, it checks against a goal, so see if the goal is within that range. If
   * so, it is possible to create that goal with the numberList and return true, else it is not, so
   * return false. 
   * @param numberList is the ArrayList of integers to try to make the largest number
   * @param goal the int value of the value we are trying to see if we can make.
   * @return boolean true if the goal is possible, false otherwise. 
   */
  public static boolean checkLargest(ArrayList<Integer> numberList, int goal){
    int largestNumber = numberList.get(0);
    for(int i = 1; i<numberList.size(); i ++){
      if(largestNumber<0){ //has hit the limit of the integer type in java.
        return true;
      }      
      if(largestNumber==1){
        largestNumber += numberList.get(i);
      }
      
      else if(numberList.get(i)==1){
        largestNumber +=1;
      }
      else{
        largestNumber *= numberList.get(i);   
      }
    }
    if(largestNumber<goal){
      return false;
    }
    return true;
  }
  /**
   * This method takes an array list of integers, and using the + and * operators, creates the smallest int possible.
   * This means multiplying always except in the case of a 1 when where it adds.
   * 
   * After getting thiss values, it checks against a goal, so see if the goal is within that range. If
   * so, it is possible to create that goal with the numberList and return true, else it is not, so
   * return false. 
   * @param numberList is the ArrayList of integers to try to make the smallest number
   * @param goal the int value of the value we are trying to see if we can make.
   * @return boolean true if the goal is possible, false otherwise. 
   */
  public static boolean checkSmallest(ArrayList<Integer> numberList, int goal){
    int smallestNumber = numberList.get(0);
    for(int i = 1; i<numberList.size(); i ++){
      if (smallestNumber==1){
        smallestNumber *= numberList.get(i);
      }
      else if (numberList.get(i)==1){
        smallestNumber*=1;
      }
      else{
        smallestNumber += numberList.get(i);
      }
    }
    if(smallestNumber>goal){
      return false;
    }
    return true;
  }
  /** Calls the smallest and largest number checks and if both pass, return true indicating that the goal we are 
    * trying to reach is possible to reach with the numbers in the numberList.
    * 
    * @param numberList is the ArrayList of integers to try to make the largest and smallest combos.
    * @param goal the int value of the value we are trying to see if we can make.
    * @return boolean true if the goal is possible, false otherwise.
    **/
  public static boolean checkPossible(ArrayList<Integer> numberList, int goal){
    boolean smallestCheck = checkSmallest( numberList, goal);
    boolean largestCheck = checkLargest( numberList, goal);
    if(smallestCheck && largestCheck){
      return true;
    }
    return false;    
  }
  /**
   * This method takes an array list of integers, and using the + and * operators, tries to find a combination
   * of all the numbers that matches a goal integer, using left to right maths.
   * 
   * The partial sum will be the first number of the list and the index will be 1 to start. The method creates 
   * an add variable that adds the next number (using the index) to the partial sum, and another multiply
   * variable that multiplys the next number to the partial sum. 
   * 
   * The program then recurses down, going 'left' by adding first, then coming back up to multiply.
   * when it enters a 'left' loop, it adds a + to a global working path string 'path'. when it comes back out,
   * it removes this. The same happens when it goes down 'right' down the multiply path, but with a *.
   * This process stops when we have hit the end of the arrayList.
   * 
   * When it reaches the end of the arrayList (all numbers have been used in some combo of + and *,
   * it checkes if the sum is the goal. if it is, it sets the golbal variable 'pathX' 'path' so we know how
   * we got there and it then changes the golbal boolean stop to true.
   * 
   * stop is checked each time we return out of a loop, so as soon as the right one is found, there will be 
   * no more recursing down and we will prevent a lot of pointless calculations.
   * 
   * @param numberList is the ArrayList of integers to use to try to combine to reach our goal.
   * @param index is the index of the ArrayList we are currently wanting to access.
   * @param partialSum is the partial total.
   * @param goal is the number we are trying to reach
   **/
  public static void leftRightRecursion( ArrayList<Integer> numberList, int index, int partialSum, int goal) {
    if(stop){
      return;
    }
    if (index == numberList.size()) {
      if (partialSum==goal){
        pathX = path;
        stop = true;
      }
      return;
    }
    int newSumAdd = partialSum + numberList.get(index);
    int newSumMultiply = partialSum * numberList.get(index);
    
    if(newSumAdd<=goal){
      path+="+";
      leftRightRecursion(numberList, index + 1, newSumAdd, goal);
      path = removeLastChar(path);
    }   
    if(stop){
      return;
    }
    if(newSumMultiply<=goal){
      path+="*";
      leftRightRecursion(numberList, index + 1, newSumMultiply, goal);
      path = removeLastChar(path);
    }   
  }
  /**
   * This method takes an array list of integers, and using the + and * operators, tries to find a combination
   * of all the numbers that matches a goal integer, using normal BEDMAS maths.
   * 
   * This method works by keeping track of our sum in the form on 2 numbers: a current sum and an intermediate.
   * The current sum is initally the first number of the list, the intermediate -1 (indicating no second
   * number to work on as of yet, and the index is 1. 
   * It then gets a third value, the next number in the list.
   * 
   * If we go down the add path, then this next number is our intermediate and the previous 2
   * numbers i and i2 can be combined into the new i. Not this is only if there has been a previous number,
   * if there has not been, then the new next number will be stored as 12e.g. a first input 2, then a plus 3 will
   * store those 2 numbers as i=2, i2=3
   * 
   * If we go down the multiply path, then there are two options. if there are 2 previous values of i and i2, then we multiply the new
   * number byt i2. E.g. i=2, i2 =3 next number is 4 and we are on the multiply path, then we want 3*4 to be store in i2.
   * If there is no i2, then we want i to be multiplied by this number.
   * 
   * As the program then recurses down, when it enters a 'left' loop, it adds a + to a global working path string 'path'.
   * When it comes back out, it removes this. The same happens when it goes down 'right' down the multiply path, but with a *.
   * This process stops when we have hit the end of the arrayList.
   * 
   * When it reaches the end of the arrayList (all numbers have been used in some combo of + and *,
   * it calculates the final sum (currentSum + intermediate, and checkes if this is the goal. if it is, 
   * it sets the golbal variable 'pathX' 'path' so we know how we got there and it then changes the golbal boolean stop to true.
   * 
   * stop is checked each time we return out of a loop, so as soon as the right one is found, there will be 
   * no more recursing down and we will prevent a lot of pointless calculations.
   * 
   * @param numberList is the ArrayList of integers to use to try to combine to reach our goal.
   * @param index is the index of the ArrayList we are currently wanting to access.
   * @param i is the current sum.
   * @param i2 is the intermediate number we are not sure if we need to * or +, -1 if there is none stored yet
   * @param goal is the number we are trying to reach
   * 
   **/
public static void normalRecursion( ArrayList<Integer> numberList, int index, int i, int i2, int goal) {
  if(stop){
    return;
  }
  if (index == numberList.size()) {
    int finalSum =0;
    if(path.length() == 0||i2==-1){
      finalSum = i;
    }
    else {
      finalSum = i + i2;
    }
    if (finalSum==goal){
      pathX = path;
      stop = true;
    }
    return;
  }
    int nextNumber = numberList.get(index);
    if(!(i>goal||i2>goal)){
      path+="+";
      if(i2!= -1){
       normalRecursion(numberList, index + 1, i+i2, nextNumber, goal);
      }
      else{
         normalRecursion(numberList, index + 1, i, nextNumber, goal);
      }
      path = removeLastChar(path);
    }   
    if(stop){
      return;
    }
    path+="*";
    if(i2==-1){
          normalRecursion(numberList, index + 1, i*nextNumber, i2, goal);
    }
    else{
      normalRecursion(numberList, index + 1, i, i2*nextNumber, goal);
    }
    path = removeLastChar(path);
  }
  
  /**
   * This is the main method which will check if input numbers can be combined to make input goal using requested
   * calculation type. Prints the answer in the reqeuested format if possible, and an appropriate error as requested
   * otherwise.
   * 
   * @param args Unused.
   * @exception IOException On input error.
   * @see IOException
   */
  public static void main (String[] args){
    Scanner scan = new Scanner (System.in);
    while(scan.hasNextLine()){
      path = ""; // These 3 lines reset global variables for each new calculation.
      pathX = ""; 
      stop = false; 
      ArrayList<Integer> numberList = new ArrayList<Integer>();
      String numbers = scan.nextLine(); //input numbers
      String result = scan.nextLine(); //goal and calculation type
      Scanner r = new Scanner(result);
      Scanner n = new Scanner(numbers);
      int goal = r.nextInt(); //goal
      char calcType = r.next().charAt(0); //Type
      if ( calcType == 'N'){ //this block sets the global boolean for normal calculation or not (left to right).
        normal = true;
      }
      else{
        normal = false;
      }
      while (n.hasNext()){ //this loop puts all the ints in the input into the arraylist. 
        numberList.add(n.nextInt());
      }
      
      if(numberList.size()==1){
        if(numberList.get(0)==goal){
          System.out.println(calcType + " " + goal + " " + goal);
          continue;
        }
      }
      
      if(checkPossible(numberList, goal)){ //if the calculation is possible 
        if(!normal){ //Call the right recursion method
          leftRightRecursion(numberList, 1, numberList.get(0), goal); 
        }
        else{
          normalRecursion(numberList, 1, numberList.get(0), -1, goal);
        }
        if(!pathX.equals("")){ //if there is an answer
          String answer = ""; //interweave answer and numbers
          for (int i=0; i < numberList.size(); i++){
            if(i != numberList.size()-1){
              answer+= numberList.get(i);
              answer+= " ";
              answer+= pathX.charAt(i);
              answer+= " ";
            }
            else{
              answer+= numberList.get(i);
            }
          }
          System.out.println(calcType + " " + goal + " " + answer); 
        }
        else{
          
          System.out.println(calcType + " " + goal + " " + "impossible");
        }
      }
      else{
        System.out.println(calcType + " " + goal + " " + "impossible");
      }
    
      
    }
  }
}