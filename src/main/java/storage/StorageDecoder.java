package storage;

import static misc.Messages.MESSAGE_INCORRECT_EVENT_INPUT;
import static misc.Messages.MESSAGE_INCORRECT_TASK_INPUT;
import static misc.Messages.MESSAGE_CORRUPTED_STORAGE_FILE;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.InvalidTaskArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

/**
 * Represents a decoder that decodes data from the storage text file
 * into Duke's TaskList. The decoder decodes a list of strings derived
 * from the load() in Storage.java. It will then parse each string into
 * an acceptable task arguments format so that a task can be constructed 
 * and be added into the TaskList. 
 * 
 * Each string will be split into a word array to carry different task arguments.
 * For example: E | 1 | homework | 1200-12-12T10:00 to 1200-12-12T11:00'
 * will be split into wordArray = {[E], [1], [homework],
 * [1200-12-12T10:00 to 1200-12-12T11:00]}. This explains the relative 
 * position index that indicates the specific task argument.
 * 
 * An InvalidTaskArgumentException is thrown whenever a line of string 
 * text cannot be parsed correctly into a task arguments format.
 * 
 */
public class StorageDecoder {
    /** The relative position index that indicates the type of a task. */
    public static int TASK_TYPE_INDEX = 0;
    
    /** The relative position index that indicates the status of a task. */
    public static int TASK_STATUS_INDEX = 1;
    
    /** The relative position index that indicates the description of a task. */
    public static int TASK_DESCRIPTION_INDEX = 2;
    
    /** The relative position index that indicates the deadlines of a task. */
    public static int TASK_DEADLINE_INDEX = 3;
    
    /** The relative position index that indicates the starting date and time of a task. */
    public static int TASK_START_DATE_TIME_INDEX = 3;
    
    /** The relative position index that indicates the ending date and time of a task. */
    public static int TASK_END_DATE_TIME_INDEX = 5;
    
    /** A counter that tracks the number of tasks in the storage text file. */
    public static int decoderTaskCounter = 1;
    
    /**
     * Decodes a list of string texts into an acceptable task arguments format
     * and append it to the TaskList. Throws an exception whenever a task 
     * cannot be decoded.
     * 
     * @param lines A list of string texts derived from Storage's load().
     * @return A TaskList ready to be loaded onto Duke's TaskList.
     * @throws StorageReadWriteException If any of the lines cannot be decoded.
     */
    public static TaskList decodeStorageFile(List<String> lines) 
            throws StorageReadWriteException {

        TaskList decodedTaskList = new TaskList();
        
        for (String line : lines) {
            Map<String, String> mapOfTaskArguments = 
                    decodeLineIntoTaskArguments(line); 
            
            Task decodedTask = makeTaskFromTaskArguments(
                    mapOfTaskArguments);
            
            decodedTaskList.loadTask(decodedTask);
            decoderTaskCounter++;
        }
        
        return decodedTaskList;
    }
    
    /** Constructs a Task object based on the decoded task arguments format. */
    private static Task makeTaskFromTaskArguments(
            Map<String, String> mapOfTaskArguments) 
                    throws StorageReadWriteException {
        
        String taskType = mapOfTaskArguments.get("taskType");
             
        Task task;
        switch (taskType) {
        case "T":
            task = makeToDosTask(mapOfTaskArguments);
            break;
        case "D":
            task = makeDeadlinesTask(mapOfTaskArguments);
            break;
        case "E":
            task = makeEventsTask(mapOfTaskArguments);
            break; 
        default: 
            throw new InvalidTaskArgumentException(
                    MESSAGE_INCORRECT_TASK_INPUT);
        } 
        return task;
    }

    /** Constructs a ToDos task given a formatted ToDos task arguments. */   
    private static ToDos makeToDosTask(
            Map<String, String> mapOfTaskArguments) {
        
        String taskType = mapOfTaskArguments
                .get("taskType");
        String taskStatus = mapOfTaskArguments
                .get("taskStatus");
        String taskDescription = mapOfTaskArguments
                .get("taskDescription");
        
        boolean isDone = (taskStatus.equals("1") ? true : false);
        return new ToDos(decoderTaskCounter, taskDescription, isDone);
    }
    
    /** Constructs a Deadlines task given a formatted Deadlines task arguments. */   
    private static Deadlines makeDeadlinesTask(
            Map<String, String> mapOfTaskArguments) {
        
        String taskType = mapOfTaskArguments
                .get("taskType");
        String taskStatus = mapOfTaskArguments
                .get("taskStatus");
        String taskDescription = mapOfTaskArguments
                .get("taskDescription");
        String taskDeadline = mapOfTaskArguments
                .get("taskDeadline");
        
        boolean isDone = (taskStatus.equals("1") ? true : false);
        
        try {
            LocalDateTime deadline = LocalDateTime
                    .parse(taskDeadline);
            return new Deadlines(decoderTaskCounter, taskDescription,
                    deadline, isDone);
        } catch (DateTimeParseException dtpe) {
            throw new InvalidTaskArgumentException(
                    MESSAGE_INCORRECT_EVENT_INPUT);
        } 
    }
    
