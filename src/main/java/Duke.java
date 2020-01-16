import java.util.Scanner;

public class Duke {
    private static void printMessagePretty(String message) {
        String lineSeparator = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        System.out.println("\n" + lineSeparator);
        System.out.println(message);
        System.out.println(lineSeparator + "\n");
    }

    private static void readAndRepeatUserInput() {
        Scanner scannerObject = new Scanner(System.in);
        final String exitKeyword = "bye";
        String userInput = "";

        while (true) {
            userInput = scannerObject.nextLine();
            if (userInput.equals(exitKeyword)) {
                break;
            }
            printMessagePretty("You typed: " + userInput);
        }
    }

    public static void main(String[] args) {
        final String greeting = "Hello! This is Quinn's chat bot.\nType something and I will repeat it\n" +
                "Say \"bye\" to quit";
        printMessagePretty(greeting);

        readAndRepeatUserInput();

        final String byeMessage = "Leaving so soon? :(";
        printMessagePretty(byeMessage);

    }
}
