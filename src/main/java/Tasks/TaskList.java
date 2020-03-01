package Tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    public void removeTask(int i) {
        this.tasks.remove(i);
    }
    public int getLength() {
        return this.tasks.size();
    }
    public void clearList() {
        this.tasks.clear();
    }

    public StringBuilder printList(){
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            string.append(i+1).append(". ").append(this.tasks.get(i).toString()).append("\n");
        }
        return string;
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }
}
