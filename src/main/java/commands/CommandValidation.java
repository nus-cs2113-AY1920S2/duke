package commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import common.exceptions.DukeException;
import common.tasks.Task;

public class CommandValidation {
    
    /**
     * Validates the showUpcoming, findTask, addTodo, addDeadline, addEvent commands.
     * Checks if the input Command is valid for the command type called.
     * 
     * @param command Command to be checked for validity.
     * @param type Command type to check validity for.
     * @throws DukeException
     */
    public static void validate(Command command, String type) throws DukeException {
        Optional<String> commandDescription = command.getDescription();
        Optional<String> commandCheck = command.getCheck();
        Optional<LocalDate> date = command.getDate();
        if (type.equals("show")) {
            if (!commandDescription.isPresent()) {
                throw new DukeException("Oops!! Please enter a time period.");
            }
            int index;
            try {
                index = Integer.valueOf(commandDescription.get());
            } catch (Exception exception) {
                throw new DukeException("Oops!! Wrong format.");
            }
            if (index < 0) {
                throw new DukeException("Oops!! Please enter a non-negative integer.");
            }
        } else {
            if (!commandDescription.isPresent()) {
                throw new DukeException("Oops!! Please enter a description.");
            }
        }
        if (type.equals("event") || type.equals("deadline")) {
            if (!commandCheck.isPresent()) {
                throw new DukeException("Oops!! Wrong format.");
            } else if (!commandCheck.get().equals("/at") && type.equals("event")) {
                throw new DukeException("Oops!! Wrong format.");
            } else if (!commandCheck.get().equals("/by") && type.equals("deadline")) {
                throw new DukeException("Oops!! Wrong format.");
            } else if (!date.isPresent()) {
                throw new DukeException("Oops!! Please specify a date.");
            }
        } 
    }
    
    /**
     * Validates the makeDone, removeTask commands.
     * Checks if the input Command is valid for the command type called.
     * 
     * @param command Command to be checked for validity.
     * @param type Command type to check validity for.
     * @param tasks List of current tasks for reference.
     * @throws DukeException
     */
    public static void validate(Command command, String type, ArrayList<Task> tasks) throws DukeException {
        Optional<String> commandDescription = command.getDescription();
        if (!commandDescription.isPresent()) {
            throw new DukeException("Oops!! Please enter a task number.");
        }
        int index;
        try {
            index = Integer.valueOf(commandDescription.get());
        } catch (Exception exception) {
            throw new DukeException("Oops!! Wrong format.");
        }
        if (index < 1) {
            throw new DukeException("Oops!! Invalid task.");
        } else if (index > tasks.size()) {
            throw new DukeException("Oops!! No such task.");
        } else if (tasks.get(index - 1).isDone() && type.equals("done")) {
            throw new DukeException("Oops!! Task is already completed.");
        }
    }
    
}
