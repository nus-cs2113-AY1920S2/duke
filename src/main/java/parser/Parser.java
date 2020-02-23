package parser;

import static misc.Messages.MESSAGE_INCORRECT_DEADLINE_INPUT;
import static misc.Messages.MESSAGE_INCORRECT_TASK_INPUT;
import static misc.Messages.MESSAGE_INVALID_COMMAND_RESULT;
import static misc.Messages.MESSAGE_INCORRECT_EVENT_INPUT;
import static misc.Messages.MESSAGE_EVENT_MISSING_START_DATE_TIME;
import static misc.Messages.MESSAGE_EVENT_MISSING_END_DATE_TIME;
import static misc.Messages.MESSAGE_EVENT_MISSING_WORD_TO;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.FilterCommand;
import command.InvalidCommandException;
import command.ListCommand;
import duke.task.InvalidTaskArgumentException;

/** 
 * Represents a Parser used by the program to parse user inputs.
 * A user input is broken down into a commandWord to create a command pertaining
 * to the command word used. The rest of the inputs given by the user will then be 
 * parsed into optional arguments to construct a task.
 * 
 * Each string will be split into a word array to carry different arguments.
 * For example: todo return book will be split into wordArray = {[todo], [return book]}. 
 * This explains the relative position index that indicates the specific commandWord
 * and the following optional task arguments.
 * 
 */
public class Parser {
    
    /** The relative position index that indicates the commandWord. */
    public static int COMMAND_INDEX = 0;
    
    /** A string array that stores all possible preposition used by the user. */
    private final String[] prepositionArray = {"/by", "/on"};
    
    /** Refers to possible command keywords such as 'list', 'delete', 'todo' etc. */
    private final String commandWord;
    
    /** An optional string to represent the description given for a task. */
    private final Optional<String> taskDescription;
    
    /** An optional string to represent the deadline via date and time stated for a deadline. */
    private final Optional<LocalDateTime> taskDeadline;
    
    /** An optional string to represent the starting date and time for an event. */
    private final Optional<LocalDateTime> taskStartDateTime;
    
    /** An optional string to represent the ending date and time for an event. */
    private final Optional<LocalDateTime> taskEndDateTime;
    
    /** 
     * An event phrase can be split further into eventWordArray.
     * For example: 'E | 1 | homework | 1200-12-12T10:00 to 1200-12-12T11:00'
     * will be split into wordArray = {[E], [1], [homework],
     * [1200-12-12T10:00 to 1200-12-12T11:00]}.
     * 
     * The wordArray can be split further into an eventWordArray.
     * For example: '1200-12-12T10:00 to 1200-12-12T11:00' will be
     * split into eventWordArray = {[1200-12-12T10:00], [to], 
     * [1200-12-12T11:00]}. Hence, the default size of this eventWordArray
     * is 3.
     */
    private final int EVENT_START_DATE_TIME_INDEX = 0;
    
    private final int EVENT_WORD_TO_INDEX = 1;
    
    private final int EVENT_END_DATE_TIME_INDEX = 2;
    
    private final int DEFAULT_EVENT_WORD_ARRAY_SIZE = 3;
    
    /** A constructor of a Parser object to encapsulate task arguments. */
    public Parser(String commandWord, Optional<String> taskDescription, 
            Optional<LocalDateTime> taskDeadline, 
            Optional<LocalDateTime> taskStartDateTime,
            Optional<LocalDateTime> taskEndDateTime) {
        
        this.commandWord = commandWord;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
        this.taskStartDateTime = taskStartDateTime;
        this.taskEndDateTime = taskEndDateTime;
    }
    
    /** Initializes the Parser when the program starts. */
    public Parser() {
        this.commandWord = "";
        this.taskDescription = Optional.empty();
        this.taskDeadline = Optional.empty();
        this.taskStartDateTime = Optional.empty();
        this.taskEndDateTime = Optional.empty();
    }
    
