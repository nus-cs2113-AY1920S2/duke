package misc;

/**
 * Encapsulates all string representation of messages used by the program.
 * 
 */
public class Messages {
    
    public static final String MESSAGE_WELCOME = 
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___| v0.2.0 \n"
            + "\nHello! I'm Duke!" 
            + "\nWhat can I do for you today?";
    
    public static final String MESSAGE_EXIT = "Duke has been closed successfully.";
    
    public static final String MESSAGE_INVALID_COMMAND_RESULT = ">>>>>>>> I'm sorry! It seems like that is not a valid command...\n";
    
    public static final String MESSAGE_COMMAND_LIST_TASK = "Here are the tasks in your list:";
    
    public static final String MESSAGE_COMMAND_RESULT_SUCCESS = ">>>>>>>> Alright! What can I do for you next?";
    
    public static final String MESSAGE_COMMAND_RESULT_FAILURE = "Oops! Something went wrong. Let me find out why..........";
    
    public static final String MESSAGE_COMMAND_RESULT_EXIT = ">>>>>>>> Bye! Hope to see you again soon!";
    
    public static final String MESSAGE_ADD_COMMAND_INVALID_TASK_DESCRIPTION = ">>>>>>>> It seems like you have forgotten to add a description to your task."; 
    
    public static final String MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_DEADLINES = ">>>>>>>> It seems like you have forgotten to add a date to your deadline";
    
    public static final String MESSAGE_ADD_COMMAND_INVALID_TASK_REQUIREMENT_EVENTS = ">>>>>>>> It seems like you have forgotten to add a start date or an end date to your event?";
    
    public static final String MESSAGE_DONE_COMMNAND_INDEX_OUT_OF_BOUNDS = ">>>>>>>> I'm sorry! There is no current task with that index...";
    
    public static final String MESSAGE_FIND_COMMAND_TASK = "Here are the matching tasks in your list:";

    public static final String MESSAGE_COMMAND_FILTER_TASK = "Here are the tasks occuring on this date:";

    public static final String MESSAGE_INVALID_TASK_TYPE = ">>>>>>>> I'm sorry! I cannot understand the type of the task given.";
    
    public static final String MESSAGE_READ_WRITE_FAILURE = "Make sure you have a 'data' folder containing a storage.txt file.";
    
    public static final String MESSAGE_CANNOT_WRITE_TASK = ">>>>>>>> I'm sorry! The current task cannot be written to file.";
    
    public static final String MESSAGE_INCORRECT_DATE_FORMAT = ">>>>>>>> I'm sorry! Please enter the date in the following format: YYYY-MM-DD";
    
    public static final String MESSAGE_INCORRECT_TASK_INPUT = ">>>>>>>> I'm sorry! Please enter the information of a task in the correct format. \n>>>>>>>> For example: todo return book \n>>>>>>>> For example: deadline finish essay /by 2020-12-01T10:00 \\n>>>>>>>> For example: event Midnight Party /on 2020-12-01T10:00 to 2020-12-01T12:00";
    
    public static final String MESSAGE_INCORRECT_DEADLINE_INPUT = ">>>>>>>> I'm sorry! Please enter the deadline date and time in the following format: YYYY-MM-DDTHH:mm. \n>>>>>>>> For example: 2020-12-01T10:00";
    
    public static final String MESSAGE_INCORRECT_EVENT_INPUT = ">>>>>>>> I'm sorry! Please enter the starting event date and time followed by the event ending date and time in the following format: YYYY-MM-DDTHH:mm to YYYY-MM-DDTHH:mm. \n>>>>>>>> For example: 2020-12-01T10:00 to 2020-12-01T12:00";
    
    public static final String MESSAGE_EVENT_MISSING_START_DATE_TIME = ">>>>>>>> I'm sorry! Please enter your event starting date and time.";
    
    public static final String MESSAGE_EVENT_MISSING_END_DATE_TIME = ">>>>>>>> I'm sorry! Please enter your event ending date and time.";
    
    public static final String MESSAGE_EVENT_MISSING_WORD_TO = ">>>>>>>> I'm sorry! You forgot to enter a 'to' between your event starting and ending date and time.";
    
    public static final String MESSAGE_CORRUPTED_STORAGE_FILE = "The storage.txt file in the current directory is corrupted, You may wish to delete the current storage.txt file and run the program again. \nIf the current storage.txt file was transferred from another computer, you may want to re-transfer a copy of your storage.txt file from your previous computer. ";
    
    public static final String MESSAGE_END_OF_INPUT_FILE = "You have reached the end of input file in your testing.";
    
    public static final String MESSAGE_FIND_COMMAND_MISSING_KEYWORD = ">>>>>>>> I'm sorry! Please enter a keyword so that I can search the current list of tasks.";
    
    public static final String MESSAGE_FILTER_COMMAND_MISSING_DATE = ">>>>>>>> I'm sorry! Please enter a date so that I can search the current list of tasks.";
    
    public static final String MESSAGE_DONE_COMMAND_MISSING_TASKID = ">>>>>>>> I'm sorry! Please enter a task index so that I can mark that as done.";
    
    public static final String MESSAGE_DELETE_COMMAND_MISSING_TASKID = ">>>>>>>> I'm sorry! Please enter a task index so that I can delete that.";
} 
