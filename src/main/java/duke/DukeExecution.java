package duke;

import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ListCommand;
import commands.FindCommand;
import commands.TaskList;
import commands.ToDoCommand;
import commands.EventCommand;
import exceptions.DukeExceptions;
import parser.Parser;
import storage.Storage;
import ui.UI;
import java.util.Scanner;

/**
 * Represents the main logic for running CLI
 */
public class DukeExecution {

    protected UI userInterface;
    protected Parser parser;
    protected Storage storage;
    protected DukeHelp dukeHelp;
    protected TaskList tasks;
    protected ListCommand list;
    protected DoneCommand done;
    protected DeleteCommand delete;
    protected FindCommand find;
    protected ToDoCommand todo;
    protected DeadlineCommand deadline;
    protected EventCommand event;

    public DukeExecution() {
        userInterface = new UI();
        parser = new Parser();
        storage = new Storage();
        dukeHelp = new DukeHelp();
        tasks = new TaskList();
        list = new ListCommand();
        done = new DoneCommand();
        delete = new DeleteCommand();
        find = new FindCommand();
        todo = new ToDoCommand();
        deadline = new DeadlineCommand();
        event = new EventCommand();
    }

    /**
     * Runs the Duke program
     * Checks for duke exceptions
     * @param input String input by user
     */
    public void execute(String input) {
        try {
            if (parser.isList()) {
                list.printList();
            } else if (parser.isDone()) {
                done.markAsDone(input);
            } else if (parser.isToDo()) {
                todo.addToDo(input);
            } else if (parser.isDeadline()){
                deadline.addDeadline(input);
            } else if (parser.isDelete()) {
                delete.deleteItem(input);
            } else if (parser.isFind()) {
                find.findSearchQuery(input);
            } else if (parser.isEvent()) {
                event.addEvent(input);
            } else if (parser.isHelp()) {
                dukeHelp.printHelp();
            } else {
                System.out.println("[Error: Duke does not understand your command]");
                dukeHelp.printHelp();
            }
        } catch (DukeExceptions exception) {
            System.out.println(exception);
        }
    }

    /**
     * Runs the Duke program
     * Terminates Duke program if user input bye
     * @param sc Scanner class to read in user input
     */
    public void runDuke(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            parser.updateInput(input);
            userInterface.printLine();
            if (parser.isBye()) {
                userInterface.printLeavingMessage();
                userInterface.printLine();
                break;
            } else {
                execute(input);
                userInterface.printLine();
            }
        }
    }
}
