package duke;

import duke.exception.DukeArgumentException;
import duke.exception.DukeIndexException;
import duke.exception.DukeNullException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * Duke is a chatbot that manages Task for user.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Duke {

    public static final String FILE_PATH = "data/duke.txt";

    /**
     * Main method for Duke.
     * @param args String[] args in main.
     */
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = getFileTask(FILE_PATH, tasks, 0);
        boolean isBye = false;
        Scanner sc = new Scanner(System.in);
        greetUser();
        while (!isBye) {
            System.out.println();
            String string = sc.nextLine();
            System.out.println("    ____________________________________________________________");
            String[] stringSplit = string.split(" ");
            try {
                switch (stringSplit[0]) {
                case "list":
                    listCommand(tasks, stringSplit, taskCount);
                    break;
                case "done":
                    doneCommand(tasks, stringSplit, taskCount);
                    break;
                case "bye":
                    isBye = byeCommand(stringSplit, taskCount, tasks);
                    break;
                case "todo":
                    taskCount = todoCommand(tasks, stringSplit, taskCount);
                    break;
                case "deadline":
                    taskCount = deadlineCommand(tasks, taskCount, string);
                    break;
                case "event":
                    taskCount = eventCommand(tasks, taskCount, string);
                    break;
                case "delete":
                    taskCount = deleteCommand(tasks, stringSplit, taskCount);
                    break;
                case "find":
                    findCommand(tasks, taskCount, string);
                    break;
                default:
                    throw new DukeNullException("     :( OOPS!!! Command does not exist.");
                }
            } catch (DukeArgumentException | DukeIndexException e) {
                System.out.println(e.getMessage());
            } catch (DukeNullException e) {
                System.out.println(e.getMessage());
                commandList();
            } catch (NumberFormatException e) {
                System.out.println("     :( OOPS!!! " + e.getMessage().substring(18) + " is not number!");
            } catch (FileNotFoundException e) {
                System.out.println("Folder does not exist yet" + e.getMessage());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            } finally {
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    /**
     * Find all Task with description that matches a key word or phrase.
     * @param tasks ArrayList containing all the Task.
     * @param taskCount Total number of Task stored.
     * @param string User input.
     * @throws DukeArgumentException If missing parameter for description.
     */
    public static void findCommand(ArrayList<Task> tasks, int taskCount, String string) throws
            DukeArgumentException {
        int deletedTask;
        if (string.length() == 4) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for find.");
        }
        String description = string.substring(5);
        boolean containDescription = false;
        int matchNumber = 1;
        for (int i = 0; i < taskCount; i++) {
            if (tasks.get(i).getDescription().contains(description)) {
                if (!containDescription) {
                    System.out.println("     Here are the matching tasks in your list:");
                    containDescription = true;
                }
                System.out.println("     " + matchNumber + "." + tasks.get(i).toString());
                matchNumber++;
            }
        }
        if (!containDescription) {
            System.out.println("     There is no matching tasks");
        }
    }

    /**
     * Open the file directory based on the filePath, add Task for storage into hard disk and close file.
     * @param filePath File directory path.
     * @param taskToAdd Sting containing Task information to store into file.
     * @throws IOException If input or output operation failed.
     */
    public static void writeFileTask(String filePath, String taskToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(taskToAdd);
        fileWriter.close();
    }

    /**
     * Open file from file directory if any and convert the string into the respective Task at the
     * start of the programs launch.
     * @param filePath File directory path.
     * @param tasks ArrayList containing all the Task.
     * @param taskCount Total number of Task stored.
     * @return Total number of Task stored or 0 if the file does not exists.
     */
    public static int getFileTask(String filePath, ArrayList<Task> tasks, int taskCount) {
        try {
            File file = new File(filePath); // create a File for the given file path
            Scanner fileScanner = new Scanner(file); // create a Scanner using the File as the source
            while (fileScanner.hasNext()) {
                String[] existingTask = fileScanner.nextLine().split(" \\| ");
                if (existingTask[0].equals("T")) {
                    tasks.add(new Todo(existingTask[2]));
                }
                if (existingTask[0].equals("D")) {
                    tasks.add(new Deadline(existingTask[2], existingTask[3]));
                }
                if (existingTask[0].equals("E")) {
                    tasks.add(new Event(existingTask[2], existingTask[3]));
                }
                if (existingTask[1].equals("1")) {
                    tasks.get(taskCount).markAsDone();
                }
                taskCount++;
            }
            return taskCount;
        } catch (FileNotFoundException e) {
            return taskCount;
        }
    }

    /**
     * Delete a Task given an index.
     * @param tasks ArrayList containing all the Task.
     * @param stringSplit User input that is split by spacing.
     * @param taskCount Total number of Task stored.
     * @return Total number of Task stored.
     * @throws DukeArgumentException If missing parameter for index.
     * @throws DukeIndexException If index provided is out of range.
     */
    public static int deleteCommand(ArrayList<Task> tasks, String[] stringSplit, int taskCount) throws
            DukeArgumentException, DukeIndexException {
        int deletedTask;
        if (stringSplit.length == 1) {
            throw new DukeArgumentException("     :( OOPS!!! Missing index for delete.");
        }
        deletedTask = Integer.parseInt(stringSplit[1]) - 1; // Might throw NumberFormatException
        if (deletedTask >= taskCount | deletedTask < 0) {
            throw new DukeIndexException("     :( OOPS!!! Invalid index for delete.");
        }
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + tasks.get(deletedTask));
        tasks.remove(deletedTask);
        taskCount--;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }

    /**
     * Add event Task given a description and a time of occurrence.
     * @param tasks ArrayList containing all the Task.
     * @param taskCount Total number of Task stored.
     * @param string User input.
     * @return Total number of Task stored.
     * @throws DukeArgumentException If missing parameter for description or date.
     */
    public static int eventCommand(ArrayList<Task> tasks, int taskCount, String string) throws
            DukeArgumentException {
        if (string.length() == 5) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for event.");
        }
        if (!string.contains("/at")) {
            throw new DukeArgumentException("     :( OOPS!!! Missing date for event.");
        }

        String description;
        String dateTime;
        description = string.substring(0, string.indexOf(" /at")).replace("event ", "");
        dateTime = string.substring(string.indexOf("/at ")).replace("/at ", "");
        tasks.add(new Event(description, dateTime));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(taskCount).toString());
        taskCount++;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }

    /**
     * Add deadline Task given a description and a due date.
     * @param tasks ArrayList containing all the Task.
     * @param taskCount Total number of Task stored.
     * @param string User input.
     * @return Total number of Task stored.
     * @throws DukeArgumentException If missing parameter for description or date.
     */
    public static int deadlineCommand(ArrayList<Task> tasks, int taskCount, String string) throws
            DukeArgumentException {
        if (string.length() == 8) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for deadline.");
        }
        if (!string.contains("/by")) {
            throw new DukeArgumentException("     :( OOPS!!! Missing date for deadline.");
        }

        String description;
        String dateTime;
        description = string.substring(0, string.indexOf(" /by")).replace("deadline ", "");
        dateTime = string.substring(string.indexOf("/by ")).replace("/by ", "");

        tasks.add(new Deadline(description, dateTime));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(taskCount).toString());
        taskCount++;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }

    /**
     * Add todo Task given a description.
     * @param tasks ArrayList containing all the Task.
     * @param stringSplit User input that is split by spacing.
     * @param taskCount Total number of Task stored.
     * @return Total number of Task stored.
     * @throws DukeArgumentException If missing parameter for index.
     */
    public static int todoCommand(ArrayList<Task> tasks, String[] stringSplit, int taskCount) throws
            DukeArgumentException {
        if (stringSplit.length == 1) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for todo.");
        }

        String description;
        description = String.join(" ", Arrays.copyOfRange(stringSplit, 1, stringSplit.length));
        tasks.add(new Todo(description));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(taskCount).toString());
        taskCount++;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }

    /**
     * Store all the Task into the hard disk and exit the program.
     * @param stringSplit User input that is split by spacing.
     * @param taskCount Total number of Task stored.
     * @param tasks ArrayList containing all the Task.
     * @return true to indicate to the program to end.
     * @throws DukeArgumentException If additional parameter is provided.
     * @throws IOException If input or output operation failed.
     */
    public static boolean byeCommand(String[] stringSplit, int taskCount, ArrayList<Task> tasks) throws
            DukeArgumentException, IOException {
        if (stringSplit.length > 1) {
            throw new DukeArgumentException("     :( OOPS!!! Description not required for bye.");
        }
        Path path = Paths.get("data");
        if(!Files.exists(path)) {
            Files.createDirectory(path);
        }
        String taskToAdd = "";
        for (int i = 0; i < taskCount; i++) {
            if ( tasks.get(i) instanceof Todo) {
                Todo todo = (Todo) tasks.get(i);
                taskToAdd = taskToAdd + "T | " + (todo.getIsDone() ? 1 : 0) + " | ";
                taskToAdd = taskToAdd + todo.getDescription();
            }
            if ( tasks.get(i) instanceof Deadline) {
                Deadline deadline = (Deadline) tasks.get(i);
                taskToAdd = taskToAdd + "D | " + (deadline.getIsDone() ? 1 : 0) + " | ";
                taskToAdd = taskToAdd + deadline.getDescription() + " | " + deadline.getDate();
            }
            if ( tasks.get(i) instanceof Event) {
                Event event = (Event) tasks.get(i);
                taskToAdd = taskToAdd + "E | " + (event.getIsDone() ? 1 : 0) + " | ";
                taskToAdd = taskToAdd + event.getDescription() + " | " + event.getDate();
            }
            taskToAdd += "\n";
        }
        writeFileTask(FILE_PATH, taskToAdd);
        System.out.println("    Bye. Hope to see you again soon!");
        return true;
    }

    /**
     * Mark a Task as done.
     * @param tasks ArrayList containing all the Task.
     * @param stringSplit User input that is split by spacing.
     * @param taskCount Total number of Task stored.
     * @throws DukeArgumentException If missing parameter for index.
     * @throws DukeIndexException If index provided is out of range.
     */
    public static void doneCommand(ArrayList<Task> tasks, String[] stringSplit, int taskCount) throws
            DukeArgumentException, DukeIndexException {
        int completedTask;
        if (stringSplit.length == 1) {
            throw new DukeArgumentException("     :( OOPS!!! Missing index for done.");
        }

        completedTask = Integer.parseInt(stringSplit[1]) - 1; // Might throw NumberFormatException
        if (completedTask >= taskCount || completedTask < 0) {
            throw new DukeIndexException("     :( OOPS!!! Invalid index for done.");
        }
        tasks.get(completedTask).markAsDone();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks.get(completedTask).toString());
    }

    /**
     * List all the Task stored.
     * @param tasks ArrayList containing all the Task.
     * @param stringSplit User input that is split by spacing.
     * @param taskCount Total number of Task stored.
     * @throws DukeArgumentException If additional parameter is provided.
     */
    public static void listCommand(ArrayList<Task> tasks, String[] stringSplit, int taskCount) throws
            DukeArgumentException {
        if (stringSplit.length > 1) {
            throw new DukeArgumentException("     :( OOPS!!! Description not required for list.");
        }

        if (taskCount == 0) {
            System.out.println("     There are currently no tasks in your list");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("     " + (i + 1) + "." + tasks.get(i).toString());
            }
        }
    }

    /**
     * Print the Welcome message and call commandList method to List out all the possible command
     * the user can execute.
     */
    public static void greetUser() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm duke.Duke");
        commandList();
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Print out all the possible command the user can execute.
     */
    public static void commandList() {
        System.out.println("    Here is the list of commands that are available:");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| Index | Input            | Command                            |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 01    | list             | List out all the stored task       |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 02    | done i           | Mark task i as done                |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 03    | bye              | Terminate the program              |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 04    | todo j           | Add a task(j) without dateline     |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 05    | dateline j /by d | Add a task(j) with due date d      |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 06    | event j /at d    | Add a task(j) that start at date d |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 07    | delete j         | Delete task(j)                     |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 08    | find j           | Find all task with description j   |");
        System.out.println("|---------------------------------------------------------------|");
    }
}
