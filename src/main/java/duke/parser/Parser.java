package duke.parser;

import duke.commands.ByCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.OnCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.BadLineFormatException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class containing static methods used to parse user input and lines that are read from file
 */
public class Parser {
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
    public static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("d/M/yyyy");

    public static Command parseUserInput(String userInput, TaskList taskList) throws BadLineFormatException {
        if (userInput.length() == 0) {
            throw new BadLineFormatException("Empty line");
        }
        String[] tokens = userInput.split("\\s+");
        String commandWord = tokens[0];

        CommandInfo commandInfo = getCommandInfo(commandWord);
        Matcher matcher = commandInfo.getPattern().matcher(userInput);
        if (!matcher.matches()) {
            throw new BadLineFormatException(commandInfo.getErrorMessage());
        }

        try {
            return getCommand(commandWord, tokens, taskList);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new BadLineFormatException(commandInfo.getErrorMessage());
        }
    }

    private static CommandInfo getCommandInfo(String commandWord) throws BadLineFormatException {
        switch(commandWord) {
        case ByCommand.KEYWORD:
            return new CommandInfo(ByCommand.FORMAT, ByCommand.ERROR_MESSAGE);
        case DeadlineCommand.KEYWORD:
            return new CommandInfo(DeadlineCommand.FORMAT, OnCommand.ERROR_MESSAGE);
        case DeleteCommand.KEYWORD:
            return new CommandInfo(DeleteCommand.FORMAT, DeleteCommand.ERROR_MESSAGE);
        case DoneCommand.KEYWORD:
            return new CommandInfo(DoneCommand.FORMAT, DoneCommand.ERROR_MESSAGE);
        case EventCommand.KEYWORD:
            return new CommandInfo(EventCommand.FORMAT, EventCommand.ERROR_MESSAGE);
        case ExitCommand.KEYWORD:
            return new CommandInfo(ExitCommand.FORMAT, ExitCommand.ERROR_MESSAGE);
        case FindCommand.KEYWORD:
            return new CommandInfo(FindCommand.FORMAT, FindCommand.ERROR_MESSAGE);
        case ListCommand.KEYWORD:
            return new CommandInfo(ListCommand.FORMAT, ListCommand.ERROR_MESSAGE);
        case OnCommand.KEYWORD:
            return new CommandInfo(OnCommand.FORMAT, OnCommand.ERROR_MESSAGE);
        case ToDoCommand.KEYWORD:
            return new CommandInfo(ToDoCommand.FORMAT, ToDoCommand.ERROR_MESSAGE);
        default:
            throw new BadLineFormatException("Unrecognized keyword");
        }
    }

    private static int getIndex(String[] tokens, String target) {
        int index = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(target)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static Command getCommand(String commandWord, String[] tokens, TaskList taskList) throws BadLineFormatException,
            DateTimeParseException, NumberFormatException {
        switch(commandWord) {
        case ByCommand.KEYWORD: {
            LocalDateTime dateTime = LocalDateTime.parse(tokens[1] + " " + tokens[2], DTF);
            return new ByCommand(taskList, dateTime);
        }
        case DeadlineCommand.KEYWORD: {
            int byIndex = getIndex(tokens, "/by");
            String description = String.join(" ", Arrays.copyOfRange(tokens, 1, byIndex));
            String dateTimeString = tokens[tokens.length - 2] + " " + tokens[tokens.length - 1];
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DTF);
            return new DeadlineCommand(taskList, description, dateTime);
        }
        case DeleteCommand.KEYWORD: {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            return new DeleteCommand(taskList, taskIndex);
        }
        case DoneCommand.KEYWORD: {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            return new DoneCommand(taskList, taskIndex);
        }
        case EventCommand.KEYWORD: {
            int atIndex = getIndex(tokens, "/at");
            String description = String.join(" ", Arrays.copyOfRange(tokens, 1, atIndex));
            String dateTimeString = String.join(" ", Arrays.copyOfRange(tokens, atIndex + 1, tokens.length));
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DTF);
            return new EventCommand(taskList, description, dateTime);
        }
        case ExitCommand.KEYWORD: {
            return new ExitCommand();
        }
        case FindCommand.KEYWORD: {
            String targetWord = tokens[1];
            return new FindCommand(taskList, targetWord);
        }
        case ListCommand.KEYWORD: {
            return new ListCommand(taskList);
        }
        case OnCommand.KEYWORD: {
            LocalDate date = LocalDate.parse(tokens[1], DF);
            return new OnCommand(taskList, date);
        }
        case ToDoCommand.KEYWORD: {
            String description = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
            return new ToDoCommand(taskList, description);
        }
        default:
            throw new BadLineFormatException("Unrecognized keyword");
        }
    }


    /**
     * Parse a line that is read from file
     * @param line line that is read from file
     * @return <code>Task</code> generated based on line
     * @throws BadLineFormatException if the line from file is badly formatted
     */
    public static Task parseFormattedLine(String line) throws BadLineFormatException {
        String[] tokens = line.split(",");
        String taskType = tokens[0];
        Pattern pattern = getPattern(taskType);
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new BadLineFormatException("Must be of pattern: " + pattern.toString());
        }

        try {
            return getTask(taskType, tokens);
        } catch (DateTimeParseException e) {
            throw new BadLineFormatException("Must be of pattern: " + pattern.toString());
        }
    }

    public static Pattern getPattern(String taskType) throws BadLineFormatException {
        switch (taskType) {
        case "T":
            return ToDo.LINE_FORMAT;
        case "D":
            return Deadline.LINE_FORMAT;
        case "E":
            return Event.LINE_FORMAT;
        default:
            throw new BadLineFormatException("First token must be T, D, or E");
        }
    }

    public static Task getTask(String taskType, String[] tokens) throws BadLineFormatException, DateTimeParseException {
        boolean isDone = tokens[1].equals("y");
        switch(taskType) {
        case "T":
            return new ToDo(tokens[2], isDone);
        case "D":
            return new Deadline(tokens[2], LocalDateTime.parse(tokens[3], DTF), isDone);
        case "E":
            return new Event(tokens[2], LocalDateTime.parse(tokens[3], DTF), isDone);
        default:
            throw new BadLineFormatException("First token must be T, D, or E");
        }
    }
}
