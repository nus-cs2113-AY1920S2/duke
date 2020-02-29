/* Exception code list:
 * 0 - Exception for Find command
 * 1 - Empty parameter
 * 2 - Incomplete parameter
 *
 */

package duke.exceptions;

import duke.ui.Ui;

public class DukeException extends Exception{
    public int exceptionCode;
    public String commandType;
    public String parameters;

    public DukeException (String commandType) {
        this.commandType = commandType;
        this.exceptionCode = 0;
    }

    public DukeException(String commandType, int exceptionCode){
        this.exceptionCode = exceptionCode;
        this.commandType = commandType;
        switch (commandType) {
        case "deadline":
            parameters = "/by";
            break;
        case "event":
            parameters = "/at";
            break;
        case "find":
            break;
        default:
            System.out.println("Undefined exception command type.");
            break;
        }
    }


    @Override
    public String getMessage(){
        switch (exceptionCode) {
        case 0:
            Ui.showInvalidFindError();
            break;
        case 1:
            Ui.showEmptyParametersError(commandType);
            break;
        case 2:
            Ui.showIncompleteParametersError(parameters);
            break;
        default:
            System.out.println("None existent exception code.");
        }

        return null;
    }

}
