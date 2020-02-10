import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;
        boolean isBye = false;
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        while (!isBye) {
            String description;
            String date;
            System.out.println();
            String string = sc.nextLine();
            System.out.println("    ____________________________________________________________");
            String[] stringSplit = string.split(" ");
            switch (stringSplit[0]) {
            case "list":
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("     " + (i + 1) + "." + tasks[i].toString());
                }
                break;
            case "done":
                int done = Integer.parseInt(stringSplit[1]) - 1;
                tasks[done].markAsDone();
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println("       " + tasks[done].toString());
                break;
            case "bye":
                System.out.println("    Bye. Hope to see you again soon!");
                isBye = true;
                break;
            case "todo":
                description = String.join(" ", Arrays.copyOfRange(stringSplit, 1, stringSplit.length));
                tasks[taskCount] = new Todo(description);
                System.out.println("     Got it. I've added this task: ");
                System.out.println("       " + tasks[taskCount].toString());
                taskCount++;
                System.out.println("     Now you have " + taskCount + " tasks in the list.");
                break;
            case "deadline":
                description = string.substring(0, string.indexOf(" /by")).replace("deadline ", "");
                date = string.substring(string.indexOf("/by ")).replace("/by ", "");
                tasks[taskCount] = new Deadline(description, date);
                System.out.println("     Got it. I've added this task: ");
                System.out.println("       " + tasks[taskCount].toString());
                taskCount++;
                System.out.println("     Now you have " + taskCount + " tasks in the list.");
                break;
            case "event":
                description = string.substring(0, string.indexOf(" /at")).replace("event ", "");
                date = string.substring(string.indexOf("/at ")).replace("/at ", "");
                tasks[taskCount] = new Event(description, date);
                System.out.println("     Got it. I've added this task: ");
                System.out.println("       " + tasks[taskCount].toString());
                taskCount++;
                System.out.println("     Now you have " + taskCount + " tasks in the list.");
                break;
            default: //add Task into List
                tasks[taskCount] = new Task(string);
                System.out.println("     added: " + tasks[taskCount].getDescription());
                taskCount++;
                break;
            }
            System.out.println("    ____________________________________________________________");
        }
    }
}
