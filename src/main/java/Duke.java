import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printGreeting();
        trackTasks();
        printExitMessage();
    }

    private static void trackTasks() {
        String[] tasks = new String[100];
        String input;
        int taskCounter = 0;
        Scanner in = new Scanner(System.in);
        while(true) {
            input = in.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println("    " + Integer.toString(i+1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCounter] = input;
                System.out.println("    added: " + input);
                taskCounter++;
            }
        }
    }

    private static void printExitMessage() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    private static void printGreeting() {
        System.out.println("    Hello I'm Yapyap.");
        System.out.println("    What can I do for you?");
    }
}
