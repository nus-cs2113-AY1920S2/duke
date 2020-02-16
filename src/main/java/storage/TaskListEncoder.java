package storage;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

/**
 * Represents an encoder that encodes data from the TaskList
 * into the storage file.
 */
public class TaskListEncoder {
    
    /** A TaskList that is to be encoded. */
    private final TaskList taskList;
    
    /** 
     * Constructor of a TaskListEncoder.
     * 
     * @param taskList
     */
    public TaskListEncoder(TaskList taskList) {
        this.taskList = taskList;
    }
    
    /** 
     * Encodes the taskList into a text format that is stored in the storage file.
     * 
     * @param taskList
     * @return String A string representation of tasks in the TaskList into text format.
     */
    public static String encodeTaskList(TaskList taskList) {
        StringBuilder encodedTaskList = new StringBuilder("");
        
        for (Task task : taskList.getTasks()) {
            encodedTaskList.append(encodeTask(task) + "\n");
        }        
        
        return encodedTaskList.toString();
    }
    
    /** 
     * Encodes a Task object into a text format.
     * 
     * @param task
     * @return A string representation of task into text format.
     */
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
                    + ((Deadlines) task);
            encodedTask.append(encodedDeadlines);
        } else if (task instanceof Events) {           
            String encodedEvents = "";
            encodedEvents += ("E"
                    + " | "
                    + (task.getDoneStatus() ? "1" : "0")
                    + " | "
                    + task.getTaskName())
                    + " | "
                    + ((Events) task);
            encodedTask.append(encodedEvents);
        } 

        return encodedTask.toString();
    }
}
