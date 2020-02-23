package duke.commands;
import duke.storage.Storage;
import duke.tasks.Task;
import java.util.ArrayList;


public abstract class Commands {
    public static String finalCommand;
    public ArrayList<Task> taskList;
    public String filePath;

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
    };

}
