package Duke;

import Exceptions.DukeExceptions;
import Parser.Parser;
import Storage.Storage;
import Tasks.TaskList;
import UI.UI;

import java.util.Scanner;

/**
 * Represents the main logic for running the CLI
 * Duke object corresponds to UI, Parser, Tasks, Storage, DukeExceptions and DukeHelp object
 */
public class Duke {

    private UI userInterface;
    private Parser parser;
    private Storage storage;
    private DukeHelp dukeHelp;
    private TaskList tasks;

    public Duke() {
        userInterface = new UI();
        parser = new Parser();
        tasks = new TaskList();
        storage = new Storage();
        dukeHelp = new DukeHelp();
    }

    private void runCommand(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            parser.updateInput(input);
            userInterface.printLine();
            try {
                if (parser.isBye()) {
                    userInterface.printLeavingMessage();
                    userInterface.printLine();
                    break;
                } else if (parser.isList()) {
                    tasks.list();
                } else if(parser.isDone()) {
                    tasks.markAsDone(input);
                } else if (parser.isToDo()) {
                    tasks.addToDo(input);
                } else if (parser.isDeadline()){
                    tasks.addDeadline(input);
                } else if (parser.isDelete()) {
                    tasks.deleteItem(input);
                } else if (parser.isFind()) {
                    tasks.findSearchQuery(input);
                } else if (parser.isEvent()) {
                    tasks.addEvent(input);
                } else if (parser.isHelp()) {
                    dukeHelp.printHelp();
                } else {
                    System.out.println("    [Error: Duke does not understand your command]");
                }
            } catch (DukeExceptions exception) {
                System.out.println(exception);
            }
            userInterface.printLine();
        }
        sc.close();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        userInterface.printGreetingMessage();
        tasks.list();
        userInterface.printLine();
        runCommand(sc);
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
