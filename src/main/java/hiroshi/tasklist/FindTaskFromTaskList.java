package hiroshi.tasklist;

import hiroshi.parser.Parser;
import hiroshi.tasks.Task;
import hiroshi.ui.Ui;

import java.util.ArrayList;

public class FindTaskFromTaskList extends TaskList{
    public FindTaskFromTaskList(){
        super();
    }

    /**
     * Print all available tasks.
     *
     * @param taskList Tasklist of all available tasks.
     * @param ogString Original command that was fed to the user.
     */
    public static void execute(ArrayList<Task> taskList, String ogString) {
        String KEY_WORD = Parser.findKeyWord(ogString);
        int taskNoCounter = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : taskList) {
            String description = task.getDescription();
            String statusIcon = task.getStatusIcon();
            String typeIcon = task.getTypeIcon();
            if (description.contains(KEY_WORD)){
                System.out.println(taskNoCounter + ". " + typeIcon + " [" + statusIcon + "] " + description);
                taskNoCounter += 1;
            }
        }
        String TASK_COUNTER = String.format("\n There were %s tasks with the \"%s\" keyword",taskNoCounter - 1, KEY_WORD);
        System.out.println(TASK_COUNTER);
        Ui.printStraightLine();
    }
}