import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Scanner in = new Scanner(System.in);
        String input = null;

        // a array to store user input task
        List<Task> tasks = new ArrayList<>();

        do {
            input = in.nextLine();
            switch (input) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println(String.format("%d.[%s] %s", i + 1, task.getStatusIcon(), task.getDescription()));
                }
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            default:
                if (input.startsWith("done")) {
                    int taskNumber = Integer.parseInt(input.substring(4).trim());
                    Task task = tasks.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("[%s] %s", task.getStatusIcon(), task.getDescription()));
                    break;
                } else {
                    System.out.println("added: "+ input);
                    tasks.add(new Task(input));
                    break;
                }
            }
        } while (!input.equals("bye"));
    }
}
