import java.util.Scanner;

public class Duke {
    private static final String LOGO = "          __ __      _           __                          \n" +
            "     /\\  /_ /_ |    (_)         / _|                         \n" +
            "    /  \\  | || |_ __ _ ___  ___| |_ ___  _ __ _ __ ___   ___ \n" +
            "   / /\\ \\ | || | '__| / __|/ _ \\  _/ _ \\| '__| '_ ` _ \\ / _ \\\n" +
            "  / ____ \\| || | |  | \\__ \\  __/ || (_) | |  | | | | | |  __/\n" +
            " /_/    \\_\\_||_|_|  |_|___/\\___|_| \\___/|_|  |_| |_| |_|\\___|\n";
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String GREETING_WORD = "     Hello! I'm Duke\n     What can I do for you?";
    private static final String BYE_WORD = "     Bye. Hope to see you again soon!";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String LIST_TASKS_PROMPT = "     Here are the tasks in your list:";
    private static final String DONE_TASKS_PROMPT = "     Nice! I've marked this task as done:";
    private static final String LIST_SINGLE_TASK_MESSAGE = "     %d.[%s] %s\n";
    private static final String ADD_SINGLE_TASK_MESSAGE = "     added: %s\n";
    private static final String DONE_SINGLE_TASK_MESSAGE = "       [%s] %s\n";

    private static void greet() {
        System.out.println(LOGO);
        System.out.println(LINE_DIVIDER);
        System.out.println(GREETING_WORD);
        System.out.println(LINE_DIVIDER);
    }

    private static int processCommand(String command, Task[] tasks, int taskNum) {
        String[] commands = command.split(" ");
        switch (commands[0]) {
        case EXIT_COMMAND:
            bye();
            return taskNum;
        case LIST_COMMAND:
            listTasks(tasks, taskNum);
            return taskNum;
        case DONE_COMMAND:
            int taskID = Integer.parseInt(commands[1]);
            markAsDone(tasks, taskID);
            return taskNum;
        default:
            addTasks(command, tasks, taskNum);
            return taskNum + 1;
        }
    }

    private static void listTasks(Task[] tasks, int taskNum) {
        System.out.println(LINE_DIVIDER);
        System.out.println(LIST_TASKS_PROMPT);
        for (int i = 0; i < taskNum; ++i) {
            System.out.printf(LIST_SINGLE_TASK_MESSAGE, i, tasks[i].getStatusIcon(), tasks[i].getTaskDescription());
        }
        System.out.println(LINE_DIVIDER);
    }

    private static void addTasks(String command, Task[] tasks, int taskNum) {
        Task currentTask = new Task(command);
        tasks[taskNum] = currentTask;
        System.out.println(LINE_DIVIDER);
        System.out.printf(ADD_SINGLE_TASK_MESSAGE, command);
        System.out.println(LINE_DIVIDER);
    }

    private static void markAsDone(Task[] tasks, int taskID) {
        tasks[taskID].markAsDone();
        System.out.println(LINE_DIVIDER);
        System.out.println(DONE_TASKS_PROMPT);
        System.out.printf(DONE_SINGLE_TASK_MESSAGE, tasks[taskID].getStatusIcon(), tasks[taskID].getTaskDescription());
        System.out.println(LINE_DIVIDER);
    }
    
    private static void bye() {
        System.out.println(LINE_DIVIDER);
        System.out.println(BYE_WORD);
        System.out.println(LINE_DIVIDER);
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskNum = 0;

        greet();

        String command;
        Scanner s  = new Scanner(System.in);
        do {
            command = s.nextLine();
            taskNum = processCommand(command, tasks, taskNum);
        } while (!command.equals(EXIT_COMMAND));
    }
}
