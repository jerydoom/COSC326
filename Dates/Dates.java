import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
* The Dates program will take an input date and check if it is in the requested format.
* If it is not, the date will be printed along with a reason and if it is valid, the date will
* be printed in the request format dd<space>(first three letters of the month)<space>yyyy.
*
* @author  Jerry Kumar
* @version 1.0
* @since   2018-03-19
*/
public class Dates{
  public static String error = ""; 
  /**
   * This method checks if the input day is in integer form
   * and in valid range. If not, the static error string is set to the appropriate
   * error.
   * @param dayString is the string to check if it is valid.
   * @return boolean false if an error is hit, true otherwise.
   */
  public static boolean dayCheck(String dayString){
    if (dayString.length()!=1 && dayString.length()!=2){
      error = "INVALID - Plese input day in the format dd, d or 0d";
      return false;
    }
    try{
    int day = Integer.parseInt(dayString);
    if (day<1||day>31){
      error = "INVALID - Day must be between 1-31";
      return false;
    }
    }catch (NumberFormatException e){
      error = "INVALID - Day must be in Integer form";
      return false;
    }
    return true;
  }
  /**
   * This method checks if the input year is in integer form
   * and in valid range (1753-3000). If not, the static error 
   * string is set to the appropriate error. 
   * Note - 2 digit years are allowed.
   * @param yearString is the string to check if it is valid.
   * @return boolean false if an error is hit, true otherwise.
   */
  public static boolean yearCheck(String yearString){
        if (yearString.length()!=2 && yearString.length()!=4){
      error = "INVALID - Plese input year in the format yyyy or yy";
      return false;
    }
    if (yearString.length()==2){
      return true;
    }
    try{
    int year = Integer.parseInt(yearString);
    if (year<1753||year>3000){
      error = "INVALID - Year must be in the range 1753-3000";
      return false;
    }
    }catch (NumberFormatException e){
      error = "INVALID - Year must be in Integer form";
      return false;
    }
    return true;
  }
   /**
   * This method checks if the input month is in an appropriate format.
   * This includes checking the day and making sure it lines up with the amount
   * of days in the chosen month and also checkes for leap year situations.
   * @param dayString is the string to check if it is a valid day for the given month.
   * @param monthString is the string to check if it is valid.
   * @param yearString is the string used to check if the month falls on a leap year.
   * @return boolean false if an error is hit, true otherwise.
   */
   public static boolean monthCheck(String dayString, String monthString, String yearString){
    try{
    int day = Integer.parseInt(dayString);
    int year = Integer.parseInt(yearString);
    /*
     * Block to make sure that the month is one of the valid choices.
     */
    String patternM = "^((0?[1-9]||1[0-2])||(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec" +
      "|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC|jan|feb|mar|apr|may|jun|jul|aug|" +
      "sep|oct|nov|dec))$";
    Pattern m = Pattern.compile(patternM);
    Matcher monthMatcher = m.matcher(monthString);
    if(!monthMatcher.matches()){
      error = "INVALID - Month must be in integer form \"(0)[1-9] 11 or 12\", OR"
        + " first three letters (all same case or first upper) of a valid month";
      return false;
    }
    /*
     * Block for dealing with the cases surrounding February.
     */
    if (monthString.equals("02")||monthString.equals("2")||monthString.equalsIgnoreCase("Feb")){
      if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))){
        if(day>29){
          error = "INVALID - Day must be between 0-29 for this month";
          return false;
      }
      }
      else if (day>28){
          error = "INVALID - Day must be between 0-28 for this month";
          return false;
        }
      }
    /*
     * Block for the months that only have 30 days.
     */
    String thirty = "^(4|6|9|11|04|06|09|Apr|Jun|Sep|Nov|" +
      "|APR|JUN|SEP|NOV|apr|jun|sep|nov)$";
    Pattern thirtyD = Pattern.compile(thirty);
    Matcher thirtyDMatcher = thirtyD.matcher(monthString);
    if(thirtyDMatcher.matches()&& day==31){
      error = "INVALID - The chosen month does not have 31 days"; 
      return false;
    }
    }catch (NumberFormatException e){
      //irrelevant as other checks would hit this but catch is required.
      error = "INVALID - Day and Year must be in Integer form"; 
      return false;
    }
    return true;
  }
   /**
   * This method counts how many occurences of a character there are in a string.
   * @param s the string to check.
   * @param c the character to look for in the string. 
   * @return int the number of occurrences of the character in the string. 
   */
  public static int count(String s, char c){
    int occurrences = 0;
    for (int i=0; i<s.length(); i++)
    {
      if (s.charAt(i) == c)
        occurrences++;
    } 
    return occurrences;
  }
  
  /**
   * This method takes a string and converts it into first letter capital,
   * rest lowercase.
   * @param s the string to convert into the format.
   * @return String the new string in the correct format.
   */
  public static String upperCaseFirst(String s) {
        char[] array = s.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        for (int i = 1; i < array.length; i++) {
          array[i] = Character.toLowerCase(array[i]);
        }
        return new String(array);
    }
  
  /**
   * This is the main method which will check all input strings using the check methods.
   * IF any are invalid, an error will be printed othewise a valid string will be created 
   * and printed.
   * @param args Unused.
   * @return Nothing.
   * @exception IOException On input error.
   * @see IOException
   */
  public static void main (String[] args){
    System.out.println("Please enter the date to check: ");
    Scanner scan = new Scanner (System.in);
    while(scan.hasNextLine()){
      /*
       * This block checks each string for appropriate number and type of seperators.
       */
      Scanner s;
      String input = scan.nextLine();
      if(count(input,'-') ==2){
        s = new Scanner(input).useDelimiter("-");
      }
      else if (count(input,'/') ==2){
        s = new Scanner(input).useDelimiter("/");
      }
      else if (count(input,' ') ==2){
        s = new Scanner(input).useDelimiter(" ");
      }
      else{
        System.out.println("INVALID - You need ONE separator in between days, months and years.\n" +  
                           "Your options are \"/\", \"-\" or \"<space>\". NOTE - Do not end your date with a space either. ");
        continue;
      }
      String day = s.next();
      String month = s.next();
      String year = s.next();    
      
      /*
       * This block uses all check methods to check input validity
       */
      if (!dayCheck(day)){
        System.out.println(input + " " + error);
        continue;
      }
      if(!yearCheck(year)){
        System.out.println(input + " " + error);
        continue;
      }
      if(!monthCheck(day,month,year)){
        System.out.println(input + " " + error);
        continue;
      }
      
      /*
       * This block converts the input (given it has passed the checks) into the 
       * requested format.
       */  
      try{
        int dayInt = Integer.parseInt(day);
        String tempDay ="";
        if (dayInt>0 && dayInt<10){
          tempDay = Integer.toString(dayInt);
          day = "0"+tempDay;
        }
        }catch (NumberFormatException e){
          e.printStackTrace(); //again irrelevant as previous checks would hit this, but a catch is required.
        }
      
      
      if(year.length()==2){
        int yearFourDigit = 0;
        try{
          int yearInt = Integer.parseInt(year);
          if (yearInt<=49){
            yearFourDigit = 2000+yearInt;
          }
          else{
            yearFourDigit = 1900+yearInt;
          }
          year = Integer.toString(yearFourDigit);
        }catch (NumberFormatException e){
          e.printStackTrace(); //again irrelevant as previous checks would hit this, but a catch is required.
        }
    }
      try{
        int monthInt = Integer.parseInt(month);
        if(monthInt == 1){
          month = "Jan";
        }else if(monthInt == 2){
          month = "Feb";
        }else if(monthInt == 3){
          month = "Mar";
        }else if(monthInt == 4){
          month = "Apr";
        }else if(monthInt == 5){
          month = "May";
        }else if(monthInt == 6){
          month = "Jun";
        }else if(monthInt == 7){
          month = "Jul";
        }else if(monthInt == 8){
          month = "Aug";
        }else if(monthInt == 9){
          month = "Sep";
        }else if(monthInt == 10){
          month = "Oct";
        }else if(monthInt == 11){
          month = "Nov";
        }else if(monthInt == 12){
          month = "Dec";
        }
      } catch(NumberFormatException e){
        month = upperCaseFirst(month);
      }
      System.out.println(day + " " + month + " " + year);
      }      
    }
}
  
  
