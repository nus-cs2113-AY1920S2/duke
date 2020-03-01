package duke.ui;

import duke.data.TaskList;
import duke.commands.CommandResult;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.format.TextFormatter.createSpaces;
import static duke.ui.Messages.NO_MATCHES_MESSAGE;

/**
 * <h3>User Interface</h3>
 * The <b>User Interface (UI)</b> manages the reading of user input and displaying of feedback messages and other important
 * information to the user.
 */
public class UI {
    private static final String LS = System.lineSeparator();
    private static final String INDENT = createSpaces(2);

    private final Scanner in;
    private final PrintStream out;

    /** Constructs the <code>UI</code>. */
    public UI() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    }

    /**
     * Reads the user input from the command line.
     *
     * @return The input given by the user
     */
    public String getInput() {
        return in.nextLine().trim();
    }

    /**
     * Shows the result message after the command given by the user input is executed.
     * <br>
     * Also, shows the <b>Task List</b> or <i>Search List</i> if indicated in <code>result</code>.
     *
     * @param result The result after executing a command given by the user input
     */
    public void showResult(CommandResult result) {
        out.println(result.getMessage().replace("\n", LS + INDENT));
        if (result.getShowListStatus()) {
            ArrayList<Integer> searchedTaskIndices = result.getSearchedIndices();
            if (searchedTaskIndices == null) {
                out.println(createList().replace("\n", LS));
            } else {
                out.println(createSearchList(searchedTaskIndices).replace("\n", LS));
            }
        }
    }

    /**
     * Shows a message to the user.
     *
     * @param message Message to be shown
     */
    public void showSystemMessage(String message) {
        out.println(message.replace("\n", LS));
    }

    /**
     * Prompts the user to make a confirmation to the context specified in the <code>confirmationMessage</code>.
     * <p></p>
     * <b>Note</b>: Accepted responses are only <i>yes</i> or <i>y</i> to confirm, and <i>no</i> or <i>n</i> to reject.
     * Any other responses will result in re-prompting the user again.
     *
     * @param confirmationMessage The confirmation message to be shown at the start to the user
     * @param validConfirmationMessage The message to inform the user of the valid response
     * @return <code>TRUE</code> if the user confirms, or <code>FALSE</code> otherwise
     */
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

    /**
     * Returns the list of tasks in the <b>Task List</b> in a decorated format.
     *
     * @return The formatted list to be shown to the user
     * @see TaskList
     */
    private String createList() {

        final String LIST_TOP =
                "    +---------+\n" +
                        "+---| L I S T |---------------------------------------------------------------+\n" +
                        "|   +---------+                                                               |\n";
        final String LIST_LEFT = "| ";
        final String LIST_RIGHT = " |";
        final String LIST_BOTTOM = "+-----------------------------------------------------------------------------+\n";
        final int LIST_LENGTH = 75;

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

    /**
     * Formats the list item indicated by the <code>index</code> of the <b>Task List</b>.
     * <br> Formatted list item will contain its list number and task details.
     *
     * @param index The index of the task in the <b>Task List</b> to be formatted
     * @return The formatted list item
     * @see TaskList
     */
    private String formatListItem(int index) {
        int listNumber = index + 1; // 0-indexing
        return listNumber + ". " + TaskList.get(index).getTaskStatus();
    }

    /**
     * Returns the list of searched tasks in the <b>Task List</b> in a decorated format.
     *
     * @param searchedTaskIndices The indices of the tasks in the <b>Task List</b> that was searched
     * @return The formatted search list to be shown to the user
     * @see TaskList
     */
    private String createSearchList(ArrayList<Integer> searchedTaskIndices) {
        if (searchedTaskIndices.isEmpty()) {
            return NO_MATCHES_MESSAGE;
        }

        final String LIST_TOP =
                "    +-----------------------+\n" +
                "+---| S E A R C H   L I S T |-------------------------------------------------+\n" +
                "|   +-----------------------+                                                 |\n";
        final String LIST_LEFT = "| ";
        final String LIST_RIGHT = " |";
        final String LIST_BOTTOM = "+-----------------------------------------------------------------------------+\n";
        final int LIST_LENGTH = 75;

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
