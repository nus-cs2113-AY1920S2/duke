package duke.exception;

import duke.ui.Ui;

/**
 * Used to handle exceptions encountered during execution.
 */
public class DukeException extends Exception {

    public static final String EXECUTION_ERROR_MESSAGE = "Execution Error!!!";

    /** Contains the type of the exception. */
    ExceptionType exceptionType;

    /**
     * Constructor for DukeException Class.
     * It creates a new DukeException with the exception type provided.
     *
     * @param exceptionType Contains the type of the exception to be created later.
     */
    public DukeException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    /**
     * Prints the corresponding exception message for the exception based on its type.
     */
    public void printExceptionMessage() {
        switch (exceptionType) {
        case InvalidCommandException:
            Ui.printInvalidCommand();
            break;
        case InvalidDoneCommandException:
            Ui.printInvalidDoneFormat();
            break;
        case InvalidDeleteCommandException:
            Ui.printInvalidDeleteFormat();
            break;
        case InvalidToDoDeclarationException:
            Ui.printInvalidToDoFormat();
            break;
        case InvalidDeadlineDeclarationException:
            Ui.printInvalidDeadlineFormat();
            break;
        case InvalidEventDeclarationException:
            Ui.printInvalidEventFormat();
            break;
        case InvalidFindCommandException:
            Ui.printInvalidFindCommand();
            break;
        case InvalidDueCommandException:
            Ui.printInvalidDueCommand();
            break;
        case EmptyCommandException:
            Ui.printEmptyLineAlert();
            break;
        default:
            Ui.printWithIndentation(EXECUTION_ERROR_MESSAGE);
            break;
        }
    }
}