    /** 
     * Parse the input from the user into commands that can be executed.
     * Throws an InvalidCommandException if a command cannot be created 
     * based on the user's input.
     * 
     * @param userInput The string text of input from the user.
     * @return A Command that is to be executed.
     * @throws InvalidCommandException  An invalid command due to an invalid
     *                                  function of the program.
     */
    public Command parseCommand(String userInput) {
        Parser parser = parseUserInputIntoCommandArguments(userInput);
        String commandWord = parser.commandWord;
        Command command;
        
        switch(commandWord) {
        case "find":
            command = new FindCommand(parser.taskDescription);
            break;
        case "filter":
            command = new FilterCommand(parser.taskDescription);
            break;
        case "list": 
            command = new ListCommand();
            break;
        case "done":
            command = new DoneCommand(parser.taskDescription);
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "todo":
            command = new AddCommand(commandWord, parser.taskDescription);
            break;
        case "deadline":
            command = new AddCommand(commandWord, parser.taskDescription, 
                    parser.taskDeadline);
            break;
        case "event":
            command = new AddCommand(commandWord, parser.taskDescription, 
                    parser.taskStartDateTime, parser.taskEndDateTime);
            break;
        case "delete":
            command = new DeleteCommand(parser.taskDescription);
            break;
        default: 
            throw new InvalidCommandException(MESSAGE_INVALID_COMMAND_RESULT);
        }
        
        return command;
    }
    
    /** Returns true if a user input contains a preposition. */
    private boolean hasPreposition(String[] argsArray) {
        return IntStream.range(0, argsArray.length)
                .anyMatch(wordIndex -> {
                    String word = argsArray[wordIndex];
                    return Arrays.stream(prepositionArray)
                            .anyMatch(preposition -> preposition.equals(word));
                });
    }
    
    /** Returns the preposition index of the user's input. */
    private int getPrepositionIndex(String[] argsArray) {
        return IntStream.range(0, argsArray.length)
                .filter(wordIndex -> {
                    String word = argsArray[wordIndex];
                    return Arrays.stream(prepositionArray)
                            .anyMatch(preposition -> preposition.equals(word));
                })
                .reduce(0, (x, y) -> x + y);
    }
    
    /** 
     * Parse user's input into a string of task's description to be used as a parameter
     * for creating a task, if the user's input does not contain a preposition. 
     */
    private Optional<String> parsePhraseWithoutPrepositionIntoTaskDescription(
            String[] userInputArray) {      
        String phrase = "";
        
        for (int i = 1; i < userInputArray.length; i++) {
            if (i != 1) {
                phrase += (" " + userInputArray[i]);
            } else {
                phrase += (userInputArray[i]);
            }
        }
        
        return Optional.of(phrase);
    }
    
    /** 
     * Parse user's input into a string of task's description to be used as a parameter
     * for creating a task, if the user's input contains a preposition.
     * 
     * @param userInputArray An array of words split from the user's input using the regex (" ").
     * @param prepositionIndex The index of the preposition in the userInputArray.
     * @return A string to be recognized as a task description phrase.
     */
    private Optional<String> parsePhraseWithPrepositionIntoTaskDescription(
            String[] userInputArray, int prepositionIndex) {
        
        String phrase = "";        
        
        for (int i = 1; i < prepositionIndex; i++) {
            if (i != 1) {
                phrase += (" " + userInputArray[i]);
            } else {
                phrase += (userInputArray[i]);
            }
        }
        
        // Wrap an optional on the phrase.
        Optional<String> optionalPhrase;
        if (phrase.equals("")) {
            optionalPhrase = Optional.empty();
        } else {
            optionalPhrase = Optional.ofNullable(phrase);
        }
        
        return optionalPhrase;
    }  
    
