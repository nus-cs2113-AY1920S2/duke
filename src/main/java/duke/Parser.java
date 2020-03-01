package duke;

import duke.task.Task;

/**
 * Makes sense of user commands by splitting a string of words
 * with the appropriate delimiter.
 */

public class Parser {
    public static String[] splitFirstWord(String words) {
        return words.split(" ",2);
    }

    public static String[] splitDeadline(String words) {
        return words.split(" /by ", 2);
    }

    public static String[] splitEvent(String words) {
        return words.split(" /at ", 2);
    }
}