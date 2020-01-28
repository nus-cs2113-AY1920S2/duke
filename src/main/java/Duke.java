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
        System.out.println("What can I do for you?");
        System.out.println("Bye. Hope to see you again soon!");
        String userInput = "";
        Task[] userInputList = new Task[100];

        do {
            Scanner scan = new Scanner(System.in);
            userInput = scan.nextLine();
            if(userInput.equals("list")) {
                for (int i = 0; i < Task.totalNumberOfTask; i++) {
                    System.out.println((i+1) + ".[" + userInputList[i].getStatusIcon() + "] " + userInputList[i].description);
                }
            } else if(userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(userInput.contains("done")) {
                int taskNumber = Integer.valueOf(userInput.replaceAll("[^0-9]", "")) - 1;
                userInputList[taskNumber].completedTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    [" + userInputList[taskNumber].getStatusIcon() + "] " + userInputList[taskNumber].description);
            } else {
                System.out.println("added: " + userInput);
                Task newInput = new Task(userInput);
                userInputList[Task.totalNumberOfTask - 1] = newInput;
            }
        } while (!userInput.equalsIgnoreCase("bye"));
    }
}
