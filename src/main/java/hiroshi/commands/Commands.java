package hiroshi.commands;
import hiroshi.storage.Storage;
import hiroshi.tasks.Task;
import java.util.ArrayList;

/** Represents a command, the taskList associated with the program and the path of the file that the command will
 * eventually influence,
 */
public abstract class Commands {

    public static String finalCommand;
    public ArrayList<Task> taskList;
    public String filePath;

    /**
     * Initializes the class variables.
     *
     * @param command Command inputted by the user
     * @param taskList List that stores the tasks mentioned until now.
     * @param filePath String that represents the relative path to the file that stores all the tasks.
     */
    public Commands(String command, ArrayList<Task> taskList, String filePath) {
        finalCommand = command.trim();
        this.taskList = taskList;
        this.filePath = filePath;
    }

    public Boolean isExit(String command) {
        String ogString = command.trim();
        Boolean yesExit = false;
        if (ogString.toUpperCase().equals("BYE")) {
            yesExit = true;
            Storage.saveTaskList(filePath, taskList);
        }
        return yesExit;
    }

    public void execute(String command, ArrayList<Task> taskList, String filePath){
    }
}