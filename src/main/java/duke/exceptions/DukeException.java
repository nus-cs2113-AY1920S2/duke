package duke.exceptions;

import duke.ui.Ui;

public class DukeException extends Exception {
    public int exceptionCode;
    public String commandType;
    public String parameters;
    final int EXCEPTION_CODE_FIND = 0;
    final int EXCEPTION_CODE_EMPTY = 1;
    final int EXCEPTION_CODE_INCOMPLETE = 2;
    final int EXCEPTION_CODE_DONE = 3;
    final int EXCEPTION_CODE_NEGATIVE = 4;
    final int EXCEPTION_CODE_CORRUPTED = 5;



    public DukeException(String commandType, int exceptionCode) {
        this.exceptionCode = exceptionCode;
        this.commandType = commandType;
        switch (commandType) {
        case "deadline":
            parameters = "/by";
            break;
        case "event":
            parameters = "/at";
            break;
        default:
            //no action required by default
            break;
        }
    }


    @Override
    public String getMessage() {
        switch (exceptionCode) {
        case EXCEPTION_CODE_FIND:
            Ui.showInvalidFindError();
            break;
        case EXCEPTION_CODE_EMPTY:
            Ui.showEmptyParametersError(commandType);
            break;
        case EXCEPTION_CODE_INCOMPLETE:
            Ui.showIncompleteParametersError(parameters);
            break;
        case EXCEPTION_CODE_DONE:
            Ui.showAlreadyDone();
            break;
        case EXCEPTION_CODE_NEGATIVE:
            Ui.showNegativeError();
            break;
        case EXCEPTION_CODE_CORRUPTED:
            Ui.showTamperedFile();
            break;
        default:
            System.out.println("None existent exception code.");
        }

        return null;
    }

}
