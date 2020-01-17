import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________");
        System.out.println("Hello,I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
        Scanner in = new Scanner(System.in);
        String input = in.next();
        while(!(input.equals("Bye"))){
            System.out.println("________________________________");
            System.out.println(input);
            System.out.println("________________________________");
            input = in.next();
        }
        System.out.println("________________________________");
        System.out.println("Bye.Hope to see you again soon!");
        System.out.println("________________________________");
    }

}
