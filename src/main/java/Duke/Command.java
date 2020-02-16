package Duke;

import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;

import java.util.ArrayList;

/**
 * Represents a command. The call for command execution is done through here too. It calls upon the <code>TaskList</code> class to do the actual executions.
 * @see TaskList
 */
public class Command {

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";

    private String fullCommand;
    private String[] splitCommand;
    private String typeOfCommand;
    private String descriptionOfCommand;
    private String timeOfCommand;
    private String number;

    public Command() {
    }

    private static void displayList(TaskList tasks, Ui ui) {
        int numberOfTasks = tasks.getNumberOfTask();
        if (numberOfTasks > 0) {
            for (int i = 0; i < numberOfTasks; i++) {
                ui.displayEachTask(i, tasks);
            }
        } else if (numberOfTasks == 0) {
            System.out.println("Nothing yet");
        }
    }

    private static void markTaskAsDone(TaskList tasks, String taskNumber, Ui ui) throws NumberFormatException {
        try {
            int taskListNumber = Integer.parseInt(taskNumber);
            if (taskListNumber > tasks.getNumberOfTask() || taskListNumber == 0) {
                ui.displayOutOfRange();
            } else {
                tasks.markTaskAsDone(taskListNumber - 1);
                ui.displayTaskMarkedAsDone(tasks, taskListNumber);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Done number field is not a number!");
        }
    }

    private static void deleteTask(TaskList taskList, String taskNumber, Ui ui) throws NumberFormatException {
        try {
            int currentNumberOfTasks = taskList.getNumberOfTask();
            int taskListNumber = Integer.parseInt(taskNumber);
            if (taskListNumber > currentNumberOfTasks || taskListNumber == 0) {
                ui.displayOutOfRange();
            } else {
                String removedTask = taskList.getTaskList().get(taskListNumber - 1).toString();
                taskList.removeTask(taskListNumber - 1);
                currentNumberOfTasks = currentNumberOfTasks - 1;
                ui.displayTaskRemoved(removedTask, currentNumberOfTasks);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Delete number field is not a number!");
        }
    }

    /**
     * Create the task objects to be placed in the task list as specified by <code>tasks</code> parameter
     * @param tasks the list of tasks
     * @param ui for the ui elements
     * @see TaskList
     * @see Ui
     * @see Todo
     * @see Deadline
     * @see Event
     */
    private void addToList(TaskList tasks, Ui ui) {
        Task newTask = null;
        switch (typeOfCommand.toLowerCase()) {
        case TODO:
            newTask = new Todo(descriptionOfCommand, timeOfCommand);
            break;
        case DEADLINE:
            newTask = new Deadline(descriptionOfCommand, timeOfCommand);
            break;
        case EVENT:
            newTask = new Event(descriptionOfCommand, timeOfCommand);
            break;
        default:
            break;
        }
        tasks.addTasks(newTask);
        ui.displayTaskAdded(newTask);
    }

    /**
     * Set method to store the array containing the split input, where the input is split by space
     * @param splitCommand the array containing the input split by space
     */
    public void setSplitCommand(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    /**
     * Set method to store the type of command this command is, be it <code>TODO,EVENT,LIST,DEADLINE,DONE,DELETE</code>
     * @param typeOfCommand the type of command this command is supposed to be
     */
    public void setTypeOfCommand(String typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }

    /**
     * Set method to store the description for each command. Definition of description differs command by command
     * <p>
     *     For <code>TODO,EVENT,DEADLINE</code>: the description refers to the action behind the type of command
     * </p>
     * <p>
     *     For <code>DONE,DELETE</code>: the description refers to the task number to be
     * </p>
     * @param descriptionOfCommand the description accompanying the command
     */
    public void setDescriptionOfCommand(String descriptionOfCommand) {
        this.descriptionOfCommand = descriptionOfCommand;
    }

    /**
     * Set method to store the date of the command itself
     *
     * <p>
     *     Note that said date should also contain the slash word too!
     * </p>
     *
     * <p>
     *     For example: <code>"todo add things /by 2018-12-01"</code>
     * </p>
     *
     * <p>
     *     <code>timeOfCommand</code> = "by 2018-12-01"
     * </p>
     * @param timeOfCommand the date with the slash word
     */
    public void setTimeOfCommand(String timeOfCommand) {
        this.timeOfCommand = timeOfCommand;
    }

    /**
     * Set method to store the entire user input
     * @param fullCommand the entire user input
     */
    public void setFullCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Execute the actual user command. Need the list of task to modify it (either add/delete/update tasks) upon user command
     * @param tasks list of Task
     * @throws NumberFormatException exception only thrown for the DONE and DELETE if task number provided is not an integer
     * @see TaskList
     */
    public void execute(TaskList tasks) throws NumberFormatException {
        Ui ui = new Ui();
        switch (typeOfCommand.toLowerCase()) {
        case TODO:
        case DEADLINE:
        case EVENT:
            addToList(tasks, ui);
            break;
        case LIST:
            displayList(tasks, ui);
            break;
        case DONE:
            markTaskAsDone(tasks, this.number, ui);
            break;
        case DELETE:
            deleteTask(tasks, this.number, ui);
            break;
        default:
            break;
        }


    }

    /**
     * Set method for storing the task number specified in the command itself
     * <p>
     *     Only used for DELETE and DONE commands
     * </p>
     * @param s task number as specified in the command itself
     */
    public void setNumber(String s) {
        this.number = s;
    }
}
