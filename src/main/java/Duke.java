import java.util.Scanner;

public class Duke {
    /*
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    */

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        int flag = 0;

        while (flag == 0) {
            String x = scanner.nextLine();
            if (x.equals("bye")) {
                System.out.println(goodbye);
                flag = 1;
            } else {
                System.out.println(x);
            }
        }

    }


}
