package Duke;

import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;

import java.util.ArrayList;

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

    private void addToList(TaskList tasks, Ui ui) {
        Task newTask = null;
        switch (typeOfCommand.toLowerCase()) {
        case TODO:
            newTask = new Todo(descriptionOfCommand, timeOfCommand);
        case DEADLINE:
            newTask = new Deadline(descriptionOfCommand, timeOfCommand);
        case EVENT:
            newTask = new Event(descriptionOfCommand, timeOfCommand);
        default:
            break;
        }
        tasks.addTasks(newTask);
        ui.displayTaskAdded(newTask);
    }

    public String[] getSplitCommand() {
        return splitCommand;
    }

    public void setSplitCommand(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    public String getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(String typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }

    public String getDescriptionOfCommand() {
        return descriptionOfCommand;
    }

    public void setDescriptionOfCommand(String descriptionOfCommand) {
        this.descriptionOfCommand = descriptionOfCommand;
    }

    public String getTimeOfCommand() {
        return timeOfCommand;
    }

    public void setTimeOfCommand(String timeOfCommand) {
        this.timeOfCommand = timeOfCommand;
    }

    public String getFullCommand() {
        return fullCommand;
    }

    public void setFullCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

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

    public void setNumber(String s) {
        this.number = s;
    }
}
