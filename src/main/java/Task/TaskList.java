package Task;

import Duke.Deadline;
import Duke.Event;
import Duke.Task;
import Duke.ToDo;
import Exceptions.MissingParameterException;
import Exceptions.NoParameterException;
import Exceptions.EmptyListException;
import Storage.Storage;

import java.util.ArrayList;


/**
 * Instance of tasks used in program
 * Contains methods required to modify tasks
 */
public class TaskList {

    public static final int LENGTH_DEADLINE = 9;
    public static final int LENGTH_EVENT = 6;
    public static final int LENGTH_TODO = 5;
    public static final int LIST_TO_INDEX = 1;
    public static final String TASK_INCORRECT_PARAMETERS_DETECTED = "[Error][New Task]: Incorrect Parameters detected";
    public static final int DATETIME_PARAMETER_SIZE = 2;

    private static ArrayList<Task> tasks;

    /**
     * Creating new instance of task list
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Updates task status upon completion
     *
     * @param indexCompleteTask index of complete task
     * @return whether update is successful or not
     */
    public static boolean completeTask(int indexCompleteTask) {
        indexCompleteTask -= 1; // index starts from 0, unlike listing number
        if ( (indexCompleteTask < tasks.size()) || (indexCompleteTask > 0)) { // check if out of bounce
            Task currentTask = tasks.get(indexCompleteTask);
            if (currentTask.getStatus()) { // check if already completed
                System.out.println("Duke.Task already completed!\n");
            } else {
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println( "["+ currentTask.getTaskType() + "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + "\n");
                return true;
            }
        } else {
            System.out.println("Error: No such index in use\n");
        }
        return false;
    }

    /**
     * Deletion of task from task list
     *
     * @param taskIndex index of task to be deleted
     * @throws EmptyListException if task list is empty
     */
    public static void deleteTask(int taskIndex) throws EmptyListException {
        if(tasks.isEmpty()) {
            throw  new EmptyListException("List is currently empty!");
        }
        taskIndex -= LIST_TO_INDEX;
        if ( (taskIndex < tasks.size()) || (taskIndex > 0)) { // check if out of bounce
            Task currentTask = tasks.get(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println("["+ currentTask.getTaskType() + "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + "\n");
            tasks.remove(taskIndex);
            System.out.println("Now you have " + tasks.size() +" tasks in the list.\n");

        } else {
            System.out.println("Error: No such index in use\n");
        }
    }


    /**
     * Processes task types with date and time fields
     *
     * @param taskDescription description of task
     * @param wordLength    length of user command for parameter validation
     * @param commandLength length of command word for extraction
     * @return word array for task fields
     * @throws NoParameterException
     * @throws MissingParameterException
     */
    public static String[] processDatedTasks(String taskDescription, int wordLength, int commandLength)
                                            throws NoParameterException, MissingParameterException {
        if (wordLength <= 1) { // empty parameter
            throw new NoParameterException("[Error][New Task]: Incorrect Parameters detected");
        }
        try {
            String itemName = taskDescription.substring(commandLength);
            String[] words = itemName.split("/"); // potential problem is not accepting date format with '/' inside, throw err if more than 2 len
            for (int i = 0; i < words.length; i++) { // checking for blank tasks, including one char of space
                if (words[i].isBlank()){
                    throw new NoParameterException(TASK_INCORRECT_PARAMETERS_DETECTED);
                }
            }
            return words;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
        }
        System.out.println("[Error]: Incorrect input found");
        return null;
    }

    /**
     * Separating date and time from parsed parameter
     *
     * @param dateTime parameter from user input task
     * @return array of size two for date and time
     * @throws MissingParameterException if either date or time is missing
     */
    public static String[] formatDatetime(String dateTime) throws MissingParameterException {
        try {
            String[] splitDateTime = dateTime.trim().split(" ");

            if (splitDateTime.length != DATETIME_PARAMETER_SIZE) {
                throw new NullPointerException();
            }
            for (int i = 0; i < splitDateTime.length; i++) { // checking for blank tasks, including one char of space
                if (splitDateTime[i].isBlank()){
                    throw new MissingParameterException();
                }
            }
            return splitDateTime;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
        }
        return null;
    }

    /**
     * Adding deadline task into task list
     *
     * @param userInput user input
     * @param wordLength length of user input for validation
     * @throws NoParameterException no parameters given
     * @throws MissingParameterException not all parameters filled
     */
    public static void addDeadline(String userInput, int wordLength) throws NoParameterException, MissingParameterException {
        try {
            String[] words = processDatedTasks(userInput, wordLength, LENGTH_DEADLINE);
            String[] splitDateTime = formatDatetime(words[1]);
            if (splitDateTime.length != DATETIME_PARAMETER_SIZE) {
                throw new MissingParameterException();
            }
            Task newTask = new Deadline(words[0], splitDateTime[0], splitDateTime[1]);
            tasks.add(newTask);
            newTask.printAddDetails(getTaskListCounter());
            Storage.saveTask(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
        }
    }

    /**
     * Adding event task into task list
     *
     * @param userInput user input
     * @param wordLength length of user input for validation
     * @throws NoParameterException no parameters given
     * @throws MissingParameterException not all parameters filled
     */
    public static void addEvent(String userInput, int wordLength) throws NoParameterException, MissingParameterException {
        try {
            String[] words = processDatedTasks(userInput, wordLength, LENGTH_EVENT);
            String[] splitDateTime = formatDatetime(words[1]);
            if (splitDateTime.length != DATETIME_PARAMETER_SIZE) {
                throw new MissingParameterException();
            }
            Task newTask = new Event(words[0], splitDateTime[0], splitDateTime[1]);
            tasks.add(newTask);
            newTask.printAddDetails(getTaskListCounter());
            Storage.saveTask(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
        }
    }

    /**
     * Adding deadline task into task list
     *
     * @param userInput user input
     * @param wordLength length of user input for validation
     * @throws NoParameterException no parameters given
     */
    public static void addTodo(String userInput, int wordLength) throws NoParameterException {

        if (wordLength <= 1) { // empty parameter
            throw new NoParameterException(TASK_INCORRECT_PARAMETERS_DETECTED);
        }
        String itemName = userInput.substring(LENGTH_TODO);
        if (itemName.isBlank()) { // task is a space/blank char
            throw new NoParameterException(TASK_INCORRECT_PARAMETERS_DETECTED);
        }
        Task newTask = new ToDo(itemName.trim());
        tasks.add(newTask);
        newTask.printAddDetails(getTaskListCounter());
        Storage.saveTask(newTask);
    }

    /**
     * Retrieves the current size of task list
     *
     * @return number of tasks
     */
    public static int getTaskListCounter() {
        return tasks.size();
    }

    /**
     * Loading todo task from data file
     *
     * @param words entry of task in data file
     */
    public static void loadFromFileTodo(String[] words) {
        boolean taskStatus = Boolean.parseBoolean(words[1]);
        String taskDescription = words[2];
        Task newTask = new ToDo(taskDescription);
        if (taskStatus) {
            newTask.markAsDone();
        }
        tasks.add(newTask);
    }

    /**
     * Loading deadline task from data file
     *
     * @param words entry of task in data file
     */
    public static void loadFromFileDeadline(String[] words) {
        boolean taskStatus = Boolean.parseBoolean(words[1]);
        String taskDescription = words[2];
        String taskDate = words[3];
        String taskTime = words[4];
        Task newTask = new Deadline(taskDescription, taskDate, taskTime);
        if (taskStatus) {
            newTask.markAsDone();
        }
        tasks.add(newTask);

    }

    /**
     * Loading event task from data file
     *
     * @param words entry of task in data file
     */
    public static void loadFromFileEvent(String[] words ) {
        boolean taskStatus = Boolean.parseBoolean(words[1]);;
        String taskDescription = words[2];
        String taskDate = words[3];
        String taskTime = words[4];
        Task newTask = new Event(taskDescription, taskDate, taskTime);
        if (taskStatus) {
            newTask.markAsDone();
        }
        tasks.add(newTask);
    }

    /**
     * Displays the full task list
     */
    public static void printList() {
        int count = 1;
        System.out.println("Listing tasks below:");

        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment!");
        } else {
            for (Task currentTask : tasks) {
                currentTask.printListDetails(count);
                count++;
            }
        }
        System.out.println("");
    }

    /**
     * Retrieves actual task list
     */
    public static ArrayList<Task> getTaskList() {
        return tasks;
    }
}
