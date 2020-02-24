package duke.ui;

import duke.data.TaskList;
import duke.commands.CommandResult;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static duke.format.TextFormatter.createSpaces;

public class Ui {
    private static final String LS = System.lineSeparator();
    private static final String INDENT = createSpaces(2);

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        in = new Scanner(System.in);
        PrintStream selectedOut;
        selectedOut = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out = selectedOut;
    }

    public String getInput() {
        return in.nextLine().trim();
    }

    public void showResult(CommandResult result) {
        out.println(result.getMessage().replace("\n", LS + INDENT));
        if (result.getPrintStatus()) {
            out.println(createList().replace("\n", LS));
        }
    }

    public void showSystemMessage(String message) {
        out.println(message.replace("\n", LS));
    }

    public boolean getConfirmation(String confirmationMessage, String validConfirmationMessage) {
        showSystemMessage(confirmationMessage);
        while (true) {
            String input = in.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                showSystemMessage(validConfirmationMessage);
            }
        }
    }

    private String createList() {

        final String LIST_TOP =
                "    +---------+\n" +
                        "+---| L I S T |----------------------------------------------------------+\n" +
                        "|   +---------+                                                          |\n";
        final String LIST_LEFT = "| ";
        final String LIST_RIGHT = " |";
        final String LIST_BOTTOM = "+------------------------------------------------------------------------+\n";
        final int LIST_LENGTH = 70;

        String list = LIST_TOP;

        // Append list items
        for (int i = 0; i < TaskList.size(); i++) {
            String listItem = formatListItem(i);
            String spaces = createSpaces(LIST_LENGTH-listItem.length()-1);

            list = list.concat(String.format("%s%s%s%s\n", LIST_LEFT, listItem, spaces, LIST_RIGHT));
        }
        list = list.concat(LIST_BOTTOM);

        // Append task count
        String totalTaskString = "Total: " + TaskList.size() + (TaskList.size() == 1 ? " task" : " tasks");
        String spaces = createSpaces(LIST_LENGTH-totalTaskString.length());
        list = list.concat(String.format("%s%s%s%s\n%s", LIST_LEFT, totalTaskString, spaces, LIST_RIGHT, LIST_BOTTOM));

        return list;
    }

    private String formatListItem(int index) {
        int listNumber = index + 1; // 0-indexing
        return listNumber + ". " + TaskList.get(index).getTaskStatus();
    }
}
