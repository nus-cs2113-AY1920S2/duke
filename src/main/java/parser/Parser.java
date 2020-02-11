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
    
    public Parser parseUserInputIntoCommandArguments(String userInput) {
        String[] argsArray = userInput.trim().split(" ");
        int prepositionIndex = 0;
        String commandType = argsArray[0];
        String taskInfo = "";
        String taskRequirement = "";
             
        if (hasPreposition(argsArray)) {
            prepositionIndex = getPrepositionIndex(argsArray);
            
            // Parse argsArray into taskInfo
            for (int j = 1; j < prepositionIndex; j++) {
                if (j != 1) {
                    taskInfo += (" " + argsArray[j]);
                } else {
                    taskInfo += (argsArray[j]);
                }
            }
            
            // Parse argsArray into taskDescription
            for (int k = prepositionIndex + 1; k < argsArray.length; k++) {
                if (k > prepositionIndex + 1) {
                    taskRequirement += (" " + argsArray[k]);
                } else {
                    taskRequirement += (argsArray[k]);
                }
            }      
        } else {
            for (int i = 1; i < argsArray.length; i++) {
                if (i != 1) {
                    taskInfo += (" " + argsArray[i]);
                } else {
                    taskInfo += (argsArray[i]);
                }
            }
        }
        
        // Wrapping an optional to taskInfo string
        Optional<String> taskInformation;
        if (taskInfo.equals("")) {
            taskInformation = Optional.empty();
        } else {
            taskInformation = Optional.ofNullable(taskInfo);
        }
        
        // Wrapping an optional to taskRequirement string
        Optional<String> taskReq;
        if (taskRequirement.equals("")) {
            taskReq = Optional.empty();
        } else {
            taskReq = Optional.ofNullable(taskRequirement);
        }
        
        return new Parser(commandType, taskInformation, taskReq); 
    }
}
