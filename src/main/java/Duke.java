import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {

        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                //c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        try {
            storage.save();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }



    public static void checkMissingDescription(String command, String taskDescription, ArrayList<Task> taskList) throws DukeException {
        String[] arr = taskDescription.split(" ");
        String action = arr[1];
        int index = 0;
        if (command.equals("deadline")) {
            index = taskDescription.indexOf("/by ");
            if (action.startsWith("/by")) {
                index = -1;
            }
        } else if (command.equals("event")) {
            index = taskDescription.indexOf("/at ");
            if (action.startsWith("/at")) {
                index = -1;
            }
        } else if (command.equals("done") || command.equals("delete")) {
            int listNumber = Integer.parseInt(action);
            if (listNumber > taskList.size()) {
                index = -1;
            }
        }
        if ((action.length() == 0) || (index == -1)) {
            throw new DukeException();
        }
    }

    public static void printErrorMessage(String command) {
        String commandType = "", addition = "";
        if (command.equals("todo")) {
            commandType = "a " + command;
            addition = ".";
        } else if (command.equals("deadline")){
            commandType = "a " + command;
            addition =  " and needs a date indicated by \"/by \".";
        } else if (command.equals("event")) {
            commandType = "an " + command;
            addition =  " and needs a date indicated by \"/at \".";
        } else if (command.equals("done") || command.equals("delete")) {
            commandType = command;
            addition = " and has to be a number in the list.";
        }
        System.out.println("☹ OOPS!!! The description of " + commandType + " cannot be empty" + addition);
    }


    public static void determineTask(String command, String taskDescription, ArrayList<Task> taskList) {
        String action, date;
        try {
            checkMissingDescription(command, taskDescription, taskList);
        } catch (DukeException e) {
            printErrorMessage(command);
            return;
        } catch (NumberFormatException e) { //if parse invalid string into Integer.parseInt()
            printErrorMessage(command);
            return;
        } catch (ArrayIndexOutOfBoundsException e) { //if arr[1] doesn't exist and no word after command
            printErrorMessage(command);
            return;
        }

    }



}
