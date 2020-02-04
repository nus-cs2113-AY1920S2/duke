import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class TaskManager {
    public static void printSplit(){
        System.out.println("    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁");
    }
    private static List taskList = new LinkedList();
    public static void addList(String task){
        taskList.add(task);
    }
    public static void deleteList(String task){
        if (taskList.contains(task)) {
            taskList.remove(task);
            System.out.println("    [" + task +"] is deleted from your task list!\n");
        } else if (!taskList.contains(task)){
            System.out.println("    Sorry, ["+ task+ "] is not fount in your list.\n");
        }
    }
    public static void printList() {
        Stream stream = taskList.stream();
        if (taskList.isEmpty()){
            System.out.println("    Congratulations, you have cleared all the tasks!\n");
        } else {
            stream.forEach(System.out::println);
            System.out.println();
        }
    }
    public static void printInst(){
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
    public static void exe(String instruction){
        Scanner scan = new Scanner(System.in);
        switch (instruction) {
        case "1": //add task
            System.out.println("    please enter the task that you want to add: ");
            String task1 = scan.nextLine();
            addList(task1);
            System.out.println("    ["+ task1 +"] has added to your task list:)\n");
            printInst();
            break;
        case "2": //delete task
            System.out.println("please enter the task that you want to delete: ");
            String task2 = scan.nextLine();
            deleteList(task2);
            printInst();
            break;
        case "3": //show list
            printList();
            printInst();
            break;
        default:
            System.out.println("    Sorry I don't understand your command :(");
            printInst();
        }
    }
}
