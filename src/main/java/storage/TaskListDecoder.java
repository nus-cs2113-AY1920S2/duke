package storage;

import static misc.Messages.MESSAGE_INVALID_TASK_TYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.InvalidTaskArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

/**
 * Represents a decoder that decodes data from the storage file into 
 * readable format for the program to load.
 */
public class TaskListDecoder {
   
    /** The relative position index in a List<Task> that stores the type of a task. */
    public static int TASK_TYPE_INDEX = 0;
    
    /** The relative position index in a List<Task> that stores the status of a task. */
    public static int TASK_STATUS_INDEX = 1;
    
    /** The relative position index in a List<Task> that stores the description of a task. */
    public static int TASK_NAME_INDEX = 2;
    
    /** The relative position index in a List<Task> that stores the requirements of a task. */
    public static int TASK_REQUIREMENT_INDEX = 3;
    
    /** A counter that tracks the number of tasks. */
    public static int decoderTaskCounter = 0;
    
    /** The default number of parameters for a task. */
    public static int DEFAULT_TASK_ARGUMENT_LENGTH = 3;
    
    /** 
     * Decodes data from the storage file into tasks that are to be 
     * added into the TaskList.
     * 
     * @param lines A List<String> to be decoded. 
     * @return TaskList A TaskList with all the tasks loaded.
     */
    public static TaskList decodeTaskList(List<String> lines) {
        TaskList decodedTaskList = new TaskList();       
             
        for (String line : lines) {
            TaskListDecoder.decoderTaskCounter++;
            List<Optional<String>> taskArgs = parseLineIntoTaskArguments(line);
            Task decodedTask = decodeTask(taskArgs);           
            decodedTaskList.loadTask(decodedTask);
        }
        return decodedTaskList;
    }
    
    /**
     * Decodes a string of text to construct a task.
     * 
     * @param taskArgs
     * @return Task A new task created from a string of text. 
     */
    public static Task decodeTask(List<Optional<String>> taskArgs) {  
        // Extract task information from the Optional wrapper.
        String taskType = taskArgs.get(TaskListDecoder.TASK_TYPE_INDEX).get();
        String taskStatus = taskArgs.get(TaskListDecoder.TASK_STATUS_INDEX).get();
        String taskName = taskArgs.get(TaskListDecoder.TASK_NAME_INDEX).get();
        Optional<String> taskRequirement = taskArgs.get(TaskListDecoder.TASK_REQUIREMENT_INDEX);
        
        boolean isDone = (taskStatus.equals("1") ? true : false);
       
        Task task;        
        switch(taskType) {
        case "T":             
            task = new ToDos(TaskListDecoder.decoderTaskCounter, 
                    taskName, isDone);           
            break;
        case "D":
            task = new Deadlines(TaskListDecoder.decoderTaskCounter, 
                    taskName, taskRequirement.get(), isDone);
            break;
        case "E":
            task = new Events(TaskListDecoder.decoderTaskCounter, 
                    taskName, taskRequirement.get(), isDone);
            break;
        default:
            throw new InvalidTaskArgumentException(MESSAGE_INVALID_TASK_TYPE);
        }
        return task;       
    }
    
    /**
     * Parse a string of text into valid arguments for a task to be constructed.
     * 
     * @param line A string of text.
     * @return List<Optional<String>> A list of optional arguments.
     */
    private static List<Optional<String>> parseLineIntoTaskArguments(String line) {
        List<Optional<String>> taskArgs = new ArrayList<>();
        String[] wordsArray = line.split(" | ");
        
        String cleanWord = "";
        for (String word : wordsArray) {           
            if (word.equals("|")) {
              taskArgs.add(Optional.of(cleanWord));
              cleanWord = "";
              continue;
            } else {               
                if (cleanWord == "") {
                    cleanWord += (word);
                } else {
                    // If the next word of the array is not "|", add a space before.
                    cleanWord += (" " + word);
                }
            }
        }
        taskArgs.add(Optional.of(cleanWord));
        
        if (taskArgs.size() == DEFAULT_TASK_ARGUMENT_LENGTH) {
            taskArgs.add(Optional.of(""));
        }
        return taskArgs;
    }
}