    /** 
     * Parse user's input into a string of task's deadline to be used as a parameter
     * for creating a deadline task.
     * 
     * @param userInputArray An array of words split from the user's input using the regex (" ").
     * @param prepositionIndex The index of the preposition in the userInputArray.
     * @return A string to be recognized as a task deadline phrase.
     */
    private Optional<LocalDateTime> parseTaskDeadline(
            String[] userInputArray, int prepositionIndex) {
        
        int deadlineIndex = prepositionIndex + 1;       
        String deadline = "";
        
        deadline += (userInputArray[deadlineIndex]);
        try {
            LocalDateTime taskDeadline = LocalDateTime
                    .parse(deadline);
            
            return Optional.of(taskDeadline);
        } catch (DateTimeParseException dtpe) {
            throw new InvalidTaskArgumentException(
                    MESSAGE_INCORRECT_DEADLINE_INPUT);
        }
    }
    
    /** 
     * Parse user's input into a string of task's start date and time to be used as a parameter
     * for creating an event task.
     * 
     * @param userInputArray An array of words split from the user's input using the regex (" ").
     * @param prepositionIndex The index of the preposition in the userInputArray.
     * @return A formatted starting dateTime for the event.
     * @throws InvalidTaskArgument If a dateTime object cannot be created. 
     */
    private Optional<LocalDateTime> parseTaskStartDateTime(
            String[] userInputArray, int prepositionIndex) {
        
        String startDateTime = getStartDateTimeFromEventPhrase(
                userInputArray, prepositionIndex);

        try {
            LocalDateTime taskStartDateTime = LocalDateTime
                    .parse(startDateTime);
            
            return Optional.of(taskStartDateTime);
        } catch (DateTimeParseException dtpe) {
            throw new InvalidTaskArgumentException(
                    MESSAGE_INCORRECT_EVENT_INPUT);
        }
    }
    
    /** 
     * Parse user's input into a string of task's end date and time to be used as a parameter
     * for creating an event task.
     * 
     * @param userInputArray An array of words split from the user's input using the regex (" ").
     * @param prepositionIndex The index of the preposition in the userInputArray.
     * @return A formatted ending dateTime for the event.
     * @throws InvalidTaskArgument If a dateTime object cannot be created. 
     */
    private Optional<LocalDateTime> parseTaskEndDateTime(
            String[] userInputArray, int prepositionIndex) {
      
        String endDateTime = getEndDateTimeFromEventPhrase(
                userInputArray, prepositionIndex);
        
        try {
            LocalDateTime taskEndDateTime = LocalDateTime
                    .parse(endDateTime);
            
            return Optional.of(taskEndDateTime);
        } catch (DateTimeParseException dtpe) {
            throw new InvalidTaskArgumentException(
                    MESSAGE_INCORRECT_EVENT_INPUT);
        }
    } 
 
    /** 
     * Parse a new phrase from the user's input starting after the preposition word to 
     * the end of sentence. 
     * For example, the phrase 'event party /on 1200-12-12T10:00 to 1200-12-12T11:11' will
     * return 1200-12-12T10:00 to 1200-12-12T11:11'.
     * 
     * @param userInputArray An array of words split from the user's input using the regex (" ").
     * @param prepositionIndex The index of the preposition in the userInputArray.
     * @return A new phrase starting after the preposition word to the end of sentence. 
     */
    private String parseEventPhraseAfterPreposition(
            String[] userInputArray, int prepositionIndex) {
        
        String phrase = "";
        
        for (int k = prepositionIndex + 1; k < userInputArray.length; k++) {
            if (k > prepositionIndex + 1) {
                phrase += (" " + userInputArray[k]);
            } else {
                phrase += (userInputArray[k]);
            }
        }
        
        return phrase;
    }
    
