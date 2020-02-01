package commands;

import commands.Command;
import commands.CommandResult;
import common.Messages;
import data.task.Task;
import data.task.TaskList;

import javax.print.attribute.standard.Media;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find all tasks matches the given description.\n"
            + "    Example: " + COMMAND_WORD + " read a book.";
    public static final String MESSAGE_EMPTY_LIST = "Can not find any task description " +
            "contains this keyword";

    protected String key;
    protected TaskList qualifiedTasks;

    public FindCommand() {
        
    }

    public FindCommand(String commandDescription) {
        this.key = commandDescription;
    }

    @Override
    public CommandResult execute() {
        System.out.println(Messages.DIVIDER);
        qualifiedTasks = duke.searchTask(key);
        if (qualifiedTasks.getInternalList().isEmpty()){
            return new CommandResult(MESSAGE_EMPTY_LIST);
        } else {
            Messages.printAllTasks(qualifiedTasks);
        }
        System.out.println(Messages.DIVIDER);
        return null;
    }

}
