package duke;

import duke.util.*;
import duke.util.TaskList;
import duke.taskmanager.Tasks;

import java.io.IOException;
import java.util.List;

public class Duke {
    /**
     * Classes used for Ui output and inputs and
     * current TaskList
     */
    private UI ui;
    private static List<Tasks> tasks;
    public Duke() throws IOException {
        ui = new UI();
        duke.util.TaskList tasklist = new TaskList(ui);
        tasks = tasklist.getTasks();
    }

    /**
     * The main run loop for Duke, requesting for user input
     * and running valid commands. Invalid commands will be
     * alerted to users.
     */
    public void run() throws IOException {
        String EXIT_COMMAND = "7";
        ui.printIntro();
        ui.printExeType();
        String exeCommand = ui.getStringInput();
        while (!exeCommand.equals(EXIT_COMMAND)) {
            Parser parser = new Parser(ui, tasks);
            parser.parseCommand(exeCommand); //to select the exeType and execute it
            ui.printExeType(); //user guide after execution of command
            exeCommand = ui.getStringInput(); //get the next command
        }
        ui.printExit();
    }

    /**
     * Main entry point for Duke
     * @param args Additional command line parameters, unused.
     */
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.run();
    }
}
