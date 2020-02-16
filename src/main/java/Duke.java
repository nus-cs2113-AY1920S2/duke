import java.util.Scanner;
import java.util.ArrayList;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Duke {
    private static int numberOfTasks = 0;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String logo = "***John***";
        displayWelcome(logo);
        startDuke();
        displayExit();
    }

    private static void displayWelcome(String logo) {
        System.out.println("Hello! I am " + logo);
        System.out.println("What would you like to do?");
    }

    private static void displayExit() {
        System.out.println("Bye. Hope to see you soon!");
    }

    private static void startDuke() {
        String userInput = "";
        Scanner in = new Scanner(System.in);
        while (true) {
            userInput = in.next();
            try {
                if (userInput.equals("bye")){
                    break;
                } else if (userInput.equals("list")){
                    listTasks();
                } else if (userInput.startsWith("delete")){
                    deleteTasks(in);
                } else if (userInput.startsWith("done")){
                    markTaskAsDone(in);
                } else if (userInput.startsWith("todo")){
                    addTodoTask(in);
                } else if (userInput.startsWith("deadline")){
                    addDeadlineTask(in);
                } else if (userInput.startsWith("event")){
                    addEventTask(in);
                } else {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void addTodoTask(Scanner in) throws DukeException {
        String todoTask = in.nextLine();
        if (todoTask == null || todoTask.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo newToDoTask = new Todo(todoTask);
        tasks.add(newToDoTask);
        numberOfTasks++;
        printNewTask();
    }

    private static void addDeadlineTask(Scanner in) throws DukeException {
        String deadlineTask = in.nextLine();

        if (deadlineTask == null || deadlineTask.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (!deadlineTask.contains("/by")) {
            throw new DukeException("☹ OOPS!!! The task has to be typed in this format (deadline {task description" +
                    "} /by {date}).");
        }

        String[] details = deadlineTask.split("/by ");

        if (details.length < 2) {
            throw new DukeException("☹ OOPS!!! Make sure to include both the description and the deadline.");
        }

        if (details.length > 2) {
            throw new DukeException("☹ OOPS!!! Make sure that only 1 /by character is inputted.");
        }

        String deadlineTaskDescription = details[0];
        String date = details[1];
        Deadline newDeadlineTask = new Deadline(deadlineTaskDescription, date);
        tasks.add(newDeadlineTask);
        numberOfTasks++;
        printNewTask();
    }

    private static void addEventTask(Scanner in) throws DukeException {
        String eventTask = in.nextLine();

        if (eventTask == null || eventTask.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        if (!eventTask.contains("/at")) {
            throw new DukeException("☹ OOPS!!! The task has to be typed in this format (event {task description" +
                    "} /at {date and time}).");
        }

        String[] details = eventTask.split("/at ");

        if (details.length < 2) {
            throw new DukeException("☹ OOPS!!! Make sure to include both the description and the deadline.");
        }

        if (details.length > 2) {
            throw new DukeException("☹ OOPS!!! Make sure that only 1 /by character is inputted.");
        }

        String eventTaskDescription = details[0];
        String date = details[1];
        Event newEventTask = new Event(eventTaskDescription, date);
        tasks.add(newEventTask);
        numberOfTasks++;
        printNewTask();
    }

    private static void markTaskAsDone(Scanner in) throws DukeException{
        if (!in.hasNextInt()) {
            throw new DukeException("☹ OOPS!!! The task item has to be an integer.");
        }

        int itemNumber = in.nextInt();
        if (itemNumber<=0 || itemNumber>numberOfTasks){
            throw new DukeException("☹ OOPS!!! The task item does not exist. Type \"list\" to see the task item " +
                    "number.");
        }

        tasks.get(itemNumber-1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(itemNumber-1));
    }

    private static void printNewTask() {
        System.out.println("New task added: ");
        System.out.println(" " + tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void listTasks() {
        System.out.println("Here are your tasks:");
        for (int j = 0; j < tasks.size(); j++) {
            System.out.println(j+1 + ". " + tasks.get(j));
        }
    }

    private static void deleteTasks(Scanner in) throws DukeException {
        if (!in.hasNextInt()) {
            throw new DukeException("☹ OOPS!!! The task item has to be an integer.");
        }

        int itemNumber = in.nextInt();
        if (itemNumber<=0 || itemNumber>numberOfTasks){
            throw new DukeException("☹ OOPS!!! The task item does not exist. Type \"list\" to see the task item " +
                    "number.");
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + tasks.get(itemNumber-1));
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        tasks.remove(itemNumber-1);
    }
}
