import java.util.ArrayList;
import java.util.List;
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
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private static void greet() {
        System.out.println(LOGO);
        System.out.println(LINE_DIVIDER);
        System.out.println(GREETING_WORD);
        System.out.println(LINE_DIVIDER);
    }

    private static void processCommand(TaskManager TaskMgr, String command) {
        String[] commands = command.split(" ");
        String[] descriptions;
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
        case TODO_COMMAND:
            descriptions = splitCommands(commands, TODO_COMMAND);
            TaskMgr.addTasks(descriptions[0], TODO_COMMAND);
            break;
        case DEADLINE_COMMAND:
            descriptions = splitCommands(commands, DEADLINE_COMMAND);
            TaskMgr.addTasks(descriptions[0], descriptions[1], DEADLINE_COMMAND);
            break;
        case EVENT_COMMAND:
            descriptions = splitCommands(commands, EVENT_COMMAND);
            TaskMgr.addTasks(descriptions[0], descriptions[1], EVENT_COMMAND);
            break;
        default:
            // default will add to todo
            TaskMgr.addTasks(command);
        }
    }

    // I know this function sucks, but it is too late to think of a better one.
    private static String[] splitCommands(String[] commands, String type) {
        List<String> descriptionList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        int i = 1;
        while (i < commands.length && !commands[i].equals("/by") && !commands[i].equals("/at")) {
            descriptionList.add(commands[i++]);
        }
        ++i;
        while (i < commands.length) {
            timeList.add(commands[i++]);
        }
        String description = String.join(" ", descriptionList);
        String time = String.join(" ", timeList);
        String[] descriptions = new String[2];
        descriptions[0] = description;
        descriptions[1] = time;
        return descriptions;
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
