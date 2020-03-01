package task;

import exception.DukeException;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task item does not exist.");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void deleteTask(int index) throws DukeException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task item does not exist.");
        }
    }

    public Task getLatestTask() {
        return tasks.get(tasks.size() - 1);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void doneTask(int index) throws DukeException {
        this.getTask(index).markAsDone();
    }

    public ArrayList<Task> findTasks(String keyWord) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.toLowerCase().contains(keyWord)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }
}
