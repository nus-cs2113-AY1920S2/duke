package duke;

import duke.exceptions.BadDeleteFormatException;
import duke.exceptions.BadDoneFormatException;
import duke.exceptions.BadFileFormatException;
import duke.exceptions.BadLineFormatException;
import duke.exceptions.BadTaskChoiceFormatException;
import duke.exceptions.InvalidKeywordException;
import duke.tasks.exceptions.BadTaskFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;
    protected final String filePath = "data/tasks.txt";

    public TaskManager() {
        tasks = new ArrayList<>();
        try {
            ArrayList<String> fileContents = FileIO.loadFromFile(filePath);
            addFileContentsToTasks(fileContents);
        } catch (FileNotFoundException e) {
            Ui.printPretty("Couldn't locate a save file. Starting with an empty list of tasks");
        } catch (BadFileFormatException e) {
            Ui.printPretty(e.getMessage());
        }

        if (tasks.size() != 0) {
            listTasks();
        }
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
                FileIO.saveToFile(filePath, getFormattedTasks());
                break;
            case DELETE:
                deleteTask(command);
                FileIO.saveToFile(filePath, getFormattedTasks());
                break;
            case DEADLINE:
                addTask(new Deadline(command));
                FileIO.saveToFile(filePath, getFormattedTasks());
                break;
            case EVENT:
                addTask(new Event(command));
                FileIO.saveToFile(filePath, getFormattedTasks());
                break;
            case TODO:
                addTask(new ToDo(command));
                FileIO.saveToFile(filePath, getFormattedTasks());
                break;
            default:
                // Should never reach default case b/c check for invalid keyword when creating command object
                break;
            }
        } catch (BadTaskFormatException | BadTaskChoiceFormatException e) {
            Ui.printPretty(e.getMessage());
        } catch (IOException e) {
            Ui.printPretty(e.getMessage() + System.lineSeparator() +
                    "*** Writing to file failed. Your task list may not have been saved. ***");
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

    protected String getFormattedTasks() {
        String formattedTasks = "";
        for (Task t : tasks) {
            formattedTasks += t.toFormattedString() + System.lineSeparator();
        }
        return formattedTasks;
    }

    protected void addFileContentsToTasks(ArrayList<String> fileContents) throws BadFileFormatException {
        int lineCounter = 1;
        String errors = "";

        for (String s : fileContents) {
            try {
                tasks.add(getTaskFromFormattedLine(s));
            } catch (BadLineFormatException e) {
                errors += e.getMessage() + " on line " + lineCounter + " of " + filePath + System.lineSeparator();
            }
            lineCounter++;
        }

        if (!errors.equals("")) {
            throw new BadFileFormatException(errors.substring(0, errors.length() - 1));
        }
    }

    protected Task getTaskFromFormattedLine(String line) throws BadLineFormatException {
        String[] tokens = line.split(",");
        if (tokens.length == 0) {
            throw new BadLineFormatException("Empty line");
        } else if (tokens.length < 3) {
            throw new BadLineFormatException("Not enough tokens");
        } else if ((tokens[0].equals("D") || tokens[0].equals("E")) && tokens.length < 4) {
            throw new BadLineFormatException("Not enough tokens");
        } else if (!(tokens[1].equals("y") || tokens[1].equals("n"))) {
            throw new BadLineFormatException("Second token must be y or n");
        }

        switch(tokens[0]) {
        case "T":
            return new ToDo(tokens[1].equals("y") ? true : false, tokens[2]);
        case "D":
            return new Deadline(tokens[1].equals("y") ? true : false, tokens[2], tokens[3]);
        case "E":
            return new Event(tokens[1].equals("y") ? true : false, tokens[2], tokens[3]);
        default:
            throw new BadLineFormatException("First token must be T, D, or E");
        }
    }
}

