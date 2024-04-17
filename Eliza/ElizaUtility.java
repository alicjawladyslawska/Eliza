/* Class Name:ElizaUtility
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This is a utility class to match strings, compress, pad, or any other kind of function required by other classes  
 * Comments: Used by all the classes for utility functions
 */
package Eliza;
public class ElizaUtility {
    static final String num = "0123456789"; // string of digits for comparison later.
    /*Function Name: amatch() ; 
	 * Parameters: String (string to match),String(pattern to match)
	 * Return Value:int (character count where a match is found. )
	 * Purpose: This method is called to match a particular pattern 
	 * */
    public static int amatch(String strInput, String patStr) {
        int count = 0;
        int i = 0;  // march through str
        int j = 0;  // march through pat
        while (i < strInput.length() && j < patStr.length()) {
            char p = patStr.charAt(j); // get character
            // stop if pattern is * or #
            if (p == '*' || p == '#') return count;
            if (strInput.charAt(i) != p) return -1; // when the character doesnt match
            // they are still equal
            i++; j++; count++;
        }
        return count;
    }
    /*Function Name: findPat() ; 
	 * Parameters: String (string to match),String(pattern to match)
	 * Return Value:int (character count where a match is found. )
	 * Purpose: Search in successive positions of the string,
     *  looking for a match to the pattern.
     *  Return the string position in str of the match,
     *  or -1 for no match.
     */
    public static int findPat(String str, String pat) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (amatch(str.substring(i), pat) >= 0)
                return count;
            count++;
        }
        return -1;
    }
   /*Function Name: findNum() ; 
	 * Parameters: String (string to match)
	 * Return Value:int (character count where a match is found. )
	 * Purpose:Look for a number in the string.
     *  Return the number of digits at the beginning.
     */
    public static int findNum(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (num.indexOf(str.charAt(i)) == -1) // the char checked is not a number then -1 
                return count;
            count++;
        }
        return count;
    }
    /*Function Name: matchA() ; 
	 * Parameters: String (string to match),String(pattern to match),String[] (string array where matches are stored for calling function)
	 * Return Value:boolean (true when a match is found. )
	 * Purpose:Match the string against a pattern and fills in
     *  matches array with the pieces that matched * and #
     */
    static boolean matchA(String str, String pat, String matches[]) {
        int i = 0;      //  move through str
        int j = 0;      //  move through matches
        int pos = 0;    //  move through pat
        while (pos < pat.length() && j < matches.length) {
            char p = pat.charAt(pos);
            if (p == '*') {
                int n;
                if (pos+1 == pat.length()) {
                    //  * is the last thing in pat
                    //  n is remaining string length
                    n = str.length() - i;
                } else {
                    //  * is not last in pat
                    //  find using remaining pat
                    n = findPat(str.substring(i), pat.substring(pos+1));
                }
                if (n < 0) return false;
                matches[j++] = str.substring(i, i+n);// this array matches to lines array in calling method
                i += n; pos++;
            } else if (p == '#') {
                int n = findNum(str.substring(i)); // the number found is either a priority or a string replacement position
                matches[j++] = str.substring(i, i+n); // store in array for calling function
                i += n; pos++;
            } else {
                int n = amatch(str.substring(i), pat.substring(pos));
                if (n <= 0) return false;
                i += n; pos += n;
            }
        }
        if (i >= str.length() && pos >= pat.length()) return true;
        return false;
    }

    /*Function Name: match() ; 
	 * Parameters: String (string to match),String(pattern to match),String[] (string array where matches are stored for calling function)
	 * Return Value:boolean (true when a match is found. )
	 * Purpose:Entry point for match
     */
    public static boolean match(String str, String pat, String matches[]) {
        return matchA(str, pat, matches);
    }
    /*Function Name: translate() ; 
	 * Parameters: String (string to match),String(pattern to match),String[] (string array where matches are stored for calling function)
	 * Return Value:boolean (true when a match is found. )
	 * Purpose:Translates corresponding characters in src to dest.
     *  Src and dest must have the same length.
     */
    public static String translate(String str, String src, String dest) {
        if (src.length() != dest.length()) {
            // impossible error
        }
        for (int i = 0; i < src.length(); i++) {
            str = str.replace(src.charAt(i), dest.charAt(i));
        }
        return str;
    }
    /*Function Name: compress() ; 
	 * Parameters: String (string to compress)
	 * Return Value: String (compressed string)
	 * Purpose: Compresses its input by:
     *    dropping space before space, comma, and period;
     *    adding space before question, if char before is not a space; and
     *    copying all others
    */
    public static String compress(String s) {
        String dest = "";
        if (s.length() == 0) return s;
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (c == ' ' &&
                 ((s.charAt(i) == ' ') ||
                 (s.charAt(i) == ',') ||
                 (s.charAt(i) == '.'))) {
                   
            } else if (c != ' ' && s.charAt(i) == '?') { // nothing
                dest += c + " ";
            } else {
                dest += c;
            }
            c = s.charAt(i);
        }
        dest += c;
        return dest;
    }
    /*Function Name: trim() ; 
	 * Parameters: String (string to trim)
	 * Return Value: String (trimmed string)
	 * Purpose:Trim off leading space
     */
    public static String trim(String s) {
        return s.trim();
    }
    /*Function Name: pad() ; 
	 * Parameters: String (string to pad)
	 * Return Value: String (padded string)
	 * Purpose:Pad by ensuring there are spaces before and after the sentence.
     */
    public static String pad(String s) {
        if (s.length() == 0) return " ";
        char first = s.charAt(0);
        char last = s.charAt(s.length()-1);
        if (first == ' ' && last == ' ') return s;
        if (first == ' ' && last != ' ') return s + " ";
        if (first != ' ' && last == ' ') return " " + s;
        if (first != ' ' && last != ' ') return " " + s + " ";
        // impossible
        return s;
    }
    /*Function Name: pad() ; 
	 * Parameters: String (string to pad)
	 * Return Value: int
	 * Purpose:Count number of occurrences of c in str
     */
    public static int count(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == c) count++;
        return count;
    } 
}