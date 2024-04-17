/* Class Name:Decomp
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This class is used to get the decomp pattern and returns the reassemble rule.   
 * Comments: Each decomp has multiple reasmb rule and returns one of them.
 */
package Eliza;
import java.lang.Math;
public class Decomp {
    /** The decomp pattern */
    String pattern;
    /** The mem flag */
    boolean mem;
    /** The reassembly list */
    ReasmbleReply reasemb;
    /** The current reassembly point */
    int currReasmb;
	/*Function Name: Decomp(); constructor
	 * Parameters: String, boolean, ReasmbleReply object
	 * Return Value:none
	 * Purpose: Store the parameters to local variables . 
	 * */
    Decomp(String pattern, boolean mem, ReasmbleReply reasemb) {
        this.pattern = pattern;
        this.mem = mem;
        this.reasemb = reasemb;
        this.currReasmb = 100;
    }
    /*Function Name: print()
	 * Parameters: int
	 * Return Value:none
	 * Purpose:Print out the decomp rule.indents are used for formatting 
     */
    public void print(int indent) {
        String m = mem ? "true" : "false";
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println("decomp: " + pattern + " " + m);
        reasemb.print(indent + 2);
    }
    /*Function Name: pattern()
	 * Parameters: none
	 * Return Value:String
	 * Purpose:Get the pattern.
     */
    public String pattern() {
        return pattern;
    }
    /*Function Name: mem()
	 * Parameters: none
	 * Return Value:boolean
	 * Purpose:Get the mem flag.
     */
    public boolean mem() {
        return mem;
    }
    /*Function Name: nextRule()
	 * Parameters: none
	 * Return Value:String
	 * Purpose:Get the next reassembly rule.
     */
    public String nextRule() {
        if (reasemb.size() == 0) {// check if there are rules for the decomp
            System.out.println("No reassembly rule.");
            return null;
        }
        return (String)reasemb.elementAt(currReasmb);// return the reassemble rule
    }
    /*Function Name: nextRule()
	 * Parameters: none
	 * Return Value:String
	 * Purpose: Step to the next reassembly rule.
     *  If mem is true, pick a random rule.
     */
    public void stepRule() {
        int size = reasemb.size();
        if (mem) {// if memory is true
            currReasmb = (int)(Math.random() * size); // find a random reply
        }
        //  Increment and make sure it is within range.
        currReasmb++;
        if (currReasmb >= size) currReasmb = 0; // find next rule 
    }


}
