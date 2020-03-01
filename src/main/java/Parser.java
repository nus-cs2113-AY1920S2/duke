/**
 * This class contains methods that handle the parsing of user input.
 */
public class Parser {
    public static final String INVALID_TASK_INDEX = "The task index you've entered is invalid.";
    public static final String EMPTY_TASK_INDEX = "The task index cannot be empty.";
    public static final String EMPTY_TODO = "The description of the todo cannot be empty.";
    public static final String EMPTY_EVENT = "The description and/or date/time of the event cannot be empty.";
    public static final String EMPTY_DEADLINE = "The description and/or date/time of the deadline cannot be empty.";
    public static final String INVALID_EVENT = "The description of an event must be in this format: \n\t event <task name> /at <date/time>";
    public static final String INVALID_DEADLINE = "The description of a deadline must be in this format: \n\t deadline <task name> /by <date/time>";
    public static final String EMPTY_FIND = "The keyword for the search cannot be empty.";

    /**
     * Returns actual task index based on user input.
     * @param words <code>String</code> <code>Array</code> of words entered by the user.
     * @param taskListSize Size of the current task list.
     * @return Actual task index in task list.
     * @throws DukeException If task index is invalid (not within the range of indexes in the task list).
     */
    public int parseTaskIndex(String[] words, int taskListSize) throws DukeException {
        String taskString = words[1];
        int taskIndex = Integer.parseInt(taskString) - 1;
        if ((taskIndex < 0) || (taskIndex >= taskListSize)) {
            throw new DukeException(INVALID_TASK_INDEX);
        }
        return taskIndex;
    }

    /**
     * Returns description of To-do task to be added to the task list based on user input.
     * @param words <code>String</code> <code>Array</code> of words entered by the user.
     * @return Description of To-do task entered by user.
     * @throws DukeException If the description entered by the user is empty.
     */
    public String parseToDo(String[] words) throws DukeException {
        if (words.length < 2 || words[1].replaceAll("\\s+","").length() == 0) {
            throw new DukeException(EMPTY_TODO);
        }
        return words[1];
    }

    /**
     * Returns <code>Array</code> of words of Event task to be added to the task list based on user input.
     * The <code>Array</code> contains the description and date/time of the Event task to be added.
     * @param words String <code>Array</code> of words entered by the user.
     * @return Description and date/time of Event task entered by user.
     * @throws DukeException If the description entered by the user does not contain the keyword "/at" or is empty.
     */
    public String[] parseEvent(String[] words) throws DukeException {
        if ((words.length < 2) || !words[1].contains(" /at ")) {
            throw new DukeException(INVALID_EVENT);
        }
        String[] eventWords = words[1].split(" /at ", 2); // Split task and date/time
        if (eventWords[0].replaceAll("\\s+","").length() == 0 || eventWords[1].replaceAll("\\s+","").length() == 0) {
            throw new DukeException(EMPTY_EVENT);
        }
        return eventWords;
    }

    /**
     * Returns <code>Array</code> of words of Deadline task to be added to the task list based on user input.
     * The <code>Array</code> contains the description and date/time of the Deadline task to be added.
     * @param words <code>String</code> <code>Array</code> of words entered by the user.
     * @return Description and date/time of Deadline task entered by user.
     * @throws DukeException If the description entered by the user does not contain the keyword "/at" or is empty.
     */
    public String[] parseDeadline(String[] words) throws DukeException{
        if ((words.length < 2) || !words[1].contains(" /by ")) {
            throw new DukeException(INVALID_DEADLINE);
        }
        String[] deadlineWords = words[1].split(" /by ", 2); // Split task and date/time
        if (deadlineWords[0].replaceAll("\\s+","").length() == 0 || deadlineWords[1].replaceAll("\\s+","").length() == 0) {
            throw new DukeException(EMPTY_DEADLINE);
        }

        return deadlineWords;
    }

    /**
     * Processes the task index of the task to be set done based on user input.
     * @param words <code>String</code> <code>Array</code> of words entered by the user.
     * @throws DukeException If the task index entered by user is empty.
     */
    public void parseDone(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException(EMPTY_TASK_INDEX);
        }
    }

    /**
     * Processes the task index of the task to be deleted based on user input.
     * @param words <code>String</code> <code>Array</code> of words entered by the user.
     * @throws DukeException If the task index entered by user is empty.
     */
    public void parseDelete(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException(EMPTY_TASK_INDEX);
        }
    }

    /**
     * Returns keyword <code>String</code> to be searched based on user input.
     * @param words <code>String</code> <code>Array</code> of words entered by the user.
     * @return Keyword <code>String</code> to be searched.
     * @throws DukeException If the keyword entered by user is empty.
     */
    public String parseFind(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException(EMPTY_FIND);
        }
        return words[1];
    }
}
