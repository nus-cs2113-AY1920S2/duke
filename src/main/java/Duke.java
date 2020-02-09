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
        case (LIST):
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

        case (TODO):
            try {
                new_task = new Todo(instruction[1]);
                tasks[taskQty] = new_task;
                taskQty += 1;
                printAddMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no task specified!");
            }
            break;

        case (DEADLINE):
            try {
                int findSeparator = instruction[1].indexOf('/');
                new_task = new Deadline(instruction[1].substring(0, findSeparator), instruction[1].substring(findSeparator + 1));
                tasks[taskQty] = new_task;
                taskQty++;
                printAddMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no task specified!");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please input task in the format: deadline task_name/deadline");
            } catch (DukeException e) {
                System.out.println("Task description or deadline field is empty.");
            }
            break;

        case (EVENT):
            try {
                int findSeparator = instruction[1].indexOf('/');
                new_task = new Event(instruction[1].substring(0, findSeparator), instruction[1].substring(findSeparator + 1));
                tasks[taskQty] = new_task;
                taskQty++;
                printAddMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no task specified!");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please input task in the format: event task_name/event_date");
            } catch (DukeException e) {
                System.out.println("Task description or event date field is empty.");
            }
            break;

        default:
            System.out.println("huh? I do not understand :(");
            break;
        }
        return inputString;
    }

    private static void printAddMessage() {
        System.out.println(LINE +
                "  Ok! I've added this task. \n" +
                "  Now you have " + String.valueOf(taskQty) + " tasks on your list.\n " +
                LINE);
    }
}
