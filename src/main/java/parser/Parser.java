package parser;


import commands.*;
import commands.add.AddDeadlineCommand;
import commands.add.AddEventCommand;
import commands.add.AddTodoCommand;
import common.Messages;
import data.TaskManager;
import data.exceptions.ParseException;
import data.task.DeadlineTask;
import data.task.EventTask;
import data.task.Task;
import data.task.TodoTask;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Parses user input.
 */
public class Parser {

    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
    /** index suffix for done and delete command */
    public static final int DELETE_INDEX = 7;
    public static final int DONE_INDEX = 5;
    /** used for checking duplicate*/
    private Task toADD;

    /**
     * Parses user input into command for execution.
     * @param userInput full user input string
     * @return the command based on the user input
     * split the user input, command word and the description
     * commandWord stores the whole user input
     * commandWordFirstPart [0] stores the Command Word
     * description stores additional information
     */
    public Command parseCommand(TaskManager taskManager, String userInput) {
        final String commandWord = userInput;
        final String []commandWordFirstPart = commandWord.split(" ");
        /** further split the user input, get the secondary part, the description */
        final String commandWordDescription = commandWord.substring(commandWordFirstPart[0].length());
        //operates according to different command word
        return getCommand(taskManager, taskManager.getTaskList().getNextTaskIndex(), commandWord, commandWordFirstPart, commandWordDescription);
    }

    /**
     * @param commandWordFirstPart, commandWordDescription the input String spited parts
     * @return parsed command
     */
    private Command getCommand(TaskManager taskManager, int nextTaskIndex, String commandWord, String[] commandWordFirstPart, String commandWordDescription) {
        switch (commandWordFirstPart[0]){
        //save to json
        case SaveToJsonCommand.COMMAND_WORD:
            return  new SaveToJsonCommand();
        //find task
        case FindCommand.COMMAND_WORD:
            return new FindCommand(commandWordDescription);
        //add event task
        case AddEventCommand.COMMAND_WORD:
            return prepareAddEventTask(nextTaskIndex, commandWordDescription);
        //add deadline task
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadlineTask(nextTaskIndex, commandWordDescription);
        //add todo task
        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodoTask(taskManager, nextTaskIndex, commandWordDescription);
        //done
        case DoneCommand.COMMAND_WORD:
            return prepareDone(commandWord);
        //list
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        //delete
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(commandWord);
        //exit
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        //clear
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        //help and default
        case HelpCommand.COMMAND_WORD:
        default:
            return new HelpCommand();
        }
    }


    /**
     * Parses user input into command for execution.
     * @param nextTaskIndex the task ID for next task
     * @param commandDescription full user input string (without the command Action)
     * @return the AddCommand obj constructed on the user input
     */
    private Command prepareAddEventTask(int nextTaskIndex, String commandDescription) {
        //split the commandDescription to task and start time
        try {
            String [] temp = commandDescription.split("/");
            return new AddEventCommand(new EventTask(nextTaskIndex, temp[0],temp[1]));
        } catch (ArrayIndexOutOfBoundsException aiobex){
            return new AddEventCommand(null);
        }
    }

    /**
     * Parses user input into command for execution.
     * @param nextTaskIndex the task ID for next task
     * @param commandDescription full user input string (without the command Action)
     * @return the AddCommand obj constructed on the user input
     */
    private Command prepareAddDeadlineTask(int nextTaskIndex, String commandDescription) {
        //split the commandDescription to task and deadline
        try {
            String [] temp = commandDescription.split("/");
            return new AddDeadlineCommand(new DeadlineTask(nextTaskIndex, temp[0],temp[1]));
        } catch (ArrayIndexOutOfBoundsException aex){
            return new AddEventCommand(null);
        }

    }

    /**
     * Parses user input into command for execution.
     * @param taskManager
     * @param nextTaskIndex the task ID for next task
     * @param commandDescription full user input string (without the command Action)
     * @return the AddCommand obj constructed on the user input
     */
    private Command prepareAddTodoTask(TaskManager taskManager,int nextTaskIndex, String commandDescription) {
        try{
            for (Task toCheck:taskManager.getTaskList().getInternalList()
            ) {
                if(toCheck.getTaskDescription().contentEquals(commandDescription)){
                    //
                    System.out.println(String.format("Alert! This task is similar to task Index %d",
                            toCheck.getTaskIndex()));
                    System.out.println("Do you want to add a duplicate task?");
                    System.out.print("Press Y to add and others to not add: ");
                    Messages.consumeLine();
                    Scanner scanner = new Scanner(System.in);
                    char userCommand  = scanner.next().charAt(0);
                    switch (userCommand){
                    case 'Y':
                    case 'y':
                        return new AddTodoCommand(new TodoTask(nextTaskIndex, commandDescription));
                    default:
                        System.out.println("The duplicate task is not added!");
                        return new ListCommand();
                    }
                }
            }
        } catch (NullPointerException npex) {
            npex.printStackTrace();
        }
        return new AddTodoCommand(new TodoTask(nextTaskIndex, commandDescription));
    }

    /**
     * Parses user input into command for execution.
     *
     * @param args full user input string
     * @return the DoneCommand obj constructed on the user input
     */
    private Command prepareDone (String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args, DONE_INDEX);//get target index
            return new DoneCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (StringIndexOutOfBoundsException nfe) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the delete person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args, DELETE_INDEX);//get target index
            return new DeleteCommand(targetIndex); //delete
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses the given arguments string as a single index number
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private int parseArgsAsDisplayedIndex(String args, int index) throws ParseException, NumberFormatException,
            StringIndexOutOfBoundsException{
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        if (args.length() < index) {
            throw new StringIndexOutOfBoundsException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return Integer.parseInt(args.substring(index));
    }

}
