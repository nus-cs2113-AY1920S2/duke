/* Exception code list:
 * 1 - Empty parameter
 * 2 - Incomplete parameter
 *
 */

public class DukeException extends Exception{
    public int exceptionCode;
    public String[] field = new String[2];
    DukeException(int exceptionCode, String field){
        this.exceptionCode = exceptionCode;
        this.field[0] = field;
        switch (field) {
        case "deadline":
            this.field[1] = "/by";
            break;
        case "event":
            this.field[1] = "/at";
            break;
        }
    }

    @Override
    public String getMessage(){
        switch (exceptionCode) {
        case 1:
            return ("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The description of " + field[0] + " cannot be empty.\n"
                    + "    ____________________________________________________________\n");
        case 2:

            return ("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! You forgot to add \"" + field[1] + " [insert time]\"\n"
                    + "    ____________________________________________________________\n");

        default:
            return "";
        }


    }

}
