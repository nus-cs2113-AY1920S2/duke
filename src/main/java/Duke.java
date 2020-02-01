import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static String lines = "____________________________________________________________\n";
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Task> userList = new ArrayList<Task>();

    private static void listCommand() {
        System.out.print(lines);
        if (userList.size() == 0) {
            System.out.println("No items added!");
        } else {
            System.out.println("Here are the tasks in your list:");
        }
        for (int i=0; i<userList.size(); i++) {
            Integer index = i+1;
            System.out.print(index + ".");
            System.out.println(userList.get(i));
        }
        System.out.print(lines);
    }

    //TODO edge case: user inputs done followed by a non integer
    //TODO edge case: user inputs done followed by an out of range integer
    private static void doneCommand(String userInput) {
        System.out.print(lines);
        System.out.println("Nice! I've marked this task as done:");
        String[] words = userInput.split(" ");
        Integer doneIndex = Integer.parseInt((words[1]));
        doneIndex--;    // decrement doneIndex from one-based indexing to zero-based indexing to access ArrayList using it
        userList.get(doneIndex).setIsDone(true);
        System.out.println(userList.get(doneIndex));
        System.out.print(lines);
    }

    private static Todo newTodo(String userInput) {
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        words.remove(0);
        return new Todo (String.join(" ", words));
    }
    private static Event newEvent(String userInput) {
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        words.remove(0);
        String description = "";
        String at = "";
        int atIndex = -1;
        for (int i=0; i<words.size(); i++) {
            if (words.get(i).equals("/at")) {
                atIndex = i;
            }
        }
        for (int i=0; i<atIndex; i++) {
            description += words.get(i);
            description += " ";
        }
        description = description.substring(0, description.length()-1);
        for (int i=atIndex+1; i < words.size(); i++) {
            at += words.get(i);
            at += " ";
        }
        at = at.substring(0, at.length()-1);
        return new Event(description, at);
    }
    private static Deadline newDeadline(String userInput) {
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        words.remove(0);
        String description = "";
        String by = "";
        int byIndex = -1;
        for (int i=0; i<words.size(); i++) {
            if (words.get(i).equals("/by")) {
                byIndex = i;
            }
        }
        for (int i=0; i<byIndex; i++) {
            description += words.get(i);
            description += " ";
        }
        description = description.substring(0, description.length()-1);
        for (int i=byIndex+1; i < words.size(); i++) {
            by += words.get(i);
            by += " ";
        }
        by = by.substring(0, by.length()-1);
        return new Deadline(description, by);
    }

    //TODO edge case: fix what to print when user input doesn't contain /by or /at during deadline/event command
    private static void addCommand(String userInput, String taskType) {
        if (taskType == "todo") {
            userList.add(newTodo(userInput));
        } else if (taskType == "event") {
            userList.add(newEvent(userInput));
        } else if (taskType == "deadline") {
            userList.add(newDeadline(userInput));
        }
        printAdded();
    }

    private static void printAdded() {
        System.out.print(lines);
        System.out.println("Got it . I've added this task:");
        System.out.print("  ");
        System.out.println(userList.get(userList.size()-1));
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
        System.out.print(lines);
    }

    private static boolean containCommand(String userInput, String command) {
        int myIntFlag = userInput.indexOf(command);
        return myIntFlag == 0; // returns true only if first index == command
    }
    //Main bot control
    private static void botResponse(String userInput) {
        while (!containCommand(userInput, "bye")) {          // if user command is not bye, go inside loop
            if (containCommand(userInput, "list")) {
                listCommand();
            } else if (containCommand(userInput, "done ")) {
                doneCommand(userInput);
            } else if (containCommand(userInput, "todo")){
                addCommand(userInput, "todo");
            } else if (containCommand(userInput, "event")) {
                addCommand(userInput, "event");
            } else if (containCommand(userInput, "deadline")) {
                addCommand(userInput, "deadline");
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
