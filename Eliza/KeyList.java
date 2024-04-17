/* Class Name:KeyList
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This class is used to stores all the keys.
*/
package Eliza;
import java.util.ArrayList;
public class KeyList {
    ArrayList<Key> keyList = new ArrayList<Key>();
	/*Function Name: add()
	 * Parameters: String, int, DecompList
	 * Return Value:none
	 * Purpose:Add a new key.
     */ 
    public void add(String key, int rank, DecompList decomp) {
        keyList.add(new Key(key, rank, decomp));
    }
    /*Function Name: print()
	 * Parameters:  int
	 * Return Value:none
	 * Purpose:Print all the keys.
     */
    public void print(int indent) {
        for (int i = 0; i < keyList.size(); i++) {//iterate thru the list
            Key k = (Key)keyList.get(i); // get key
            k.print(indent); // print key
        }
    }
    /*Function Name: getKey()
	 * Parameters:  String
	 * Return Value:Key
	 * Purpose:Search the key list for a given key.
     *  Return the Key if found, else null.
     */
    Key getKey(String s) {
        for (int i = 0; i < keyList.size(); i++) { //iterate thru KeyList
            Key key = (Key)keyList.get(i); // get Key
            if (s.equals(key.getKey())) return key; //if matched return key
        }
        return null; // no match return null
    }
    /*Function Name: buildKeyStack()
	 * Parameters:  KeyStack,String (Key string)
	 * Return Value:none
	 * Purpose:Break the string s into words.
     *  For each word, if isKey is true, then push the key
     *  into the stack.
     */
    public void buildKeyStack(KeyStack stack, String keyStr) {
        stack.reset(); // clear keys in stack 
        keyStr = ElizaUtility.trim(keyStr); // remove spaces 
        String lines[] = new String[2];
        Key k;
        while (ElizaUtility.match(keyStr, "* *", lines)) {// iterate till all keys are stacked 
            k = getKey(lines[0]);
            if (k != null) stack.pushKey(k);
            keyStr = lines[1];
        }
        k = getKey(keyStr);
        if (k != null) stack.pushKey(k);
    }
}