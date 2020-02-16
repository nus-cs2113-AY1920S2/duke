package duke.task;

import static misc.Messages.MESSAGE_COMMAND_LIST_TASK;
import static misc.Messages.MESSAGE_DONE_COMMNAND_INDEX_OUT_OF_BOUNDS;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.task.Task;

import static misc.Messages.MESSAGE_COMMAND_LIST_TASK;
import static misc.Messages.MESSAGE_DONE_COMMNAND_INDEX_OUT_OF_BOUNDS;
import static misc.Messages.MESSAGE_FIND_COMMAND_TASK;
import static misc.Messages.MESSAGE_COMMAND_FILTER_TASK;
import static misc.Messages.MESSAGE_INCORRECT_DATE_FORMAT;

/**
 * Encapsulates the information of a TaskList. 
 * Stores an internal List<Task> that is manipulated based on
 * Duke's command execution. 
 */
public class TaskList {
    
    /** Represents the first element index of the List<Task>. */
    public final int FIRST_ELEMENT_INDEX = 0;
    
    /** A counter that represents the number of tasks in List<Task>. */
    public static int taskIdCounter = 1;
    
    /** An internal storage of List of Task. */
    private final List<Task> tasks;
    private final DateTimeFormatter dateFormatter = 
            DateTimeFormatter.ofPattern("uuuu-MM-dd")
            .withResolverStyle(ResolverStyle.STRICT);
    
    /** 
     * Constructor for a new TaskList.
     * Takes in a old TaskList and performs a deep copy of it.
     * 
     * @param oldTaskList
     */
    public TaskList(TaskList oldTaskList) {
        this.tasks = oldTaskList.getTasks()
                .stream()
                .collect(Collectors.toList());
    }
    
    /** Initializes the List of tasks. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public List<Task> getTasks() {
        return this.tasks;
    }
    
    /** List all tasks in the List<Task>. */
    public void findTask(String keyword) {
        System.out.println(MESSAGE_FIND_COMMAND_TASK);
        
        this.tasks.stream()
            .filter(task -> task.getTaskName()
                    .contains(keyword))
            .forEachOrdered(System.out::println);
        
        System.out.println("\n");
    }
    
    public void filterTask(String date) throws IllegalArgumentException {
        try {
            LocalDate.parse(date, dateFormatter);            
            System.out.println(MESSAGE_COMMAND_FILTER_TASK);
            
            this.tasks.stream()
                .filter(task -> task.getDate().isPresent())
                .filter(task -> task.getDate().get().equals(date))
                .forEachOrdered(System.out::println);
            
            System.out.println("\n");
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(MESSAGE_INCORRECT_DATE_FORMAT);
        }       
    }

    public void listTask() {
        System.out.println(MESSAGE_COMMAND_LIST_TASK);
        
        this.tasks.stream()
                .forEachOrdered(System.out::println);
            
        System.out.println("\n");
    }
    
    /** 
     * Delete a task within List<Task> based on a given task ID.
     * Throws an exception if the List<Task> does not contain that ID.
     * 
     * @param taskId
     * @throws IndexOutOfBoundsException
     */
    public void deleteTask(int taskId) throws IndexOutOfBoundsException {
        if (taskId > this.tasks.size()) {
            throw new IndexOutOfBoundsException(
                    MESSAGE_DONE_COMMNAND_INDEX_OUT_OF_BOUNDS);
        }
        
        Task deleteTask = getDeletedTask(taskId);
        TaskList.taskIdCounter--;
        createDeleteTaskMessage(deleteTask);
        this.tasks.remove(taskId - 1); 
        reorderTask();
    }
    
    /** Reorder the tasks within List<Task> according to ascending task IDs. */
    public void reorderTask() {
        int newIndex = 1;
        List<Task> newTasks = new ArrayList<>();
        
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            Task newTask = currentTask.setNewTaskId(newIndex);
            newTasks.add(newTask);
            newIndex++;
        }
        
        this.tasks.clear();
        this.tasks.addAll(newTasks);
    }
    
    /**
     * Completes a task based on a given task ID.
     * Throws an exception if the List<Task> does not contain that ID.
     * 
     * @param taskId
     * @throws IndexOutOfBoundsException
     */
    public void completeTask(int taskId) throws IndexOutOfBoundsException {  
        if (taskId > this.tasks.size()) {
            throw new IndexOutOfBoundsException(
                    MESSAGE_DONE_COMMNAND_INDEX_OUT_OF_BOUNDS);
        }
        
        List<Task> newTasks = this.tasks.stream()
                .map(task -> {
                    return ((task.getTaskId() == taskId) ? task.makeDone() : task);
                })
                .collect(Collectors.toList());
        
        createCompleteTaskMessage(taskId);
        this.tasks.clear();
        this.tasks.addAll(newTasks);
    }
    
    /**
     * Adds a task into the List<Task>.
     * 
     * @param task
     */
    public void addTask(Task task) {
        createAddTaskMessage(task);     
        this.tasks.add(task);
        TaskList.taskIdCounter++;       
    }
    
    /** 
     * Loads a task from the storage file into the List<Task>.
     * 
     * @param task
     */
    public void loadTask(Task task) {    
        this.tasks.add(task);
        TaskList.taskIdCounter++;       
    } 
    
    public Task getDeletedTask(int taskId) {
        Task task = this.tasks
                .stream()
                .filter(x -> x.getTaskId() == taskId)
                .map(x -> x)
                .collect(Collectors.toList())
                .get(FIRST_ELEMENT_INDEX);
        return task;
    }
    
    public Task getCompletedTask(int taskId) {
        Task task = this.tasks
                .stream()
                .filter(x -> x.getTaskId() == taskId)
                .map(x -> x.makeDone())
                .collect(Collectors.toList())
                .get(FIRST_ELEMENT_INDEX);
        return task;
    }
    
    /** 
     * Generates a delete task message after deleting a task.
     * 
     * @param task
     */
    public void createDeleteTaskMessage(Task task) {
        String output = ("Nice! I've removed this task:\n"
                + "  " 
                + task
                + "\nNow you have " 
                + (this.tasks.size() - 1)
                + " tasks in the list.\n");
        System.out.println(output);
    }
    
    /** 
     * Generates a complete task message after completing a task.
     * 
     * @param taskId
     */
    public void createCompleteTaskMessage(int taskId) {
        String output = ("Nice! I've marked this task as done:\n"
                + "  " 
                + getCompletedTask(taskId).taskWithSymbol()
                + ("\n"));
        System.out.println(output);
    }
    
    /** 
     * Generates an add task message after adding a task.
     * 
     * @param task
     */
    public void createAddTaskMessage(Task task) {
        String message = "";
        message += ("Got it. I've added this task:\n"
                + "  " 
                + task
                + "\nNow you have " 
                + TaskList.taskIdCounter
                + " tasks in the list.\n");
        System.out.println(message);
    }
}
