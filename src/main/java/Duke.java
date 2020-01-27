import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String lines = "____________________________________________________________\n";
    private static Scanner in = new Scanner(System.in);

    private static void botResponse(String userInput) {
        ArrayList<Task> userList = new ArrayList<Task>();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.print(lines);
                if (userList.size() == 0) {
                    System.out.println("No items added!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                }
                for (int i=0; i<userList.size(); i++) {
                    Integer index = i+1;
                    System.out.println(index + ".[" + userList.get(i).getStatusIcon() + "] " + userList.get(i).getDescription());
                }
                System.out.print(lines);
            } else if (userInput.indexOf("done ") != -1) {
                System.out.print(lines);
                System.out.println("Nice! I've marked this task as done:");
                String[] words = userInput.split(" ");
                Integer doneIndex = Integer.parseInt((words[1]));
                doneIndex--;    // decrement doneIndex from one-based indexing to zero-based indexing to access ArrayList using it
                userList.get(doneIndex).setIsDone(true);
                System.out.println("[" + userList.get(doneIndex).getStatusIcon() + "] " + userList.get(doneIndex).getDescription());
                System.out.print(lines);
            }
            else {
                Task newTask = new Task(userInput);
                userList.add(newTask);
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
