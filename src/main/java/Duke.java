import java.util.Scanner;
import java.util.Vector;

public class Duke {

    private static Vector list = new Vector();

    private void printList() {
        System.out.println("Sure! Lumi shall print out your list!\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
        System.out.println();
    }

    private void addToList(String input) {
        list.add(input);
        System.out.println("Alright, Lumi has added: " + input + "!\n");
    }

    private void readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.toLowerCase().equals("bye")) {
            return;
        } else if (input.toLowerCase().equals("list")) {
            printList();
        } else {
            addToList(input);
        }
        readInput();
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
        System.out.println("Welcome to\n" + logo);

        Duke chatBot = new Duke();
        chatBot.runChat();
    }
}
