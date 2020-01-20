import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner myInput = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String userInput = myInput.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = myInput.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon! Maybe next time");
        myInput.close();
    }
}
