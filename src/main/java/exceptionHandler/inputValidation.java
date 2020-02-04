package exceptionHandler;

public class inputValidation {
    protected String defaultStr = "I couldn't process that: ";
    protected String doneNumErr = defaultStr + "Please indicate the item number without letters (i.e. done 2)";
    protected String doneParamCount = defaultStr + "Please indicate an item number (i.e. done 2)";
    protected String doneNotenough = defaultStr + "There isn't that many tasks :(";
    protected String todoParamCount = defaultStr + "Please give me a todo detail";
    protected String eventDateMissing = defaultStr + "When is the event?";
    protected String eventMissingDesc = defaultStr + "I need more info!";
    public inputValidation() {

    }

    public Boolean validatingDone(String cmd, int taskCount) {
        // checks if the item number is given
        if (cmd.length() <= 4) {
            System.out.println(doneParamCount);
            return false;
        }
        // checks if the given item number is numeric
        int itemNumber;
        try {
            itemNumber = Integer.parseInt(cmd.substring(5));
        } catch (NumberFormatException nfe) {
            System.out.println(doneNumErr);
            return false;
        }
        // check if item number is within the list count
        if (itemNumber <= taskCount) {
            return true;
        } else {
            System.out.println(doneNotenough);
            return false;
        }
    }

    public Boolean validatingTodo(String cmd) {
        // checks if the item number is given
        if (cmd.length() <= 4) {
            System.out.println(todoParamCount);
            return false;
        } else {
            return true;
        }
    }

    public Boolean validatingEventDeadline(String cmd) {
        if (cmd.length() <= 5) {
            System.out.println(eventMissingDesc);
            return false;
        }
        try {
            int indexOfAt = cmd.indexOf("/at");
            int indexOfby = cmd.indexOf("/by");
            if (indexOfAt == -1 && indexOfby == -1) {
                System.out.println(eventDateMissing);
                return false;
            }
            String eventDate = cmd.substring(indexOfAt+4);
        } catch (NumberFormatException missingItem) {
            System.out.println(eventDateMissing);
            return false;
        }
        return true;
    }
}
