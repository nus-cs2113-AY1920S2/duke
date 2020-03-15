package duke;

import duke.command.Command;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeArgumentException;
import duke.exception.DukeDateTimeException;
import duke.exception.DukeIndexException;
import duke.exception.DukeNullException;
import duke.task.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * Duke is a chatbot that manages Task for user.
 *
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Duke {

    public static final String FILE_PATH = "data/duke.txt";

    /**
     * Main method for Duke.
     *
     * @param args String[] args in main.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Command command;
        Parser parser = new Parser();
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> tasks;
        try {
            tasks = storage.load();
        } catch (DateTimeParseException e) {
            System.out.println("     :( OOPS!!! Please enter a valid date and time");
            tasks = new ArrayList<>();
        }
        TaskList taskList = new TaskList(tasks); //Use this to replace tasks
        boolean isBye = false;
        ui.greetUser();
        while (!isBye) {
            String userCommand = ui.getUserCommand();
            String[] stringSplit = userCommand.split(" ");
            try {
                switch (stringSplit[0]) {
                case "list":
                case "help":
                case "find":
                    command = parser.parseCommand(userCommand);
                    command.execute(taskList, ui, storage);
                    break;
                case "done":
                    command = parser.parseCommand(userCommand);
                    command.execute(taskList, ui, storage);
                    int doneIndex = Integer.parseInt(stringSplit[1]) - 1;
                    if (!(doneIndex >= tasks.size() || doneIndex < 0)) {
                        tasks.get(doneIndex).markAsDone();
                    }
                    break;
                case "bye":
                    isBye = byeCommand(stringSplit, tasks);
                    break;
                case "todo":
                    command = parser.parseCommand(userCommand);
                    command.execute(taskList, ui, storage);
                    tasks.add(new Todo(String.join(" ", Arrays.copyOfRange(stringSplit, 1, stringSplit.length))));
                    break;
                case "deadline":
                    deadlineCommand(tasks, userCommand);
                    taskList.addTask(new Deadline(userCommand.substring(0, userCommand.indexOf(" /by")).replace("deadline ", ""), userCommand.substring(userCommand.indexOf("/by ")).replace("/by ", "")));
                    break;
                case "event":
                    eventCommand(tasks, userCommand);
                    taskList.addTask(new Event(userCommand.substring(0, userCommand.indexOf(" /at")).replace("event ", ""), userCommand.substring(userCommand.indexOf("/at ")).replace("/at ", "")));
                    break;
                case "delete":
                    deleteCommand(tasks, stringSplit);
                    taskList.deleteTask(Integer.parseInt(stringSplit[1]) - 1);
                    break;
                /*case "find":
                    findCommand(tasks, userCommand);
                    break;*/
                default:
                    throw new DukeNullException("     :( OOPS!!! Command does not exist.");
                }
            } catch (DukeArgumentException | DukeIndexException | DukeDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (DukeNullException e) {
                System.out.println(e.getMessage());
                System.out.println("     To view the list of commands available use the command: help");
            } catch (NumberFormatException e) {
                System.out.println("     :( OOPS!!! " + e.getMessage().substring(18) + " is not number!");
            } catch (FileNotFoundException e) {
                System.out.println("Folder does not exist yet" + e.getMessage());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("     :( OOPS!!! Please enter a valid date and time");
            } finally {
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    /**
     * Open the file directory based on the filePath, add Task for storage into hard disk and close file.
     *
     * @param filePath  File directory path.
     * @param taskToAdd Sting containing Task information to store into file.
     * @throws IOException If input or output operation failed.
     */
    public static void writeFileTask(String filePath, String taskToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(taskToAdd);
        fileWriter.close();
    }

    /**
     * Delete a Task given an index.
     *
     * @param tasks       ArrayList containing all the Task.
     * @param stringSplit User input that is split by spacing.
     * @throws DukeArgumentException If missing parameter for index.
     * @throws DukeIndexException    If index provided is out of range.
     */
    public static void deleteCommand(ArrayList<Task> tasks, String[] stringSplit) throws
            DukeArgumentException, DukeIndexException {
        int deletedTask;
        int taskCount = tasks.size();
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
    }

    /**
     * Add event Task given a description and a time of occurrence.
     *
     * @param tasks  ArrayList containing all the Task.
     * @param string User input.
     * @throws DukeArgumentException If missing parameter for description or date.
     */
    public static void eventCommand(ArrayList<Task> tasks, String string) throws
            DukeArgumentException, DukeDateTimeException {
        if (string.length() == 5) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for event.");
        }
        if (!string.contains("/at")) {
            throw new DukeArgumentException("     :( OOPS!!! Missing date for event.");
        }

        String description;
        String dateTime;
        int taskCount = tasks.size();
        description = string.substring(0, string.indexOf(" /at")).replace("event ", "");
        dateTime = string.substring(string.indexOf("/at ")).replace("/at ", "");

        LocalDate date;
        LocalTime time;
        try {
            date = LocalDate.parse(dateTime.split(" ")[0]);
            time = LocalTime.parse(dateTime.split(" ")[1]);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeException("     :( OOPS!!! Please enter a valid date and time");
        }

        tasks.add(new Event(description, dateTime));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(taskCount).toString());
        taskCount++;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Add deadline Task given a description and a due date.
     *
     * @param tasks  ArrayList containing all the Task.
     * @param string User input.
     * @throws DukeArgumentException If missing parameter for description or date.
     */
    public static void deadlineCommand(ArrayList<Task> tasks, String string) throws
            DukeArgumentException, DukeDateTimeException {
        if (string.length() == 8) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for deadline.");
        }
        if (!string.contains("/by")) {
            throw new DukeArgumentException("     :( OOPS!!! Missing date for deadline.");
        }

        String description;
        String dateTime;
        int taskCount = tasks.size();
        description = string.substring(0, string.indexOf(" /by")).replace("deadline ", "");
        dateTime = string.substring(string.indexOf("/by ")).replace("/by ", "");

        LocalDate date;
        LocalTime time;
        try {
            date = LocalDate.parse(dateTime.split(" ")[0]);
            time = LocalTime.parse(dateTime.split(" ")[1]);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeException("     :( OOPS!!! Please enter a valid date and time");
        }

        tasks.add(new Deadline(description, dateTime));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(taskCount).toString());
        taskCount++;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Store all the Task into the hard disk and exit the program.
     *
     * @param stringSplit User input that is split by spacing.
     * @param tasks       ArrayList containing all the Task.
     * @return true to indicate to the program to end.
     * @throws DukeArgumentException If additional parameter is provided.
     * @throws IOException           If input or output operation failed.
     */
    public static boolean byeCommand(String[] stringSplit, ArrayList<Task> tasks) throws
            DukeArgumentException, IOException {
        if (stringSplit.length > 1) {
            throw new DukeArgumentException("     :( OOPS!!! Description not required for bye.");
        }
        Path path = Paths.get("data");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        String taskToAdd = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Todo) {
                Todo todo = (Todo) tasks.get(i);
                taskToAdd = taskToAdd + "T | " + (todo.getIsDone() ? 1 : 0) + " | ";
                taskToAdd = taskToAdd + todo.getDescription();
            }
            if (tasks.get(i) instanceof Deadline) {
                Deadline deadline = (Deadline) tasks.get(i);
                taskToAdd = taskToAdd + "D | " + (deadline.getIsDone() ? 1 : 0) + " | ";
                taskToAdd = taskToAdd + deadline.getDescription() + " | " + deadline.getDate();
            }
            if (tasks.get(i) instanceof Event) {
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
     *
     * @param tasks       ArrayList containing all the Task.
     * @param stringSplit User input that is split by spacing.
     * @throws DukeArgumentException If missing parameter for index.
     * @throws DukeIndexException    If index provided is out of range.
     */
    /*public static void doneCommand(ArrayList<Task> tasks, String[] stringSplit) throws
            DukeArgumentException, DukeIndexException {
        int completedTask;
        if (stringSplit.length == 1) {
            throw new DukeArgumentException("     :( OOPS!!! Missing index for done.");
        }

        try {
            completedTask = Integer.parseInt(stringSplit[1]) - 1; // Might throw NumberFormatException
            if (completedTask >= tasks.size() || completedTask < 0) {
                throw new DukeIndexException("     :( OOPS!!! Invalid index for done.");
            }
            tasks.get(completedTask).markAsDone();
            //System.out.println("     Nice! I've marked this task as done:");
            //System.out.println("       " + tasks.get(completedTask).toString());
        } catch (NumberFormatException e) {
            throw new DukeIndexException("     :( OOPS!!! " + e.getMessage().substring(18) + " is not number!");
        }
    }*/

    /**
     * List all the Task stored.
     * @param tasks ArrayList containing all the Task.
     * @param stringSplit User input that is split by spacing.
     * @throws DukeArgumentException If additional parameter is provided.
     * @return ListCommand Object that provides the stored tasks.
     */
    /*public static ListCommand listCommand(ArrayList<Task> tasks, String[] stringSplit) throws
            DukeArgumentException {
        if (stringSplit.length > 1) {
            throw new DukeArgumentException("     :( OOPS!!! Description not required for list.");
        }
        return new ListCommand();
    }*/

    /**
     * Add todo Task given a description.
     *
     * @param tasks       ArrayList containing all the Task.
     * @param stringSplit User input that is split by spacing.
     * @return TodoCommand which adds Todo Task into the TaskList.
     * @throws DukeArgumentException If missing parameter for index.
     */
    /*public static TodoCommand todoCommand(ArrayList<Task> tasks, String[] stringSplit) throws
            DukeArgumentException {
        if (stringSplit.length == 1) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for todo.");
        }

        String description;
        description = String.join(" ", Arrays.copyOfRange(stringSplit, 1, stringSplit.length));
        tasks.add(new Todo(description));
        int taskCount = tasks.size();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(taskCount).toString());
        taskCount++;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        return new TodoCommand(description);
    }*/

    /**
     * Find all Task with description that matches a key word or phrase.
     *
     * @param tasks  ArrayList containing all the Task.
     * @param string User input.
     * @throws DukeArgumentException If missing parameter for description.
     */
    /*public static void findCommand(ArrayList<Task> tasks, String string) throws
            DukeArgumentException {
        if (string.length() == 4) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for find.");
        }
        String description = string.substring(5);
        boolean containDescription = false;
        int matchNumber = 1;
        for (int i = 0; i < tasks.size(); i++) {
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
    }*/
}