    /** 
     * Parse a new phrase from the user's event phrase derived from parseEventPhraseAfterPreposition() 
     * For example, the phrase '1200-12-12T10:00 to 1200-12-12T11:11' will
     * return '1200-12-12T10:00'.
     * 
     * @param userInputArray An array of words split from the user's input using the regex (" ").
     * @param prepositionIndex The index of the preposition in the userInputArray.
     * @return A new phrase representing the event starting date and time.
     * @throws InvalidTaskArgument  If a starting date and time is empty or not in
     *                              a correct format of 'YYYY-MM-DDTHH:mm'.
     */
    private String getStartDateTimeFromEventPhrase(
            String[] userInputArray, int prepositionIndex) {
        
        String eventPhraseAfterPreposition = 
                parseEventPhraseAfterPreposition(
                        userInputArray, prepositionIndex);
        
        String[] eventPhraseArray = eventPhraseAfterPreposition.split(" ");
        
        if (eventPhraseArray[EVENT_START_DATE_TIME_INDEX].equals("to")) {
            throw new InvalidTaskArgumentException(
                    MESSAGE_EVENT_MISSING_START_DATE_TIME);
        } else if (!eventPhraseArray[EVENT_WORD_TO_INDEX].equals("to")) {
            throw new InvalidTaskArgumentException(
                    MESSAGE_EVENT_MISSING_WORD_TO);
        } else if (eventPhraseArray.length != 
                DEFAULT_EVENT_WORD_ARRAY_SIZE) {
            throw new InvalidTaskArgumentException(
                    MESSAGE_EVENT_MISSING_END_DATE_TIME);
        }
        return eventPhraseArray[EVENT_START_DATE_TIME_INDEX];
    }
    
    /** 
     * Parse a new phrase from the user's event phrase derived from parseEventPhraseAfterPreposition() 
     * For example, the phrase '1200-12-12T10:00 to 1200-12-12T11:11' will
     * return '1200-12-12T11:11'.
     * 
     * @param userInputArray An array of words split from the user's input using the regex (" ").
     * @param prepositionIndex The index of the preposition in the userInputArray.
     * @return A new phrase representing the event ending date and time.
     * @throws InvalidTaskArgument  If a ending date and time is empty or not in
     *                              a correct format of 'YYYY-MM-DDTHH:mm'.
     */
    private String getEndDateTimeFromEventPhrase(
            String[] userInputArray, int prepositionIndex) {
        
        String eventPhraseAfterPreposition = 
                parseEventPhraseAfterPreposition(
                        userInputArray, prepositionIndex);
        
        String[] eventPhraseArray = eventPhraseAfterPreposition.split(" ");
        
         
        return eventPhraseArray[EVENT_END_DATE_TIME_INDEX];
    }
    
    /**
     * Parse a user's input into a format specified by the constructor of a command.
     * If the user's input contains a preposition, it can be parsed into command arguments
     * for both Deadlines task and Events task. Else, it can only be parsed into command
     * arguments for ToDos task only.
     * 
     * @param userInput A string of text from the user's input.
     * @return A parser object that can create a command.
     */
    private Parser parseUserInputIntoCommandArguments(String userInput) {
        String[] argsArray = userInput.trim().split(" ");
        int prepositionIndex = 0;
        
        Optional<String> taskDescription = Optional.empty();
        Optional<LocalDateTime> taskDeadline = Optional.empty();
        Optional<LocalDateTime> taskStartDateTime = Optional.empty();
        Optional<LocalDateTime> taskEndDateTime = Optional.empty();        
        
        String commandWord = argsArray[COMMAND_INDEX];
        
        if (hasPreposition(argsArray)) {
            prepositionIndex = getPrepositionIndex(argsArray);
            String preposition = argsArray[prepositionIndex];
            
            taskDescription = 
                    parsePhraseWithPrepositionIntoTaskDescription(
                            argsArray, prepositionIndex);
            
            switch (preposition) {
            case "/by" : 
                taskDeadline = parseTaskDeadline(
                        argsArray, prepositionIndex);
                break;
            case "/on" :
                taskStartDateTime = parseTaskStartDateTime(
                        argsArray, prepositionIndex);
                taskEndDateTime = parseTaskEndDateTime(
                        argsArray, prepositionIndex);
                break;
            default:
                throw new InvalidTaskArgumentException(
                        MESSAGE_INCORRECT_TASK_INPUT);
            }
        } else if (!hasPreposition(argsArray)) {
            taskDescription = 
                    parsePhraseWithoutPrepositionIntoTaskDescription(
                            argsArray);
        }
                  
        return new Parser(commandWord, taskDescription, taskDeadline,
                taskStartDateTime, taskEndDateTime); 
    }
}
