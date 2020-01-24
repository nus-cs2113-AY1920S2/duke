import java.util.Scanner;
public class Duke {
    public static void printSplit(){
        System.out.println("    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁");
    }


    public static void printBye() {
        printSplit();
        System.out.println("    Bye. Hope to see you again soon!");
        printSplit();
    }

    public static void main(String[] args) {
        Task task = new Task();
        Duke split = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
        printSplit();
        System.out.println("    Sorry I don't understand your command :(\n" +
                "    Please select one of the following (key in number): \n" +
                "    1. Add a new task, \n" +
                "    2. Delete an old task, \n" +
                "    3. Show my tasks, or\n"+
                "    4.bye \n" +
                "    to end this conversation");
        printSplit();

        Scanner scan = new Scanner(System.in);
        int inst = scan.nextInt();
        while (inst != 4) {
            Task.exe(inst);
            inst = scan.nextInt();
        }
        printBye();


    }
}

