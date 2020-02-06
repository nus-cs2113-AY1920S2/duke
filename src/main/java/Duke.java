import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(String.format("%d. %s", task.getTaskId(), task.toString()));
        }
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | __ __ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void main(String[] args) {
        printLogo();
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            String[] splitUpInput = userInput.split(" ");
            if (splitUpInput[0].equals("list")) {
                printTasks();
            } else if (splitUpInput[0].equals("done")) {
                tasks.get(Integer.parseInt(Character.toString(
                        userInput.charAt(userInput.length() - 1))) - 1).setDone();
            } else if (splitUpInput[0].equals("todo")) {
                Task task = new ToDo(userInput.substring(5));
                tasks.add(task);
                System.out.println("Got it. I've added this task:\n  " + task.toString()
                        + String.format("\nNow you have %d tasks in the list.", Task.getTotalNumOfTasks()));
            } else if (splitUpInput[0].equals("deadline")) {
                Task task = new Deadline(userInput.substring(9));
                tasks.add(task);
                System.out.println("Got it. I've added this task:\n  " + task.toString()
                        + String.format("\nNow you have %d tasks in the list.", Task.getTotalNumOfTasks()));
            } else if (splitUpInput[0].equals("event")) {
                Task task = new Event(userInput.substring(6));
                tasks.add(task);
                System.out.println("Got it. I've added this task:\n  " + task.toString()
                        + String.format("\nNow you have %d tasks in the list.", Task.getTotalNumOfTasks()));
            }
            userInput = scan.nextLine(); 
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
