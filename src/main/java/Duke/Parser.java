package Duke;

import java.util.List;

/** Parses user's input to get specific details for categorizing them */
public class Parser {

    /**
     * Parses string with whitespace to separate input.
     *
     * @param input String to be parsed.
     * @return Parsed input as a String array.
     */
    public static String[] parseInput (String input) {
        return input.split(" ");
    }

    /**
     * Gets description of todo from string list.
     *
     * @param arguments List of String containing parsed Todo task.
     * @return Description of todo as a string.
     */
    public static String getTodoDescription (List<String> arguments) {
        return String.join(" ", arguments);
    }

    /**
     * Gets description of deadline from string list.
     *
     * @param parsedList List of String containing parsed deadline task.
     * @return Description of deadline as a string.
     * @throws DukeException When user does not input date and time of deadlilne.
     */
    public static String getDeadlineDescription (List<String> parsedList) throws DukeException {
        int i = parsedList.indexOf("/by");
        if (i == -1) {
            throw new DukeException("Please enter again with the following format: D/NAME /by yyyy-mm-dd HHmm");
        }
        else {
            return String.join(" ", parsedList.subList(1, i));
        }
    }

    /**
     * Gets description of event from string list.
     *
     * @param parsedList List of String containing parsed event task.
     * @return Description of event as a string.
     * @throws DukeException When user does not input date and time of event.
     */
    public static String getEventDescription (List<String> parsedList) throws DukeException {
        int i = parsedList.indexOf("/at");
        if (i == -1) {
            throw new DukeException("Please enter again with the following format: E/NAME /at yyyy-mm-dd HHmm");
        }
        else {
            return String.join(" ", parsedList.subList(1, i));
        }
    }

    /**
     * Gets time and date of deadline task from string list.
     *
     * @param parsedList List of String containing parsed deadline task.
     * @return Time and date of deadline task as a string.
     * @throws DukeException When user does not input date and time of deadline.
     */
    public static String getBy(List<String> parsedList) throws DukeException {
        int i = parsedList.indexOf("/by");
        if (i == -1) {
            throw new DukeException("Please enter again with the following format: D/NAME /by D/TIME");
        }
        else {
            return String.join(" ", parsedList.subList(i + 1, parsedList.size()));
        }
    }

    /**
     * Gets time and date of event task from string list.
     *
     * @param parsedList List of String containing parsed event task.
     * @return Time and date of event task as a string.
     * @throws DukeException When user does not input date and time of event.
     */
    public static String getAt (List<String> parsedList) throws DukeException {
        int i = parsedList.indexOf("/at");
        if(i == -1) {
            throw new DukeException("Please enter again with the following format: E/NAME /at D/TIME");
        }
        else {
            return String.join(" ", parsedList.subList(i + 1, parsedList.size()));
        }
    }

    /**
     * Checks if string is a number.
     *
     * @param strNum String to check.
     * @return True if it is numeric, else false.
     */
    public static boolean isNumeric(String strNum){
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
