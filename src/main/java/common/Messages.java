package common;

import tasklist.TaskList;
import data.Task;

/**
 * This class acts as a container for the error messages and strings that are used by the rest of the classes.
 * <p></p>
 * <p>It also holds some generalized methods that can be used to print messages, mainly for the classes involving Task manipulation.</p>
 */
public class Messages {

    private static final String UNDERSCORED_DIVIDER = "\t____________________________________________________________";

    private static final String CURLY_DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /**
     * A platform independent line separator.
     */
    public static final String LS = System.lineSeparator();

    public static final String SAVE_TASKLIST_TO_FILE_FAILURE_MESSAGE = "\tError saving taskList to duke.txt";

    public static final String START_MESSAGE = "Hello! I'm Duke" + LS
            + "What can I do for you?";

    public static final String FIRST_EXIT_MESSAGE = "Bye! Hope to see you again soon" + LS;

    public static final String SECOND_EXIT_MESSAGE = "********************CONNECTION TERMINATED********************";

    public static final String USER_INPUT_ARROWHEAD_DISPLAY = ">>>";

    public static final String BYE_COMMAND = "bye";

    public static final String LIST_COMMAND = "list";

    public static final String DONE_COMMAND = "done";

    public static final String FIND_COMMAND = "find";

    public static final String DELETE_COMMAND = "delete";

    public static final String TODO_COMMAND = "todo";

    public static final String DEADLINE_COMMAND = "deadline";

    public static final String EVENT_COMMAND = "event";

    public static final String WHITESPACE_DELIMITER = " ";

    public static final String REMARKS_DELIMITER = " /";

    public static final String TASKLIST_SAVE_DIRECTORY = "data";

    public static final String TASKLIST_SAVE_FILEPATH = "data/duke.txt";

    public static final String TASKLIST_SAVE_PIPE_DELIMITER = " \\| ";

    public static final String NO_MATCHING_SEARCH_RESULTS_MESSAGE =
            "\tNo tasks containing the search keyword were found.";

    public static final String EMPTY_COMMAND_ERROR_MESSAGE = "\t\u2639 !!ERROR!! Command cannot be whitespaces.";

    public static final String INVALID_TASK_NUMBER_ERROR_MESSAGE =
            "\t\u2639 !!ERROR!! The task number you have provided is not valid.";

    public static final String INVALID_COMMAND_ERROR_MESSAGE =
            "\t\u2639 !!ERROR!! I'm sorry, but I don't know what that means :-(";

    public static final String EMPTY_LIST_ERROR_MESSAGE = "\tThe list is empty.";

    public static final String TASK_ALREADY_COMPLETED_ERROR_MESSAGE = "\tThis task has already been marked completed.";

    public static final String TODO_HAS_REMARK_SECTION_ERROR_MESSAGE =
            "\t\u2639 !!ERROR!! task command should not have a remarks section.";

    public String addUnderscoreBorders(String inputText) {
        return UNDERSCORED_DIVIDER + LS + inputText + LS + UNDERSCORED_DIVIDER;
    }

    public String addCurlyBorders(String inputText) {
        return CURLY_DIVIDER + LS + inputText + LS + CURLY_DIVIDER;
    }

    public static String getTaskDoneMessage(int queryNumber, TaskList listInput) {
        return "\tGreat job! I've marked this task as done:" + LS + "\t" + Integer.toString(queryNumber)
                + ".[" + listInput.getTaskStatusIcon(queryNumber - DISPLAYED_INDEX_OFFSET) + "] "
                + listInput.getTaskDescription(queryNumber - DISPLAYED_INDEX_OFFSET);
    }

    public static String getTaskAddedMessage(Task newTask, TaskList listInput) {
        return "\tGot it. I've added this task:" + LS + "\t" + newTask.toString() + LS
                + "\tNow you have " + listInput.getTaskCount() + " tasks in the list.\n";

    }

    public static String getTaskRemovedMessage(Task removedTask, TaskList listInput) {
        return "\tGot it. I've removed this task: \n\t" + removedTask.toString() + LS
                + "\tNow you have " + listInput.getTaskCount() + " tasks in the list.\n";
    }

    public static String addTaskEmptyDescriptionErrorMessage(String taskType) {
        return "\t\u2639 !!ERROR!! The description of a " + taskType + " cannot be empty.";
    }

    public static String addTaskEmptyRemarksErrorMessage(String taskType) {
        return "\t\u2639 !!ERROR!! The remarks section of a " + taskType + " cannot be empty.";
    }

    public static String executeCommandInsufficientParameterErrorMessage(String taskType) {
        return "\t\u2639 !!ERROR!! " + taskType + " command is missing additional parameters.";
    }

    public static String splitInputEventOrDeadlineMissingRemarksErrorMessage(String taskType) {
        return "\t\u2639 !!ERROR!! " + taskType + " command should have a remarks section.";
    }

}
