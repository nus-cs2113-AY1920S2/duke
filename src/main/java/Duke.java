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
        String[] list = new String[100];
        int itemCount = 0;
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
            } else if(input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for(int i = 0; i < itemCount; ++i) {
                    System.out.println("    " + (i+1) + ". " + list[i]);
                }
                System.out.println("    ____________________________________________________________");
            }
            else {
                System.out.println("    ____________________________________________________________");
                list[itemCount] = input;
                itemCount++;
                System.out.println("    added: " + input);
                System.out.println("    ____________________________________________________________");
            }
        } while (isExit == false);
    }
}