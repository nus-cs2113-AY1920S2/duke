package duke;

public class Parser {
    public static String[] splitFirstWord(String line) {
        return line.split(" ",2);
    }

    public static String[] splitDeadline(String word) {
        return word.split(" /by ", 2);
    }

    public static String[] splitEvent(String word) {
        return word.split(" /at ", 2);
    }
}