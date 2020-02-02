import java.util.Scanner;

public class Duke {

    public static void exit() {
        System.out.println("------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------");
        System.out.println();
    }

    public static void echo() {
        Scanner in = new Scanner(System.in);
        String command;
        while(in.hasNextLine()) {
            command = in.nextLine();
            if(command.equals("bye")) {
                exit();
                return;
            } else {
                System.out.println("------------------------------------");
                System.out.println(command);
                System.out.println("------------------------------------");
                System.out.println();
            }
        }
    }

    public static void greet(){
        System.out.println("------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------");
        System.out.println();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }
}
