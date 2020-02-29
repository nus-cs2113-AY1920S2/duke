import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static int NUM_OF_TASK = 0;
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DELETE_COMMAND = "delete";

    private Ui ui;
    private DukeManager dukeManager;
    private Scanner in;
    private Storage storage;

    public static void main(String[] args) {
        new Duke().start();
    }

    /*
     * Set up the required classes
     */
    private void start() {
        this.ui = new Ui();
        this.dukeManager = new DukeManager();
        this.in = new Scanner(System.in);
        this.storage = new Storage();
        run();
    }

    /*
     * Run DUKE until user terminates
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        ArrayList<Task> Tasks = storage.openData();
        while (!isExit) {
            String fullCommand[] = Parser.readInput(in);
            ui.showLine();
            dukeManager.execute(fullCommand);
            isExit = dukeManager.getIsExit();
        }
        ui.printExitMessage();
    }
}

