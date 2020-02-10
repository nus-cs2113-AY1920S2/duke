import java.util.Scanner;

public class Duke {
    private static final int TASKLIST_SIZE = 100;
    private static int numberOfTasks = 0;
    private static final Task[] tasks = new Task[TASKLIST_SIZE];
    public static void main(String[] args) {
        String logo = "***John***";
        displayWelcome(logo);
        startDuke();
        displayExit();
    }

    private static void displayWelcome(String logo) {
        System.out.println("Hey! I am " + logo);
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
                System.out.println(e);
            }
        }
    }


    private static void addTodoTask(Scanner in) throws DukeException {
        String todoTask = in.nextLine();
        if (todoTask == null || todoTask.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        tasks[numberOfTasks] = new Todo(todoTask);
        numberOfTasks++;
        printNewTask();
    }

    private static void addDeadlineTask(Scanner in) throws DukeException {
        String deadlineTask = in.nextLine();

        if (deadlineTask == null || deadlineTask.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (!deadlineTask.contains("/by")) {
            throw new DukeException("☹ OOPS!!! The format of the deadline task is invalid.");
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
        tasks[numberOfTasks] = new Deadline(deadlineTaskDescription, date);
        numberOfTasks++;
        printNewTask();
    }

    private static void addEventTask(Scanner in) throws DukeException {
        String eventTask = in.nextLine();

        if (eventTask == null || eventTask.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        if (!eventTask.contains("/at")) {
            throw new DukeException("☹ OOPS!!! The format of the event task is invalid.");
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
        tasks[numberOfTasks] = new Event(eventTaskDescription, date);
        numberOfTasks++;
        printNewTask();
    }

    private static void markTaskAsDone(Scanner in) throws DukeException{
        if (!in.hasNextInt()) {
            throw new DukeException("☹ OOPS!!! The task item has to be an integer.");
        }

        int itemNumber = in.nextInt();
        if (itemNumber<=0 || itemNumber>numberOfTasks){
            throw new DukeException("☹ OOPS!!! The task item does not exist.");
        }

        tasks[itemNumber-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[itemNumber-1]);
    }

    private static void printNewTask() {
        System.out.println("New task added: ");
        System.out.println(" " + tasks[numberOfTasks-1]);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    private static void listTasks() {
        System.out.println("Here are your tasks:");
        for (int j = 0; j < numberOfTasks; j++) {
            System.out.println(j+1 + ". " + tasks[j]);
        }
    }
}
