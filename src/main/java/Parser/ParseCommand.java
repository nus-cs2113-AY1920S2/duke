package Parser;
import TaskList.*;
import Ui.TextUi;
import Storage.FileOperation;
import Exception.DukeException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a parser for commands. A ParseCommand object corresponds to
 * the full command, the type of command and the status of the program
 */
public class ParseCommand {
    private Boolean exitStatus = false;
    private String cmdType;
    private String fullCommand;

    public ParseCommand(String fullCommand) throws DukeException {
        this.fullCommand = fullCommand;
        this.cmdType = cmdTypeIdentifier(fullCommand);
    }

    /**
     * Returns the type of command for execution.
     *
     * @param cmd The command to be checked for type
     * @return type The action to process
     * @throws DukeException If the command is invalid
     */
    private String cmdTypeIdentifier(String cmd) throws DukeException {
        try {
            if (cmd.equalsIgnoreCase("list")) {
                return "LIST";
            } else if (cmd.equalsIgnoreCase("bye")) {
                exitStatus = true;
                return "BYE";
            } else if (cmd.substring(0,4).equalsIgnoreCase("find")) {
                return "FIND";
            } else if (cmd.substring(0,4).equalsIgnoreCase("done")) {
                return "DONE";
            } else if (cmd.substring(0,4).equalsIgnoreCase("todo")) {
                return "TODO";
            } else if (cmd.substring(0,5).equalsIgnoreCase("event")) {
                return "EVENT";
            } else if (cmd.substring(0,6).equalsIgnoreCase("delete")) {
                return "DELETE";
            } else if (cmd.substring(0,8).equalsIgnoreCase("deadline")) {
                return "DEADLINE";
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException();
        }
        return null;
    }

    /**
     * Returns the status of program.
     *
     * @return the status of the program
     */
    public boolean isExit () {
        return exitStatus;
    }

    /**
     * Returns the type of command for execution.
     *
     * @param taskList The list of the different type of tasks
     * @param ui The Textui object for printing of values
     * @param storage The FileOperation object for the writing of the tasks
     * @throws DukeException If the command is invalid
     * @throws IOException If there is writing error
     */
    public void execute(ArrayList<Task> taskList, TextUi ui, FileOperation storage) throws IOException, DukeException {
        if (cmdType != null) {
            switch (cmdType) {
            case "LIST":
                ui.printTaskList(taskList);
                break;
            case "DONE":
                markTaskAsDone(taskList);
                break;
            case "TODO":
                taskList.add(createToDo(taskList));
                break;
            case "EVENT":
                taskList.add(createEvent(taskList));
                break;
            case "DEADLINE":
                taskList.add(createDeadline(taskList));
                break;
            case "DELETE":
                taskList.remove(findObjectToRemove(taskList));
                break;
            case "FIND":
                ui.printTaskList(findTask(taskList));
                break;
            default:
                break;
            }
            storage.saveTaskList(taskList);
        }
    }

    /**
     * Find task using keyword specified by user
     *
     * @param taskList The list of the different type of tasks
     * @throws DukeException If the command is complete (i.e. invalid item number)
     */
    public ArrayList<Task> findTask(ArrayList<Task> taskList) throws DukeException {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        String keyword;
        try {
            keyword = fullCommand.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException();
        }
        System.out.println(keyword);
        for (Task item:taskList) {
            if (item.getDescription().contains(keyword)) {
                filteredTasks.add(item);
            }
        }
        return filteredTasks;
    }

    /**
     * Change the Task completion to done
     *
     * @param taskList The list of the different type of tasks
     * @throws DukeException If the command is complete (i.e. invalid item number)
     */
    public void markTaskAsDone(ArrayList<Task> taskList) throws DukeException {
        int lenOfCmd = fullCommand.length() - 1;
        try {
            int index = Integer.parseInt(fullCommand.substring(lenOfCmd)) - 1;
            taskList.get(index).setDone();
            System.out.println("Nice! I've marked this task as done: " + taskList.get(index).printObject());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException();
        }
    }

    /**
     * Returns the task to be added into the tasklist
     *
     * @param taskList The list of the different type of tasks
     * @return a task of the Todo Class
     * @throws DukeException If the command is invalid
     */
    public Todo createToDo(ArrayList<Task> taskList) throws DukeException {
        try {
            Todo item = new Todo(fullCommand.substring(5));
            System.out.println("Got it. I've added this task: " + item.printObject());
            System.out.println("Now you have " + (taskList.size() + 1) + " tasks in the list.");
            return item;
        } catch (StringIndexOutOfBoundsException  e) {
            throw new DukeException();
        }
    }

    /**
     * Returns the task to be added into the tasklist
     *
     * @param taskList The list of the different type of tasks
     * @return a task of the Event Class
     * @throws DukeException If the command is invalid
     */
    public Event createEvent(ArrayList<Task> taskList) throws DukeException {
        try {
            int indexOfAt = fullCommand.indexOf("/at");
            String eventDescription = fullCommand.substring(6, indexOfAt - 1);
            String eventDate = fullCommand.substring(indexOfAt + 4);
            Event item = new Event(eventDescription, eventDate);
            System.out.println("Got it. I've added this task: " + item.printObject());
            System.out.println("Now you have " + (taskList.size() + 1) + " tasks in the list.");
            return item;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    /**
     * Returns the task to be added into the tasklist
     *
     * @param taskList The list of the different type of tasks
     * @return a task of the Deadline Class
     * @throws DukeException If the command is invalid
     */
    public Deadline createDeadline(ArrayList<Task> taskList) throws DukeException {
        try {
            int indexOfBy = fullCommand.indexOf("/by");
            String deadlineDesc = fullCommand.substring(9, indexOfBy-1);
            String deadlineDate = fullCommand.substring(indexOfBy+4);
            Deadline item = new Deadline(deadlineDesc, deadlineDate);
            System.out.println("Got it. I've added this task: " + item.printObject());
            System.out.println("Now you have "+ (taskList.size()+1) +" tasks in the list.");
            return item;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    /**
     * Returns the index of the Task to be removed
     *
     * @param taskList The list of the different type of tasks
     * @return index of object
     * @throws DukeException If the command is invalid
     */
    public int findObjectToRemove(ArrayList<Task> taskList) throws DukeException {
        try {
            int indexOfObject = Integer.parseInt(fullCommand.substring(7)) - 1;
            System.out.println("Noted. I've removed this task: " + taskList.get(indexOfObject).printObject());
            System.out.println("Now you have " + (taskList.size() - 1) + " tasks in the list.");
            return indexOfObject;
        } catch (StringIndexOutOfBoundsException e){
            throw new DukeException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }
}
