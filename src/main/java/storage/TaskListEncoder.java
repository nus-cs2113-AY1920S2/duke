package storage;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

public class TaskListEncoder {
    private final TaskList taskList;
    
    public TaskListEncoder(TaskList taskList) {
        this.taskList = taskList;
    }
    
    public static String encodeTaskList(TaskList taskList) {
        StringBuilder encodedTaskList = new StringBuilder("");
        
        for (Task task : taskList.getTasks()) {
            encodedTaskList.append(encodeTask(task) + "\n");
        }        
        
        return encodedTaskList.toString();
    }
    
    public static String encodeTask(Task task) {
        StringBuilder encodedTask = new StringBuilder("");
        
        if (task instanceof ToDos) {
            String encodedToDos = "";           
            encodedToDos += ("T"
                    + " | "
                    + (task.getDoneStatus() ? "1" : "0")
                    + " | "
                    + ((ToDos) task).getTaskName());
            encodedTask.append(encodedToDos);
        } else if (task instanceof Deadlines) {           
            String encodedDeadlines = "";
            encodedDeadlines += ("D"
                    + " | "
                    + (task.getDoneStatus() ? "1" : "0")
                    + " | "
                    + task.getTaskName())
                    + " | "
                    + ((Deadlines) task).getDateTime();
            encodedTask.append(encodedDeadlines);
        } else if (task instanceof Events) {           
            String encodedEvents = "";
            encodedEvents += ("E"
                    + " | "
                    + (task.getDoneStatus() ? "1" : "0")
                    + " | "
                    + task.getTaskName())
                    + " | "
                    + ((Events) task).getDateTime();
            encodedTask.append(encodedEvents);
        } 

        return encodedTask.toString();
    }
}
