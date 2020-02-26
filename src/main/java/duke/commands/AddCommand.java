package duke.commands;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.IOException;

import static duke.Duke.tasks;
import static duke.storage.Storage.appendToFile;

/**
 * Add the different type of tasks to the list
 */
public class AddCommand extends Command {
    
    private static final String COMMAND_ADD_TODO_DESC = "Adds a todo task to the list.";
    private static final String COMMAND_ADD_DEADLINE_DESC = "Adds a deadline task to the list.";
    private static final String COMMAND_ADD_EVENT_DESC = "Adds a event task to the list.";
    public static final String COMMAND_WORD_TODO = "todo";
    private static final String COMMAND_TODO_PARAMETER = "<DESCRIPTION>";
    private static final String COMMAND_TODO_EXAMPLE = "todo read book";
    
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    private static final String COMMAND_DEADLINE_EXAMPLE =
           LS + TAB + "deadline return book /by Sun 2pm" + LS + TAB + "deadline return book /by 2020-02-18 2pm" + LS + TAB +
                    "deadline return book /by 18-Feb-2020 2pm";
    
    private static final String COMMAND_DEADLINE_EVENT_PARAMETER = "<DESCRIPTION> /by <[DATE|DAY]> <INFO>" + LS + TAB +
            "DATE Format: yyyy-mm-dd or dd-mm-yyyy or dd-MMM-yyyy or yyyy-MMM-dd" + LS + TAB + "MMM: Jan - Dec" + LS + TAB + "DAY Format: Mon - Sun";
    
    public static final String COMMAND_WORD_EVENT = "event";
    private static final String COMMAND_EVENT_EXAMPLE =
           LS+ TAB+ "event project meeting /at Mon 2-4pm" + LS + TAB + "event project meeting /at 2020-02-18 2-4pm" + LS + TAB +
                    "event project meeting /at 18-Feb-2020 2-4pm";
    
    private static final String COMMAND_ADD_MESSAGE =
            "Got it. I've added this task:" + LS + TAB + "%s" + LS + "Now " + "you have %d task(s) in the list.";
    
    public static final String MESSAGE_USAGE =
            String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_TODO, COMMAND_ADD_TODO_DESC) + LS +
                    String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_TODO_PARAMETER) + LS +
                    String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_TODO_EXAMPLE) + LS + LS +
                    String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_DEADLINE, COMMAND_ADD_DEADLINE_DESC) + LS +
                    String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_DEADLINE_EVENT_PARAMETER) + LS +
                    String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DEADLINE_EXAMPLE) + LS + LS +
                    String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_EVENT, COMMAND_ADD_EVENT_DESC) + LS +
                    String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_DEADLINE_EVENT_PARAMETER) + LS +
                    String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_EVENT_EXAMPLE) + LS;
    
    private String description;
    private String commandWord;
    private String dayAndTime;
    
    
    /**
     * Constructor for AddCommand
     *
     * @param commandWord command word from the user
     * @param description the parameters of the command string
     */
    public AddCommand(String commandWord, String description) {
        this.description = description;
        this.commandWord = commandWord;
        
    }
    
    /**
     * Constructor for AddCommand
     *
     * @param commandWord command word from the user
     * @param description the parameters of the command string
     * @param dayAndTime  the parameter for day and time
     */
    public AddCommand(String commandWord, String description, String dayAndTime) {
        this.description = description;
        this.commandWord = commandWord;
        this.dayAndTime = dayAndTime;
        
    }
    
    /**
     * Execute the add operation flow
     *
     * @return the add message to the user
     */
    @Override
    public CommandResult execute() {
        try {
            switch (commandWord) {
            case AddCommand.COMMAND_WORD_TODO:
                tasks.add(new Todo(description));
                break;
            case AddCommand.COMMAND_WORD_DEADLINE:
                tasks.add(new Deadline(description, dayAndTime));
                break;
            case AddCommand.COMMAND_WORD_EVENT:
                tasks.add(new Event(description, dayAndTime));
                break;
            }
            appendToFile(tasks.get(tasks.size() - 1).toStorage());
            return new CommandResult(
                    DIVIDER + LS + String.format(COMMAND_ADD_MESSAGE, tasks.get(tasks.size() - 1), tasks.size()) + LS +
                            DIVIDER);
        } catch (IOException e) {
            return new CommandResult(e.toString());
        }
    }
}
