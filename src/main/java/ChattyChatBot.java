import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChattyChatBot {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String BOT_NAME = "Chatty Chat Bot";

    private static final String BYE_STRING = "bye";
    private static final String LIST_STRING = "list";
    private static final String DONE_STRING = "done";
    private static final String TODO_STRING = "todo";
    private static final String DEADLINE_STRING = "deadline";
    private static final String EVENT_STRING = "event";

    private static final String SPACE_SEPARATOR = " ";
    private static final String DOT_CHARACTER = ".";
    private static final String BY_STRING = "/by";
    private static final String AT_STRING = "/at";

    private static final String LINE_BREAK = "____________________________________________________________";

    private static final String ADDED_TASK_CONFIRMATION = "Got it. I've added this task:";
    private static final String TASK_SUMMARY_FIRST_HALF = "Now you have ";
    private static final String TASK_SUMMARY_SECOND_HALF = " tasks in the list.";

    public static void main(String[] args) {
        sendWelcomeMessage();

        List<Task> tasks = new ArrayList<>();

        String userInput = "";
        do {
            userInput = SCANNER.nextLine();

            System.out.println(LINE_BREAK);

            // Solution below adapted from: https://stackoverflow
            // .com/questions/5067942/what-is-the-best-way-to-extract-the-first-word-from-a-string-in-java
            String[] array = userInput.split(SPACE_SEPARATOR, 2);
            String action = array[0];

            switch (action) {
            case LIST_STRING:
                listAllTasks(tasks);
                break;
            case DONE_STRING:
                markTaskAsDone(tasks, array[1]);
                break;
            case TODO_STRING:
                addToDoTask(tasks, array[1]);
                break;
            case DEADLINE_STRING:
                addDeadlineTask(tasks, array[1]);
                break;
            case EVENT_STRING:
                addEventTask(tasks, array[1]);
                break;
            case BYE_STRING:
                sendByeMessage();
                break;
            default:
                sendDefaultResponse();
            }

            System.out.println(LINE_BREAK);
        } while (!userInput.equals(BYE_STRING));
    }

    private static void listAllTasks(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + DOT_CHARACTER + SPACE_SEPARATOR + task.toString());
        }
    }

    private static void markTaskAsDone(List<Task> tasks, String indexStr) {
        int taskIdx = Integer.parseInt(indexStr);
        Task task = tasks.get(taskIdx - 1);
        task.markAsDone();
        System.out.println(task.toString());
    }

    private static void addToDoTask(List<Task> tasks, String description) {
        ToDo newToDoTask = new ToDo(description);
        tasks.add(newToDoTask);

        System.out.println(ADDED_TASK_CONFIRMATION);
        System.out.println(newToDoTask);
        System.out.println(TASK_SUMMARY_FIRST_HALF + tasks.size() + TASK_SUMMARY_SECOND_HALF);
    }

    private static void addDeadlineTask(List<Task> tasks, String inputStr) {
        String[] array = inputStr.split(BY_STRING);
        Deadline newDeadlineTask = new  Deadline(array[0], array[1]);
        tasks.add(newDeadlineTask);

        System.out.println(ADDED_TASK_CONFIRMATION);
        System.out.println(newDeadlineTask);
        System.out.println(TASK_SUMMARY_FIRST_HALF + tasks.size() + TASK_SUMMARY_SECOND_HALF);
    }

    private static void addEventTask(List<Task> tasks, String inputStr) {
        String[] array = inputStr.split(AT_STRING);
        Event newEventTask = new Event(array[0], array[1]);
        tasks.add(newEventTask);

        System.out.println(ADDED_TASK_CONFIRMATION);
        System.out.println(newEventTask);
        System.out.println(TASK_SUMMARY_FIRST_HALF + tasks.size() + TASK_SUMMARY_SECOND_HALF);
    }

    private static void sendDefaultResponse() {
        System.out.println("I'm " + BOT_NAME);
        System.out.println("How may I help you?");
    }

    private static void sendWelcomeMessage() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello from " + BOT_NAME);
        System.out.println("Glad to be at your service!");
        System.out.println(LINE_BREAK);
    }

    private static void sendByeMessage() {
        System.out.println("Thanks for chatting with " + BOT_NAME);
        System.out.println("See you again soon!");
    }
}
