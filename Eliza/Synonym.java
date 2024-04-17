/* Class Name:Synonym
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This class maintains Eliza synonym list.
 *  Collection of all the synonym elements.
*/
package Eliza;
import java.util.ArrayList;
public class Synonym {
    ArrayList<WordList> synList = new ArrayList<WordList>();
    /*Function Name: add()
	 * Parameters:WordList
	 * Return Value:void
	 * Purpose:Adds wordlist to SynList. 
     */
    public void add(WordList words) {
        synList.add(words);
    }
    /*Function Name: elementAt()
	 * Parameters:int 
	 * Return Value:WordList
	 * Purpose: Returns value at the location of i
     */
    public WordList elementAt(int i) {
        return synList.get(i);
    }
    /*Function Name: print()
	 * Parameters:int 
	 * Return Value:void
	 * Purpose:Prnt the synonym lists.
     */
    public void print(int indent) {
        for (int i = 0; i < synList.size(); i++) {
            for (int j = 0; j < indent; j++) System.out.print(" ");
            System.out.print("synon: ");
            WordList w = (WordList)synList.get(i);
            w.print(indent);
        }
    }

    /**
     *  Find a synonym word list given the any word in it.
     */
    public WordList find(String s) {
        for (int i = 0; i < synList.size(); i++) {
            WordList w = (WordList)synList.get(i);
            if (w.find(s)) return w;
        }
        return null;
    }
    /**
     *  Decomposition match,
     *  If decomp has no synonyms, do a regular match.
     *  Otherwise, try all synonyms.
     */
    boolean matchDecomp(String str, String pat, String lines[]) {
        if (! ElizaUtility.match(pat, "*@* *", lines)) {
            //  no synonyms in decomp pattern
            return ElizaUtility.match(str, pat, lines);
        }
        //  Decomp pattern has synonym -- isolate the synonym
        String first = lines[0];
        String synWord = lines[1];
        String theRest = " " + lines[2];
        //  Look up the synonym
        WordList syn = find(synWord);
        if (syn == null) {
            System.out.println("Could not fnd syn list for " + synWord);
            return false;
        }
        //  Try each synonym individually
        for (int i = 0; i < syn.size(); i++) {
            //  Make a modified pattern
            pat = first + (String)syn.elementAt(i) + theRest;
            if (ElizaUtility.match(str, pat, lines)) {
                int n = ElizaUtility.count(first, '*');
                //  Make room for the synonym in the match list.
                for (int j = lines.length-2; j >= n; j--)
                    lines[j+1] = lines[j];
                //  The synonym goes in the match list.
                lines[n] = (String)syn.elementAt(i);
                return true;
            }
        }
        return false;
    }

}