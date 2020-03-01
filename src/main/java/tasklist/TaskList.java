package tasklist;

import tasktype.Task;
import data.Duke;

import java.util.ArrayList;

/**
 * This class is a list of {@link Task} objects. It acts as an abstract data type, various operations can be
 * conducted on the TaskList object like adding/removing Tasks or getting the total number of Tasks.
 * <p></p>
 * <p>
 * It can also execute operations on individual Tasks; you can get or change the completion status of a Task.
 * </p>
 * @see ArrayList
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> taskListFromSave) {
        this.tasks = taskListFromSave;
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public boolean getTaskDoneStatus(int taskNumber) {
        return this.tasks.get(taskNumber).getIsDone();
    }

    public void markTaskAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markAsDone();
    }

    public String getTaskDescription(int taskNumber) {
        return this.tasks.get(taskNumber).getDescription();
    }

    public String getTaskStatusIcon(int taskNumber) {
        return this.tasks.get(taskNumber).getStatusIcon();
    }

    public void addTask(Task taskToAdd) {
        this.tasks.add(taskToAdd);
    }

    public Task deleteTask(int taskNumber) {
        return this.tasks.remove(taskNumber);
    }

    /**
     * This methods iterates through a TaskList and finds the Tasks containing a given search keyword.
     * It is used primarily in the findTasksByKeyword method of the FIND command.
     * @param taskListToSearch the TaskList to be searched
     * @param searchKeyword the keyword to be searched for
     * @param searchResultIndexInput if a Task is added, the index of that Task in the actual input is stored in this ArrayList of Integers.
     * @return the ArrayList of Tasks containing the search keyword.
     * @see command.Command#findTasksByKeyword
     */
    public ArrayList<Task> findSearchResults(ArrayList<Task> taskListToSearch,
                                             String searchKeyword, ArrayList<Integer> searchResultIndexInput) {
        ArrayList<Task> matchingResults = new ArrayList<>();
        for (Task task : taskListToSearch) {
            boolean doesTaskContainSearchValue = task.addIfContainsKeyword(matchingResults, searchKeyword);
            if (doesTaskContainSearchValue) {
                searchResultIndexInput.add(taskListToSearch.indexOf(task));
            }
        }
        return matchingResults;
    }
}
