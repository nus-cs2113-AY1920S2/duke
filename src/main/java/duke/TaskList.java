package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int counter;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.counter =0;
    }
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
        this.counter = tasks.size();
    }
    public void addTask(Task task) {
        tasks.add(task);
        this.counter = tasks.size()
    }
    public void removeTask(int number) {
        tasks.remove(number -1);
        this.counter = tasks.size();
    }
    public int getSize() {
        return this.counter;
    }
    public Task getIndex(int number) {
        return tasks.get(number);
    }
}
