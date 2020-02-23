public class Parser {

    /**
     * Create an object command that contains the instruction and details
     * from the complete user input command, information should be delimited
     * by white spaces.
     * @param fullCommand String object with the full command instruction obtained from user
     * @return an object command that contains an instruction and a string with details
     */
    public static Command parse(String fullCommand) {
        Command command;
        String[] requests = fullCommand.split(" ", 2);
        if (requests.length == 1) {
            command = new Command(requests[0],"");
        } else {
            command = new Command(requests[0],requests[1]);
        }
        return command;
    }
}
