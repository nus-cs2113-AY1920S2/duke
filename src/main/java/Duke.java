import java.util.ArrayList;
import java.util.Scanner;

public class Duke implements Logo {
    public static void main(String[] args) {

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner temp = new Scanner(System.in);
        String cmd = temp.nextLine();

        ArrayList<Todo> todos = new ArrayList<Todo>();

        while (!cmd.equals("bye")) {
            lineBreak();

            if (cmd.equals("list")) {
                System.out.println("  Here are the tasks in your list:");
                for (int k = 1; k<=todos.size(); ++k) {
                    System.out.println("    " + k + ". " + todos.get(k-1));
                }
            }
            else if (cmd.contains("done")){
                int option = Integer.parseInt(cmd.substring(cmd.length()-1));
                todos.get(option-1).setDone();
            }
            else {
                String taskType = cmd.substring(0, cmd.indexOf(" "));
                String description;

                switch (taskType) {
                case "todo":
                    description = cmd.substring(cmd.indexOf(" ")+1);
                    todos.add(new Todo(description));
                    break;
                case "deadline":
                    description = cmd.substring(cmd.indexOf(" ")+1, cmd.indexOf("/")-1);
                    String by = cmd.substring(cmd.indexOf("/")+4);
                    todos.add(new Deadline(description, by));
                    break;
                case "event":
                    description = cmd.substring(cmd.indexOf(" ")+1, cmd.indexOf("/")-1);
                    String at = cmd.substring(cmd.indexOf("/")+4);
                    todos.add(new Event(description, at));
                    break;
                default:
                    System.out.println("Error");
                }
                System.out.println("  Got it. I've added this task:");
                System.out.println("     " + todos.get(todos.size()-1));
                System.out.println("  Now you have " + todos.size() + " tasks in the list.");
            }
            
            lineBreak();
            System.out.println();
            cmd = temp.nextLine();

        }
        lineBreak();
        System.out.println("  Bye. Hope to see you again soon!");
        lineBreak();
    }

    public final static void lineBreak() {
        System.out.println("  ____________________________________________________________");
    }
}
