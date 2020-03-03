package duke.ui;

/**
 * HelpList is the public class responsible for storing information present in the help menu.
 */

public class HelpList {

    /**
     * Intro message of the help menu.
     */

    public static String INTRO_HELP = "The following commands are supported by Duke:\n\n";

    /**
     * Help message for adding a to-do.
     */

    public static String TODO_HELP = "To add a ToDo, enter: todo description\n";

    /**
     * Help message for adding a deadline.
     */

    public static String DEADLINE_HELP = "To add a Deadline, date in yyyy-mm-dd or otherwise, enter: deadline /by date\n";

    /**
     * Help message for adding an event.
     */

    public static String EVENT_HELP = "To add an Event, enter: event /at location\n";

    /**
     * Help message for displaying the tasklist.
     */

    public static String LIST_HELP = "To display your task list, enter: list\n";

    /**
     * Help message for finding a task.
     */

    public static String FIND_HELP = "To find a task from a substring, enter: find substring\n";

    /**
     * Help message for deleting a task.
     */

    public static String DELETE_HELP = "To delete a task from your task list with its index, enter: delete index\n";

    /**
     * Help message for marking a task as done.
     */

    public static String DONE_HELP = "To mark a task as done with its index, enter: done index\n";

    /**
     * Help message for clearing the tasklist.
     */

    public static String CLEAR_HELP = "To clear your task list, enter: clear\n";

    /**
     * Help message for exiting the application.
     */

    public static String BYE_HELP = "To exit the programme, enter: bye\n";

    /**
     * Help message for displaying the help menu.
     */

    public static String HELP_LIST_HELP = "To display the help list, enter: /help\n";


    /**
     * Message displayed when user opens the help menu.
     *
     * @return the help menu message.
     */

    public static String HelpListMessage() {
        StringBuilder output = new StringBuilder();
        output.append(INTRO_HELP + TODO_HELP + DEADLINE_HELP + EVENT_HELP +
                FIND_HELP + DELETE_HELP + DONE_HELP + LIST_HELP +
                CLEAR_HELP + HELP_LIST_HELP + BYE_HELP);
        return output.toString();
    }

}
