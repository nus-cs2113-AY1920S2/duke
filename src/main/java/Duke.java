import java.util.Scanner;

public class Duke {
    public static void printIntro(){
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
    public static void executeTaskManager(){
        TaskManager task = new TaskManager();
        TaskManager.printInst();
        Scanner scan = new Scanner(System.in);
        String inst = scan.next();
        while (!inst.equals("4")) {
            TaskManager.exe(inst);
            inst = scan.next();
        }
    }
    public static void main(String[] args) {
        printIntro();
        executeTaskManager();
//        TaskManager task = new TaskManager();
//        TaskManager.printInst();
//        Scanner scan = new Scanner(System.in);
//        String inst = scan.next();
//        while (!inst.equals("4")) {
//            TaskManager.exe(inst);
//            inst = scan.next();
//        }
        printExit();
    }
}

