/* Class Name:Eliza
 * Written By: Sid Ramachandran & Alicia 
 * Date: 9th February
 * Purpose: This is the entry class with the main() method that calls the ElizaProcessor  
 * Comments: calls the readScript() and runProgram in ElizaProcessor class
 */
package Eliza;

public class Eliza {
    public static void main(String args[]) {
        String script = "Eliza/script";// default script 
        String test = ""; // test word
        ElizaProcessor eliza = new ElizaProcessor();// instantiate ElizaProcessor
        if (args.length > 0) 
            script = args[0]; // take the input
        else 
        {
            System.out.println("No script given. Default therapist used.");
        }
        int res = eliza.readScript(script); // call readScript() method. 
        if (res == 0) 
        {
            if (args.length > 1) 
                test = args[1];
            res = eliza.runProgram(test);// call runProgram() to process user input
        }
        //System.exit(res); // when user quits, system returns 1 and exits 
    }

}
