import Eliza.*;

public class Testing {
    public static void main(String[] args) {
        System.out.println("Testing Eliza.java");
        String[] inputArgs = {};
        inputArgs = new String[]{"","test"};
        System.out.println("Eliza with no script file:");
        Eliza.main(inputArgs);
        System.out.println();

        System.out.println("Eliza with a script that doesnt exist..");
        inputArgs = new String[]{"eieuoq","test"};
        Eliza.main(inputArgs);
        System.out.println();

        System.out.println("Eliza as a therapist - autotest:");
        inputArgs = new String[]{"script","test"};
        Eliza.main(inputArgs);
        System.out.println();

        System.out.println("Eliza as an 8 Yr Old:");
        inputArgs = new String[]{"script_8yrold.txt","test_8yrold.txt"};
        Eliza.main(inputArgs);
        System.out.println();

        System.out.println("Eliza as a friend:");
        inputArgs = new String[]{"script_friend.txt","test_friend.txt"};
        Eliza.main(inputArgs);
        System.out.println();

    }
}
