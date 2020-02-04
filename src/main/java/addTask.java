import java.util.List;
import java.util.Scanner;

public class addTask extends TaskManager {
    public static final String TODO = "1";
    public static final String DEADLINE = "2";
    public static final String EVENT = "3";
    private static String task;
    private static String typeTask;
    private static String extendsTask;

    public addTask(List taskList) {
        super(taskList);
    }

    public void categorizeTask(String input){
        Scanner scan = new Scanner(System.in);
        System.out.println("    Please enter the task: ");
        task = scan.nextLine();
        switch (input) {
        case TODO:
            System.out.println("    ["+ task + "] is added onto your task list:)\n");
            taskList.add(task);
            break;
        case DEADLINE:
            System.out.println("    Please enter the deadline: ");
            extendsTask = scan.nextLine();
            taskList.add(task + " (by " + extendsTask + ")");
            System.out.println("    ["+ task + " (by " + extendsTask + ")] is added onto your task list:)\n");
            break;
        case EVENT:
            extendsTask = scan.nextLine();
            taskList.add(task + " (at " + extendsTask + ")");
            System.out.println("    ["+ task + " (at " + extendsTask + ")] is added onto your task list:)\n");
            break;
        default:
            System.out.println("Wrong input");
        }
        System.out.println("    Enter anything to continue");
        scan.next();
        printInst();
    }

    public void exe() {
        Scanner scan = new Scanner(System.in);
        System.out.println("    Which category does your task belong to? \n"+
                "   1. ToDos: tasks without any date/time attached to it (e.g., visit new theme park)\n" +
                "   2. Deadlines: tasks that need to be done before a specific date/time" +
                " (e.g., submit report by 11/10/2019 5pm)\n" +
                "   3. Events: tasks that start at a specific time and ends at a specific time" +
                " (e.g., team project meeting on 2/10/2019 2-4pm)") ;
        typeTask = scan.nextLine();
        categorizeTask(typeTask);
    }
}
