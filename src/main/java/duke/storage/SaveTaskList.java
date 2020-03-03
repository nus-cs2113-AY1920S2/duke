package duke.storage;
import duke.taskList.TaskList;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import java.util.*;
import java.util.ArrayList;

public class SaveTaskList {

    public static List<String> saveTaskList(TaskList toSave) {
        final List<String> saveTask = new ArrayList<>();
        toSave.getAllTasks().forEach(task -> saveTask.add(storeTask(task)));
        return saveTask;
    }

    private static String storeTask(Task t) {
        String fileDoneStatus;
        if (t.getStatusIcon().equals("\u2718")) {
            fileDoneStatus = "0";
        } else {
            fileDoneStatus = "1";
        }
        final StringBuilder storedTask = new StringBuilder();
        if (t instanceof ToDos){
            storedTask.append(t.getTaskType());
            storedTask.append(" | ").append(fileDoneStatus);
            storedTask.append(" | ").append(t.description);
        } else if (t instanceof Deadlines) {
            storedTask.append(t.getTaskType());
            storedTask.append(" | ").append(fileDoneStatus);
            storedTask.append(" | ").append(t.description).append(" | ");
            storedTask.append(((Deadlines) t).getDueDate());
        } else if (t instanceof Events) {
            storedTask.append(t.getTaskType());
            storedTask.append(" | ").append(fileDoneStatus);
            storedTask.append(" | ").append(t.description).append(" | ");
            storedTask.append(((Events) t).getTimeOfEvent());
        }

        return storedTask.toString();
    }
}