    /** Constructs an Events task given a formatted Events task arguments. */   
    private static Events makeEventsTask(
            Map<String, String> mapOfTaskArguments) 
            throws InvalidTaskArgumentException {
        
        String taskType = mapOfTaskArguments
                .get("taskType");
        String taskStatus = mapOfTaskArguments
                .get("taskStatus");
        String taskDescription = mapOfTaskArguments
                .get("taskDescription");
        String taskStartDate = mapOfTaskArguments
                .get("taskStartDate");
        String taskEndDate = mapOfTaskArguments
                .get("taskEndDate");        
     
        boolean isDone = (taskStatus.equals("1") ? true : false);
        
        try {
            LocalDateTime startDate = LocalDateTime
                    .parse(taskStartDate);
            LocalDateTime endDate = LocalDateTime
                    .parse(taskEndDate);
            return new Events(decoderTaskCounter, taskDescription,
                    startDate, endDate, isDone);
        } catch (DateTimeParseException dtpe) {
            throw new InvalidTaskArgumentException(
                    MESSAGE_INCORRECT_EVENT_INPUT);
        }        
    }
    
    /** 
     * Decodes each line of string text into acceptable task arguments format. 
     * 
     * @param line  The line of string text to be decoded.
     * @throws StorageReadWriteException  If the storage text file is a blank file
     *                                    or does not conform to the encoding 
     *                                    standard.  
     */
    private static Map<String, String> decodeLineIntoTaskArguments(
            String line) 
            throws StorageReadWriteException {
        
        Map<String, String> mapOfTaskArguments = new HashMap<>();        
        String[] wordArray = line.split(" | ");
        String taskType = wordArray[TASK_TYPE_INDEX];
        
        switch (taskType) {
        case "T":
            mapOfTaskArguments = decodeToDosIntoTaskArguments(line);
            break;
        case "D":
            mapOfTaskArguments = decodeDeadlinesIntoTaskArguments(line);
            break;
        case "E":
            mapOfTaskArguments = decodeEventsIntoTaskArguments(line);
            break; 
        default:
            throw new StorageReadWriteException(
                    MESSAGE_CORRUPTED_STORAGE_FILE);
        }
        
        return mapOfTaskArguments;
    }
    
    /** Decodes each line of string text into acceptable Events task arguments format. */
    private static Map<String, String> decodeEventsIntoTaskArguments(
            String line) {
        
        Map<String, String> eventArguments = new HashMap<>();
        String[] wordArray = splitLineIntoReadableEventDateTime(line);

        eventArguments.put(
                "taskType", wordArray[TASK_TYPE_INDEX]);
        eventArguments.put(
                "taskStatus", wordArray[TASK_STATUS_INDEX]);
        eventArguments.put(
                "taskDescription", wordArray[TASK_DESCRIPTION_INDEX]);
        eventArguments.put(
                "taskStartDate", wordArray[TASK_START_DATE_TIME_INDEX]);
        eventArguments.put(
                "taskEndDate", wordArray[TASK_END_DATE_TIME_INDEX]);
        
        return eventArguments;
    }
    
    /** Decodes each line of string text into acceptable Deadlines task arguments format. */
    private static Map<String, String> decodeDeadlinesIntoTaskArguments(
            String line) {
        
        Map<String, String> deadlineArguments = new HashMap<>();        
        String[] wordArray = splitLineIntoWords(line);       
        
        deadlineArguments.put(
                "taskType", wordArray[TASK_TYPE_INDEX]);
        deadlineArguments.put(
                "taskStatus", wordArray[TASK_STATUS_INDEX]);
        deadlineArguments.put(
                "taskDescription", wordArray[TASK_DESCRIPTION_INDEX]);
        deadlineArguments.put(
                "taskDeadline", wordArray[TASK_DEADLINE_INDEX]);
        
        return deadlineArguments;
    }

    /** Decodes each line of string text into acceptable ToDos task arguments format. */
    private static Map<String, String> decodeToDosIntoTaskArguments(
            String line) {
        
        Map<String, String> todosArguments = new HashMap<>();       
        String[] wordArray = splitLineIntoWords(line);  
        
        todosArguments.put(
                "taskType", wordArray[TASK_TYPE_INDEX]);
        todosArguments.put(
                "taskStatus", wordArray[TASK_STATUS_INDEX]);
        todosArguments.put(
                "taskDescription", wordArray[TASK_DESCRIPTION_INDEX]);

        return todosArguments;
    }
    
    /** 
     * Split a line of string text using the regex ("|to"). This is used only 
     * to decode a string text that into an Event task argument format.
     * 
     * For example: E | 1 | homework | 1200-12-12T10:00 to 1200-12-12T11:00'
     * will be split into wordArray = {[E], [1], [homework],
     * [1200-12-12T10:00 to 1200-12-12T11:00]}.
     * 
     */
    private static String[] splitLineIntoReadableEventDateTime(
            String line) {
        
        String[] wordArray = line.split("[|to]");        
            
        for (int i = 0; i < wordArray.length; i++) {
                wordArray[i] = wordArray[i].trim();
        }
            
        return wordArray;
    }       
    
    /** 
     * Split a line of string text using the regex ("|"). This is used mainly to 
     * decode a string text that into a ToDos or a Deadlines task argument format.
     * 
     * For example: D | 1 | homework | 1200-12-12T10:00'
     * will be split into wordArray = {[D], [1], [homework], [1200-12-12T10:00]}.
     * 
     */   
    private static String[] splitLineIntoWords(String line) {
        String[] wordArray = line.split("[|]");        
        
        for (int i = 0; i < wordArray.length; i++) {
            wordArray[i] = wordArray[i].trim();
        }
        
        return wordArray;
    }
}
