import java.util.Scanner;

public class Duke {
    public static final int TASK_LIMIT = 100;
    public static final String LINE_SPLITTING = "\t____________________________________________________________\n";
    public static final String BYE = "bye";
    public static final String BYE_MESSAGE = "\tBye. Hope to see you again soon!";
    public static final String HELLO_MESSAGE = "\tHello! I'm Duke\n";
    public static final String HELP_MESSAGE = "\tIt seems like you are needing some help.\n";
    public static final String DONE = "done";
    public static final String LIST = "list";
    public static final String TASK_MARKING_MESSAGE = "\tNice! I've marked this task as done:";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String TASK_LISTING = "\tHere are the tasks in your list:";
    public static final String ADDED_TASK_MESSAGE = "\tGot it. I've added this task:";
    public static final int TODO_WORD_LENGTH = 4;
    public static final int DEADLINE_WORD_LENGTH = 8;
    public static final int EVENT_WORD_LENGTH = 5;
    public static final String SPACE = " ";

    public static void printList(Task[] tasks) {
        System.out.println(TASK_LISTING);
        for(int i = 0; i < TASK_LIMIT; i ++) {
            if(tasks[i] == null) {
                break;
            }
            System.out.println("\t" + (i + 1) + "." + tasks[i]);
        }
    }


    public static void main(String[] args) {
        Task[] tasks = new Task[TASK_LIMIT];
        welcomeMessage();
        int listCount = 0;
        Scanner commandScanner = new Scanner(System.in);
        String command = commandScanner.nextLine();
        while(!command.equals(BYE)) {
            String[] commandSplitter = command.split(SPACE);
            System.out.print(LINE_SPLITTING);
            if(command.equals(LIST)) {
                printList(tasks);
            } else {
                String prefix = commandSplitter[0];
                switch (prefix) {
                case DONE:
                    markTaskMessage(tasks, commandSplitter[1]);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                default:
                    listCount = addTask(tasks, listCount, command, prefix);
                }
            }
            System.out.println(LINE_SPLITTING);
            command = commandScanner.nextLine();
        }
        System.out.print(LINE_SPLITTING);
        byeMessage();
    }

    private static int addTask(Task[] tasks, int listCount, String command, String prefix) {
        int splitIndex = command.indexOf("/");
        System.out.println(ADDED_TASK_MESSAGE);
        switch(prefix) {
        case TODO:
            String activity = command.substring(TODO_WORD_LENGTH + 1);
            tasks[listCount++] = new ToDo(activity);
            break;
        case DEADLINE:
            String activity2 = command.substring(DEADLINE_WORD_LENGTH + 1, splitIndex - 1);
            String date = command.substring(splitIndex + 4);
            tasks[listCount++] = new Deadline(activity2, date);
            break;
        case EVENT:
            String activity3 = command.substring(EVENT_WORD_LENGTH + 1, splitIndex - 1);
            String time = command.substring(splitIndex + 4);
            tasks[listCount++] = new Event(activity3, time);
            break;
        default:
            tasks[listCount++] = new Task(command);
        }
        String printTask = "\t" + tasks[listCount - 1];
        System.out.println(printTask);
        String printTaskCount = "\tNow you have " + listCount + " tasks in the list.";
        System.out.println(printTaskCount);
        return listCount;
    }

    private static void markTaskMessage(Task[] tasks, String word) {
        System.out.println(TASK_MARKING_MESSAGE);
        int index = Integer.parseInt(word);
        tasks[index - 1].markAsDone();
        System.out.printf("\t%s\n", tasks[index - 1]);
    }

    private static void byeMessage() {
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE_SPLITTING);
    }

    private static void welcomeMessage() {
        String greeting = LINE_SPLITTING + HELLO_MESSAGE + HELP_MESSAGE + LINE_SPLITTING;
        System.out.println(greeting);
    }
}
