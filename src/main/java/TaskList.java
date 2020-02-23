import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        this.printSize();
    }

    public Task getByIndex(int index) {
        return tasks.get(index);
    }

    public void removeByTask(Task task) {
        tasks.remove(task);
    }

    public void removeByIndex(int index) {
        tasks.remove(index);
    }

    public void setDoneByIndex(int index) {
        tasks.get(index).setDone(true);
    }

    public void list() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.size(); ++i) {
            System.out.printf("\t%d.%s", i+1, this.getByIndex(i));
            System.out.println();
        }
    }

    public int size() {
        return tasks.size();
    }

    public void printSize() {
        System.out.printf("\tNow you have %d tasks in the list.%s",
                this.size(), System.lineSeparator());
    }
}