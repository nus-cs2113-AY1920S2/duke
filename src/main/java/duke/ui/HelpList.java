package duke.ui;

public class HelpList {

    public static String TODO_HELP = "To add a ToDo, enter: todo {description}\n";
    public static String DEADLINE_HELP = "To add a Deadline with a description, enter: deadline /by {description}\n" +
            "To add a Deadline with a date(yyyy-mm-dd), enter: deadline /by {date}\n";
    public static String EVENT_HELP = "To add an Event, enter: event /at {location}\n";
    public static String LIST_HELP = "To display your task list, enter: list\n";
    public static String FIND_HELP = "To find a task from a substring, enter: find {substring}\n";
    public static String DELETE_HELP = "To delete a task from your task list with its index, enter: delete {task index}\n";
    public static String DONE_HELP = "To mark a task as done with its index, enter: done {task index}\n";
    public static String CLEAR_HELP = "To clear your task list, enter: clear\n";
    public static String BYE_HELP = "To exit the programme, enter: bye\n";
    public static String HELP_LIST_HELP = "To display the help list, enter: /help\n";

    public static String HelpListMessage() {
        StringBuilder output = new StringBuilder();
        output.append(TODO_HELP + DEADLINE_HELP + EVENT_HELP + LIST_HELP +
                FIND_HELP + DELETE_HELP + DONE_HELP +
                CLEAR_HELP + HELP_LIST_HELP + BYE_HELP);
        return output.toString();
    }

}
