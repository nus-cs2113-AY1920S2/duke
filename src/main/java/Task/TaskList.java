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



public class TaskList {

    public static final int LENGTH_DEADLINE = 9;
    public static final int LENGTH_EVENT = 6;
    public static final int LENGTH_TODO = 5;
    public static final int LIST_TO_INDEX = 1;
    public static final int DATETIME_PARAMETER_SIZE = 2;

    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

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

    public static void deleteTask(int taskIndex) throws EmptyListException {

        if(tasks.isEmpty()) {
            throw  new EmptyListException();
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



    public static String[] processDatedTasks(String taskDescription, int wordLength, int commandLength)
                                            throws NoParameterException, MissingParameterException {
        if (wordLength <= 1) { // empty parameter
            throw new NoParameterException();
        }
        try {
            String itemName = taskDescription.substring(commandLength);
            String[] words = itemName.split("/"); // potential problem is not accepting date format with '/' inside, throw err if more than 2 len
            for (int i = 0; i < words.length; i++) { // checking for blank tasks, including one char of space
                if (words[i].isBlank()){
                    throw new MissingParameterException();
                }
            }
            return words;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
        }

        return null;
    }

    public static String[] formatDatetime(String dateTime) throws MissingParameterException {
        try {
            String[] splitDateTime = dateTime.trim().split(" ");
            System.out.println("1: " + splitDateTime[0]);
            System.out.println("2: " + splitDateTime[1]);
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

    public static void addEvent(String userInput, int wordLength) throws NoParameterException, MissingParameterException {
        try {
            String[] words = processDatedTasks(userInput, wordLength, LENGTH_EVENT);
            String[] splitDateTime = formatDatetime(words[1]);
            System.out.println("Date: " + splitDateTime[0]);
            System.out.println("Time: " + splitDateTime[1]);
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

    public static void addTodo(String userInput, int wordLength) throws NoParameterException {

        if (wordLength <= 1) { // empty parameter
            throw new NoParameterException();
        }
        String itemName = userInput.substring(LENGTH_TODO);
        if (itemName.isBlank()) { // task is a space/blank char
            throw new NoParameterException();
        }
        Task newTask = new ToDo(itemName.trim());
        tasks.add(newTask);
        newTask.printAddDetails(getTaskListCounter());
        Storage.saveTask(newTask);
    }

    public static int getTaskListCounter() {
        return tasks.size();
    }

    public static int getTaskIndexCounter() {
        return tasks.size() - 1;
    }

    public static void loadFromFileTodo(String[] words) {
        boolean taskStatus = Boolean.parseBoolean(words[1]);
        String taskDescription = words[2];
        Task newTask = new ToDo(taskDescription);
        if (taskStatus) {
            newTask.markAsDone();
        }
        tasks.add(newTask);
    }

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

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }
}