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

public class Parser {
    private final String[] prepositionArray = {"/by", "/at"};
    private final String commandType;
    private final Optional<String> taskInfo;
    private final Optional<String> taskRequirement;
    
    public Parser(String commandType, Optional<String> taskInfo, Optional<String> taskRequirement) {
        this.commandType = commandType;
        this.taskInfo = taskInfo;
        this.taskRequirement = taskRequirement;
    }
    
    public Parser() {
        this.commandType = "";
        this.taskInfo = Optional.empty();
        this.taskRequirement = Optional.empty();
    }
    
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
    
    public boolean hasPreposition(String[] argsArray) {
        return IntStream.range(0, argsArray.length)
                .anyMatch(wordIndex -> {
                    String word = argsArray[wordIndex];
                    return Arrays.stream(prepositionArray)
                            .anyMatch(preposition -> preposition.equals(word));
                });
    }
    
    public int getPrepositionIndex(String[] argsArray) {
        return IntStream.range(0, argsArray.length)
                .filter(wordIndex -> {
                    String word = argsArray[wordIndex];
                    return Arrays.stream(prepositionArray)
                            .anyMatch(preposition -> preposition.equals(word));
                })
                .reduce(0, (x, y) -> x + y);
    }
    
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
