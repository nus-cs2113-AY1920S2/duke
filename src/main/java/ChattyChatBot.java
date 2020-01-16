import java.util.Scanner;

public class ChattyChatBot {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        String name = "Chatty Chat Bot";
        System.out.println("Hello from " + name);

        while (true) {
            String userInput = SCANNER.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Thanks for chatting with me! Bye~");
                break;
            }
            System.out.println(userInput);
        }
    }
}
