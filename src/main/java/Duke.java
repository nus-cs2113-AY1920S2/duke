import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();
    static String commands = "Here is the list of commands available\n"
            + "\"bye\": to exit\n" +
            "\"list\": to show the list of all your tasks\n" +
            "\"todo\": add a todo\n" +
            "\"deadline\": add a deadline\n" +
            "\"event\": add an event\n" +
            "\"done\": check off a task on your list";

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        String output = "";
        for (Task task : tasks) {
            System.out.println(String.format("%d. %s", task.getTaskId(), task.toString()));
            output += String.format("%d. %s\n", task.getTaskId(), task.toString());
        }
        try (PrintWriter printer = new PrintWriter("out.txt")) {
            printer.println(output);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void checkInput(String userInput) throws IndexOutOfBoundsException, IllegalArgumentException {
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
        } else if (userInput.startsWith("delete")) { // output is wrong once we have double digits. like i fI input 21, it actually works and won't give error
            int taskId = Integer.parseInt(Character.toString(userInput.charAt(7)));
            Task task = tasks.get(taskId - 1);
            tasks.remove(task);
            System.out.println("I have removed this task:\n" + task.toString() +
                    String.format("\nYou only have %d tasks left to do. 加油!", tasks.size()));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | __ __ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(commands);
    }

    public static void main(String[] args) {
        printLogo();
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            try {
                checkInput(userInput);
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("You have given the wrong number of arguments\n" +
                        "Please try again or input \"bye\" to exit");
                System.out.println(exception.getMessage());
            } catch (IllegalArgumentException exception) {
                System.out.println("Sorry I don't understand that command.\n" + commands);
                System.out.println(exception.getMessage());
            }
            userInput = scan.nextLine();
        }
        System.out.println("Bye from Singapore!");
    }
}
