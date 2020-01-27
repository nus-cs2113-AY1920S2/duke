import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String greeting = "Hello! This is Quinn's chat bot.\n" +
            "Type something to add it to the list\n" +
            "Type \"list\" to display your items\n" +
            "Type \"bye\" to exit";
    private static final String byeMessage = "Leaving so soon? :(";
    private static ArrayList<String> items;

    private static void printMessagePretty(String message) {
        String lineSeparator = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println("\n" + lineSeparator);
        System.out.println(message);
        System.out.println(lineSeparator + "\n");
    }

    private static void printItems() {
        String itemsString = "";
        for (int i = 0; i < items.size(); i++) {
            itemsString += String.format("%d) %s%s", i + 1, items.get(i), i == items.size() - 1 ? "" : "\n");
        }
        printMessagePretty(itemsString);
    }

    public static void main(String[] args) {
        items = new ArrayList<String>();
        Scanner scannerObject = new Scanner(System.in);
        String userInput = "";

        printMessagePretty(greeting);
        while (true) {
            userInput = scannerObject.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                printItems();
            } else {
                items.add(userInput);
                printMessagePretty("added: " + userInput);
            }
        }
        printMessagePretty(byeMessage);
    }
}
