package duke;

import java.io.*;
import java.util.Scanner;

import duke.commands.Command;
import duke.exceptions.MissingDateTimeException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingLocationException;
import duke.exceptions.WhitespaceExceptions;
import duke.parser.Parser;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.*;
import java.util.ArrayList;
import duke.storage.*;

/**
 * Duke is a a chatbot with task managing functions.
 * @author Lim Yu Xiang
 * @version CS2113 AY19/20 Sem 2 Duke
 */

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    public static final String FILE_PATH = "data/Tasklist.txt";
    /**
     * Main method for Duke.
     * @param args String[] args in main.
     */
    public static void main(String[] args) throws IOException, MissingDescriptionException, MissingLocationException,
            MissingDateTimeException, WhitespaceExceptions {
        DisplayUI ui = new DisplayUI();
        Storage storage = new Storage(FILE_PATH);
        TaskList taskList = new TaskList();
        Parser parser = new Parser();

        tasks = storage.importTaskFromFile();
        programStart(ui, storage, taskList);
        loopTillEnd(ui, storage, taskList, parser);
    }


    /**
     * Run greetings and show table of available functions
     */
    private static void programStart(DisplayUI ui, Storage storage, TaskList taskList) {
        ui.showStartMessages();
    }

    /**
     *
     * @param ui ui object for printing out statements
     * @param storage Storage object to access and modify TaskList.txt
     * @param taskList Array list of tasks (Not used at the moment)
     * @param parser Read in input from user and return a command object
     * @throws MissingDescriptionException
     * @throws MissingLocationException
     * @throws MissingDateTimeException
     * @throws WhitespaceExceptions
     * @throws IOException
     */
    private static void loopTillEnd(DisplayUI ui, Storage storage, TaskList taskList, Parser parser)
            throws MissingDescriptionException, MissingLocationException, MissingDateTimeException, WhitespaceExceptions, IOException {
        Scanner myScanner = new Scanner(System.in);
        boolean flag = true; //Boolean flag for while loop
        String function;
        while (flag) {
            function = myScanner.next();
            Command command = parser.parse(myScanner, function);
            flag = command.execute(function, ui, storage, taskList, tasks);

        }
    }
}