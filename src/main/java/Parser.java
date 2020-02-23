public class Parser {

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
