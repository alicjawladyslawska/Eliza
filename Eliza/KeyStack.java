/* Class Name:KeyStack
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This class keeps a stack of keys. The keys are kept in rank order.
*/
package Eliza;
public class KeyStack {
    /** The stack size */
    final int stackSize = 50;
    /** The key stack */
    Key keyStack[] = new Key[stackSize];
    /** The top of the key stack */
    int keyTop = 0;

    /*Function Name: print()
	 * Parameters:  none
	 * Return Value:none
	 * Purpose:Print all the keys in key stack
     */
    public void print() {
        System.out.println("Key stack " + keyTop);
        for (int i = 0; i < keyTop; i++) {
            keyStack[i].printKey(0);
        }
    }
    /*Function Name: keyTop()
	 * Parameters:  none
	 * Return Value:int
	 * Purpose: Get the stack size.
     */
    public int keyTop() {
        return keyTop;
    }
    /*Function Name: reset()
	 * Parameters:  none
	 * Return Value:none
	 * Purpose: Reset the key stack.
     */
    public void reset() {
        keyTop = 0;
    }
    /*Function Name: getKey()
	 * Parameters:  none
	 * Return Value:Key
	 * Purpose: Get a key from the stack.
     */
    public  Key getKey(int n) {
        if (n < 0 || n >= keyTop) return null; // if n is greater than or equal to top value return null
        return keyStack[n];
    }
    /*Function Name: pushKey()
	 * Parameters:  Key
	 * Return Value:void
	 * Purpose: Push a key in the stack.
     *  Keep the highest rank keys at the bottom.
     */
    public void pushKey(Key key) {
        if (key == null) {
            System.out.println("push null key");
            return;
        }
        int i;
        for (i = keyTop; i > 0; i--) {//iterate to find highest key
            if (key.rank > keyStack[i-1].rank) keyStack[i] = keyStack[i-1]; // move higher keys to bottom
            else break;
        }
        keyStack[i] = key;
        keyTop++;
    }


}