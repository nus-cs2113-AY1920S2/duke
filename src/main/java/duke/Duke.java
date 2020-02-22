package duke;
import java.util.ArrayList;


public class Duke {
    /**
     * Keeps track of Class variable taskList.
     */
    public Ui ui;
    public Storage storage;
    public TaskList taskList;

    /**
     * Constructor
     */
    public Duke(String filePath ) {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(filePath);
        this.run(TaskList.taskList, filePath);
    }

    /**
     * Gets the program statted by adding an initial task or taking the initial command
     */
    public void run(ArrayList<Task> taskList, String filePath) {
        boolean isExit = false;
        while (!isExit){
            String command = Ui.readCommand();
            Commands c = new Commands(command);
            c.runCommand(Commands.finalCommand, taskList, filePath);
            isExit = c.isExit(Commands.finalCommand);
        }
        Ui.printStraightLine();
        Ui.showByeMessage();
    }


    /**
     * Main method
     */
    public static void main(String[] args) {
        new Duke("files/TaskList.txt");
       }
}


