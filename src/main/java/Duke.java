import java.util.Scanner;

public class Duke {
    private static String lines = "____________________________________________________________\n";

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        String toprint = lines + "Hello! I'm Duke\n" + "What can I do for you?\n" + lines;
        System.out.print(toprint);
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            System.out.print(lines + userInput + "\n" + lines);
            userInput = in.nextLine();
        }
        System.out.print(lines + "Bye. Hope to see you again soon!\n" + lines);
    }


//    For unit testing purposes
//    public static int multiply(int i, int j) {
//        return i * j;
//    }

}
