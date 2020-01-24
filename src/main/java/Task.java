import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class Task {
    private static List taskList = new LinkedList();
    public static void addList(String task){
        taskList.add(task);
    }
    public static void deleteList(String task){
        taskList.remove(task);
    }
    public static void printList() {
        Stream stream = taskList.stream();
        stream.forEach(System.out::println);
    }
    public static void printSalute(){
        System.out.println(
                        "    Please select one of the following (key in number): \n" +
                        "    1. Add a new task, \n" +
                        "    2. Delete an old task, \n" +
                        "    3. Show my tasks, or\n"+
                        "    4.bye \n" +
                        "    to end this conversation");
    }
    public static void exe(int instruction){
        Scanner scan = new Scanner(System.in);
        switch (instruction) {
        case 1: //add task
            System.out.println("please enter the task that you want to add(key in number): ");
            String task1 = scan.nextLine();
            taskList.add(task1);
            System.out.println(task1 +" is added to your task list:)");
            printSalute();
            break;
        case 2: //delete task
            System.out.println("please enter the task that you want to delete: ");
            String task2 = scan.nextLine();
            taskList.remove(task2);
            System.out.println(task2 +" is deleted from your task list!");
            printSalute();
            break;
        case 3: //show list
            printList();
            printSalute();
            break;
        default:
            System.out.println("    Sorry I don't understand your command :(\n" +
                            "    Please select one of the following (key in number): \n" +
                            "    1. Add a new task, \n" +
                            "    2. Delete an old task, \n" +
                            "    3. Show my tasks, or\n"+
                            "    4.bye \n" +
                            "    to end this conversation");
        }
    }
}
