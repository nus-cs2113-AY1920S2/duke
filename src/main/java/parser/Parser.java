package parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import commands.Command;
import common.exceptions.DukeException;

public class Parser {
    public static int COMMAND_TYPE = 0;
    public static int COMMAND_DESCRIPTION = 1;
    public static int COMMAND_CHECK = 2;
    public static int COMMAND_DATE = 3;
    public static int COMMAND_TIME = 4;
    
    /**
     * Parses a String user input into a Command that can be easily understood by the chatbot.
     * The necessary information that will be extracted are the command type, command description,
     * command check (used to check input format, optional), date (optional), time (optional).
     * 
     * @param input User input that has been obtained by the UI.
     * @return command Command that contains all necessary information for the chatbot to process.
     * @throws DukeException
     */
    public Command parse(String input) throws DukeException {
        String[] inputCommand = input.split(" ");
        String[] commandParts = new String[5];
        String commandType = inputCommand[0];
        commandParts[COMMAND_TYPE] = commandType;
        if (inputCommand.length > 1) {
            commandParts[COMMAND_DESCRIPTION] = inputCommand[1];
            int index = -1;
            for (int i = 0; i < inputCommand.length; i++) {
                if (inputCommand[i].equals("/by") || inputCommand[i].equals("/at")) {
                    index = i;
                }
            }
            if (index == -1) {
                String description = input.replaceFirst(commandType, "");
                commandParts[COMMAND_DESCRIPTION] = description.trim();
            } else if (index > 1) {
                String description = "";
                for (int i = 1; i < index; i++) {
                    description += inputCommand[i] + " ";
                }
                commandParts[COMMAND_DESCRIPTION] = description.trim();
                int j = 2;
                int i = index;
                while (i < inputCommand.length && j < 5) {
                    commandParts[j] = inputCommand[i];
                    i++;
                    j++;
                }
            }
        }
        Command command;
        try {
            command = process(commandParts);
        } catch (DukeException e) {
            command = new Command(e);
        }
        return command;
    }
    
    /**
     * Parses stored text from the storage and extracts all relevant information.
     * This information is to be passed back to Storage for the purposes of populating
     * the task list according to the stored tasks in the text file.
     * 
     * @param input Input from saved text in text file.
     * @return commandParts String[] containing needed task information.
     */
    public String[] parseFromStorage(String input) {
        String[] commandParts = new String[5];
        int index = input.indexOf('(');
        String description;
        if (index == -1) {
            description = input.substring(7);
        } else {
            description = input.substring(7, index);
            int index2 = input.indexOf(':') + 2;
            String trimmed = input.substring(index2, input.length() - 1);
            String[] inputCommands = trimmed.split(" ");
            String[] dateTime = new String[2];
            for (int i = 0; i < inputCommands.length; i++) {
                dateTime[i] = inputCommands[i];
            }
            commandParts[COMMAND_DATE] = dateTime[0];
            commandParts[COMMAND_TIME] = dateTime[1];
        }
        commandParts[COMMAND_TYPE] = input.substring(1, 2);
        commandParts[COMMAND_DESCRIPTION] = description;
        return commandParts;
    }
    
    /**
     * Needed for the parse method. A String[] containing command information
     * is processed into a Command that can be easily understood by the chatbot.
     * 
     * @param commandParts String[] that is to be processed into a Command.
     * @return Command Command that is understood by the chatbot.
     * @throws DukeException
     */
    public Command process(String[] commandParts) throws DukeException {
        String commandType = commandParts[COMMAND_TYPE];
        Optional<String> description = Optional.ofNullable(commandParts[COMMAND_DESCRIPTION]);
        Optional<String> check = Optional.ofNullable(commandParts[COMMAND_CHECK]);
        Optional<LocalDate> date = parseDate(commandParts[COMMAND_DATE]);
        Optional<LocalTime> time = parseTime(commandParts[COMMAND_TIME]);
        return new Command(commandType, description, check, date, time);
    }
    
    /**
     * Parses a String representation of a date into a LocalDate object which can be
     * easily understood by the chatbot. The accepted input formats are dictated by 
     * a DateTimeParser.
     * 
     * @param date String representation of a date.
     * @return parsedDate Optional<LocalDate> that contains a LocalDate if the input date is valid.
     * @throws DukeException
     */
    public Optional<LocalDate> parseDate(String date) throws DukeException {
        DateTimeParser dateParser = new DateTimeParser();
        Optional<LocalDate> parsedDate;
        try {
            parsedDate = Optional.ofNullable(LocalDate.parse(date, dateParser.dateKey));
        } catch (NullPointerException e) {
            parsedDate = Optional.empty();
        } catch (DateTimeParseException e) {
            throw new DukeException("Oops!! Wrong date format.");
        }
        return parsedDate;
    }
    
    /**
     * Parses a String representation of a time into a LocalTime object which can be
     * easily understood by the chatbot. The accepted input formats are dictated by 
     * a DateTimeParser.
     * 
     * @param time String representation of a date.
     * @return parsedTime Optional<LocalTime> that contains a LocalTime if the input time is valid.
     * @throws DukeException
     */
    public Optional<LocalTime> parseTime(String time) throws DukeException {
        DateTimeParser timeParser = new DateTimeParser();
        Optional<LocalTime> parsedTime;
        try {
            parsedTime = Optional.ofNullable(LocalTime.parse(time, timeParser.timeKey));
        } catch (NullPointerException e) {
            parsedTime = Optional.empty();
        } catch (DateTimeParseException e) {
            throw new DukeException("Oops!! Wrong time format.");
        }
        return parsedTime;
    }
    
}
