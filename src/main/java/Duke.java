import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("Bye. Hope to see you again soon!");
        String userInput = "";
        String[] userInputList = new String[100];
        int inputListCount = 0;

        do {
            Scanner scan = new Scanner(System.in);
            userInput = scan.nextLine();
            if(userInput.equals("list")) {
                for (int i = 0; i < inputListCount; i++) {
                    System.out.println((i+1) + ". " + userInputList[i]);
                }
            } else if(userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("added: " + userInput);
                userInputList[inputListCount] = userInput;
                inputListCount++;
            }
        } while (!userInput.equalsIgnoreCase("bye"));
    }
}
