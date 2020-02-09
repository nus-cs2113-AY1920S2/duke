import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int taskQty = 0;
    private static final String LINE = "____________________________________________________________ \n";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    public static void printList(Task[] list) {
        for (int i = 0; i < taskQty; i++) {
            System.out.print(String.valueOf(i+1) + ". ");
            System.out.println(list[i]);
        }
    }

    public static void main(String[] args) {

        System.out.println(LINE +
                        " Hello! I'm Duke :)\n" +
                        " What can I do for you?\n" + LINE);

        Scanner scanner = new Scanner(System.in);
        String inputString = "";

        while (!inputString.equals("bye bye")) {
            inputString = runCommands(scanner);
        }

        System.out.println(" Bye. Hope to see you again soon!\n" + LINE);
    }

    private static String runCommands(Scanner scanner) {
        String inputString;
        inputString = scanner.nextLine();
        String instruction[] = inputString.split(" ", 2);
        Task new_task;

        switch (instruction[0]) {
        case "list":
            printList(tasks);
            break;
        case (DONE):
            try {
                int index = Integer.parseInt((inputString.substring(5)));
                System.out.println(index);
                tasks[index-1].markAsDone();
                System.out.println(LINE + "  Yay! You have done: " + tasks[index-1].description + "\n" + LINE);
            } catch (NullPointerException e) {
                System.out.println("This task does not exist!");
            }
            break;
        case "todo":
            new_task = new Todo(inputString.substring(5));
            tasks[taskQty] = new_task;
            taskQty += 1;
            System.out.println( LINE +
                    "  Ok! I've added this task. \n" +
                    "  Now you have " + String.valueOf(taskQty) + " tasks on your list.\n " +
                    LINE);
            break;
        case (DEADLINE):
            int findSeparator = inputString.indexOf('/');
            new_task = new Deadline(inputString.substring(9, findSeparator-1), inputString.substring(findSeparator+4));
            tasks[taskQty] = new_task;
            taskQty++;
            System.out.println(" ____________________________________________________________\n" +
                    "  Ok! I've added this task. \n" +
                    "  Now you have " + String.valueOf(taskQty) + " tasks on your list.\n " +
                    "____________________________________________________________\n");
            break;
        case (EVENT):
            int findSeparator2 = inputString.indexOf('/');
            new_task = new Event(inputString.substring(6, findSeparator2-1), inputString.substring(findSeparator2+4));
            tasks[taskQty] = new_task;
            taskQty++;
            System.out.println(" ____________________________________________________________\n" +
                    "  Ok! I've added this task. \n" +
                    "  Now you have " + String.valueOf(taskQty) + " tasks on your list.\n " +
                    "____________________________________________________________\n");
            break;
        default:
            System.out.println("huh?");
            break;
        }
        return inputString;
    }
}
