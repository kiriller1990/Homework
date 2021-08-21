import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * Task 1: Using the String class metods,output a string that is the result of concatenating two strings.
     * @param str1 - first part of text.
     * @param str2 - second part of text.
     * @return concatenating text.
     */
  public  static String glue(String str1, String str2) {
        return str1.concat(str2);
    }

    /**
     * Task 2: Find the index of the character x (x is a user-supplied character) in the string. If there is no such character, then -1.
     * @param str1 - the text in which we are looking for the symbol.
     * @param str2 - desired symbol.
     * @return index of desired symbol.
     */
   public static int symbolIndex(String str1,String str2) {
        return str1.indexOf(str2);
    }

    /**
     * Task 3: Determine if two strings are the same case sensitive.
     * @param str1 - String 1.
     * @param str2 - string 2.
     * @return the result of the comparison (true if the strings are the same).
     */
   public static boolean stringComparison(String str1,String str2) {
        return str1.equals(str2);
    }

    /**
     * Task 4: Remove leading and trailing spaces in the line. Make the entire string consist of uppercase letters.
     * @param str1 - The text to be manipulated.
     * @return uppercase text without spaces.
     */
   public static String trimAndUpperCase(String str1) {
        str1 = str1.trim();
        return str1.toUpperCase();
    }

    /**
     * Task 5:Extract the substring from the string starting from the n-th character to the m-th character.
     * @param str - The text to be manipulated.
     * @param startIndex - the index of the start of the substring extraction.
     * @param endIndex - the index of the end of the substring extraction
     * @return substring from the n-th character to the m-th character
     */
    public static String stringRetrieval(String str,int startIndex,int endIndex) {
        return str.substring(startIndex,endIndex);
    }

    /**
     * Task 6: Change all sad emoji :( in the line to funny :).
     * @param str - the text in which to replace the emoji.
     * @param letter1 - emoji that we change.
     * @param letter2 - emoji to change to.
     * @return text with corrected emoji.
     */
   public static String replaceSmyles(String str,String letter1,String letter2) {
        return str.replace(letter1,letter2);
    }

    /**
     * Task 7: Write a method that takes 2 parameters text and word, and returns:
     *          true if the line starts and ends with this word
     *          false in all others
     * @param text - text that will be searched for.
     * @param word - the word with which the text should begin and end.
     * @return the result of the comparison (true if text starts and ends with word).
     */
   public static boolean comparisonStartEndLastWords(String text,String word) {
        boolean startWord = text.startsWith(word);
        boolean endWord = text.endsWith(word);
        if (startWord  && endWord ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Task 8: Determine the number of English vowels in a sentence.
     * @param text - the text in which the search will be carried out.
     * @return the number of vowels in English letters.
     */
   public static int numberOfEnglishVowels(String text) {
        int count = 0;
        Pattern pattern = Pattern.compile("[AEIOUYaeiouy]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Task 9: Count the total number of punctuation marks (periods, commas, question marks, and exclamation marks) in the string.
     * @param text - the text in which the search will be carried out.
     * @return the number of punctuation marks.
     */
   public static int numberOfPunctuationsMarks(String text) {
        int count = 0;
        Pattern pattern = Pattern.compile("[.,?!]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Task 10: Check if the entered string is a palindrome.
     * @param text -the text in which the search will be carried out.
     * @return check result (true if the string is a polyindrome.
     */
   public static  boolean palindrome(String text) {
        text = text.replaceAll("[ ,.?!-]", "");
        text = text.toLowerCase();
        StringBuffer text1 = new StringBuffer(text);
        return text.contentEquals(text1.reverse());
    }

    /**
     * Task 11: Split the string into equal parts of n characters and store the individual parts into an array.
     * @param text - the text that will be manipulated.
     * @param n - the number of characters in each part.
     * @return text split into equal n characters each.
     */
   public static String[] splitArray(String text, int n) {
        return text.split("(?<=\\G.{" + n + "})");
    }

    /**
     * Task 12: Count the number of words in the text. Please note that words can be separated by multiple spaces.
     * @param text - the text in which the search will be carried out.
     * @return the number of words in the text.
     */
   public static int wordsCount(String text) {
        int count = 0;
        Pattern pattern = Pattern.compile("(\\s*)(\\w+)(\\s*)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Task 13: The last name and first name are given as one line. Return initials according to FL pattern
     *         for example: DmitRY RakOVets → DR.
     *          Take into account that input parameters can be in any register,
     *          and the resulting row must be at the top.
     * @param text - text that contains the last name and first name.
     * @return initials.
     */
   public static String initials(String text) {
        text = text.trim();
        String[] array = text.split(" ");
        String text2 = array[0];
        String text3 = array[1];
        text3 = text3.trim();
        char a = text2.charAt(0);
        char b = text3.charAt(0);
        String text4 = "" + a + b;
        return text4.toUpperCase();
    }

    /**
     * Task 14: Return a string that contains all the numbers in the text.
     * @param text - the text in which the search will be carried out.
     * @return numbers contained in the text.
     */
   public static String digitString(String text) {
        String digitString = new String();
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            digitString += matcher.group();
        }
        return digitString;
    }
}



