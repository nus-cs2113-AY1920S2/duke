package alie.exceptions;

/**
 * Exception for when an invalid command is detected
 */
public class InvalidCmdException extends Exception {
    //exceptions specific to ALIE
    public static final String TOO_MANY_DATES_ERROR = "Please only provide 1 date.";
    public static String TODO_MISSING_NAME_ERROR = "Please enter NAME for %1$s";
    public static final String DEADLINE_OR_EVENT_MISSING_DETAILS_ERROR  =
            "Please enter NAME or DATE for %1$s";
    public static final String INVALID_NUM_ERROR = "Please provide a positive number.";
    public static final String INVALID_ID_ERROR = "Please provide a valid index. %1$s";
    public static final String COMPLETED_TASK_ERROR = "Task is already completed";
    public static final String MISSING_INDEX_ERROR = "Please provide a number.";

    public InvalidCmdException (String errorMsg) {
        super(errorMsg);
    }

    /**
     * Formats the string that is return from the exception thrown
     * @return String with default error message for this error.
     */
    @Override
    public String toString() {
        return "INVALID CMD ERROR: " + super.getMessage();
    }
}
