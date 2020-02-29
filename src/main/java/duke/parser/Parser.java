package duke.parser;

import duke.commands.*;
import duke.asset.IllegalDukeException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;
/**
 * This class handles with making sense of commands<br>
 * entered by Users<br>
 * This class has four methods:<br>
 * 1) parseCommand() : splits User input by spaces<br>
 * 2) getTaskInfo() : separates the User input into two Strings (Action and Timing)<br>
 * 3) validateCommand() : ensures that User input is valid and legal<br>
 * 4) parse() : assigns each command enter by User to the suitable Command class<br>
 */
public class Parser  {
    public static final String WRONG_INPUT="\t OOPS!!! I'm sorry, but I don't know what that means :(\n" +
            "\t Input command is wrong. Enter \"help\" for list of accepted\n\t commands";
    public static final String NO_TASK_NUMBER = "\t Please enter a task number!";
    public static final String MULTIPLE_WHITE_SPACES= "\\s+";
    public static final String WRONG_USAGE_OF_COMMAND = " cannot be used with other commands";
    public static final String NOT_INTEGER = "\t Argument after command used is not an integer!";
    public static final String INDICATE_TIMING_INDICATOR = "\t Use \"/\" to indicate timing!";
    public static final String NON_POSITIVE_INTEGER = "\t Task index cannot be negative or zero!";
    public static final String INDICATE_TIMING ="\t Please enter a timing!";
    public static final String WRONG_TIMING_FORMAT="\t Timing format is wrong! Ensure it is either \"yyyy-MM-dd\"\n" +
            "\t or \"yyyy-MM-dd HH:mm\" ***Hours are in 24 hrs format***";
    public static final String AVOID_DELIMITER = "\t Please refrain from using any \"~\" as it will lead to\n" +
            "\t improper storage of your list!";

/**
 * This method splits User input by spaces into an array of Strings<br>
 *
 * @param inCommand  This is the full input entered by User.<br>
 * @return This returns the array of Strings that is split by spaces.<br>
 */
    public static String[] parseCommand(String inCommand) {
        inCommand = inCommand.trim();
        String[] temp = inCommand.split(MULTIPLE_WHITE_SPACES);
        return temp;
    }
/**
 * This method separates User input into two Strings (Action and Timing)<br>
 *
 * @param temp This is the array of Strings that was split by parseCommand().<br>
 * @return String[]  This returns the two Strings, Action and Timing.<br>
 * @throws IllegalDukeException if command entered by the User is invalid.<br>
 */
    public static String[] getTaskInfo(String[] temp) throws IllegalDukeException {
        String action = "";
        String timing = "";
        boolean flip = false;
        if(temp[0].equals("todo")){
            for(int i=1; i<temp.length; i++){
                action+=temp[i] + " ";
            }
        }else {
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
            timing=timing.replace('/',' ');
            timing = timing.trim();
            validateTiming(timing);
            timing= convertToDateFormat(timing);
        }
        action = action.trim();
        if(action.contains("~")){
            throw new IllegalDukeException(AVOID_DELIMITER);
        }else if(action.isEmpty()){
            throw new IllegalDukeException("\t OOPS!!! The description of a " + temp[0] +
                    " cannot be empty.");
        }
        String[] temp2 = new String[2];
        temp2[0] = action;
        temp2[1] = timing;
        return temp2;
    }
    /**
     * This method converts timing from LocalDateTime<br>
     *
     * format to a meaningful String format.<br>
     * @param inTiming This is the timing in LocalDateTime.<br>
     * @return timing in the following format: dd Month yyyy HH:mm<br>
     */
    public static String convertToDateFormat(String inTiming){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(inTiming, formatter);
        Integer day = dateTime.getDayOfMonth();
        DayOfWeek dayOfWeek= dateTime.getDayOfWeek();
        Month month =dateTime.getMonth();
        Integer year =dateTime.getYear();
        Integer hour =dateTime.getHour();
        Integer min =dateTime.getMinute();
        String minute;
        if(min<10){
            minute= "0" + min.toString();
        }else{
            minute= min.toString();
        }
        return String.format(" %s, %s %s %s %s:%s ", dayOfWeek.toString(), day.toString(), month.toString(),
                year.toString(), hour.toString(), minute);
    }
    /**
     * This method ensures that timing entered by User is valid.<br>
     * @param timing This is the timing given by User.<br>
     * @throws IllegalDukeException if timing entered is invalid.<br>
     */
    public static void validateTiming(String timing) throws IllegalDukeException {
        try{
            String[] temp =timing.split(MULTIPLE_WHITE_SPACES);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(timing, formatter);
        }catch(DateTimeParseException e){
            throw new IllegalDukeException(WRONG_TIMING_FORMAT);
        }
    }
    /**
     * This method checks is User indicated timing and its indicator, "/". <br>
     * @param inCommand This is the full command entered by User. <br>
     * @throws IllegalDukeException if the command entered is invalid or illegal.
     */
    public static void checkTimingIndicator(String inCommand) throws IllegalDukeException{
        if(inCommand.contains("/")){
            String check = inCommand + "*";
            String[] tempCommand = check.split("/");
            if(tempCommand[1].equals("*")){
                throw new IllegalDukeException(INDICATE_TIMING);
            }
        }else{
            throw new IllegalDukeException(INDICATE_TIMING_INDICATOR);
        }
    }

    /**
     * This method checks if User entered an Integer for the Task Number. <br>
     * @param userCommand This is the full command that has been split into an array of Strings<br>
     * @throws IllegalDukeException if the command entered by User is invalid or illegal.<br>
     */
    public static void checkInteger(String[] userCommand) throws IllegalDukeException{
        try {
            if(!userCommand[1].equals("all")){
                int number = parseInt(userCommand[1]) - 1;
                if(number<=-1){
                    throw new IllegalDukeException(NON_POSITIVE_INTEGER);
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalDukeException(NOT_INTEGER);
        }
    }
/**
 * This method ensures that User input is valid and legal.<br>
 *
 * @param inCommand This is the full input given by User.<br>
 * @return String[] This is the validated array of Strings<br>
 * to be used in the parse().<br>
 * @throws IllegalDukeException if command enter is invalid or illegal.<br>
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
                    checkInteger(userCommand);
                }
                break;
            case "todo":
            case "deadline":
            case "event":
            case "find" :
                if(userCommand.length==1) {
                    throw new IllegalDukeException("\t OOPS!!! The description of a " + userCommand[0] +
                            " cannot be empty.");
                }else if (userCommand[0].equals("deadline") || userCommand[0].equals("event")){
                    checkTimingIndicator(inCommand);
                }
                break;
            default:
                throw new IllegalDukeException(WRONG_INPUT);
        }
        out = userCommand;
        return out;
    }
/**
 * This method assigns each command enter by User to the suitable Command class.<br>
 * This method ensures that User input is validated before assigning to<br>
 * designated Command class. Therefore it calls validateCommand() before<br>
 * assigning commands through switch statement.<br>
 *
 * @param input is the full command entered by User.<br>
 * @return outCommand This is the designated command based on user input.<br>
 * @throws IllegalDukeException if command enter is invalid or illegal.<br>
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
