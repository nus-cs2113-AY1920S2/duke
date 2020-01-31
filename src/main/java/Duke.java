import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> list = new ArrayList<Task>();

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println(String.format("%d. [%c] %s", task.getTaskId(),
                    task.isDone() ? '✓' : '✗', task.getTaskName()));
        }
    }

    public static void main(String[] args) {
        String logo = " ____         _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printTasks();
            } else if (userInput.length() > 4 && userInput.substring(0, 5).equals("done ")) {
                list.get(Integer.parseInt(Character.toString(
                        userInput.charAt(userInput.length() - 1))) - 1).setDone();
            } else {
                System.out.println("added: " + userInput);
                Task task = new Task(userInput);
                list.add(task);
            }
            userInput = scan.nextLine(); 
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
