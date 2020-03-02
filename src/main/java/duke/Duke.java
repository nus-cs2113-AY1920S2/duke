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

public class Duke {

    public static final String FILE_PATH = "data/duke.txt";

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

    public static void writeFileTask(String filePath, String taskToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(taskToAdd);
        fileWriter.close();
    }

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

    public static int deleteCommand(ArrayList<Task> tasks, String[] stringSplit, int taskCount) throws
            DukeArgumentException {
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

    public static int eventCommand(ArrayList<Task> tasks, int taskCount, String string) throws
            DukeArgumentException {
        if (string.length() == 5) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for event.");
        }
        if (!string.contains("/at")) {
            throw new DukeArgumentException("     :( OOPS!!! Missing date for event.");
        }

        String description;
        String date;
        description = string.substring(0, string.indexOf(" /at")).replace("event ", "");
        date = string.substring(string.indexOf("/at ")).replace("/at ", "");
        tasks.add(new Event(description, date));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(taskCount).toString());
        taskCount++;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }

    public static int deadlineCommand(ArrayList<Task> tasks, int taskCount, String string) throws
            DukeArgumentException {
        if (string.length() == 8) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for deadline.");
        }
        if (!string.contains("/by")) {
            throw new DukeArgumentException("     :( OOPS!!! Missing date for deadline.");
        }

        String description;
        String date;
        description = string.substring(0, string.indexOf(" /by")).replace("deadline ", "");
        date = string.substring(string.indexOf("/by ")).replace("/by ", "");
        tasks.add(new Deadline(description, date));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(taskCount).toString());
        taskCount++;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }

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

    public static void listCommand(ArrayList<Task> tasks, String[] stringSplit, int taskCount) throws DukeArgumentException {
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

    public static void greetUser() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm duke.Duke");
        commandList();
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

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
        System.out.println("|---------------------------------------------------------------|");
    }
}
