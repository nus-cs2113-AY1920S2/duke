import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS_COUNT = 100;
    private static Task[] tasks = new Task[MAX_TASKS_COUNT];
    private static int indexOfTasks = 0;

    public static void main(String[] args) {
        String name = "Duke";
        welcome(name);

        String input;
        Scanner reader = new Scanner(System.in);

        do {
            input = reader.nextLine();
            input = initialInputProcessing(input);
            printLine();
            if (input.equals("bye")) {
                // close the interpreter
                System.out.println("\tBye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                // list all tasks
                listTasks();
            } else {
                // input involves 2 phrases
                int startIndexOfInput = input.indexOf(' ');
                String cmdType = input.substring(0, startIndexOfInput).toLowerCase();
                String cmdDescription = input.substring(startIndexOfInput + 1);

                if (cmdType.equals("done")) {
                    // mark a task as done
                    System.out.println("\tNice! I've marked this task as done:");
                    int indexOfTasks = Integer.parseInt(cmdDescription) - 1;
                    tasks[indexOfTasks].setDone(true);
                    System.out.println("\t" + tasks[indexOfTasks]);
                } else {
                    // add stuff to String[] tasks
                    addTask(cmdType, cmdDescription);
                }
            }
            printLine();
        } while (!input.equals("bye"));
    }

    private static String initialInputProcessing(String input) {
        input = input.trim();   // strip leading & trailing spaces
        return input;
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
