import java.util.LinkedList;
import java.util.List;

public class TaskManager {
    protected List taskList = new LinkedList();
    public TaskManager(List taskList) {
        this.taskList = taskList;
    }
    public void printSplit() {
        System.out.println("    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁");
    }
    public void printInst() {
        printSplit();
        System.out.println(
                "    Please select one of the following (please key in the number): \n" +
                "    1. Add a new task, \n" +
                "    2. Delete an old task, \n" +
                "    3. Show my tasks, or\n"+
                "    4. See you next time! \n" +
                "    to end this conversation");
        printSplit();
    }

}
