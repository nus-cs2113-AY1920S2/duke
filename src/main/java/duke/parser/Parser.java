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
    public static final String WRONG_INPUT="\t OOPS!!! I'm sorry, but I don't know what that means. :(" +
            " Input command is wrong. Enter \"help\" for list of accepted commands";
    public static final String NO_TASK_NUMBER = "\t Please enter a task number!";
    public static final String MULTIPLE_WHITE_SPACES= "\\s+";
    public static final String WRONG_USAGE_OF_COMMAND = " cannot be used with other commands";
    public static final String NOT_INTEGER = "\t Argument after command used is not an integer!";
    public static final String INDICATE_TIMING_INDICATOR = "\t Use \"/\" to indicate timing!";
    public static final String NON_POSITIVE_INTEGER = "\t Task index cannot be negative or zero!";
    public static final String INDICATE_TIMING ="\t Please enter a timing!";
    public static final String WRONG_TIMING_FORMAT="\t Timing format is wrong! Ensure it is either \"yyyy-MM-dd\"" +
            " or \"yyyy-MM-dd HH:mm\" ***Hours are in 24 hrs format***";
    public static final String AVOID_DELIMITER = "\t Please refrain from using any \"~\" as it will lead to" +
            " improper storage of your list!";
    public static final String TIMING_INDICATOR = "/";
    public static final String SPACE = " ";
    public static final String EMPTY_TIMING_CHECKER = "*";

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
     * This method converts timing from LocalDateTime<br>
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
        String minute = formatTiming(min);
        String hourString = formatTiming(hour);
        return String.format(" %s, %s %s %s %s:%s ", dayOfWeek.toString(), day.toString(), month.toString(),
                year.toString(), hourString, minute);
    }
/**
 * This method separates User input into two Strings (Action and Timing)<br>
 *
 * @param temp This is the array of Strings that was split by parseCommand().<br>
 * @return String[]  This returns the two Strings, Action and Timing.<br>
 * @throws IllegalDukeException if command entered by the User is invalid.<br>
 */
    public static String[] getTaskInfo(String[] fullCommand) throws IllegalDukeException {
        StringBuilder action = new StringBuilder();
        StringBuilder timing = new StringBuilder();
        boolean flip = false;
        if(fullCommand[0].equals("todo")){
            for(int i=1; i<fullCommand.length; i++){
                action.append(fullCommand[i]).append(SPACE);
            }
        }else {
            for (int i = 1; i < (fullCommand.length); i++) {
                if (fullCommand[i].contains(TIMING_INDICATOR)) {
                    flip = true;
                    if(fullCommand[i].charAt(0)=='/'){
                        timing.append(fullCommand[i]).append(SPACE);
                    }else{
                        String[] temp = fullCommand[i].split(TIMING_INDICATOR);
                        action.append(temp[0]).append(SPACE);
                        timing.append(temp[1]).append(SPACE);
                    }
                }else {
                    if (flip) {
                        timing.append(fullCommand[i]).append(SPACE);
                    } else {
                        action.append(fullCommand[i]).append(SPACE);
                    }
                }
            }
            timing = new StringBuilder(timing.toString().replace(TIMING_INDICATOR, SPACE));
            timing = new StringBuilder(timing.toString().trim());
            validateTiming(timing.toString());
            timing = new StringBuilder(convertToDateFormat(timing.toString()));
        }
        action = new StringBuilder(action.toString().trim());
        if(action.toString().contains("~")){
            throw new IllegalDukeException(AVOID_DELIMITER);
        }else if(action.length() == 0){
            throw new IllegalDukeException("\t OOPS!!! The description of a " + fullCommand[0] +
                    " cannot be empty.");
        }
        String[] temp2 = new String[2];
        temp2[0] = action.toString();
        temp2[1] = timing.toString();
        return temp2;
    }
    /**
     * This method formats timing with an Integer value smaller<br>
     * than 10 to be printed with a "0" at the front<br>
     * @param timing This is the timing
     * @return String that represents two digits.
     */
    public static String formatTiming(Integer timing){
        String formattedTiming;
        if(timing<10){
            formattedTiming= "0" + timing.toString();
        }else{
            formattedTiming= timing.toString();
        }
        return formattedTiming;
    }
    /**
     * This method checks is User indicated timing and its indicator, "/". <br>
     * @param inCommand This is the full command entered by User. <br>
     * @throws IllegalDukeException if the command entered is invalid or illegal.
     */
    public static void checkTimingIndicator(String inCommand) throws IllegalDukeException{
        if(inCommand.contains(TIMING_INDICATOR)){
            String check = inCommand + EMPTY_TIMING_CHECKER;
            String[] tempCommand = check.split(TIMING_INDICATOR);
            if(tempCommand[1].equals(EMPTY_TIMING_CHECKER)){
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
                throw new IllegalDukeException("\t Command " + userCommand[0] + WRONG_USAGE_OF_COMMAND);
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
                outCommand = new Command(fullCommand);
                break;
        }
        return outCommand;
    }
}
