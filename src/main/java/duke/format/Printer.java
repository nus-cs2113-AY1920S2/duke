package duke.format;

import duke.task.Task;

import java.util.ArrayList;

import static java.lang.System.lineSeparator;

public class Printer {

    private static void printSpaces(int numberOfSpaces) {
        for (int i = 0; i < numberOfSpaces; i++) {
            System.out.print(" ");
        }
    }

    public static void printWelcomeMessage() {
        String logo =
            "  __       _______  _______  ________  _______  _______  _______  ________\n" +
            " |\\_\\     |\\___\\__\\|\\ __\\__\\|\\ ______\\|\\______\\|\\___\\__\\|\\______\\|\\ ______\\\n" +
            " | | |    | |  |  || |  |  | \\|__   _|| |  ___|| |  |  || |     | \\|__   _|\n" +
            " | | |    | |  |  || |  |  |   | | |  | | |    | |  |  || |  |  |   | | |\n" +
            " | | |__  | |  |  || |     | __| | |_ | | |___ | |     || |     |   | | |\n" +
            " | | |__\\ | |     || | | | ||\\__\\| |_\\| | |___\\| |  |  || |  |  |   | | |\n" +
            "  \\|_____| \\|_____| \\|_|_|_| \\|______| \\|_____| \\|__|__| \\|__|__|    \\|_|";
        System.out.println("Welcome to\n" + logo);
    }

    public static void printLoadMessage() {
        System.out.println("Initializing LumiChat v0.0.1.4...\n");
        System.out.println("LumiChat is now ready.\n");
        System.out.println("  Hey I'm Lumi!");
        System.out.println("  How may Lumi assist you today?\n");
    }

    public static void printExitMessage() {
        System.out.println(TextFormatter.SAD_FACE + "Goodbye! Lumi will miss you!");
    }

     public static void printAddTaskMessage(ArrayList<Task> list) {
        int latestTaskIndex = list.size() - 1; // 0-based indexing
        System.out.println(TextFormatter.HAPPY_FACE + "Alright, Lumi has added: " + list.get(latestTaskIndex).getTask() + "!");
        System.out.println(TextFormatter.createSpaces(8) + list.get(latestTaskIndex).getTaskStatus());
        if (list.size() == 1) {
            System.out.println("  You now have " + list.size() + " task in Lumi's list!\n");
        } else {
            System.out.println("  You now have " + list.size() + " tasks in Lumi's list!\n");
        }
    }

    public static void printCompleteTaskMessage(ArrayList<Task> list, int listNumber) {
        System.out.println(TextFormatter.HAPPY_FACE + "Well done! Lumi marks this task as completed!");
        System.out.println(TextFormatter.createSpaces(8) + list.get(listNumber).getTaskStatus() +
                lineSeparator());
    }

    public static void printAlreadyCompletedTaskMessage(ArrayList<Task> list, int listNumber) {
        System.out.println(TextFormatter.ANGRY_FACE +
        "Hey!! Lumi already marked <" + list.get(listNumber).getTask() + "> as completed!" + lineSeparator());
    }


    public static void printList(ArrayList<Task> list, boolean isStandardPrint) {

        if (isStandardPrint) {
            System.out.println(TextFormatter.HAPPY_FACE + "Sure! Lumi prints your list!");
        }

        final String LIST_TOP =
                "    +---------+\n" +
                "+---| L I S T |------------------------------------------------+\n" +
                "|   +---------+                                                |";
        final String LIST_LEFT = "| ";
        final String LIST_RIGHT = " |";
        final String LIST_BOTTOM = "+--------------------------------------------------------------+\n";
        final int LIST_LENGTH = 60;

        System.out.println(LIST_TOP);

        // Print list items
        for (int i = 0; i < list.size(); i++) {
            String listItem = i+1 + ". " + list.get(i).getTaskStatus();

            System.out.print(LIST_LEFT);
            System.out.print(listItem);
            System.out.print(TextFormatter.createSpaces(LIST_LENGTH-listItem.length()-1));
            System.out.println(LIST_RIGHT);
        }

        System.out.print(LIST_BOTTOM);

        // Print total number of tasks
        String totalTaskString = "Total: " + list.size() + (list.size() == 1 ? " task" : " tasks");
        System.out.print(LIST_LEFT);
        System.out.print(totalTaskString);
        System.out.print(TextFormatter.createSpaces(LIST_LENGTH-totalTaskString.length()));
        System.out.println(LIST_RIGHT);
        System.out.println(LIST_BOTTOM);
    }

    public static void printDeleteTaskConfirmationMessage(ArrayList<Task> list, int listNumber) {
        System.out.println(TextFormatter.THINKING_FACE + "Umm... Lumi needs you to confirm to delete this task:");
        System.out.println(TextFormatter.createSpaces(8) + list.get(listNumber).getTaskStatus() +
                lineSeparator());
    }

    public static void printPromptValidConfirmationMessage() {
        System.out.println(TextFormatter.THINKING_FACE +
                "Uh... Lumi needs you to enter either " +
                TextFormatter.toBoldAndItalic("YES") + " to confirm deletion or " +
                TextFormatter.toBoldAndItalic("NO") + " to cancel..." + lineSeparator());
    }

    public static void printDeleteTaskMessage(ArrayList<Task> list, int listNumber) {
        System.out.println(TextFormatter.HAPPY_FACE + "Bleep, Lumi say bye-bye to: " + list.get(listNumber).getTask() + "!");
        System.out.println(TextFormatter.createSpaces(8) + list.get(listNumber).getTaskStatus());

        int newListSize = list.size() - 1;
        if (newListSize == 1) {
            System.out.println("  You now have " + newListSize + " task in Lumi's list!\n");
        } else {
            System.out.println("  You now have " + newListSize + " tasks in Lumi's list!\n");
        }
    }

    public static void printAbortDeleteMessage() {
        System.out.println(TextFormatter.HAPPY_FACE + "OK, Lumi continues without deleting!" + lineSeparator());
    }
}
