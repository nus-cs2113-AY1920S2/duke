package Parser;

import Commands.*;
import Asset.IllegalDukeException;
import static java.lang.Integer.parseInt;
/**
 * This class handles with making sense of commands
 * entered by Users
 *
 * This class has four methods:
 * 1) parseCommand() : splits User input by spaces
 * 2) getTaskInfo() : separates the User input into two Strings (Action and Timing)
 * 3) validateCommand() : ensures that User input is valid and legal
 * 4) parse() : assigns each command enter by User to the suitable Command class
 */
public class Parser  {
    public static final String WRONG_INPUT="\t ☹ OOPS!!! I'm sorry, but I don't know what that means :(\n" +
            "\t Input command is wrong. Enter \"help\" for list of accepted\n\t commands";
    public static final String NO_TASK_NUMBER = "\t Please enter a task number!";
    public static final String MULTIPLE_WHITE_SPACES= "\\s+";
    public static final String WRONG_USAGE_OF_COMMAND = " cannot be used with other commands";
    public static final String NOT_INTEGER = "\t Argument after command used is not an integer!";
    public static final String INDICATE_TIMING = "\t Use \"/\" to indicate timing!";
/**
 * This method splits User input by spaces into an array of Strings
 *
 * @param inCommand  This is the full input entered by User.
 * @return String[]  This returns the array of Strings that is split by spaces.
 */
    public static String[] parseCommand(String inCommand) {
        inCommand = inCommand.trim();
        String[] temp = inCommand.split(MULTIPLE_WHITE_SPACES);
        return temp;
    }
/**
 * This method separates User input into two Strings (Action and Timing)
 *
 * @param temp This is the array of Strings that was split by parseCommand().
 * @return String[]  This returns the two Strings, Action and Timing.
 */
    public static String[] getTaskInfo(String[] temp) {
        String action = "";
        String timing = "";
        boolean flip = false;
        for (int i = 1; i < (temp.length); i++) {
            if (temp[i].charAt(0) == '/') {
                flip = true;
            }
            if (flip) {
                timing += temp[i] + " ";
            } else {
                action += temp[i] + " ";
            }
        }
        action = action.trim();
        timing = timing.trim();
        timing=timing.replace('/',' ');
        String[] temp2 = new String[2];
        temp2[0] = action;
        temp2[1] = timing;
        return temp2;
    }
/**
 * This method ensures that User input is valid and legal
 *
 * @param inCommand This is the full input given by User.
 * @return String[] This is the validated array of Strings
 * to be used in the parse().
 * @throws IllegalDukeException if command enter is invalid
 * or illegal.
 */
   public static String[] validateCommand(String inCommand) throws IllegalDukeException {
        inCommand=inCommand.toLowerCase();
        String[] userCommand = parseCommand(inCommand);
        String[] out = null;
        switch (userCommand[0]) {
            case "bye":
            case "help":
            case "list":
                if(userCommand.length>1){
                throw new IllegalDukeException("Command " + userCommand[0] + WRONG_USAGE_OF_COMMAND);
                }
                break;
            case "done":
            case "delete":
                if(userCommand.length==1) {
                    throw new IllegalDukeException(NO_TASK_NUMBER);
                } else {
                    try {
                        if(!userCommand[1].equals("all")){
                            int number = parseInt(userCommand[1]) - 1;
                        }
                    } catch (NumberFormatException e) {
                            throw new IllegalDukeException(NOT_INTEGER);
                    }
                }
                break;
            case "todo":
            case "deadline":
            case "event":
            case "find" :
                if(userCommand.length==1){
                    throw new IllegalDukeException("\t ☹ OOPS!!! The description of a " + userCommand[0] +
                            " cannot be empty."
                    );
                }else if (userCommand[0].equals("deadline") || userCommand[0].equals("event")){
                    int i=1;
                    boolean hasTiming=false;
                    while(i<userCommand.length) {
                        if (userCommand[i].charAt(0) == '/') {
                            hasTiming = true;
                        }
                        i++;
                    }
                    if (!hasTiming) {
                            throw new IllegalDukeException(INDICATE_TIMING);
                    }
                }
                break;
            default:
                throw new IllegalDukeException(WRONG_INPUT);
        }
        out = userCommand;
        return out;
    }
/**
 * This method assigns each command enter by User to the suitable Command class
 *
 * This method ensures that User input is validated before assigning to
 * designated Command class. Therefore it calls validateCommand() before
 * assigning commands through switch statement.
 *
 * @param input is the full command entered by User.
 * @return outCommand This is the designated command based on user input.
 * @throws IllegalDukeException if command enter is invalid
 * or illegal.
 */
    public static Command parse(String input) throws IllegalDukeException {
        String[] fullCommand = validateCommand(input);
        Command outCommand;
        switch (fullCommand[0]) {
            case "bye":
                outCommand = new ByeCommand(fullCommand);
                break;
            case "list":
            case "help":
                outCommand = new Command(fullCommand);
                break;
            case "done":
                outCommand = new DoneCommand(fullCommand);
                break;
            case "todo":
            case "deadline":
            case "event":
                outCommand = new AddCommand(fullCommand);
                break;
            case "delete":
                outCommand = new DeleteCommand(fullCommand);
                break;
            case "find" :
                outCommand = new FindCommand(fullCommand);
                break;
            default:
                outCommand = new WrongCommand(fullCommand);
                break;
        }
        return outCommand;
    }
}
