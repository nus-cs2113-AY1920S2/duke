import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChattyChatBot {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        sendWelcomeMessage();

        List<Task> tasks = new ArrayList<>();

        String userInput = "";
        do {
            userInput = SCANNER.nextLine();

            System.out.println(Constants.LINE_BREAK);

            // Solution below adapted from: https://stackoverflow
            // .com/questions/5067942/what-is-the-best-way-to-extract-the-first-word-from-a-string-in-java
            String[] array = userInput.split(Constants.SPACE_SEPARATOR, 2);
            String action = array[0];

            switch (action) {
            case Constants.LIST_STRING:
                listAllTasks(tasks);
                break;
            case Constants.DONE_STRING:
                markTaskAsDone(tasks, array[1]);
                break;
            case Constants.TODO_STRING:
                addToDoTask(tasks, array[1]);
                break;
            case Constants.DEADLINE_STRING:
                addDeadlineTask(tasks, array[1]);
                break;
            case Constants.EVENT_STRING:
                addEventTask(tasks, array[1]);
                break;
            case Constants.BYE_STRING:
                sendByeMessage();
                break;
            default:
                sendDefaultResponse();
            }

            System.out.println(Constants.LINE_BREAK);
        } while (!userInput.equals(Constants.BYE_STRING));
    }

    private static void listAllTasks(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + Constants.DOT_CHARACTER + Constants.SPACE_SEPARATOR + task.toString());
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

        System.out.println(Constants.ADDED_TASK_CONFIRMATION);
        System.out.println(newToDoTask);
        System.out.println(Constants.TASK_SUMMARY_FIRST_HALF + tasks.size() + Constants.TASK_SUMMARY_SECOND_HALF);
    }

    private static void addDeadlineTask(List<Task> tasks, String inputStr) {
        String[] array = inputStr.split(Constants.BY_STRING);
        Deadline newDeadlineTask = new  Deadline(array[0], array[1]);
        tasks.add(newDeadlineTask);

        System.out.println(Constants.ADDED_TASK_CONFIRMATION);
        System.out.println(newDeadlineTask);
        System.out.println(Constants.TASK_SUMMARY_FIRST_HALF + tasks.size() + Constants.TASK_SUMMARY_SECOND_HALF);
    }

    private static void addEventTask(List<Task> tasks, String inputStr) {
        String[] array = inputStr.split(Constants.AT_STRING);
        Event newEventTask = new Event(array[0], array[1]);
        tasks.add(newEventTask);

        System.out.println(Constants.ADDED_TASK_CONFIRMATION);
        System.out.println(newEventTask);
        System.out.println(Constants.TASK_SUMMARY_FIRST_HALF + tasks.size() + Constants.TASK_SUMMARY_SECOND_HALF);
    }

    private static void sendDefaultResponse() {
        System.out.println("I'm " + Constants.BOT_NAME);
        System.out.println("How may I help you?");
    }

    private static void sendWelcomeMessage() {
        System.out.println(Constants.LINE_BREAK);
        System.out.println("Hello from " + Constants.BOT_NAME);
        System.out.println("Glad to be at your service!");
        System.out.println(Constants.LINE_BREAK);
    }

    private static void sendByeMessage() {
        System.out.println("Thanks for chatting with " + Constants.BOT_NAME);
        System.out.println("See you again soon!");
    }
}
