/* Class Name:Memory
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This to maintain Eliza pre-post entry (two words).
 *  This is used to store pre transforms or post transforms.
*/
package Eliza;

public class PrePost {
    /** The words stores with pre: or post: tags in script file */
    String src; //word 1
    String dest;// word 2
	/*Function Name: PrePost(); Constructor
	 * Parameters: String (source), String (dest)
	 * Return Value:none
	 * Purpose:assign parameters to local variables.
     */
    PrePost(String src, String dest) {
        this.src = src;
        this.dest = dest;
    }

    /*Function Name: print()
	 * Parameters: int (indent for formatting)
	 * Return Value:none
	 * Purpose:Print the pre-post entry.
     */
    public void print(int indent) {
        for (int i = 0; i < indent; i++) System.out.print(" ");//iterate thru the indent to print space
        System.out.println("pre-post: " + src + "  " + dest); // print the pre and post 
    }

    /*Function Name: src()
	 * Parameters: int (indent for formatting)
	 * Return Value:none
	 * Purpose:Get src string
     */
    public String src() {
        return src;
    }
    /*Function Name: dest()
	 * Parameters: int (indent for formatting)
	 * Return Value:none
	 * Purpose:Get dest. string
     */
    public String dest() {
        return dest;
    }
}
