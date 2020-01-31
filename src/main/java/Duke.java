import java.util.Scanner;

public class Duke {
    private static final String LOGO = "          __ __      _           __                          \n" +
            "     /\\  /_ /_ |    (_)         / _|                         \n" +
            "    /  \\  | || |_ __ _ ___  ___| |_ ___  _ __ _ __ ___   ___ \n" +
            "   / /\\ \\ | || | '__| / __|/ _ \\  _/ _ \\| '__| '_ ` _ \\ / _ \\\n" +
            "  / ____ \\| || | |  | \\__ \\  __/ || (_) | |  | | | | | |  __/\n" +
            " /_/    \\_\\_||_|_|  |_|___/\\___|_| \\___/|_|  |_| |_| |_|\\___|\n";
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String GREETING_WORD = "     Hello! I'm A11riseforme\n     What can I do for you?";
    private static final String BYE_WORD = "     Bye. Hope to see you again soon!";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";

    private static void greet() {
        System.out.println(LOGO);
        System.out.println(LINE_DIVIDER);
        System.out.println(GREETING_WORD);
        System.out.println(LINE_DIVIDER);
    }

    private static void processCommand(TaskManager TaskMgr, String command) {
        String[] commands = command.split(" ");
        switch (commands[0]) {
        case EXIT_COMMAND:
            bye();
            break;
        case LIST_COMMAND:
            TaskMgr.listTasks();
            break;
        case DONE_COMMAND:
            int taskID = Integer.parseInt(commands[1]);
            TaskMgr.markAsDone(taskID);
            break;
        default:
            TaskMgr.addTasks(command);
        }
    }

    private static void bye() {
        System.out.println(LINE_DIVIDER);
        System.out.println(BYE_WORD);
        System.out.println(LINE_DIVIDER);
    }

    public static void main(String[] args) {
        TaskManager TaskMgr = new TaskManager(100);

        greet();

        String command;
        Scanner s  = new Scanner(System.in);
        do {
            command = s.nextLine();
            processCommand(TaskMgr, command);
        } while (!command.equals(EXIT_COMMAND));
    }
}
