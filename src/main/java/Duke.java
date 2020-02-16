import java.util.Scanner;
import java.util.ArrayList;
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
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String dataPath = "duke.txt";
    public static void main(String[] args) {
        String logo = "***John***";
        displayWelcome(logo);
        try {
            loadTasks();
        }
        catch (FileNotFoundException e){
            System.out.println(" ☹ OOPS!!! File not found.");
        }
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
        while (true) {
            try {
                userInput = in.next();
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    listTasks();
                } else if (userInput.startsWith("delete")) {
                    deleteTasks(in);
                    saveTasks();
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
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("☹ OOPS!!! File not found.");
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
        Deadline newDeadlineTask = new Deadline(deadlineTaskDescription, date);
        tasks.add(newDeadlineTask);
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
        Event newEventTask = new Event(eventTaskDescription, date);
        tasks.add(newEventTask);
        printNewTask();
    }

    private static void markTaskAsDone(Scanner in) throws DukeException{
        if (!in.hasNextInt()) {
            throw new DukeException("☹ OOPS!!! The task item has to be an integer.");
        }

        int itemNumber = in.nextInt();
        if (itemNumber <= 0 || itemNumber > tasks.size()) {
            throw new DukeException("☹ OOPS!!! The task item does not exist. Type \"list\" to see the task item " +
                    "number.");
        }

        tasks.get(itemNumber-1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(itemNumber-1));
    }

    private static void printNewTask() {
        System.out.println("New task added: ");
        System.out.println(" " + tasks.get(tasks.size() - 1));
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
        if (itemNumber <= 0 || itemNumber > tasks.size()) {
            throw new DukeException("☹ OOPS!!! The task item does not exist. Type \"list\" to see the task item " +
                    "number.");
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + tasks.get(itemNumber - 1));
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        tasks.remove(itemNumber-1);
    }

    private static void loadTasks() throws FileNotFoundException {
        File f = new File(dataPath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String taskString = s.nextLine();
            String[] details = taskString.split("\\|", -1);
            boolean isDone = Integer.parseInt(details[1]) == 1;
            String taskType = details[0];
            switch (taskType){
            case "T":
                tasks.add(new Todo(details[2], isDone));
                break;
            case "D":
                tasks.add(new Deadline(details[2], details[3], isDone));
                break;
            case "E":
                tasks.add(new Event(details[2], details[3], isDone));
                break;
            default:
                throw new FileNotFoundException("☹ OOPS!!! Problem loading data.");
            }
        }
    }

    private static void saveTasks() throws IOException {
        FileWriter fw = new FileWriter(dataPath);
        StringBuilder textToAdd = new StringBuilder();
        for (Task t : tasks) {
            textToAdd.append(t.convertToData()).append("\n");
        }
        fw.write(textToAdd.toString());
        fw.close();
    }
}
