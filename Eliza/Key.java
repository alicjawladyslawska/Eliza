/* Class Name:Key
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This class is used to maintain the key, a rank, and a list of decompositon rules.
*/
package Eliza;
public class Key {
    /** The key itself */
    String key;
    /** The numerical rank */
    int rank;
    /** The list of decompositions */
    DecompList decomp;
	/*Function Name: Key(); Constructor
	 * Parameters: String, int, DecompList
	 * Return Value:none
	 * Purpose:assign parameters to local variables.
     */
    public Key(String key, int rank, DecompList decomp) {
        this.key = key;
        this.rank = rank;
        this.decomp = decomp;
    }
	/*Function Name: Key(); Constructor
	 * Parameters: None
	 * Return Value:none
	 * Purpose:Another initialization for gotoKey.
     */
    public Key() {
        key = null;
        rank = 0;
        decomp = null;
    }
	/*Function Name: copy()
	 * Parameters: Key
	 * Return Value:none
	 * Purpose:Copies content of the target key in case of goto 
     */
    public void copy(Key k) {
        key = k.getKey();
        rank = k.rank();
        decomp = k.decomp();
    }
    /*Function Name: print()
	 * Parameters: int (indent for formatting)
	 * Return Value:none
	 * Purpose:Print the key and all under it.
     */
    public void print(int indent) {
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println("key: " + key + " " + rank);
        decomp.print(indent+2);
    }
    /*Function Name: printKey()
	 * Parameters: int (indent for formatting)
	 * Return Value:none
	 * Purpose:Print the key and rank only, not the rest.
     */
    public void printKey(int indent) {
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println("key: " + key + " " + rank);
    }
    /*Function Name: getKey()
	 * Parameters: none
	 * Return Value:String
	 * Purpose:Get the key value.
     */
    public String getKey() {
        return key;
    }
    /*Function Name: rank()
	 * Parameters: none
	 * Return Value:int
	 * Purpose:Get the rank.
     */
    public int rank() {
        return rank;
    }
    /*Function Name: decomp()
	 * Parameters: none
	 * Return Value:String
	 * Purpose:Get the decomposition list.
     */
    public DecompList decomp() {
        return decomp;
    }
}
