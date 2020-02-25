package duke.exception;

import duke.ui.Ui;

public class DukeException extends Exception {
    ExceptionType exceptionType;

    public DukeException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public void printExceptionMessage() {
        switch (exceptionType) {
        case InvalidCommand:
            Ui.printInvalidCommand();
            break;
        case InvalidDoneCommand:
            Ui.printInvalidDoneFormat();
            break;
        case InvalidDeleteCommand:
            Ui.printInvalidDeleteFormat();
            break;
        case InvalidToDoDeclaration:
            Ui.printInvalidToDoFormat();
            break;
        case InvalidDeadlineDeclaration:
            Ui.printInvalidDeadlineFormat();
            break;
        case InvalidEventDeclaration:
            Ui.printInvalidEventFormat();
            break;
        case InvalidFindCommand:
            Ui.printInvalidFindCommand();
            break;
        case EmptyCommand:
            Ui.printEmptyLineAlert();
            break;
        default:
            Ui.printWithIndentation("Execution Error!!!");
            break;
        }
    }
}
