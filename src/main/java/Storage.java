import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Task> myList = new ArrayList<>();

    public void storeTasks(Task task) {
        myList.add(task);
    }

    public void getTasks() {
        Printer.printTasks(myList);
    }
}
