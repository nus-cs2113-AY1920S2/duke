import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");

        Scanner scan = new Scanner( System.in );

        while(true) {
            String userData = scan.nextLine();
            System.out.println(userData);
            if(userData.toLowerCase().equals("bye")){
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
