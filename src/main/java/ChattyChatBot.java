import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChattyChatBot {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        String name = "Chatty Chat Bot";
        System.out.println("Hello from " + name);

        List<Task> tasks = new ArrayList<>();

        while (true) {
            String userInput = SCANNER.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Thanks for chatting with me! Bye~");
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + ". [" + task.getStatusIcon() + "] " + task.getDescription());
                }
            } else if (userInput.matches("done [0-9]+")) {
                int taskIdx = Integer.parseInt(userInput.split(" ")[1]);
                Task task = tasks.get(taskIdx - 1);
                task.markAsDone();
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
            } else {
                tasks.add(new Task(userInput));
                System.out.println("added: " + userInput);
            }
        }
    }
}
