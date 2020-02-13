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

    public static void inputChecker(String userInput) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (userInput.startsWith("list")) {
            printTasks();
        } else if (userInput.startsWith("done")) {
            tasks.get(Integer.parseInt(Character.toString(
                    userInput.charAt(userInput.length() - 1))) - 1).setDone();
        } else if (userInput.startsWith("todo")) {
            Task task = new ToDo(userInput.substring(5));
            tasks.add(task);
            System.out.println("Got it. I've added this task:\n  " + task.toString()
                    + String.format("\nNow you have %d tasks in the list.", Task.getTotalNumOfTasks()));
        } else if (userInput.startsWith("deadline")) {
            Task task = new Deadline(userInput.substring(9));
            tasks.add(task);
            System.out.println("Got it. I've added this task:\n  " + task.toString()
                    + String.format("\nNow you have %d tasks in the list.", Task.getTotalNumOfTasks()));
        } else if (userInput.startsWith("event")) {
            Task task = new Event(userInput.substring(6));
            tasks.add(task);
            System.out.println("Got it. I've added this task:\n  " + task.toString()
                    + String.format("\nNow you have %d tasks in the list.", Task.getTotalNumOfTasks()));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void printLogo() {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | __ __ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         */
        String logo = "|  |  \n" +
                "|--| .\n" +
                "|  | |\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("Sorry I don't understand that command. Here is the list of commands available\n"
                + "\"bye\": to exit\n\"list\": to show the list of all your tasks\n\"todo\": add a todo\n" +
                "\"deadline\": add a deadline\n\"event\": add an event\n" +
                "\"done\": check off a task on your list");
        System.out.println("OIOIOIO");
    }

    public static void main(String[] args) {
        printLogo();
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            try {
                inputChecker(userInput);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You have given the wrong number of arguments\n" +
                        "Please try again or input \"bye\" to exit");
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry I don't understand that command. Here is the list of commands available\n"
                        + "\"bye\": to exit\n\"list\": to show the list of all your tasks\n\"todo\": add a todo\n" +
                        "\"deadline\": add a deadline\n\"event\": add an event\n" +
                        "\"done\": check off a task on your list");
            }
            userInput = scan.nextLine();
        }
        System.out.println("Bye!");
    }
}
