import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS_COUNT = 100;
    private static Task[] tasks = new Task[MAX_TASKS_COUNT];
    private static int indexOfTasks = 0;

    public static void main(String[] args) {
        String name = "Duke";
        String input;
        Scanner reader = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);

        printLine();
        welcome(name);
        printLine();

        do {
            input = reader.nextLine();
            printLine();
            if (input.equals("bye")) {
                // close the interpreter
                System.out.println("\tBye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                // list all tasks
                listTasks();
            } else if (input.split(" ")[0].equals("done")) {
                // mark a task as done
                System.out.println("\tNice! I've marked this task as done:");
                int indexOfTasks = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[indexOfTasks].setDone(true);
                System.out.println("\t" + tasks[indexOfTasks]);
            } else {
                // add stuff to String[] tasks
                addTask(input);
            }
            printLine();
        } while (!input.equals("bye"));
    }

    public static void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void welcome(String name) {
        System.out.println("\tHello! I'm " + name);
        System.out.println("\tWhat can I do for you?");
    }

    public static void addTask(String taskDescription) {
        if (indexOfTasks < MAX_TASKS_COUNT) {
            tasks[indexOfTasks] = new Task(taskDescription);
            indexOfTasks++;
            System.out.println("\tadded: " + taskDescription);
        }
    }

    public static void listTasks() {
        for (int i = 0; i < indexOfTasks; i++) {
            System.out.printf("\t%d. %s", i+1, tasks[i]);
            System.out.println();
        }
    }
}
