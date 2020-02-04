import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private static void addTask(String command, String description) {
        Task task = null;
        String taskDescription = null;
        switch (command) {
        case TODO_COMMAND:
            taskDescription = description.trim();
            task = new Todo(taskDescription);
            break;
        case DEADLINE_COMMAND:
            String[] taskBy = description.split(DEADLINE_MARKER);
            taskDescription = taskBy[0].trim();
            String by = taskBy[1].trim();
            task = new Deadline(taskDescription, by);
            break;
        case EVENT_COMMAND:
            String[] taskAt = description.split(EVENT_MARKER);
            taskDescription = taskAt[0].trim();
            String at = taskAt[1].trim();
            task = new Event(taskDescription, at);
        }

        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    private static void markAsDone(int taskNumber) {
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
        displayWelcomeMessage();
        
        Scanner in = new Scanner(System.in);
        String input = null;

        do {
            input = in.nextLine();
            String[] split = input.split("\\s+", 2); // limit: the number of segments after split
            String command = split[0];
            
            switch (command) {
            case LIST_COMMAND:
                listTasks();
                break;
            case DONE_COMMAND:
                markAsDone(Integer.parseInt(split[1].trim()));
                break;
            case BYE_COMMAND:
                displayExitMessage();
                break;
            default:
                addTask(command, split[1]);
                break;
            }
        } while (!input.equals(BYE_COMMAND));
    }    

    private static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    private static void displayExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
