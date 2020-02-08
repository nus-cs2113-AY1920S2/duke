import javax.sound.midi.SysexMessage;
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

    private static void doneCommand(String userInput) throws DukeException {
        System.out.print(lines);
        String[] words = userInput.split(" ");
        if (words.length != 2) {
            throw new DukeException("Format error, please follow: \"done <task number>\"");
        }
        try {
            Integer doneIndex = Integer.parseInt((words[1]));
            doneIndex--;    // decrement doneIndex from one-based indexing to zero-based indexing to access ArrayList using it
            userList.get(doneIndex).setIsDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(userList.get(doneIndex));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!! Task number " + words[1] + " is not within your list. type \"list\" for more info.");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!! " + words[1] + " is not a valid task number");
        }
        System.out.print(lines);
    }

    private static Todo newTodo(String userInput) throws DukeException {
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        words.remove(0);
        String task = String.join(" ", words);
        if (task.length() == 0) {
            throw new DukeException("Todo task name cannot be empty.");
        }
        return new Todo (task);
    }
    private static Event newEvent(String userInput) throws DukeException {
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
        if (atIndex == -1) {
            throw new DukeException("Format error, please follow: event <task> /at <time>");
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
    private static Deadline newDeadline(String userInput) throws DukeException {
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
        if (byIndex == -1) {
            throw new DukeException("Format error, please follow: deadline <task> /by <time>");
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

    private static void addCommand(String userInput, String taskType) {
        System.out.print(lines);
        try {
            switch (taskType) {
                case "todo":
                    userList.add(newTodo(userInput));
                    break;
                case "event":
                    userList.add(newEvent(userInput));
                    break;
                case "deadline":
                    userList.add(newDeadline(userInput));
                    break;
            }
            printAddedInfo();
        } catch (DukeException e) {
            System.out.println("OOPS!! " + e);
        }
        System.out.print(lines);
    }

    private static void printAddedInfo() {
        System.out.println("Got it . I've added this task:");
        System.out.print("  ");
        System.out.println(userList.get(userList.size()-1));
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
    }

    //Main bot control
    private static void botResponse(String keyword, String userInput) throws DukeException {
            switch (keyword) {
                case "list":
                    listCommand();
                    break;
                case "done":
                    doneCommand(userInput);
                    break;
                case "todo":
                    addCommand(userInput, "todo");
                    break;
                case "event":
                    addCommand(userInput, "event");
                    break;
                case "deadline":
                    addCommand(userInput, "deadline");
                    break;
                default:
                    throw new DukeException("Unknown command.");
            }
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        String toPrint = lines + "Hello! I'm Duke\n" + "What can I do for you?\n" + lines;
        System.out.print(toPrint);

        String userInput = in.nextLine();
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        String keyword = words.get(0);

        while (!keyword.equals("bye")) {          // if user command is not bye, go inside loop
            try {
                botResponse(keyword, userInput);
            } catch (DukeException e) {
                System.out.print(lines);
                System.out.println("OOPS!! " + e);
                System.out.print(lines);
            } finally {
                userInput = in.nextLine();
                words = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
                keyword = words.get(0);
            }
        }
        System.out.print(lines + "Bye. Hope to see you again soon!\n" + lines);
    }


//    For unit testing purposes
//    public static int multiply(int i, int j) {
//        return i * j;
//    }

}
