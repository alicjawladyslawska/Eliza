/* Class Name:WordList
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This class maintains Eliza word list.
*/
package Eliza;
import java.util.ArrayList;

public class WordList {

    ArrayList<String> wordList = new ArrayList<String>();
    /*Function Name: add()
	 * Parameters:String
	 * Return Value:void
	 * Purpose:Add another word to the list.
     */
    public void add(String word) {
        wordList.add(word);
    }
    /*Function Name: print()
	 * Parameters:int 
	 * Return Value:void
	 * Purpose:Print a word list on one line.
     */
    public void print(int indent) {
        for (int i = 0; i < wordList.size(); i++) {
            String s = (String)wordList.get(i);
            System.out.print(s + "  ");
        }
        System.out.println();
    }
    /*Function Name: find()
	 * Parameters:String 
	 * Return Value:boolean
	 * Purpose:Find a string in a word list.
     *  Return true if the word is in the list, false otherwise.
     */
    boolean find(String s) {
        for (int i = 0; i < wordList.size(); i++) {
            if (s.equals((String)wordList.get(i))) return true;
        }
        return false;
    }
    /*Function Name: size()
	 * Parameters:int 
	 * Return Value:Integer
	 * Purpose:Return size of the list
     */
    public Integer size() {
        return wordList.size();
    }

    /*Function Name: elementAt()
	 * Parameters:int 
	 * Return Value:String
	 * Purpose:Return the word at i 
     */
    public String elementAt(int i) {
        return wordList.get(i);
    }

}
