# Please read this file before running the program.

CS 1006 Project 1
___________________________________

Description: This assignment has one main program that is run to start the chatbot Eliza. This program calls the other required programs to load the scripts and prepare the results. 

Table of contents:

1. Eliza zip file 
2. script (for therapist)
3. script_8yrold.txt
4. script_friend.txt
5. Testing.java (for auto testing all the 3 scripts
6. test (for therapist)
3. test_8yrold.txt
4. test_friend.txt

How to run the project:
1. First, unzip the zip file to a folder. Or pull it from GitHub. 
2. This will create a Eliza folder and copy all the classes and script files.
3. Then, run $java -cp . *.java to compile the classes. 
4. Since we are using a package, move to the parent folder $cd..
5. Now, run the program $java -cp . Eliza.Eliza Eliza/script 
6. Change the scripts with the name as given above
7. No script will default to script which is therapist. 

How to run the test program:
1. compile Testing. $javac -cp . Testing.java
2. Run Testing program :  $java -cp . Testing 
Test scripts will be read to simulate the conversations. 

Script notes:
1. The program is dynamic and is driven by the script. You can add your own keys or synonyms in the script in the format already provided. 
2. If you want to store any particular decomp rule in memory add a $ after the * for that rule. 



Credits:
1. Class notes and assignments - Dr Dobson and Dr Mann
2. https://en.wikipedia.org/wiki/ELIZA
3. All other credits are included in the project report in context. 