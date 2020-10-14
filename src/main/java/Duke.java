import Duke.data.Storage;
import Duke.data.TaskList;
import Duke.data.exceptions.DukeExceptions;
import Duke.data.objects.Deadline;
import Duke.data.objects.Event;
import Duke.data.objects.Task;
import Duke.data.objects.ToDo;
import Duke.parser.Parser;
import Duke.ui.TextUi;

import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
public class Duke {
    private TextUi ui;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;
    /** Version info of the program. */
    public static final String VERSION = "> Version 1.1";

    /**
     * Sets up the required objects, and prints the welcome message.
     *
     */
    private void start() {
        this.ui = new TextUi();
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.storage = new Storage();
        storage.loadList(taskList);
        ui.showWelcomeMessage(VERSION);
    }

    private static void exitProgram() {
        System.exit(0);
    }
    private void run(){
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        boolean runStill = true;
        do {
            String userCommandText = ui.getUserCommand();
            runStill = parser.execute(ui, taskList, userCommandText);
        } while (runStill);
        exit();
    }
    /** Prints the Farewell message and exits. */
    private void exit() {
        ui.showFarewellMessage();
        System.exit(0);
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
