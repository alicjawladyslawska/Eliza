/* Class Name:ElizaProcessor
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This is the main class that reads the script file, gathers the input and 
 *          proesses the response.  
 * Comments: Calls the ElizaUtility, Key and all the other classes to craft an appropriate response. 
 */
package Eliza;
import java.lang.*;
import java.io.*;

 public class ElizaProcessor {
    final boolean echoInput = false;
    final boolean printData = false;
    final boolean printKeys = false;
    final boolean printSyns = false;
    final boolean printPrePost = false;
    final boolean printInitialFinal = false;
    /** The key list */
    KeyList keys = new KeyList();
    /** The syn list */
    Synonym syns = new Synonym();
    /** The pre list */
    PrePostList pre = new PrePostList();
    /** The post list */
    PrePostList post = new PrePostList();
    /** Initial string */
    String initial = "Hello.";
    /** Final string */
    String finl = "Goodbye.";
    /** List of words that indicates quit*/
    WordList quit = new WordList();
    /** List to manage the key by its priority */
    KeyStack keyStack = new KeyStack();
    /** List to store rules marked with a $ in memory */
    Memory mem = new Memory();
    DecompList lastDecomp; // create a variable for decompostion list class 
    ReasmbleReply lastReasemb; // create a variable for reassemble class 
    boolean finished = false; // boolean to indicate when the chat has ended. 
    static final int success = 0; // flag to indicate success
    static final int failure = 1; // flag to indicate failure
    static final int gotoRule = 2; // flag to indicate is there is a different key to goto
    /*Function Name: finished() ; 
	 * Parameters: none
	 * Return Value:boolean
	 * Purpose: This method is called when the user ends the chat with a quit word
	 * */
    public boolean finished() {
        return finished;
    }
	/*Function Name: getScriptData()
	 * Parameters: String (each line of the script file)
	 * Return Value:none
	 * Purpose: Each line is processed and depending on the tag, the value is stored 
     *         in the respective object. 
	 * */
    public void getScriptData(String lineStr) {
        String lines[] = new String[4]; // a string array to store the tokenized value of the line
        if (ElizaUtility.match(lineStr, "*reasmbstr: *", lines)) { // check if line has reasmbstr
            if (lastReasemb == null) { // reasmb should not be null as it will be initiated when decomp rule is processed
                System.out.println("Error: no last reasemb"); // if it is null - display an error. 
                return;
            }
            lastReasemb.add(lines[1]); // add the reassemble rule. 
        }
        else if (ElizaUtility.match(lineStr, "*decomprule: *", lines)) {// process decomprule line
            if (lastDecomp == null) {// this should not be null as it will be initialized when Key is processed. 
                System.out.println("Error: no last decomprule"); // display an error if it is null
                return;
            }
            lastReasemb = new ReasmbleReply();// create reasmble object (there is a decomprule so get reasmb ready)
            String temp = new String(lines[1]); // store the decomp rule 
            if (ElizaUtility.match(temp, "$ *", lines)) { //$ means this rule will be stored in mem
                lastDecomp.add(lines[0], true, lastReasemb);// Memory = true
            } else {
                lastDecomp.add(temp, false, lastReasemb); // memory is false
            }
        }
        else if (ElizaUtility.match(lineStr, "*key: * #*", lines)) { // process key line with a priority # 
            lastDecomp = new DecompList(); // create a decomp object instance 
            lastReasemb = null; // clear the reasmb handle 
            int nPriority = 0;
            if (lines[2].length() != 0) { // Check if there is a priority for the key 
                try {
                    nPriority = Integer.parseInt(lines[2]); // get the priorty value and store in n
                } catch (NumberFormatException e) {
                    System.out.println("Number is wrong in key: " + lines[2]);
                }
            }
            keys.add(lines[1], nPriority, lastDecomp); // add the key to the key list with priority. 
        }
        else if (ElizaUtility.match(lineStr, "*key: *", lines)) { // process a Key row without a priority.
            lastDecomp = new DecompList();//initiate decomp list 
            lastReasemb = null; // reset reasmb variable
            keys.add(lines[1], 0, lastDecomp); // add to Keys list with priority as 0 
        }
        else if (ElizaUtility.match(lineStr, "*syn: * *", lines)) { //process a line with syn( synonym)
            WordList words = new WordList(); // create a wordlist instance - stores synonyms 
            words.add(lines[1]); // add the words with syn: label in the script. 
            lineStr = lines[2]; // get the first synonym 
            while (ElizaUtility.match(lineStr, "* *", lines)) { //iterate till all synonyms are stored
                words.add(lines[0]);
                lineStr = lines[1];
            }
            words.add(lineStr); // create the wordlist
            syns.add(words);// store the wordlist in synonym list. 
        }
        else if (ElizaUtility.match(lineStr, "*pre: * *", lines)) { // process the pre line
            pre.add(lines[1], lines[2]); // add to pre list 
        }
        else if (ElizaUtility.match(lineStr, "*post: * *", lines)) { //process the post line
            post.add(lines[1], lines[2]); // add to post list
        }
        else if (ElizaUtility.match(lineStr, "*initial: *", lines)) { //process initial line
            initial = lines[1]; // store to initial string. 
        }
        else if (ElizaUtility.match(lineStr, "*final: *", lines)) { //process final line
            finl = lines[1]; // store in finl string. 
        }
        else if (ElizaUtility.match(lineStr, "*quit: *", lines)) {// process quit line
            quit.add(" " + lines[1]+ " "); // store the quit words into the quit list.
        }
        else {
            System.out.println("Unrecognized input: " + lineStr); // if the lines dont have any of the tags above(spelling errors etc) - display error
        }
    }
	/*Function Name: print()
	 * Parameters: none
	 * Return Value:none
	 * Purpose: prints the values read from the script. 
	 * */
    public void print() {
        if (printKeys) keys.print(0); // if printKeys set to true, print the keys
        if (printSyns) syns.print(0); // if printSyns set to true, print the synonyms
        if (printPrePost) { // if printPrePost set to true, print the pre/post rules
            pre.print(0);
            post.print(0);
        }
        if (printInitialFinal) {// if true, print the intial and final strings. 
            System.out.println("initial: " + initial);
            System.out.println("final:   " + finl);
            quit.print(0);
        }
    }
	/*Function Name: processInput()
	 * Parameters: String
	 * Return Value:String
	 * Purpose: prints the values read from the script. 
	 * */
    public String processInput(String inputStr) {
        String reply;
        //  Do some input transformations first.
        inputStr = inputStr.toLowerCase(); // convert to lower case. 
        inputStr = ElizaUtility.translate(inputStr, "@#$%^&*()_-+=~`{[}]|:;<>\\\"",
        "                          "  ); // replace spl characters with space
        inputStr = ElizaUtility.translate(inputStr, ",?!", "..."); // a comma or exclamatory will be processed as separate sentence. 
        //  Compress out multiple spaces.
        inputStr = ElizaUtility.compress(inputStr);
        String lines[] = new String[2];
        //  Break apart sentences, and do each separately.
        while (ElizaUtility.match(inputStr, "*.*", lines)) {// process each sentence separately
            reply = processSentence(lines[0]); // take each sentence
            if (reply != null) return reply; // if not null then return it. 
            inputStr = ElizaUtility.trim(lines[1]); // if no match - loop thru
        }
        if (inputStr.length() != 0) { // check if inputstr has content 
            reply = processSentence(inputStr); // see if a valid response is created 
            if (reply != null) return reply; // return if not null 
        }
        //  Nothing matched, so try memory.
        String memStr = mem.get();
        if (memStr != null) return memStr; // get the rule from memory
        //  No memory, reply with none.
        Key key = keys.getKey("none"); // if nothing matched - get response from none 
        if (key != null) {
            Key dummy = null;
            reply = decompose(key, inputStr, dummy);
            if (reply != null) return reply;
        }
        //  No "none", just say anything.
        return "I am at a loss for words.";
    }
	/*Function Name: processSentence()
	 * Parameters: String
	 * Return Value:String
	 * Purpose: Process a sentence.
     *  (1) Make pre transformations.
     *  (2) Check for quit word.
     *  (3) Scan sentence for keys, build key stack.
     *  (4) Try decompositions for each key.
	 * */
    String processSentence(String inputStr) {
        inputStr = pre.translate(inputStr);// change "you" in input to "I" etc
        inputStr = ElizaUtility.pad(inputStr);// add spaces 
        if (quit.find(inputStr)) {// if a quit word is found end. 
            finished = true; // set the finished flag to true
            return finl;// return the string stored for final: in the script. 
        }
        keys.buildKeyStack(keyStack, inputStr); // Build the key list 
        for (int i = 0; i < keyStack.keyTop(); i++) { // search for the top priority key
            Key gotoKey = new Key(); // instantiate Key for gotoKey to pass to decomposition
            String reply = decompose(keyStack.getKey(i), inputStr, gotoKey);// create the reply.
            if (reply != null) return reply; // if reply is not null return reply.
            //  If decomposition returned gotoKey, try it
            while (gotoKey.getKey() != null) {
                reply = decompose(gotoKey, inputStr, gotoKey); // get reply from gotokey
                if (reply != null) return reply;
            }
        }
        return null;
    } 
	/*Function Name: decompose()
	 * Parameters: Key(primary key),String(inputStr),Key(gotoKey)
	 * Return Value:String
	 * Purpose: Decompose the input string based on given key:
     *  Try each decomposition rule in order.
     *  If it matches, assemble a reply and return it.
     *  If assembly fails, try another decomposition rule.
     *  If assembly is a goto rule, return null and give the key.
     *  If assembly succeeds, return the reply;
     */
    String decompose(Key key, String inputStr, Key gotoKey) {
        String reply[] = new String[10]; // create a reply array of strings
        for (int i = 0; i < key.decomp().size(); i++) { // iterate thru decomp list for the key
            Decomp decompObj = (Decomp)key.decomp().elementAt(i); //get the ith element from the decomp lsit 
            String pat = decompObj.pattern();
            if (syns.matchDecomp(inputStr, pat, reply)) { // match the pattern with inputStr
                String rep = assemble(decompObj, reply, gotoKey); //assemble a response
                if (rep != null) return rep; // return response. 
                if (gotoKey.getKey() != null) return null;
            }
        }
        return null;
    }
	/*Function Name: assemble()
	 * Parameters: Decomp(Decomp object),String[] (reply),Key(gotoKey)
	 * Return Value:String
	 * Purpose: Assembles a reply from a decomp rule and the input.
     *  If the reassembly rule is goto, return null and give
     *    the gotoKey to use.Otherwise return the response.
     */
    String assemble(Decomp decompObj, String reply[], Key gotoKey) {
        String lines[] = new String[3]; // create a string array
        decompObj.stepRule(); // find the next rule in the decomp object
        String rule = decompObj.nextRule(); // store next rule in a string
        if (ElizaUtility.match(rule, "goto *", lines)) { // if there is a goto
            //  goto rule -- set gotoKey and return false.
            gotoKey.copy(keys.getKey(lines[0])); // copy the line from the goto key pointer
            if (gotoKey.getKey() != null) return null; 
            System.out.println("Goto rule did not match key: " + lines[0]);
            return null;
        }
        String workStr = "";
        while (ElizaUtility.match(rule, "* %#%*", lines)) {// check if a input string is to be substituted in teh response
            //  reassembly rule with number substitution
            rule = lines[2];// there might be more
            int n = 0;
            try {
                n = Integer.parseInt(lines[1]) - 1; // get the number 
            } catch (NumberFormatException e) {
                System.out.println("Number is wrong in reassembly rule " + lines[1]);
            }
            if (n < 0 || n >= reply.length) {
                System.out.println("Substitution number is incorrect " + lines[1]);
                return null;
            }
            reply[n] = post.translate(reply[n]);// call translate() to plug in the string pertaining to n
            
            workStr += lines[0] + " " + reply[n];// concatenate the response
        }
        workStr += rule;
        if (decompObj.mem()) { // check if memory flag is true
            mem.save(workStr); // save the reply in memory 
            return null;
        }
        return workStr; // return the assembled string
    }
	/*Function Name: readScript()
	 * Parameters: String (name of script file)
	 * Return Value:int
	 * Purpose: reads the script file and calls getScriptData() to extract data.
     */
    int readScript( String script) {
        try {
                BufferedReader reader = new BufferedReader(new FileReader(script));//read the script file
                while (true) { //start while loop
                    String s;
                    s = reader.readLine(); // read line by line
                    if (s == null) break; // if no more lines break
                    if (echoInput) System.out.println(s); // if echoInput is true - print each line 
                    getScriptData(s);// call method to process script file and store values in objects
                }
        } catch (IOException e) {
            System.out.println("There was a problem reading the script file.");
            System.out.println("Tried " + script);
            return 1; // failure
        }
        if (printData) print();
        return 0;// success 
    }
	/*Function Name: runProgram()
	 * Parameters: none
	 * Return Value:int
	 * Purpose: Gets input from user and calls processInput to validate and assemble a response
     */
    int runProgram(String test) {
        try {
            BufferedReader inreader;
            if(test.length() > 2)
            {
                inreader = new BufferedReader(new FileReader(test));//read the script file
            }
            else{ 
                inreader = new BufferedReader(new InputStreamReader(System.in));// read from input 
            }
            String strInput;
            strInput = "Hello.";// default prompt
            while (true) {
                System.out.println(">> " + strInput); // display the user input (acknowledging the input)
                String reply = processInput(strInput);// call process method
                System.out.println(reply); // print the reply
                if (finished) break;// if finished is true - break 
                strInput = inreader.readLine(); // keep reading next input from user 
                if (strInput == null) break;
            }
        } catch (IOException e) {
            System.out.println("Problem reading test file.");
            return 1;
        }
    return 0;
    }


 }