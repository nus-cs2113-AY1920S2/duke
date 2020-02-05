package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.exception.DukeException;

public class Duke {
    private final TaskList taskList;
    
    public Duke(TaskList newTaskList) {
        this.taskList = new TaskList(newTaskList);
    }
    
    public Duke() {
        this.taskList = new TaskList();
    }
    
    public void executeListCommand() {
        this.taskList.listTask();
    }
    
    public void executeDoneCommand(int taskId) {
        this.taskList.completeTask(taskId);
    }
    
    public void executeAddCommand(Task task) {   
        this.taskList.addTask(task);
    }
    
    public TaskList getTaskList() {
        return this.taskList;
    }
}
