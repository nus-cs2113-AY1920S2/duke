package duke.task;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static duke.Duke.*;

/**
 * TaskList - class to store different types of tasks such as Todo, Deadline, Event
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int index;

    /**
     * Constructor of TaskList to initialize an empty array list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of TaskList to pass an existed tasks array list
     * to the TaskList class
     *
     * @param tasks an array list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Method to get the list of all tasks contained in the TaskList
     *
     * @return tasks List of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Method to print list of tasks contained in the TaskList with index
     * started from 1
     */
    public void printList() {
        System.out.println(TASK_LISTING);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(TAB_SPACE + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Method to delete a certain task in the list with certain index(started from 1)
     * and save it back to the data file
     *
     * @param index index of the task that needs to be deleted (started from 1)
     * @param storage pass the storage object to use method to save file after
     *                deleting
     * @throws IOException if something wrong with input
     */
    public void delete(int index, Storage storage) throws IOException{
        try {
            System.out.println(TASK_REMOVED_MESSAGE);
            System.out.println("\t" + tasks.get(index - 1));
            tasks.remove(tasks.get(index - 1));
            storage.save(this);
            String printTaskCount = "\tNow you have " + tasks.size() + " tasks in the list.";
            System.out.println(printTaskCount);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(OUT_OF_BOUND_MESSAGE);
        } catch (NumberFormatException e) {
            System.out.println(WRONG_NUMBER_FORMAT_MESSAGE);
        }
    }

    /**
     * Method to mark a task in the list as done with certain index (started from
     * 1) and save it back to the data file
     *
     * @param index index of the task that needs to be deleted (started from 1)
     * @param storage pass the storage object to use method to save file after
     *      *                marking
     * @throws IOException if something wrong with input
     */
    public void markAsDone(int index, Storage storage) throws IOException{
        try {
            if (index >= tasks.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.get(index - 1).markAsDone();
            storage.save(this);
            System.out.println(TASK_MARKING_MESSAGE);
            String printTask = "\t%s\n";
            System.out.printf(printTask, tasks.get(index - 1));
        } catch (NumberFormatException e) {
            System.out.println(WRONG_NUMBER_FORMAT_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(OUT_OF_BOUND_MESSAGE);
        }
    }

    /**
     * Method to add a certain task with description (and maybe date and time)
     * depending on certain prefixes in the command and store it back to the data file
     * after adding
     *
     * @param storage pass the storage object to use method to save file after
     *      *      *                adding
     * @param command pass the whole command into the function
     * @param prefix first word in the command describing which type of task to be added
     * @throws DateTimeParseException when the date and time is in the wrong format
     */
    public void addTask(Storage storage, String command, String prefix) throws DateTimeParseException {
        try {
            int splitIndex = command.indexOf("/");
            switch (prefix) {
            case EMPTY_STRING:
                throw new NullPointerException();
            case TODO:
                String activity = command.substring(TODO_WORD_LENGTH + 1);
                tasks.add(new ToDo(activity));
                break;
            case DEADLINE:
                String activity2 = command.substring(DEADLINE_WORD_LENGTH + 1, splitIndex - 1);
                String date = command.substring(splitIndex + 4);
                tasks.add(new Deadline(activity2, date));
                break;
            case EVENT:
                String activity3 = command.substring(EVENT_WORD_LENGTH + 1, splitIndex - 1);
                String time = command.substring(splitIndex + 4);
                tasks.add(new Event(activity3, time));
                break;
            default:
                throw new IOException();
            }
            storage.save(this);
            if (tasks.size() > TASK_LIMIT) {
                throw new ArrayIndexOutOfBoundsException();
            }
            System.out.println(ADDED_TASK_MESSAGE);
            String printTask = "\t" + tasks.get(tasks.size() - 1);
            System.out.println(printTask);
            String printTaskCount = "\tNow you have " + tasks.size() + " tasks in the list.";
            System.out.println(printTaskCount);
        } catch (NullPointerException e) {
            System.out.println(EMPTY_INPUT_MESSAGE);
        } catch (StringIndexOutOfBoundsException e) {
            String invalidFormatMessage = "\t☹ OOPS!!! The description of a " + prefix + " cannot be empty or"
                    + " it is in the wrong format.";
            System.out.println(invalidFormatMessage);
        } catch (IOException e) {
            System.out.println(DUMMY_INPUT_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LIMIT_REACHED_MESSAGE);
        }
    }

    /**
     * Method to find certain tasks with certain key words in the task list and
     * list them with indexes started from 1
     *
     * @param keyWord words that is typed in to find the tasks with the key words
     */
    public void findTask(String keyWord) {
        keyWord = keyWord.trim().toLowerCase();
        ArrayList<Task> key = new ArrayList<>();
        for (Task t : tasks) {
            boolean isMatched = t.getDescription().trim().toLowerCase().contains(keyWord);
            if (isMatched) {
                key.add(t);
            }
        }
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < key.size(); i++) {
            System.out.println("\t" + (i + 1) + ": " + key.get(i));
        }
    }
}
