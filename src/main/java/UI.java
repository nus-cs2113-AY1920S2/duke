import Features.Task;
import java.util.ArrayList;

public class UI {
    private static String lines = "____________________________________________________________\n";
    public static void printAddedInfo(ArrayList<Task> userList) {
        System.out.println("Got it . I've added this task:");
        System.out.print("  ");
        System.out.println(userList.get(userList.size()-1));
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
    }

    public  void intro() {
        String toPrint = lines + "Hello! I'm Duke\n" + "What can I do for you?\n" + lines;
        System.out.print(toPrint);
    }
    public static void printLines() {
        System.out.println(lines);
    }
    public  void exit() {
        System.out.print(lines + "Bye. Hope to see you again soon!\n" + lines);
    }
}
