package duke;

import duke.tasks.exceptions.BadTaskFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void executeUserInput(String userInput) {
        Command command;
        try {
            command = new Command(userInput);
        } catch (InvalidKeywordException e) {
            Ui.printPretty(e.getMessage());
            return;
        }
        executeCommand(command);
    }

    protected void executeCommand(Command command) {
        try {
            switch (command.getKeyword()) {
            case LIST:
                listTasks();
                break;
            case DONE:
                markAsDone(command);
                break;
            case DELETE:
                deleteTask(command);
                break;
            case DEADLINE:
                addTask(new Deadline(command));
                break;
            case EVENT:
                addTask(new Event(command));
                break;
            case TODO:
                addTask(new ToDo(command));
                break;
            default:
                // Should never reach default case b/c check for invalid keyword when creating command object
                break;
            }
        } catch (BadTaskFormatException | BadTaskChoiceFormatException e) {
            Ui.printPretty(e.getMessage());
        }
    }

    protected void addTask(Task newTask) {
        tasks.add(newTask);
        String message = "Added this task:" + System.lineSeparator() + newTask.toString() + System.lineSeparator() +
                "Now you have " + tasks.size() + " tasks in the list";
        Ui.printPretty(message);
    }

    protected void listTasks() {
        String message = "These are your tasks:" + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            String lineEnd = i == tasks.size() - 1 ? "" : System.lineSeparator(); // Do this so no extra newline
            message += (String.format("%d. %s", i + 1, tasks.get(i).toString()) + lineEnd);
        }
        Ui.printPretty(message);
    }

    protected int getTaskIndex(Command command) throws BadTaskChoiceFormatException {
        String[] tokens = command.getTokens();
        if (tokens.length <= 1) {
            throw new BadTaskChoiceFormatException("Specify a task by entering a task number");
        } else if (tokens.length > 2) {
            throw new BadTaskChoiceFormatException("Too many tokens");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BadTaskChoiceFormatException("Task number is not parsable");
        }

        if (taskIndex > tasks.size() - 1 || taskIndex < 0) {
            throw new BadTaskChoiceFormatException("Task number is invalid");
        }

        return taskIndex;
    }

    protected void deleteTask(Command command) throws BadDeleteFormatException {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(command);
        } catch (BadTaskChoiceFormatException e) {
            throw new BadDeleteFormatException(e.getMessage());
        }

        Task t = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        Ui.printPretty("Deleted task " + (taskIndex + 1) + ":\n" + t.toString() + "\nNow you have " + tasks.size() +
                " tasks left in your list");
    }

    protected void markAsDone(Command command) throws BadDoneFormatException {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(command);
        } catch (BadTaskChoiceFormatException e) {
            throw new BadDoneFormatException(e.getMessage());
        }

        Task t = tasks.get(taskIndex);
        t.setIsDone(true);
        Ui.printPretty("Task " + (taskIndex + 1) + " has been marked as done\n" + t.toString());
    }
}
