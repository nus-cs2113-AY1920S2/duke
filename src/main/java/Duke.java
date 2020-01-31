package src.main.java;
import java.util.Scanner;

public class Duke {

    public static final int MAXIMUM_TASK = 100;

    public static void main(String[] args) {
        greetUser();
        String userInput = "";
        Task[] userInputList = new Task[MAXIMUM_TASK];
        do {
            userInput = scanInput();
            switch(userInput) {
                case "list" :
                    listTasks(userInputList);
                    break;
                case "bye" :
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "done":
                    markedAsDone(userInput, userInputList);
                    break;
                default:
                    addTask(userInput, userInputList);
                    break;
            }
        } while (!userInput.equalsIgnoreCase("bye"));
    }

    private static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    private static void addTask(String userInput, Task[] userInputList) {
        System.out.println("added: " + userInput);
        Task newInput = new Task(userInput);
        userInputList[Task.totalNumberOfTask - 1] = newInput;
    }

    private static void markedAsDone(String userInput, Task[] userInputList) {
        int taskNumber = Integer.valueOf(userInput.replaceAll("[^0-9]", "")) - 1;
        userInputList[taskNumber].completedTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    [" + userInputList[taskNumber].getStatusIcon() + "] " + userInputList[taskNumber].description);
    }

    private static void listTasks(Task[] userInputList) {
        for (int i = 0; i < Task.totalNumberOfTask; i++) {
            System.out.println((i+1) + ".[" + userInputList[i].getStatusIcon() + "] " + userInputList[i].description);
        }
    }

    private static String scanInput() {
        String userInput;
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();
        return userInput;
    }
}
