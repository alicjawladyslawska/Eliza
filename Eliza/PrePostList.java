/* Class Name:PrePostList
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This class maintains the Eliza prePost list.
 *  This list of pre-post entries is used to perform word transformations
 *  prior to or after other processing.
*/
package Eliza;
import java.util.ArrayList;
public class PrePostList {
    ArrayList<PrePost> PrePostList = new ArrayList<PrePost>();
    /*Function Name: add()
	 * Parameters:String (source),String(dest)
	 * Return Value:void
	 * Purpose:Add another entry to the list.
     */
    public void add(String src, String dest) {
        PrePostList.add(new PrePost(src, dest));
    }
    /*Function Name: print()
	 * Parameters:int
	 * Return Value:void
	 * Purpose:Prnt the pre-post list.
     */
    public void print(int indent) {
        for (int i = 0; i < PrePostList.size(); i++) { //for all items in the pre post list 
            PrePost p = (PrePost)PrePostList.get(i);// get the value
            p.print(indent);// print the source and destination values from PrePost class
        }
    }
    /*Function Name: xlate()
	 * Parameters:int
	 * Return Value:void
	 * Purpose:Translate a string.
     *  If str matches a src string on the list,
     *  return the corresponding dest.
     *  If no match, return the input.
     */
    String xlate(String str) {
        for (int i = 0; i < PrePostList.size(); i++) {
            PrePost p = (PrePost)PrePostList.get(i); // get the prepost entry from list 
            if (str.equals(p.src())) { // compare the str with source and see if equal
                return p.dest(); //return destination string as match is found
            }
        }
        return str; // if no match return source
    }

    /*Function Name: translate()
	 * Parameters:String
	 * Return Value:String
	 * Purpose:Translate a string s.
     *  (1) Trim spaces off.
     *  (2) Break s into words.
     *  (3) For each word, substitute matching src word with dest.
     */
    public String translate(String s) {
        String lines[] = new String[2];
        String work = ElizaUtility.trim(s);
        s = "";
        while (ElizaUtility.match(work, "* *", lines)) {
            s += xlate(lines[0]) + " ";
            work = ElizaUtility.trim(lines[1]);
        }
        s += xlate(work);
        return s;
    }
}