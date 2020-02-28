package duke;

import commands.TaskList;
import ui.UI;
import commands.ListCommand;
import java.util.Scanner;

/**
 * Represents the main logic and user interface for running the CLI
 * Duke object corresponds to UI, Parser, Tasks, Storage, DukeExceptions and DukeHelp object
 */
public class Duke {

    private UI userInterface;
    private DukeExecution dukeExecution;
    private TaskList tasks;
    private ListCommand list;

    public Duke() {
        userInterface = new UI();
        dukeExecution = new DukeExecution();
        tasks = new TaskList();
        list = new ListCommand();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        userInterface.printGreetingMessage();
        list.printList();
        userInterface.printLine();
        dukeExecution.runDuke(sc);
    }
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke dukeFeatures = new Duke();
        dukeFeatures.run();
    }
}
