package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeException;

public class Parser {


    public static Command parseInput(String userInput) throws DukeException {
        String[] inputSegments = (userInput.trim() + " ").split(" ",2);
        String commandWord = inputSegments[0];
        String commandDetails = inputSegments[1];


        switch (commandWord){
        case "bye":
            return prepareBye();
        case "done":
            return prepareDone(commandDetails);
        case "delete":
            return prepareDelete(commandDetails);
        case "todo":
            return prepareToDo(commandDetails);
        case "list":
            return prepareList();
        case "deadline":
            return prepareDeadline(commandDetails);
        case "event":
            return prepareEvent(commandDetails);
        case "find":
            return prepareFind(commandDetails);
        default:
            return prepareNonExistent();
        }
    }

    private static Command prepareFind(String commandDetails) {
        return new Find(commandDetails.trim());
    }

    private static Command prepareNonExistent() {
        return new NonExistent();
    }

    private static Command prepareEvent(String commandDetails) throws DukeException {
        return new Event(commandDetails);
    }

    private static Command prepareDeadline(String commandDetails) throws DukeException {
        return new Deadline(commandDetails);
    }

    private static Command prepareList() {
        return new List();
    }

    private static Command prepareToDo(String commandDetails) throws DukeException {
        return new Todo(commandDetails);
    }

    private static Command prepareDelete(String commandDetails) {
        return new Delete(commandDetails);
    }

    private static Command prepareDone(String commandDetails) {
        return new Done(commandDetails);
    }

    private static Command prepareBye() {
        return new Bye();
    }

}
