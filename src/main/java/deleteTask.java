import java.util.List;
import java.util.Scanner;

public class deleteTask extends TaskManager {
    public deleteTask(List taskList) {
        super(taskList);
    }
    public void deleteList(String task){
        if (taskList.contains(task)) {
            taskList.remove(task);
            System.out.println("    [" + task +"] is deleted from your task list!\n");
        } else if (!taskList.contains(task)){
            System.out.println("    Sorry, ["+ task+ "] is not fount in your list.\n");
        }
    }
    public void exe() {
        Scanner scan = new Scanner(System.in);
        System.out.println("please enter the task that you want to delete: ");
        String task2 = scan.nextLine();
        deleteList(task2);
        printInst();
    }
}
