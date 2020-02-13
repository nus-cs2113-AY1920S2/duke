package duke;

import duke.tasks.exceptions.BadTaskFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManager {
    protected ArrayList<Task> tasks;
    protected final String filePath = "data/tasks.txt";

    public TaskManager() {
        tasks = new ArrayList<>();
        loadFromFile();
        listTasks();
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
                saveToFile();
                break;
            case DELETE:
                deleteTask(command);
                saveToFile();
                break;
            case DEADLINE:
                addTask(new Deadline(command));
                saveToFile();
                break;
            case EVENT:
                addTask(new Event(command));
                saveToFile();
                break;
            case TODO:
                addTask(new ToDo(command));
                saveToFile();
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

    protected void saveToFile() {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(filePath);
            String formattedTasks = "";
            for (Task t : tasks) {
                formattedTasks += t.toFormattedString() + System.lineSeparator();
            }
            fw.write(formattedTasks);
            fw.close();
        } catch (IOException e) {
            Ui.printPretty(e.getMessage());
            Ui.printPretty("*** Writing to file failed. Your task list may not have been saved. ***");
        }
    }

    protected void loadFromFile() {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            int counter = 1;
            String errors = "";

            while (s.hasNext()) {
                try {
                    tasks.add(getTaskFromFormattedLine(s.nextLine()));
                } catch (BadFileFormatException e){
                    errors += e.getMessage() + " on line " + counter + " of " + filePath + System.lineSeparator();
                }
                counter++;
            }

            if (!errors.equals("")) {
                Ui.printPretty(errors.substring(0, errors.length() - 1));
            }
        } catch (FileNotFoundException e) {
            Ui.printPretty("Couldn't locate a save file. Starting with an empty list of tasks");
        }
    }

    protected Task getTaskFromFormattedLine(String line) throws BadFileFormatException {
        String[] tokens = line.split(",");
        if (tokens.length == 0) {
            throw new BadFileFormatException("Empty line");
        } else if (tokens.length < 3) {
            throw new BadFileFormatException("Not enough tokens");
        } else if ((tokens[0].equals("D") || tokens[0].equals("E")) && tokens.length < 4) {
            throw new BadFileFormatException("Not enough tokens");
        } else if (!(tokens[1].equals("y") || tokens[1].equals("n"))) {
            throw new BadFileFormatException("Second token must be y or n");
        }

        switch(tokens[0]) {
        case "T":
            return new ToDo(tokens[1].equals("y") ? true : false, tokens[2]);
        case "D":
            return new Deadline(tokens[1].equals("y") ? true : false, tokens[2], tokens[3]);
        case "E":
            return new Event(tokens[1].equals("y") ? true : false, tokens[2], tokens[3]);
        default:
            throw new BadFileFormatException("First token must be T, D, or E");
        }
    }
}

