package Duke.Parser;

import Duke.Commands.*;
import Duke.Asset.IllegalDukeException;

import javax.swing.text.DateFormatter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    public static final String INDICATE_TIMING_INDICATOR = "\t Use \"/\" to indicate timing!";
    public static final String NON_POSITIVE_INTEGER = "\t Task index cannot be negative or zero!";
    public static final String INDICATE_TIMING ="\t Please enter a timing!";
    public static final String WRONG_TIMING_FORMAT="\t Timing format is wrong! Ensure it is either \"yyyy-MM-dd\"\n" +
            "\t or \"yyyy-MM-dd HH:mm\" ***Hours are in 24 hrs format***";

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
        String[] temp2 = new String[2];
        temp2[0] = action;
        temp2[1] = timing;
        return temp2;
    }
    /**
     * This method converts timing from LocalDateTime
     * format to a meaningful String format.
     *
     * @param inTiming This is the timing in LocalDateTime.
     * @return timing in the following format: dd Month yyyy HH:mm
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
       String outTiming = " " + dayOfWeek.toString() + ", " + day.toString() + " " + month.toString() + " " +
               year.toString() + " " + hour.toString() + ":" + minute + " ";
       return outTiming;
    }
    /**
     * This method ensures that timing entered by User is valid.
     *
     * @param timing This is the timing given by User.
     * @throws IllegalDukeException if timing entered is invalid
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
                            if(number<=-1){
                                throw new IllegalDukeException(NON_POSITIVE_INTEGER);
                            }
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
                    boolean hasTimingIndicator=false;
                    String[]tempCommand =inCommand.split("/");
                    if(tempCommand.length==2){
                        hasTimingIndicator = true;
                    }
                    if (!hasTimingIndicator) {
                            throw new IllegalDukeException(INDICATE_TIMING_INDICATOR);
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
