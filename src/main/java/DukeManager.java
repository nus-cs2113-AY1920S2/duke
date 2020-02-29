import task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class DukeManager {

    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    private boolean isExit = false;
    private TaskList taskList;


    public DukeManager() {
        taskList = new TaskList();
    }

    public void execute(String[] arr) {
        try {
            switch (arr[0]) {
            case (BYE_COMMAND):
                isExit = true;
                taskList.exitAndSave();
                break;
            case (LIST_COMMAND):
                taskList.getList();
                break;
            case (DONE_COMMAND):
                taskList.setTaskDone(arr[1]);
                break;
            case (TODO_COMMAND):
                taskList.addTodo(arr);
                break;
            case (EVENT_COMMAND):
                taskList.addEvent(arr);
                break;
            case (DEADLINE_COMMAND):
                taskList.addDeadline(arr);
                break;
            case (DELETE_COMMAND):
                taskList.deleteTask(arr);
                break;
            case (FIND_COMMAND):
                taskList.findKeyword(arr);
                break;
            default:
                Ui.showError("I'm sorry but I don't understand ●︿●");
            }
        } catch (DukeException e) {

        }
    }

    public boolean getIsExit() {
        return isExit;
    }
}
