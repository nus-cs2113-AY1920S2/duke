/* Exception code list:
 * 1 - Empty parameter
 * 2 - Incomplete parameter
 *
 */

package duke.exceptions;

import duke.ui.Ui;

public class DukeException extends Exception{
    public int exceptionCode;
    final private int CUSTOM_EXCEPTION_COUNT = 2;
    public String commandType;
    public String parameters;


    public DukeException(int exceptionCode, String commandType){
        this.exceptionCode = exceptionCode;
        this.commandType = commandType;
        switch (commandType) {
        case "deadline":
            parameters = "/by";
            break;
        case "event":
            parameters = "/at";
            break;
        }
    }


    @Override
    public String getMessage(){
        switch (exceptionCode) {

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
