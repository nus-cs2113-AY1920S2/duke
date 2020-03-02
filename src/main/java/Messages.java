/**
 * Contains all the frequently used messages displayed to the user.
 */

public class Messages {

    /**
     * List of commands that the user can input.
     */
    public static String LIST_OF_COMMANDS = "Here is the list of commands available\n"
            + "\"bye\": to exit\n" +
            "\"list\": to show the list of all your tasks\n" +
            "\"todo\": add a todo\n" +
            "\"deadline\": add a deadline\n" +
            "\"event\": add an event\n" +
            "\"done\": check off a task on your list";

    /**
     * Exit message.
     */
    public static String EXIT_MESSAGE = "Bye la you";

    /**
     * Duke logo.
     */
    public static String LOGO = " ____        _        \n" +
            "|  _ \\\\ _   _| | __ __ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\\\__,_|_|\\_\\\\___\n";

    /**
     * Welcome message when the user first enters.
     */
    public static String WELCOME_MESSAGE = "Hello there I am\n" + LOGO + LIST_OF_COMMANDS;
}
