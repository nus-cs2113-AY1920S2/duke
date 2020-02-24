package TaskList;

import Duke.Deadline;
import Duke.Event;
import Duke.Task;
import Duke.ToDo;
import Exceptions.NoParameterException;
import Exceptions.emptyListException;

import java.util.ArrayList;

public class TaskList {

    public static void completeTask(ArrayList<Task> tasks, int taskIndex) {
        taskIndex--; // index starts from 0, unlike listing number
        if ( (taskIndex < tasks.size()) || (taskIndex > 0)) { // check if out of bounce
            Task currentTask = tasks.get(taskIndex);
            if (currentTask.getStatus()) { // check if already completed
                System.out.println("Duke.Task already completed!\n");
            } else {
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println( "["+ currentTask.getTaskType() + "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + "\n");
            }
        } else {
            System.out.println("Error: No such index in use\n");
        }
    }

    public static void deleteTask(ArrayList<Task> tasks, int taskIndex) throws emptyListException {

        if(tasks.isEmpty()) {
            throw  new emptyListException();
        }
        taskIndex -= LIST_TO_INDEX;
        if ( (taskIndex < tasks.size()) || (taskIndex > 0)) { // check if out of bounce
            Task currentTask = tasks.get(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println("["+ currentTask.getTaskType() + "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + "\n");
            tasks.remove(taskIndex);
            System.out.println("Now you have " + tasks.size() +" tasks in the list.\n");
            rebuildTaskFile(tasks);
        } else {
            System.out.println("Error: No such index in use\n");
        }
    }







    public static String[] processDatedTasks(String taskDescription, int taskCounter, int wordLength, int commandLength) throws NoParameterException {
        if (wordLength <= 1) { // empty parameter
            throw new NoParameterException();
        }
        try {
            String itemName = taskDescription.substring(commandLength);
            String[] words = itemName.split("/"); // potential problem is not accepting date format with '/' inside, throw err if more than 2 len
            for (int i = 0; i < words.length; i++) { // checking for blank tasks, including one char of space
                if (words[i].isBlank()){
                    throw new NoParameterException();
                }
            }
            return words;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
            taskCounter--;
        }
        System.out.println("Ugh null");
        return null;
    }

    public static void addDeadline(ArrayList<Task> tasks, String taskDescription, int taskCounter, int wordLength) throws NoParameterException {
        try {
            String[] words = processDatedTasks(taskDescription, taskCounter, wordLength, LENGTH_DEADLINE);
            Task newTask = new Deadline(words[0].trim(), words[1].trim());
            tasks.add(newTask);
            newTask.printAddDetails(taskCounter);
            saveTask(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
        }
    }

    public static void addEvent(ArrayList<Task> tasks, String taskDescription, int taskCounter, int wordLength) throws NoParameterException {
        try {
            String[] words = processDatedTasks(taskDescription, taskCounter, wordLength, LENGTH_EVENT);
            Task newTask = new Event(words[0].trim(), words[1].trim());
            tasks.add(newTask);
            newTask.printAddDetails(taskCounter);
            saveTask(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
        }
    }

    public static void addTodo(ArrayList<Task> tasks, String taskDescription, int taskCounter, int wordLength) throws NoParameterException {
        if (wordLength <= 1) { // empty parameter
            throw new NoParameterException();
        }
        String itemName = taskDescription.substring(LENGTH_TODO);
        if (itemName.isBlank()) { // task is a space/blank char
            throw new NoParameterException();
        }
        Task newTask = new ToDo(itemName.trim());
        tasks.add(newTask);
        newTask.printAddDetails(taskCounter);
        saveTask(newTask);
    }

}
