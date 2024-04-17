/* Class Name:Memory
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This to maintain a list for memory recall
*/
package Eliza;
public class Memory {
    /** The memory size */
    final int memMax = 20;
    /** The memory */
    String memory[] = new String[memMax];
    /** The memory top */
    int memTop = 0;
	/*Function Name: save()
	 * Parameters: none
	 * Return Value:none
	 * Purpose:saves parameter string to memory 
     */
    public void save(String str) {
        if (memTop < memMax) {
            memory[memTop++] = new String(str);
        }
    }
	/*Function Name: get()
	 * Parameters: none
	 * Return Value:String
	 * Purpose:returns the lowest value in the stack 
     */
    public String get() {
        if (memTop == 0) return null;
        String m = memory[0];
        for (int i = 0; i < memTop-1; i++) //iterates thru the stack till last item
            memory[i] = memory[i+1];
        memTop--;
        return m;
    }
}

