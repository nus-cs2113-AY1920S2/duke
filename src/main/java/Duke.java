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
    public static final String ADDED_MESSAGE = "\tadded: ";

    public static void printList(Task[] tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < tasks.length; i ++) {
            if(tasks[i] == null) {
                break;
            }
            System.out.println("\t" + (i + 1) + tasks[i]);
        }
    }


    public static void main(String[] args) {
        Task[] tasks = new Task[TASK_LIMIT];
        welcomeMessage();
        int listCount = 0;
        while(true) {
            Scanner commandScanner = new Scanner(System.in);
            String command = commandScanner.nextLine();
            String[] words = command.split(" ");
            System.out.print(LINE_SPLITTING);
            if(command.equals(BYE)) {
                byeMessage();
                break;
            }
            else if(command.equals(LIST)) {
                printList(tasks);
            }
            else if(words.length == 2 && words[0].equals(DONE)) {
                markTaskMessage(tasks, words[1]);
            }
            else {
                System.out.println(ADDED_MESSAGE + command);
                tasks[listCount++] = new Task(command);
            }
            System.out.println(LINE_SPLITTING);
        }
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
