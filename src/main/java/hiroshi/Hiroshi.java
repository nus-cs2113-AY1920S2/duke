package hiroshi;

import hiroshi.commands.Commands;
import hiroshi.commands.RunCommand;
import hiroshi.storage.Storage;
import hiroshi.tasklist.TaskList;
import hiroshi.tasks.Task;
import hiroshi.ui.Ui;

import java.util.ArrayList;

/**
 * <h1>Hiroshi Nagai Task Tracker Chatbot</h1>
 * Entry point of the "Hiroshi Nagai" Task Tracker application.
 * Initializes the application and starts the interaction with the user.
 * <b>Note:</b> This application is written for CS2113 at the NUS School of computing.
 * @author  Alaukik Nath Pant
 * @since   2020-02-24
 */
public class Hiroshi {

    /** Version info of the program. */
    public static final String VERSION = "Hiroshi Nagai - Version 2.0";

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /** Constructor that runs the program.*/
    public Hiroshi(String filePath ) {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(filePath);
        this.run(TaskList.taskList, filePath);
    }

    /**
     * Gets the program started by creating a tasklist with the tasks given in the file.
     * Reads the user command and executes it, until the user issues the exit("bye") command.
     *
     * @param taskList List that stores the tasks mentioned until now.
     * @param filePath String that represents the relative path to the file that stores all the tasks.
     */
    public void run(ArrayList<Task> taskList, String filePath) {
        boolean isExit = false;
        while (!isExit){
            String command = Ui.readCommand();
            RunCommand c = new RunCommand(command, taskList, filePath);
            c.execute(Commands.finalCommand, taskList, filePath);
            isExit = c.isExit(Commands.finalCommand);
        }
        Ui.printStraightLine();
        Ui.showByeMessage();
    }

    public static void main(String[] args) {
        new Hiroshi("files/TaskList.txt");
       }


}


