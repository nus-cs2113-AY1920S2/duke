package storage;

import static misc.Messages.MESSAGE_CANNOT_WRITE_TASK;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

/**
 * Represents an encoder that encodes data from the TaskList
 * into the storage text file.
 * 
 */
public class StorageEncoder {
    
    /** A TaskList that is to be encoded into a storage file. */
    private final TaskList taskList;
    
    /** Constructor of a TaskListEncoder. */
    public StorageEncoder(TaskList taskList) {
        this.taskList = taskList;
    }
    
    /** 
     * Encodes the taskList into a text format that is stored in the storage file.
     * 
     * @param taskList
     * @return String A string representation of tasks in the TaskList into text format.
     * @throws StorageReadWriteException If the storage file is missing or cannot be overwritten.
     */
    public static String encodeTaskList(TaskList taskList) 
            throws StorageReadWriteException {
        
        StringBuilder encodedTaskList = new StringBuilder("");
        
        for (Task task : taskList.getTasks()) {
            encodedTaskList.append(encodeTask(task) + "\n");
        }        
        
        return encodedTaskList.toString();
    }
    
    /** 
     * Encodes a Task object into a text format and appends to
     * the encodedTaskList so that it can be written collectively
     * into the storage text file. Throws an exception if the task 
     * cannot be written in a text format.
     */
    private static String encodeTask(Task task) 
            throws StorageReadWriteException {
        
        StringBuilder encodedTask = new StringBuilder("");
        
        if (task instanceof ToDos) {            
            encodedTask.append(encodeToDos(task));
        } else if (task instanceof Deadlines) {           
            encodedTask.append(encodeDeadlines(task));
        } else if (task instanceof Events) {           
            encodedTask.append(encodeEvents(task));
        } else {
            throw new StorageReadWriteException(
                    MESSAGE_CANNOT_WRITE_TASK);
        }
        return encodedTask.toString();
    }
    
    /**
     * Encodes a ToDos object into a text format.
     * For example: 'T | 1 | return book'
     */
    private static String encodeToDos(Task task) {
        String encodedToDos = ""; 
        
        encodedToDos += ("T"
                + " | "
                + (task.getDoneStatus() ? "1" : "0")
                + " | "
                + ((ToDos) task).getTaskName());
        
        return encodedToDos;
    }
    
    /**
     * Encodes a Deadlines object into a text format.
     * For example: 'D | 1 | homework | 1200-12-12T10:00'
     */
    private static String encodeDeadlines(Task task) {
        String encodedDeadlines = "";
        
        encodedDeadlines += ("D"
                + " | "
                + (task.getDoneStatus() ? "1" : "0")
                + " | "
                + task.getTaskName())
                + " | "
                + ((Deadlines) task).getDate().get()
                + "T"
                + ((Deadlines) task).getTime().get();
        
        return encodedDeadlines;
    }
    
    /**
     * Encodes an Events object into a text format.
     * For example: 'E | 1 | homework | 1200-12-12T10:00 to 1200-12-12T11:00'
     */
    private static String encodeEvents(Task task) {
        String encodedEvents = "";
        
        encodedEvents += ("E"
                + " | "
                + (task.getDoneStatus() ? "1" : "0")
                + " | "
                + task.getTaskName())
                + " | "
                + ((Events) task).getStartDate().get()
                + "T"
                + ((Events) task).getStartTime().get()
                + " to "
                + ((Events) task).getEndDate().get()
                + "T"
                + ((Events) task).getStartTime().get();
        
        return encodedEvents;
    }
    
}
