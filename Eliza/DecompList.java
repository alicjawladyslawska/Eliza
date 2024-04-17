/* Class Name:DecompList
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This class is used to maintain a list of Decomp objects since keys can have more than one decomp rule.  
*/
package Eliza;
import java.util.ArrayList;
public class DecompList {
    ArrayList<Decomp> decompList = new ArrayList<Decomp>();
    /*Function Name: add()
	 * Parameters: String(decomp rule), boolean, ReasmblReply Object
	 * Return Value:none
	 * Purpose:Add another decomp rule to the list.
     */
    public void add(String decompRule, boolean mem, ReasmbleReply reasmb) {
        decompList.add(new Decomp(decompRule, mem, reasmb));// add to decompList
    }
    /*Function Name: size()
	 * Parameters: None
	 * Return Value:Integer
	 * Purpose:Return size of the list
     */
    public Integer size() {
        return decompList.size();
    }
    /*Function Name: elementAt()
	 * Parameters: int
	 * Return Value:Decomp object
	 * Purpose: Element At 
     */
    public Decomp elementAt(int i) {
        return decompList.get(i);// returns the Decomp object at position i
    }
    /*Function Name: print()
	 * Parameters: int
	 * Return Value:void
	 * Purpose:Print the whole decomp list.
     */
    public void print(int indent) {
        for (int i = 0; i < size(); i++) { // iterate thru the list & print
            Decomp d = (Decomp)elementAt(i);
            d.print(indent);
        }
    }
}
