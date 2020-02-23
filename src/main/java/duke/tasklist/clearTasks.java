package duke.tasklist;

import duke.tasks.Task;

import java.util.ArrayList;

public class clearTasks extends TaskList {
    public clearTasks(){
        super();
    }

    public static void execute(ArrayList<Task> taskList){
        taskList.clear();
    }
}
