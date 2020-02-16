import java.util.Scanner;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;



public class Duke {
    private static final int TASKLIST_SIZE = 100;
    private static int numberOfTasks = 0;
    private static final Task[] tasks = new Task[TASKLIST_SIZE];
    private static final String dataPath = "duke.txt";
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
        String userInput;
        Scanner in = new Scanner(System.in);
        try {
            loadTasks();
            while (true) {
                userInput = in.next();
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    listTasks();
                } else if (userInput.startsWith("done")) {
                    markTaskAsDone(in);
                    saveTasks();
                } else if (userInput.startsWith("todo")) {
                    addTodoTask(in);
                    saveTasks();
                } else if (userInput.startsWith("deadline")) {
                    addDeadlineTask(in);
                    saveTasks();
                } else if (userInput.startsWith("event")) {
                    addEventTask(in);
                    saveTasks();
                } else {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (DukeException e) {
                System.out.println(e);
        } catch (IOException e) {
                System.out.println("☹ OOPS!!! File not found.");
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

        String[] details = deadlineTask.split("/by ");

        if (details.length != 2) {
            throw new DukeException("☹ OOPS!!! Incorrect format.");
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

        String[] details = eventTask.split("/at ");

        if (details.length != 2) {
            throw new DukeException("☹ OOPS!!! Incorrect format.");
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
            throw new DukeException("☹ OOPS!!! The task item does not exist. Type \"list\" to see the task item " +
                    "number.");
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

    private static void loadTasks() throws FileNotFoundException, DukeException {
        File f = new File(dataPath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String taskString = s.nextLine();
            String[] details = taskString.split("\\|", -1);
            boolean isDone = Integer.parseInt(details[1]) == 1;
            String taskType = details[0];
            switch (taskType){
            case "T":
                tasks[numberOfTasks] = new Todo(details[2], isDone);
                break;
            case "D":
                tasks[numberOfTasks] = new Deadline(details[2], details[3], isDone);
                break;
            case "E":
                tasks[numberOfTasks] = new Event(details[2], details[3], isDone);
                break;
            default:
                throw new DukeException("☹ OOPS!!! Problem loading data.");
            }
            numberOfTasks++;
        }
    }

    private static void saveTasks() throws IOException {
        FileWriter fw = new FileWriter(dataPath);
        StringBuilder textToAdd = new StringBuilder();
        for (int i = 0; i < numberOfTasks; i++) {
            textToAdd.append(tasks[i].convertToData()).append("\n");
        }
        fw.write(textToAdd.toString());
        fw.close();
    }
}
