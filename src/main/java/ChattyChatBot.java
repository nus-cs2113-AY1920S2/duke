import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChattyChatBot {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        String name = "Chatty Chat Bot";
        System.out.println("Hello from " + name);

        List<String> tasks = new ArrayList<>();

        while (true) {
            String userInput = SCANNER.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Thanks for chatting with me! Bye~");
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(userInput);
                System.out.println("added: " + userInput);
            }
        }
    }
}
