import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String lines = "____________________________________________________________\n";
    private static Scanner in = new Scanner(System.in);

    private static void botResponse(String userInput) {
        ArrayList<String> userList = new ArrayList<String>();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.print(lines);
                if (userList.size() == 0) {
                    System.out.println("No items added!");
                }
                for (int i=0; i<userList.size(); i++) {
                    Integer index = i+1;
                    System.out.println(index + ". " + userList.get(i));
                }
                System.out.print(lines);
            } else {
                userList.add(userInput);
                System.out.print(lines + "added: " + userInput + "\n" + lines);
            }
            userInput = in.nextLine();
        }
        System.out.print(lines + "Bye. Hope to see you again soon!\n" + lines);
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        String toprint = lines + "Hello! I'm Duke\n" + "What can I do for you?\n" + lines;
        System.out.print(toprint);
        String userInput = in.nextLine();
        botResponse(userInput);
    }


//    For unit testing purposes
//    public static int multiply(int i, int j) {
//        return i * j;
//    }

}
