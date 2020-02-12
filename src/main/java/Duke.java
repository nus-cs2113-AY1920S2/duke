import java.io.IOException;
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
    public static final String EMPTY_STRING = "";
    public static final String EMPTY_INPUT_MESSAGE = "\t☹ OOPS!!! Input cannot be empty";
    public static final String DUMMY_INPUT_MESSAGE = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String LIMIT_REACHED_MESSAGE = "\t☹ OOPS!!! Task limit has reached";
    public static final String WRONG_NUMBER_FORMAT_MESSAGE = "\t☹ OOPS!!! The task's index cannot be a float number";
    public static final String OUT_OF_BOUND_MESSAGE = "\t☹ OOPS!!! The task's index is out of bound or does not exist";

    public static void printList(Task[] tasks) {
        System.out.println(TASK_LISTING);
        for(int i = 0; i < TASK_LIMIT; i ++) {
            if(tasks[i] == null) {
                break;
            }
            System.out.println("\t" + (i + 1) + "." + tasks[i]);
        }
    }


    public static void main(String[] args) throws IOException, NullPointerException{
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
                case EMPTY_STRING:
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
        byeMessage();
    }

    private static int addTask(Task[] tasks, int listCount, String command, String prefix) {
        try {
            int splitIndex = command.indexOf("/");
            switch (prefix) {
            case EMPTY_STRING:
                throw new NullPointerException();
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
                throw new IOException();
            }
            System.out.println(ADDED_TASK_MESSAGE);
            String printTask = "\t" + tasks[listCount - 1];
            System.out.println(printTask);
            String printTaskCount = "\tNow you have " + listCount + " tasks in the list.";
            System.out.println(printTaskCount);
        }
        catch (NullPointerException e) {
            System.out.println(EMPTY_INPUT_MESSAGE);
        }
        catch (StringIndexOutOfBoundsException e) {
            String invalidFormatMessage = "\t☹ OOPS!!! The description of a " + prefix + " cannot be empty or" +
                    " it is in the wrong format.";
            System.out.println(invalidFormatMessage);
        }
        catch (IOException e) {
            System.out.println(DUMMY_INPUT_MESSAGE);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LIMIT_REACHED_MESSAGE);
        }
        return listCount;
    }

    private static void markTaskMessage(Task[] tasks, String word) {
        try {
            int index = Integer.parseInt(word);
            tasks[index - 1].markAsDone();
            System.out.println(TASK_MARKING_MESSAGE);
            String printTask = "\t%s\n";
            System.out.printf(printTask, tasks[index - 1]);
        }
        catch (NumberFormatException e) {
            System.out.println(WRONG_NUMBER_FORMAT_MESSAGE);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(OUT_OF_BOUND_MESSAGE);
        }
        catch (NullPointerException e) {
            System.out.println(OUT_OF_BOUND_MESSAGE);
        }
    }

    private static void byeMessage() {
        System.out.print(LINE_SPLITTING);
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE_SPLITTING);
    }

    private static void welcomeMessage() {
        String greeting = LINE_SPLITTING + HELLO_MESSAGE + HELP_MESSAGE + LINE_SPLITTING;
        System.out.println(greeting);
    }
}
