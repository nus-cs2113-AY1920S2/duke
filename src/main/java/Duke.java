package src.main.java;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("----------------------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("----------------------------------------------");
        String userInput;
        do {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            System.out.println("----------------------------------------------");
            System.out.println(userInput);
            System.out.println("----------------------------------------------");
        } while (!userInput.equalsIgnoreCase("bye"));

        System.out.println("----------------------------------------------");
        System.out.println("Bye. I hope to see u again soon ^^");
        System.out.println("----------------------------------------------");
    }
}
