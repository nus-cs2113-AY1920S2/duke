import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS_COUNT = 100;
    private static Task[] tasks = new Task[MAX_TASKS_COUNT];
    private static int indexOfTasks = 0;
    private static String[] command = new String[2];

    public static void main(String[] args) {
        String name = "Duke";
        welcome(name);

        String input;
        Scanner reader = new Scanner(System.in);

        do {
            input = reader.nextLine();
            command = inputProcessing(input);
            printLine();

            switch (command[0]) {
            case "bye":
                // close the interpreter
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            case "list":
                // list all tasks
                listTasks();
                break;
            case "todo":
            case "deadline":
            case "event":
                // add stuff to String[] tasks
                addTask(command[0], command[1]);
                break;
            case "done":
                // mark a task as done
                System.out.println("\tNice! I've marked this task as done:");
                int indexOfTasks = Integer.parseInt(command[1]) - 1;
                tasks[indexOfTasks].setDone(true);
                System.out.println("\t" + tasks[indexOfTasks]);
                break;
            default:
                System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            printLine();
        } while (!command[0].equals("bye"));
    }

    /**
     * Split first word from the rest of String.
     * @param input: one-line string
     * @return array of 2 String, [0]: first word, [1]: subsequent words.
     */
    private static String[] inputProcessing(String input) {
        String[] output = new String[2];

        input = input.trim();   // strip leading & trailing spaces
        int startIndexOfDescription = input.indexOf(' ');

        if (startIndexOfDescription == -1) {
            // only has 1 word
            output[0] = input.toLowerCase();
        } else {
            // has 2 words
            String cmdType = input.substring(0, startIndexOfDescription).toLowerCase();
            String cmdDescription = input.substring(startIndexOfDescription + 1);
            output[0] = cmdType;
            output[1] = cmdDescription;
        }

        return output;
    }

    public static void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void welcome(String name) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);

        printLine();
        System.out.println("\tHello! I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    public static void addTask(String type, String description) {
        if (indexOfTasks >= MAX_TASKS_COUNT) {
            // exit if task count has exceeded
            return;
        }

        if (type.equals("todo")) {
            tasks[indexOfTasks] = new ToDo(description);
        } else if (type.equals("deadline")) {
            String[] cmdArgs = new String[2];
            cmdArgs = processArgs(description);
            tasks[indexOfTasks] = new Deadline(cmdArgs[0], cmdArgs[1]);
        } else if (type.equals("event")) {
            String[] cmdArgs = new String[2];
            cmdArgs = processArgs(description);
            tasks[indexOfTasks] = new Event(cmdArgs[0], cmdArgs[1]);
        }

        Task.incrementTaskCount();

        // print status message
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tasks[indexOfTasks]);
        printTaskCount();

        indexOfTasks++;
    }

    public static void printTaskCount() {
        System.out.printf("\tNow you have %d tasks in the list.%s",
                Task.getTaskCount(), System.lineSeparator());
    }

    public static void listTasks() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < indexOfTasks; i++) {
            System.out.printf("\t%d.%s", i + 1, tasks[i]);
            System.out.println();
        }
    }

    private static String[] processArgs(String s) {
        String[] tokens = s.split("/");
        tokens[0] = tokens[0].trim();
        tokens[1] = tokens[1].substring(tokens[1].indexOf(' ') + 1);
        return tokens;
    }
}
