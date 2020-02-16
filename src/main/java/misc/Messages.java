package misc;

/**
 * Encapsulates all string representation of messages used by the program.
 */
public class Messages {
    
    public static final String MESSAGE_WELCOME = 
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___| \n"
            + "\nHello! I'm Duke!" 
            + "\nWhat can I do for you today?";
    
    public static final String MESSAGE_EXIT = "Duke has been closed successfully.";
    
    public static final String MESSAGE_INVALID_COMMAND_RESULT = ">>>>>>>> I'm sorry! It seems like that is not a valid command...\n";
    
    public static final String MESSAGE_COMMAND_LIST_TASK = "Here are the tasks in your list:";
    
    public static final String MESSAGE_COMMAND_RESULT_SUCCESS = ">>>>>>>> Alright! What can I do for you next? 😀";
    
    public static final String MESSAGE_COMMAND_RESULT_FAILURE = "☹ Oops! Something went wrong. Let me find out why..........";
    
    public static final String MESSAGE_COMMAND_RESULT_EXIT = ">>>>>>>> Bye! Hope to see you again soon! 😀";
    
    public static final String MESSAGE_ADD_COMMAND_INVALID_TASK_INFO = ">>>>>>>> It seems like you have forgotten to add a description to your task."; 
    
    public static final String MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_DEADLINES = ">>>>>>>> It seems like you have forgotten to add a date to your deadline";
    
    public static final String MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_EVENTS = ">>>>>>>> It seems like you have forgotten to add a venue to your event?";
    
    public static final String MESSAGE_DONE_COMMNAND_INDEX_OUT_OF_BOUNDS = ">>>>>>>> I'm sorry! There is no current task with that index...";
    
    public static final String MESSAGE_FIND_COMMAND_TASK = "Here are the matching tasks in your list:";

    public static final String MESSAGE_COMMAND_FILTER_TASK = "Here are the tasks occuring on this date:";

    public static final String MESSAGE_INVALID_TASK_TYPE = ">>>>>>>> I'm sorry! I cannot understand the type of the task given.";
    
    public static final String MESSAGE_READ_WRITE_FAILURE = "Make sure you have a 'data' folder containing a storage.txt file.";
    
    public static final String MESSAGE_INCORRECT_DATE_FORMAT = ">>>>>>>> I'm sorry! Please enter the date in the following format: YYYY-MM-DD";
    
    public static final String MESSAGE_INCORRECT_DATE_FORMAT_INPUT = ">>>>>>>> I'm sorry! Please enter the date and time in the following format: YYYY-MM-DDTHH:mm. \n>>>>>>>> For example: 2020-12-01T10:00";
}
