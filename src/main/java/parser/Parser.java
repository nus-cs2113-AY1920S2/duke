package parser;

import static misc.Messages.MESSAGE_INVALID_COMMAND_RESULT;

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

/** 
 * Represents a Parser used by the program that is
 * responsible in parsing all user inputs.
 */
public class Parser {
    
    /** A string array that stores all possible preposition used by the user. */
    private final String[] prepositionArray = {"/by", "/at"};
    
    /** Refers to possible command keywords such as 'list', 'delete', 'todo' etc. */
    private final String commandType;
    
    /** An optional string to represent the information given for a task. */
    private final Optional<String> taskInfo;
    
    /** An optional string to represent the requirements stated for a task. */
    private final Optional<String> taskRequirement;
    
    /** 
     * Constructor of a Parser
     * 
     * @param commandType
     * @param taskInfo
     * @param taskRequirement
     */
    public Parser(String commandType, Optional<String> taskInfo, Optional<String> taskRequirement) {
        this.commandType = commandType;
        this.taskInfo = taskInfo;
        this.taskRequirement = taskRequirement;
    }
    
    /** 
     * Constructor of a Parser that does not take in any arguments.
     */
    public Parser() {
        this.commandType = "";
        this.taskInfo = Optional.empty();
        this.taskRequirement = Optional.empty();
    }
    
    /** 
     * Parse the input from the user into commands that can be executed.
     * Throws an exception if a command cannot be created based on the user's input.
     * 
     * @param userInput
     * @return Command
     * @throws InvalidCommandException
     */
    public Command parseCommand(String userInput) {
        Parser parser = parseUserInputIntoCommandArguments(userInput);
        String commandType = parser.commandType;
        Command command;
        
        switch(commandType) {
        case "find":
            command = new FindCommand(parser.taskInfo.get());
            break;
        case "filter":
            command = new FilterCommand(parser.taskInfo.get());
            break;
        case "list": 
            command = new ListCommand();
            break;
        case "done":
            command = new DoneCommand(Integer.parseInt(parser.taskInfo.get()));
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "todo":
            command = new AddCommand(commandType, parser.taskInfo, parser.taskRequirement);
            break;
        case "deadline":
            command = new AddCommand(commandType, parser.taskInfo, parser.taskRequirement);
            break;
        case "event":
            command = new AddCommand(commandType, parser.taskInfo, parser.taskRequirement);
            break;
        case "delete":
            command = new DeleteCommand(Integer.parseInt(parser.taskInfo.get()));
            break;
        default: 
            throw new InvalidCommandException(MESSAGE_INVALID_COMMAND_RESULT);
        }
        
        return command;
    }
    
    /** Test if a user input contains a preposition. */
    public boolean hasPreposition(String[] argsArray) {
        return IntStream.range(0, argsArray.length)
                .anyMatch(wordIndex -> {
                    String word = argsArray[wordIndex];
                    return Arrays.stream(prepositionArray)
                            .anyMatch(preposition -> preposition.equals(word));
                });
    }
    
    /** 
     * Get the preposition index from a array of user's input arguments.
     * 
     * @param argsArray
     * @return
     */
    public int getPrepositionIndex(String[] argsArray) {
        return IntStream.range(0, argsArray.length)
                .filter(wordIndex -> {
                    String word = argsArray[wordIndex];
                    return Arrays.stream(prepositionArray)
                            .anyMatch(preposition -> preposition.equals(word));
                })
                .reduce(0, (x, y) -> x + y);
    }
    
    /** 
     * Parse all words before a preposition based on the user's input.
     * 
     * @param userInputArray
     * @param prepositionIndex
     * @return An optional string phrase of all the words before the preposition.
     */
    public Optional<String> parsePhraseBeforePreposition(String[] userInputArray, 
            int prepositionIndex) {
        
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
     * Parse all words after a preposition based on the user's input.
     * 
     * @param userInputArray
     * @param prepositionIndex
     * @return An optional string phrase of all the words after the preposition.
     */
    public Optional<String> parsePhraseAfterPreposition(String[] userInputArray, 
            int prepositionIndex) {
        
        String phrase = "";
        
        for (int k = prepositionIndex + 1; k < userInputArray.length; k++) {
            if (k > prepositionIndex + 1) {
                phrase += (" " + userInputArray[k]);
            } else {
                phrase += (userInputArray[k]);
            }
        }
        
        // Format phrase into readable date time.
        //phrase = parsePhraseIntoDateTime(phrase);
        
        // Wrap an optional on the phrase
        Optional<String> optionalPhrase;
        if (phrase.equals("")) {
            optionalPhrase = Optional.empty();
        } else {
            optionalPhrase = Optional.ofNullable(phrase);
        }
        
        return optionalPhrase;
    }
    
    public Optional<String> parsePhraseWithoutPreposition(String[] userInputArray) {      
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
     * Parse a user's input into a format specified by the constructor of a command.
     * 
     * @param userInput
     * @return Parser A parser object that can create a command.
     */
    public Parser parseUserInputIntoCommandArguments(String userInput) {
        String[] argsArray = userInput.trim().split(" ");
        int prepositionIndex = 0;
        String commandType = argsArray[0];
        Optional<String> taskInfo = Optional.empty();
        Optional<String> taskRequirement = Optional.empty();
        
        // If input contains preposition, break into respective optional arguments.
        if (hasPreposition(argsArray)) {
            prepositionIndex = getPrepositionIndex(argsArray);
            
            taskInfo = parsePhraseBeforePreposition(
                    argsArray, prepositionIndex);
            
            taskRequirement = parsePhraseAfterPreposition(
                    argsArray, prepositionIndex);                      
        } else {
            taskInfo = parsePhraseWithoutPreposition(argsArray);
        }
        
        return new Parser(commandType, taskInfo, taskRequirement); 
    }
}
