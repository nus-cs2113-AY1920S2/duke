package duke.ui;

import duke.data.TaskList;
import duke.commands.CommandResult;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.format.TextFormatter.createSpaces;
import static duke.ui.Messages.NO_MATCHES_MESSAGE;

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
            ArrayList<Integer> searchedTaskIndices = result.getPrintListIndices();
            if (searchedTaskIndices == null) {
                out.println(createList().replace("\n", LS));
            } else {
                out.println(createFoundList(searchedTaskIndices).replace("\n", LS));
            }
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

        StringBuilder list = new StringBuilder(LIST_TOP);

        // Append list items
        for (int i = 0; i < TaskList.size(); i++) {
            String listItem = formatListItem(i);
            String spaces = createSpaces(LIST_LENGTH-listItem.length()-1);

            list.append(String.format("%s%s%s%s\n", LIST_LEFT, listItem, spaces, LIST_RIGHT));
        }
        list.append(LIST_BOTTOM);

        // Append task count
        String totalTaskString = "Total: " + TaskList.size() + (TaskList.size() == 1 ? " task" : " tasks");
        String spaces = createSpaces(LIST_LENGTH-totalTaskString.length());
        list.append(String.format("%s%s%s%s\n%s", LIST_LEFT, totalTaskString, spaces, LIST_RIGHT, LIST_BOTTOM));

        return list.toString();
    }

    private String formatListItem(int index) {
        int listNumber = index + 1; // 0-indexing
        return listNumber + ". " + TaskList.get(index).getTaskStatus();
    }

    private String createFoundList(ArrayList<Integer> searchedTaskIndices) {
        if (searchedTaskIndices.isEmpty()) {
            return NO_MATCHES_MESSAGE;
        }

        final String LIST_TOP =
                "    +-----------------------+\n" +
                "+---| S E A R C H   L I S T |--------------------------------------------+\n" +
                "|   +-----------------------+                                            |\n";
        final String LIST_LEFT = "| ";
        final String LIST_RIGHT = " |";
        final String LIST_BOTTOM = "+------------------------------------------------------------------------+\n";
        final int LIST_LENGTH = 70;

        StringBuilder list = new StringBuilder(LIST_TOP);

        // Append list items
        for (Integer index : searchedTaskIndices) {
            String listItem = formatListItem(index);
            String spaces = createSpaces(LIST_LENGTH-listItem.length()-1);

            list.append(String.format("%s%s%s%s\n", LIST_LEFT, listItem, spaces, LIST_RIGHT));
        }
        list.append(LIST_BOTTOM);

        // Append task count
        String totalTaskString = "Search Total: " +
                searchedTaskIndices.size() + (searchedTaskIndices.size() == 1 ? " task" : " tasks");
        String spaces = createSpaces(LIST_LENGTH-totalTaskString.length());
        list.append(String.format("%s%s%s%s\n%s", LIST_LEFT, totalTaskString, spaces, LIST_RIGHT, LIST_BOTTOM));

        return list.toString();
    }
}
