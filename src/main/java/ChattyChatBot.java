import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChattyChatBot {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String BOT_NAME = "Chatty Chat Bot";

    private static final String BYE_STRING = "bye";
    private static final String LIST_STRING = "list";
    private static final String DONE_STRING_REGEX = "done [0-9]+";
    private static final String ADDED_PREFIX = "added: ";

    private static final String SPACE_SEPARATOR = " ";
    private static final String LEFT_SQUARE_BRACKET = "[";
    private static final String RIGHT_SQUARE_BRACKET = "]";
    private static final String DOT_CHARACTER = ".";

    public static void main(String[] args) {
        sendWelcomeMessage();

        List<Task> tasks = new ArrayList<>();

        while (true) {
            String userInput = SCANNER.nextLine();
            if (userInput.equals(BYE_STRING)) {
                sendByeMessage();
                break;
            } else if (userInput.equals(LIST_STRING)) {
                listAllTasks(tasks);
            } else if (userInput.matches(DONE_STRING_REGEX)) {
                markTaskAsDone(tasks, userInput);
            } else {
                addTask(tasks, userInput);
            }
        }
    }

    private static void listAllTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + DOT_CHARACTER + SPACE_SEPARATOR + LEFT_SQUARE_BRACKET + task.getStatusIcon() +
                    RIGHT_SQUARE_BRACKET + SPACE_SEPARATOR + task.getDescription());
        }
    }

    private static void markTaskAsDone(List<Task> tasks, String userInput) {
        int taskIdx = Integer.parseInt(userInput.split(SPACE_SEPARATOR)[1]);
        Task task = tasks.get(taskIdx - 1);
        task.markAsDone();
        System.out.println(LEFT_SQUARE_BRACKET + task.getStatusIcon() + RIGHT_SQUARE_BRACKET + SPACE_SEPARATOR +
                task.getDescription());
    }

    private static void addTask(List<Task> tasks, String userInput) {
        tasks.add(new Task(userInput));
        System.out.println(ADDED_PREFIX + userInput);
    }

    private static void sendWelcomeMessage() {
        System.out.println("Hello from " + BOT_NAME);
        System.out.println("Glad to be at your service!");
    }

    private static void sendByeMessage() {
        System.out.println("Thanks for chatting with " + BOT_NAME);
        System.out.println("See you again soon!");
    }
}
