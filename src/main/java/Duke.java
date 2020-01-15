import java.util.Scanner;

public class Duke {

    private void echoInput(String input) {
        System.out.println(input + "\n");
    }

    private void readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.toLowerCase().equals("bye")) {
            return;
        } else {
            echoInput(input);
            readInput();
        }
    }

    private void runChat() {
        System.out.println("Initializing Asterisk v0.0.1.1...\n\n" +
                "Asterisk ChatBot is now ready.\n\n " +
                "Hey, I'm Asterisk!\n How may I assist you today?\n");

        readInput();

        System.out.println("Bye! See you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke chatBot = new Duke();
        chatBot.runChat();
    }
}
