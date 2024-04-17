/* Class Name:ReasmbleReply
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This class maintains Eliza reassembly list.
*/
package Eliza;
import java.util.ArrayList;
import java.lang.*;
public class ReasmbleReply {
    ArrayList<String> reasembList = new ArrayList<String>();
    /*Function Name: add()
	 * Parameters:String (reasmb)
	 * Return Value:void
	 * Purpose:Add an element to the reassembly list.
     */
    public void add(String reasmb) {
        reasembList.add(reasmb);
    }
    /*Function Name: size()
	 * Parameters:none
	 * Return Value:Integer
	 * Purpose:Return size of the list
     */
    public Integer size() {
        return reasembList.size();
    }
    /*Function Name: elementAt()
	 * Parameters:int
	 * Return Value:String
	 * Purpose:returns Element At the i location passed 
     */
    public String elementAt(int i) {
        return reasembList.get(i);
    }
    /*Function Name: print()
	 * Parameters:int
	 * Return Value:void
	 * Purpose:Print the reassembly list.
     */
    public void print(int indent) {
        for (int i = 0; i < reasembList.size(); i++) {
            for (int j = 0; j < indent; j++) System.out.print(" ");
            String s = (String)reasembList.get(i);
            System.out.println("reasemb: " + s);
        }
    }
}
