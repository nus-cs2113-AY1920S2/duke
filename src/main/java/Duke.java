import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;

public class Duke {
    private static final String LIST_COMMAND = "list";
    private static final String BYE_COMMAND = "bye";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_MARKER = "/by";
    private static final String EVENT_MARKER = "/at";

    // a array to store user input task
    private static List<Task> tasks = new ArrayList<>();

    private static void addTask(String input) {
        Task task = null;
        String description = null;
        if (input.startsWith(TODO_COMMAND)) {
            description = input.substring(TODO_COMMAND.length()).trim();
            task = new Todo(description);
        } else if(input.startsWith(DEADLINE_COMMAND)) {
            int byPosition = input.indexOf(DEADLINE_MARKER);
            description = input.substring(DEADLINE_COMMAND.length(), byPosition).trim();
            String by = input.substring(byPosition + DEADLINE_MARKER.length()).trim();
            task = new Deadline(description, by);
        } else if (input.startsWith(EVENT_COMMAND)) { // Event
            int atPosition = input.indexOf(EVENT_MARKER);
            description = input.substring(EVENT_COMMAND.length(), atPosition).trim();
            String at = input.substring(atPosition + EVENT_MARKER.length()).trim();
            task = new Event(description, at);
        }

        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    private static void markAsDone(String input) {
        int taskNumber = Integer.parseInt(input.substring(4).trim());
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        System.out.println(task);
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.print(String.format("%d.", i + 1));
            System.out.println(task);
        }
    }

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

        do {
            input = in.nextLine();
            switch (input) {
            case LIST_COMMAND:
                listTasks();
                break;
            case BYE_COMMAND:
                System.out.println("Bye. Hope to see you again soon!");
                break;
            default:
                if (input.startsWith(DONE_COMMAND)) {
                    markAsDone(input);
                    break;
                } else {
                    addTask(input);
                    break;
                }
            }
        } while (!input.equals(BYE_COMMAND));
    }
}
