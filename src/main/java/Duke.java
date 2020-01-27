import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String greeting = "Hello! This is Quinn's chat bot.\n" +
            "Type something to add it to the list\n" +
            "Type \"list\" to display your items\n" +
            "Type \"bye\" to exit";
    private static final String byeMessage = "Leaving so soon? :(";
    private static ArrayList<Task> tasks;

    private static void printMessagePretty(String message) {
        String lineSeparator = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println("\n" + lineSeparator);
        System.out.println(message);
        System.out.println(lineSeparator + "\n");
    }

    private static void printItems() {
        String itemsString = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String lineEnd = i == tasks.size() - 1 ? "" : "\n";
            itemsString += String.format("%d) [%s] %s%s", i + 1, currentTask.getStatusIcon(),
                    currentTask.getDescription(), lineEnd);
        }
        printMessagePretty(itemsString);
    }

    public static void main(String[] args) {
        tasks = new ArrayList<Task>();
        Scanner scannerObject = new Scanner(System.in);
        String userInput = "";

        printMessagePretty(greeting);
        while (true) {
            userInput = scannerObject.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                printItems();
            } else if (userInput.startsWith("done")) {
                // Should do some more error checking here... maybe later
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                Task chosenTask = tasks.get(index);
                chosenTask.setIsDone(true);
                String doneMessage = chosenTask.getDescription() + "\n" +
                        "has been marked as done! [" + chosenTask.getStatusIcon() + "]";
                printMessagePretty(doneMessage);
            } else {
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                printMessagePretty("added: " + newTask.getDescription());
            }
        }
        printMessagePretty(byeMessage);
    }
}
