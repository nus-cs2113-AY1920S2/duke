import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
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
        addTask add = new addTask(taskList);
        deleteTask delete = new deleteTask(taskList);
        printTasks print = new printTasks(taskList);
        if (inst.equals("1")) {
            add.exe();
        } else if (inst.equals("2")) {
            delete.exe();
        } else if (inst.equals("3")) {
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

        while (!inst.equals("4")) {
            executeTaskManager(inst);
            inst = scan.next();
        }
        printExit();
    }
}

