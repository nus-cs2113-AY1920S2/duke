package chatty.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static chatty.util.Constants.REGEX_MATCH_ALL_CHARACTER;

/**
 * List of Tasks.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a task to the list.
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     * @param idx The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int idx) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        return task;
    }

    /**
     * Gets the total number of tasks in the list.
     * @return Total number of tasks in the list.
     */
    public int getTotalTaskNum() {
        return tasks.size();
    }

    /**
     * Gets the task at a specific index.
     * @param idx The index.
     * @return The task at the specified index.
     */
    public Task getTaskAtIdx(int idx) {
        return tasks.get(idx);
    }

    /**
     * Marks the task at a specific index as done.
     * @param idx The index.
     * @return The task marked as done.
     */
    public Task markTaskAsDone(int idx) {
        Task task = tasks.get(idx);
        task.markAsDone();
        return task;
    }

    /**
     * Finds the tasks with the specified keyword.
     * @param keyword The specified keyword.
     * @return The task list of the found tasks.
     */
    public TaskList findTaskWithKeyword(String keyword) {
        TaskList taskListWithKeyword = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().matches(REGEX_MATCH_ALL_CHARACTER + keyword + REGEX_MATCH_ALL_CHARACTER)) {
                taskListWithKeyword.addTask(task);
            }
        }
        return taskListWithKeyword;
    }
  
    /**
     * Finds the tasks on the specified date.
     * @param date The specified date.
     * @return The task list of the found tasks.
     */
    public TaskList getTasksOnDate(LocalDate date) {
        TaskList tasksOnDate = new TaskList();
        for (Task task : tasks) {
            if (task instanceof Event) {
                LocalDate startTime = ((Event) task).getStartTime();
                LocalDate endTime = ((Event) task).getEndTime();
                if ((startTime.isBefore(date) || startTime.equals(date))
                        && (endTime.isAfter(date) || endTime.equals(date))) {
                    tasksOnDate.addTask(task);
                }
            } else if (task instanceof Deadline && ((Deadline) task).getDateTime().equals(date)) {
                tasksOnDate.addTask(task);
            }
        }
        return tasksOnDate;
    }
}
