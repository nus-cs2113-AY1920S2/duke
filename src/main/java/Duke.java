import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        boolean isExit = false;
        do {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(" _                \n"
                        + "| |               \n"
                        + "| |__  _   _  ___ \n"
                        + "| '_ \\| | | |/ _ \\\n"
                        + "| |_) | |_| |  __/\n"
                        + "|_.__/ \\__, |\\___|\n"
                        + "        __/ |     \n"
                        + "       |___/      \n");
                isExit = true;
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + input);
                System.out.println("    ____________________________________________________________");
            }
        } while (isExit == false);
    }
}