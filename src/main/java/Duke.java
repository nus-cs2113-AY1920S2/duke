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
        System.out.println("Initializing LumiChat v0.0.1.1...\n\n" +
                "LumiChat is now ready.\n\n " +
                "Hey, I'm Lumi!\n How may I assist you today?\n");

        readInput();

        System.out.println("Bye! See you again soon!");
    }

    public static void main(String[] args) {
        String logo =
                "  __       _______  _______  ________  _______  _______  _______  ________\n" +
                " |\\_\\     |\\___\\__\\|\\ __\\__\\|\\ ______\\|\\______\\|\\___\\__\\|\\______\\|\\ ______\\\n" +
                " | | |    | |  |  || |  |  | \\|__   _|| |  ___|| |  |  || |     | \\|__   _|\n" +
                " | | |    | |  |  || |  |  |   | | |  | | |    | |  |  || |  |  |   | | |\n" +
                " | | |    | |  |  || |     |   | | |  | | |    | |     || |     |   | | |\n" +
                " | | |__  | |  |  || | | | | __| | |_ | | |___ | |  |  || |     |   | | |\n" +
                " | | |__\\ | |     || | | | ||\\__\\| |_\\| | |___\\| |  |  || |  |  |   | | |\n" +
                "  \\|_____| \\|_____| \\|_|_|_| \\|______| \\|_____| \\|__|__| \\|__|__|    \\|_|\n";
        System.out.println("Welcome to " + logo);

        Duke chatBot = new Duke();
        chatBot.runChat();
    }
}
