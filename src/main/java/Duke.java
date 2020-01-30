import java.util.Scanner;

public class Duke {
    private static final String logo = "          __ __      _           __                          \n" +
            "     /\\  /_ /_ |    (_)         / _|                         \n" +
            "    /  \\  | || |_ __ _ ___  ___| |_ ___  _ __ _ __ ___   ___ \n" +
            "   / /\\ \\ | || | '__| / __|/ _ \\  _/ _ \\| '__| '_ ` _ \\ / _ \\\n" +
            "  / ____ \\| || | |  | \\__ \\  __/ || (_) | |  | | | | | |  __/\n" +
            " /_/    \\_\\_||_|_|  |_|___/\\___|_| \\___/|_|  |_| |_| |_|\\___|\n";
    private static final String lineDivider = "    ____________________________________________________________";
    private static final String indentation = "     ";
    private static final String greetingWords = "     Hello! I'm Duke\n     What can I do for you?";
    private static final String byeWords = "     Bye. Hope to see you again soon!";

    private static void greet() {
        System.out.println(logo);
        System.out.println(lineDivider);
        System.out.println(greetingWords);
        System.out.println(lineDivider);
    }

    private static int processCommand(String command, Task[] tasks, int taskNum) {
        String[] commands = command.split(" ");
        switch (commands[0]) {
        case "bye":
            bye();
            return taskNum;
        case "list":
            listTasks(tasks, taskNum);
            return taskNum;
        case "done":
            int taskID = Integer.parseInt(commands[1]);
            markAsDone(tasks, taskID);
            return taskNum;
        default:
            addTasks(command, tasks, taskNum);
            return taskNum + 1;
        }
    }

    private static void listTasks(Task[] tasks, int taskNum) {
        System.out.println(lineDivider);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskNum; ++i) {
            System.out.printf("     %d.[%s] %s\n", i, tasks[i].getStatusIcon(), tasks[i].getTaskDescription());
        }
        System.out.println(lineDivider);
    }

    private static void addTasks(String command, Task[] tasks, int taskNum) {
        Task currentTask = new Task(command);
        tasks[taskNum] = currentTask;
        System.out.println(lineDivider);
        System.out.printf("     added: %s\n", command);
        System.out.println(lineDivider);
    }

    private static void markAsDone(Task[] tasks, int taskID) {
        tasks[taskID].markAsDone();
        System.out.println(lineDivider);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.printf("       [%s] %s\n", tasks[taskID].getStatusIcon(), tasks[taskID].getTaskDescription());
        System.out.println(lineDivider);
    }
    
    private static void bye() {
        System.out.println(lineDivider);
        System.out.println(byeWords);
        System.out.println(lineDivider);
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
        } while (!command.equals("bye"));
    }
}
