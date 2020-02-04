import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String EXITCOMMAND = "4";
    protected static List taskList = new LinkedList();
    public static void printIntro() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
    }
    public static void printExit() {
        System.out.println(
                "    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁\n" +
                "    Bye. Hope to see you again soon!\n" +
                "    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁" );
    }
    public static void executeTaskManager(String inst) {
        TaskManager manage = new TaskManager(taskList);
        Scanner scan = new Scanner(System.in);
        if (inst.equals("1")) {
            addTask add = new addTask(taskList);
            add.exe();
        } else if (inst.equals("2")) {
            deleteTask delete = new deleteTask(taskList);
            delete.exe();
        } else if (inst.equals("3")) {
            printTasks print = new printTasks(taskList);
            print.exe();
        } else {
            System.out.println("    Sorry I don't understand your command :(");
            manage.printInst();
        }
    }
    public static void main(String[] args) {
        TaskManager manage = new TaskManager(taskList);
        printIntro();
        Scanner scan = new Scanner(System.in);
        manage.printInst();
        String inst = scan.next();
        while (!inst.equals(EXITCOMMAND)) {
            executeTaskManager(inst);
            inst = scan.next();
        }
        printExit();
    }
}

