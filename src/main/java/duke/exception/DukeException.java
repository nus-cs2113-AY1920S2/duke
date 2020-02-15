package duke.exception;

import duke.print.PrintHelper;

public class DukeException extends Exception {
    ExceptionType exceptionType;

    public DukeException(ExceptionType exceptionType){
        this.exceptionType = exceptionType;
    }

    public void printExceptionMessage(){
        switch (exceptionType){
        case InvalidCommand:
            PrintHelper.printInvalidCommand();
            break;
        case InvalidDoneCommand:
            PrintHelper.printInvalidDoneFormat();
            break;
        case InvalidDeleteCommand:
            PrintHelper.printInvalidDeleteFormat();
            break;
        case InvalidToDoDeclaration:
            PrintHelper.printInvalidToDoFormat();
            break;
        case InvalidDeadlineDeclaration:
            PrintHelper.printInvalidDeadlineFormat();
            break;
        case InvalidEventDeclaration:
            PrintHelper.printInvalidEventFormat();
            break;
        case EmptyCommand:
            PrintHelper.printEmptyLineAlert();
            break;
        }
    }
}
