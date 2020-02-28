package common;

/**
 * Stored messages for greeting and bidding farewell to the user.
 */
public class Messages {
    public final static String name = "Rick";
    public final static String line = "____________________________________________________________";
    public final static String LINE_BUFFER = "  ";
    
    public final static String MESSAGE_GREETING = LINE_BUFFER + "Hello! I'm " + name +
        '\n' + LINE_BUFFER + "What can I do for you?";
    public final static String MESSAGE_GOODBYE = LINE_BUFFER + "Bye. Hope to see you again soon!";
    
}
