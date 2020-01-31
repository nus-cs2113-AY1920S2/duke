import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<String> myList = new ArrayList<>();

    public void storeTasks(String userInput) {
        myList.add(userInput);
    }

    public void getTasks() {
        Printer.printTasks(myList);
    }
}
