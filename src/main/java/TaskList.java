import Tasks.Task;
import Tasks.ToDo;
import Tasks.Deadline;
import Tasks.Event;

import java.util.ArrayList;

/**
 * This class contains methods that handles the task list.
 */
public class TaskList {
    public static final String EMPTY_LIST = "Your task list is empty! Try adding a task first!";
    public static final String INVALID_TASK_INDEX = "The task index you've entered is invalid.";
    public static final String KEYWORD_NOT_FOUND = "There are no tasks found in the list that match your keyword.";

    private ArrayList<Task> taskList;

    /**
     * Class constructor of the <code>TaskList</code> class.
     * @param savedList <code>ArrayList</code> of <code>Task</code> previously stored in the text file.
     */
    public TaskList(ArrayList<Task> savedList) {
        this.taskList = savedList;
    }

    /**
     * Returns current task list.
     * @return <code>ArrayList</code> of <code>Task</code> currently.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns size of current task list.
     * @return size of <code>ArrayList</code> of <code>Task</code> currently.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Processes list command.
     * @throws DukeException If current task list is empty.
     */
    public void listCommand() throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(EMPTY_LIST);
        }
    }

    /**
     * Marks the task as done.
     * @param taskIndex Index of the task to be marked as done.
     * @throws DukeException If task index is invalid (not within the range of indexes in the task list).
     * @throws NumberFormatException If the user has entered as invalid number.
     */
    public void markDone(int taskIndex) throws DukeException, NumberFormatException {
        if ((taskIndex < 0) || (taskIndex >= taskList.size())) {
            throw new DukeException(INVALID_TASK_INDEX);
        }
        taskList.get(taskIndex).setDone(true);
    }

    /**
     * Removes the task from the list.
     * @param taskIndex Index of the task to be deleted.
     * @throws DukeException If task index is invalid (not within the range of indexes in the task list).
     * @throws NumberFormatException If the user has entered as invalid number.
     */
    public void deleteTask(int taskIndex) throws DukeException, NumberFormatException {
        if ((taskIndex < 0) || (taskIndex >= taskList.size())) {
            throw new DukeException(INVALID_TASK_INDEX);
        }

        taskList.remove(taskList.get(taskIndex));
    }

    /**
     * Adds the To-do task to the list.
     * @param toDoTask To-do task to be added.
     */
    public void addToDo(String toDoTask) {
        taskList.add(new ToDo(toDoTask));
    }

    /**
     * Adds the Event task to the list.
     * @param eventWords <code>Array</code> of words that contains the description and date/time of the Event task to be added.
     */
    public void addEvent(String[] eventWords) {
        String eventTask = eventWords[0];
        String eventAt = eventWords[1];
        taskList.add(new Event(eventTask, eventAt));
    }

    /**
     * Adds the Deadline task the list.
     * @param deadlineWords <code>Array</code> of words that contains the description and date/time of the Deadline task to be added.
     */
    public void addDeadline(String[] deadlineWords) {
        String deadlineTask = deadlineWords[0];
        String deadlineBy = deadlineWords[1];
        taskList.add(new Deadline(deadlineTask, deadlineBy));
    }

    /**
     * Returns <code>ArrayList</code> of <code>Task</code> which matches the keyword entered by the user.
     * @param findKeywords <code>String</code> of keyword entered by user.
     * @return <code>ArrayList</code> of <code>Task</code> which matches the keyword.
     * @throws DukeException If keyword does not match any task in the list.
     */
    public ArrayList<Task> findTask(String findKeywords) throws DukeException {
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task t : taskList) {
            if(t.getTask().toLowerCase().contains(findKeywords.toLowerCase())) {
                foundList.add(t);
            }
        }
        if (foundList.isEmpty()) {
            throw new DukeException(KEYWORD_NOT_FOUND);
        }
        return foundList;
    }
}
