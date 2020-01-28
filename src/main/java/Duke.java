import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Isabella" + System.lineSeparator() + "What can I do for you?" + System.lineSeparator());
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(line + System.lineSeparator());
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");


    }
}
