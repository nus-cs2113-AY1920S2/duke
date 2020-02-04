import java.util.List;
import java.util.Scanner;

public class addTask extends TaskManager {
    public addTask(List taskList) {
        super(taskList);
    }

    public void addList(String task) {
        taskList.add(task);
    }
    public void exe() {
        Scanner scan = new Scanner(System.in);
        System.out.println("    please enter the task that you want to add: ");
        String task1 = scan.nextLine();
        addList(task1);
        System.out.println("    ["+ task1 +"] has added to your task list:)\n");
        printInst();
    }
}
