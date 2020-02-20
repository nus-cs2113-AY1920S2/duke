package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String taskType;
        String args;
        String[] words;
        words = fullCommand.split("\\s+",2);
        taskType = words[0];

        boolean isTodo = taskType.equalsIgnoreCase("todo");
        boolean isDeadline = taskType.equalsIgnoreCase("deadline");
        boolean isEvent = taskType.equalsIgnoreCase("event");
        boolean isExit = taskType.equalsIgnoreCase("bye");
        boolean isList = taskType.equalsIgnoreCase("list");
        boolean isDone = taskType.equalsIgnoreCase("done");
        boolean isDelete = taskType.equalsIgnoreCase("delete");
        boolean isFind = taskType.equalsIgnoreCase("find");
        boolean isAdd = isTodo || isDeadline || isEvent;

        boolean isNotEmpty = words.length > 1;
        if(isNotEmpty){
            args = fullCommand.replace(taskType + " ", "");
        } else {
            args = "";
        }

        if(isAdd) {
            return new AddCommand(fullCommand, taskType, args);
        } else if(isList) {
            return new ListCommand(fullCommand, taskType, args);
        } else if(isDelete) {
            return new DeleteCommand(fullCommand, taskType, args);
        } else if(isDone){
            return new DoneCommand(fullCommand, taskType, args);
        } else if(isExit){
            return new ExitCommand(fullCommand, taskType, args);
        } else if(isFind){
            return new FindCommand(fullCommand, taskType, args);
        } else {
            throw new DukeException("Your command cannot be used.");
        }

    }

}
