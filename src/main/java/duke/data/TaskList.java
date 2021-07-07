package duke.data;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents Duke's task list.
 */
public class TaskList extends ArrayList<Task>{

    public TaskList(){
        super();
    }

    public void list() {
        for (Task task: this) {
            int taskID = super.indexOf(task) + 1;
            System.out.println("\t" + taskID + "." + task.toString());
        }
    }

}
