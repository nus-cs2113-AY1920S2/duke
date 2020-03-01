package duke.data;

import duke.format.DateTime;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <h3>Task List</h3>
 * The <b>Task List</b> contains a list of all the <b>Tasks</b> that the user has added. The <b>Task List</b>
 * is responsible for managing the list and task-related operations such as <i>adding</i>, <i>deleting</i>,
 * <i>doing</i> and <i>finding</i> tasks.
 *
 * @see Task
 */
public class TaskList {
    // The task list containing all the tasks
    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Returns the <b>Task</b> object at the specified <code>index</code> of the <code>taskList</code>.
     *
     * @param index The index of the <code>taskList</code> with the requested <b>Task</b>
     * @return The <b>Task</b> object at the specified <code>index</code> of the <code>taskList</code>
     * @see Task
     */
    public static Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the current size of the <code>taskList</code>.
     *
     * @return The size of the <code>taskList</code>
     */
    public static int size() {
        return taskList.size();
    }

    /**
     * Adds a specified <b>Task</b> into the <code>taskList</code>.
     *
     * @param task The <b>Task</b> to be added
     * @see Task
     */
    public static void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a <b>Task</b> at the specified <code>index</code> of the <code>taskList</code>.
     * <p></p>
     * <b>Note</b>: The <code>index</code> provided must be in the <code>taskList</code>.
     *
     * @param index The index of the <code>taskList</code> with the <b>Task</b> to be deleted
     * @see Task
     */
    public static void delete(int index)  {
        taskList.remove(index);
    }

    /**
     * Sets a <b>Task</b> at the specified <code>index</code> of the <code>taskList</code> to be <i>done</i>
     * <b>if</b> it is <b><u>not</u></b> already done before.
     * <p></p>
     * <b>Note</b>: The <code>index</code> provided must be in the <code>taskList</code>.
     *
     * @param index The index of the <code>taskList</code> with the <b>Task</b> to be done
     * @return <code>TRUE</code> if the <i>task</i> is being done, or <code>FALSE</code> if the <i>task</i> is
     * already done
     * @see Task
     */
    public static boolean doTask(int index) {
        if (!taskList.get(index).getIsDone()) {
            taskList.get(index).setDone(true);
            return true;
        }
        return false;
    }

    /**
     * Filters the <code>taskList</code> for <b>Tasks</b> that have the specified <code>searchWord</code> in their
     * <i>task description</i>.
     * <br> Returns an <code>Array</code> containing the indices of the found <b>Tasks</b>, if any.
     *
     * @param searchWord The word(s) used as the keyword(s) to filter the <code>taskList</code>
     * @return An <code>Array</code> containing the indices of the found <i>tasks</i>
     * @see Task
     */
    public static ArrayList<Integer> filter(String searchWord) {
        ArrayList<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < taskList.size(); ++i) {
            String task = taskList.get(i).getTask();
            if (task.contains(searchWord)) {
                indexList.add(i);
            }
        }

        return indexList;
    }

    /**
     * Filters the <code>taskList</code> for <b>Deadline</b> and <b>Event</b> tasks that have their <i>datetime</i>
     * information on the <u>same</u> <i>date</i> as the specified <code>date</code>.
     * <br> Returns an <code>Array</code> containing the indices of the found <b>Deadline</b> and <b>Event</b> tasks,
     * if any.
     *
     * @param date The date to filter the <code>taskList</code>
     * @return An <code>Array</code> containing the indices of the found <i>tasks</i>
     * @see duke.task.Deadline
     * @see duke.task.Event
     */
    public static ArrayList<Integer> filterDate(LocalDate date) {
        ArrayList<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < taskList.size(); ++i) {
            DateTime dateTime = taskList.get(i).getDateTime();
            if (dateTime != null && dateTime.isOn(date)) {
                indexList.add(i);
            }
        }

        return indexList;
    }

    /**
     * Filters the <code>taskList</code> for <b>Deadline</b> and <b>Event</b> tasks that have their <i>datetime</i>
     * information on an <u>earlier</u> <i>date</i> compared to the specified <code>date</code>.
     * <br> Returns an <code>Array</code> containing the indices of the found <b>Deadline</b> and <b>Event</b> tasks,
     * if any.
     *
     * @param date The date to filter the <code>taskList</code>
     * @return An <code>Array</code> containing the indices of the found <i>tasks</i>
     * @see duke.task.Deadline
     * @see duke.task.Event
     */
    public static ArrayList<Integer> filterDateBefore(LocalDate date) {
        ArrayList<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < taskList.size(); ++i) {
            DateTime dateTime = taskList.get(i).getDateTime();
            if (dateTime != null && dateTime.isBefore(date)) {
                indexList.add(i);
            }
        }

        return indexList;
    }

    /**
     * Filters the <code>taskList</code> for <b>Deadline</b> and <b>Event</b> tasks that have their <i>datetime</i>
     * information on a <u>later</u> <i>date</i> compared to the specified <code>date</code>.
     * <br> Returns an <code>Array</code> containing the indices of the found <b>Deadline</b> and <b>Event</b> tasks,
     * if any.
     *
     * @param date The date to filter the <code>taskList</code>
     * @return An <code>Array</code> containing the indices of the found <i>tasks</i>
     * @see duke.task.Deadline
     * @see duke.task.Event
     */
    public static ArrayList<Integer> filterDateAfter(LocalDate date) {
        ArrayList<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < taskList.size(); ++i) {
            DateTime dateTime = taskList.get(i).getDateTime();
            if (dateTime != null && dateTime.isAfter(date)) {
                indexList.add(i);
            }
        }

        return indexList;
    }
}
