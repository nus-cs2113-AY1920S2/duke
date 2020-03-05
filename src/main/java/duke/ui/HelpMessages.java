package duke.ui;

import duke.commands.AddToDoCommand;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.DoCommand;
import duke.commands.ListCommand;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;

import static duke.format.TextFormatter.createSpaces;

public class HelpMessages {

    private static final String TAB = createSpaces(8);
    private static final String NEW_PARAGRAPH = "\n\n";

    public static final String ADD_HELP =
            "Add a task to your Task List. \n\nYou may add 3 different types of tasks:\n" +
            TAB + "To Do, Deadline and Event\n" +
            "To find out how to add each type of task, enter:\n" +
            TAB + "help todo" +
            TAB + "help deadline" +
            TAB + "help event\n";

    public static final String ADD_TO_DO_HELP =
            "Add a To Do task to your Task List." + NEW_PARAGRAPH +
            "Format:\n" +
            TAB + AddToDoCommand.FORMAT + "\n" +
            "<task description> - The description of the To Do task" + NEW_PARAGRAPH +
            "Example Usage:\n" +
            TAB + "todo watch anime" + NEW_PARAGRAPH +
            "Expected Outcome:\n" +
            "    ʘᗜʘ Alright, Lumi has added: watch anime!\n" +
            "          [T][✘] watch anime\n" +
            "    You now have 1 task in Lumi's list!\n";

    public static final String ADD_DEADLINE_HELP =
            "Add a Deadline task to your Task List." + NEW_PARAGRAPH +
            "Format:\n" +
            TAB + AddDeadlineCommand.FORMAT + "\n" +
            "<task description> - The description of the Deadline task\n" +
            "<date> - The date of the deadline\n" +
            "<time> - The time of the deadline\n" +
            "NOTE: <date> and <time> must adhere to a certain set of date time formats. \nTo find out about the specific " +
            "date time formats allowed, enter:\n" +
            TAB + "help datetime" + NEW_PARAGRAPH +
            "Example Usage:\n" +
            TAB + "deadline complete math assignment /by 12/6 8am" + NEW_PARAGRAPH +
            "Expected Outcome:\n" +
            "    ʘᗜʘ Alright, Lumi has added: complete math assignment!\n" +
            "          [D][✘] complete math assignment (by: 12/06/2020 08:00AM)\n" +
            "    You now have 2 tasks in Lumi's list!\n";

    public static final String ADD_EVENT_HELP =
            "Add an Event task to your Task List." + NEW_PARAGRAPH +
            "Format:\n" +
            TAB + AddEventCommand.FORMAT + "\n" +
            "<task description> - The description of the Event task\n" +
            "<date> - The date of the event\n" +
            "<time> - The time of the event\n" +
            "NOTE: <date> and <time> must adhere to a certain set of date time formats. \nTo find out about the specific " +
            "date time formats allowed, enter:\n" +
            TAB + "help datetime" + NEW_PARAGRAPH +
            "Example Usage:\n" +
            TAB + "event meet up with friends for lunch /at tmr 1230" + NEW_PARAGRAPH +
            "Expected Outcome:\n" +
            "    ʘᗜʘ Alright, Lumi has added: meet up with friends for lunch!\n" +
            "          [E][✘] meet up with friends for lunch (at: tomorrow 12:30PM)\n" +
            "    You now have 3 tasks in Lumi's list!\n";

    public static final String DO_HELP =
            "Mark a task in your Task List as done." + NEW_PARAGRAPH +
            "Format:\n" +
            TAB + DoCommand.FORMAT + "\n" +
            "<list number> - The list number of the task to be marked as done\n" +
            "NOTE: You may find the list number of the task by viewing the Task List. \nTo find out how to view the Task" +
            "List, enter:\n" +
            TAB + "help list" + NEW_PARAGRAPH +
            "Example Usage:\n" +
            TAB + "done 2" + NEW_PARAGRAPH +
            "Expected Outcome:\n" +
            "    ʘᗜʘ Well done! Lumi marks this task as completed!\n" +
            "          [D][✓] complete math assignment (by: 12/06/2020 08:00AM)\n";

    public static final String LIST_HELP =
            "View all your tasks as a list." + NEW_PARAGRAPH +
            "Format:\n" +
            TAB + ListCommand.FORMAT + NEW_PARAGRAPH +
            "Example Usage:\n" +
            TAB + "list" + NEW_PARAGRAPH +
            "Expected Outcome:\n" +
            "    ʘᗜʘ Sure! Lumi prints your list!\n" +
            "      +---------+\n" +
            "  +---| L I S T |---------------------------------------------------------------+\n" +
            "      +---------+ \n" +
            "    1. [T][✘] watch anime\n" +
            "    2. [D][✓] complete math assignment (by: 12/06/2020 08:00AM)\n" +
            "    3. [E][✘] meet up with friends for lunch (at: tomorrow 12:30PM)\n" +
            "  +-----------------------------------------------------------------------------+\n" +
            "    Total: 3 tasks\n" +
            "  +-----------------------------------------------------------------------------+\n";

    public static final String DELETE_HELP =
            "Delete a task from your Task List. \n\nYou will receive an additional prompt to confirm your deletion." +
            NEW_PARAGRAPH + "Format:\n" +
            TAB + DeleteCommand.FORMAT + "\n" +
            "<list number> - The list number of the task to be deleted\n" +
            "NOTE: You may find the list number of the task by viewing the Task List. \nTo find out how to view the Task" +
            "List, enter:\n" +
            TAB + "help list" + NEW_PARAGRAPH +
            "Example Usage:\n" +
            TAB + "delete 1" + NEW_PARAGRAPH +
            "Expected Outcome:\n" +
            "- Confirmed Deletion\n" +
            "    ʘᗜʘ Bleep, Lumi says bye-bye to:\n" +
            "          [T][✘] watch anime\n" +
            "    You now have 2 tasks in Lumi's list!\n" +
            "- Aborted Deletion\n" +
            "    ʘᗜʘ OK, Lumi continues without deleting!\n";

    public static final String FIND_HELP =
            "Filter tasks from your Task List by a specified keyword.\n\n" +
            "The keyword can contain multiple words and need not be complete words. \nThe Task List is then filtered " +
            "for tasks which task description matches the keyword. \nFiltering is done in a non-case-sensitive " +
            "manner.\n\n" +
            "All matched tasks will be shown as a Search List" + NEW_PARAGRAPH +
            "Format:\n" +
            TAB + FindCommand.FORMAT + "\n" +
            "<keyword> - The keyword to be used to filter the Task List" + NEW_PARAGRAPH +
            "Example Usage:\n" +
            TAB + "find ASSIGNMENT" + NEW_PARAGRAPH +
            "Expected Outcome:\n" +
            "- Successful Search\n" +
            "    ʘᗜʘ Sure! Lumi searches your list...\n" +
            "      +-----------------------+\n" +
            "  +---| S E A R C H   L I S T |-------------------------------------------------+\n" +
            "      +-----------------------+\n" +
            "    1. [D][✓] complete math assignment (by: 12/06/2020 08:00AM)\n" +
            "    4. [D][✘] do Computing Assignments (by: 02/03/2020 11:59PM)\n" +
            "    5. [E][✘] Group project assignment meeting (at: 05/03/2020 02:00PM)\n" +
            "  +-----------------------------------------------------------------------------+\n" +
            "    Search Total: 3 tasks\n" +
            "  +-----------------------------------------------------------------------------+\n" +
            "- No Search Results\n" +
            "    ʘᗜʘ Sure! Lumi searches your list...\n" +
            "    ʘoʘ? Huh? Lumi is not able to find anything...\n";

    public static final String DUE_HELP =
            "Filter tasks from your Task List according to a specified time period.\n\n" +
            "A time period is defined by an optional time specifier (e.g. on, before, after) and a date. \nIf the time " +
            "specifier is left out, the time period is set to be on the date itself. \nThe Task List is then filtered " +
            "for Deadline and Event tasks which date time information matches the specified time period.\n\n" +
            "All matched tasks will be shown as a Search List." + NEW_PARAGRAPH +
            "Format:\n" +
            TAB + "due <time specifier> <date>" + "\n" +
            "<time specifier> - The optional time specifier to define the time period to filter the Task List\n" +
            "<date> - The date to define the time period to filter the Task List" +
            "NOTE: <time specifier> and <date> must adhere to a certain set of date time formats. \nTo find out about " +
            "the specific date time formats allowed, enter:\n" +
            TAB + "help datetime" + NEW_PARAGRAPH +
            "Example Usage:\n" +
            "- With time specifier\n" +
            TAB + "due after tmr\n" +
            "- Without time specifier\n" +
            TAB + "due today" + NEW_PARAGRAPH +
            "Expected Outcome:\n" +
            "    ʘᗜʘ Sure! Lumi searches your list...\n" +
            "      +-----------------------+\n" +
            "  +---| S E A R C H   L I S T |-------------------------------------------------+\n" +
            "      +-----------------------+\n" +
            "    1. [D][✓] complete math assignment (by: 12/06/2020 08:00AM)\n" +
            "    5. [E][✘] Group project assignment meeting (at: 05/03/2020 02:00PM)\n" +
            "    6. [E][✘] attend party (at: 12/04/2020 04:00PM)\n" +
            "  +-----------------------------------------------------------------------------+\n" +
            "    Search Total: 3 tasks\n" +
            "  +-----------------------------------------------------------------------------+\n";

    public static final String EXIT_HELP =
            "Exit the LumiChat program. Your Task List will be saved upon exit." + NEW_PARAGRAPH +
                    "Format:\n" +
                    TAB + "exit\n";

    public static final String DATETIME_HELP =
            "Any date time data that you provide has to adhere to certain formats pre-defined by the LumiChat " +
            "program.\n" +
            "There are three datetime format categories in this program: Date Formats, Time Formats and Time Specifiers\n" +
            "For more information about them, enter:\n" +
            TAB + "help date" +
            TAB + "help time" +
            TAB + "help timespec\n";


    public static final String DATE_HELP =
            "Date Formats\n" +
            "There are two types of date formats allowed:\n" +
            "1. Words\n" +
            "You may enter only the following date words.\n" +
            TAB + "today or tdy - represents the current date\n" +
            TAB + "tomorrow or tmr - represents the next date\n" +
            TAB + "yesterday or yst - represents the previous date" + NEW_PARAGRAPH +
            "2. Standard Date Formats\n" +
            "This refers to the typical dates that are represented with numbers and delimiter symbols.\n" +
            "\n" +
            "Dates should be in the order of day, month, then an optional year. \nIf the year is not provided, the " +
            "program will automatically assume it to be the current year. \nAlso, the day, month and year should only " +
            "be entered as numbers and not words (e.g. January is not accepted for the month attribute).\n" +
            "\n" +
            "Regarding delimiters, the program will only consider \'/\' and \'-\' as valid delimiters for dates. \n" +
            "Delimiters are optional and may be omitted provided you include the year of the date (e.g. 1/1/20, " +
            "1/1 and 010120 are accepted, but not 0101).\n" +
            "\n" +
            "An exhaustive list of the standard date formats is given below for your reference.\n" +
            TAB + "dd/MM/yyyy, d/MM/yyyy, dd/M/yyyy, d/M/yyyy, \n" +
            TAB + "dd/MM/yy, d/MM/yy, dd/M/yy, d/M/yy,\n" +
            TAB + "dd/MM, d/MM, dd/M, d/M,\n" +
            TAB + "dd-MM-yyyy, d-MM-yyyy, dd-M-yyyy, d-M-yyyy, \n" +
            TAB + "dd-MM-yy, d-MM-yy, dd-M-yy, d-M-yy,\n" +
            TAB + "dd-MM, d-MM, dd-M, d-M,\n" +
            TAB + "ddMMyyyy, ddMMyy\n";

    public static final String TIME_HELP =
            "Time Formats\n" +
            "Time formats that are represented with numbers, delimiter symbols and optional am-pm markers " +
            "are generally accepted.\n" +
            "\n" +
            "The time should be in the order of hour, then minute. \nThe seconds attribute of time should not be given. \n" +
            "The minute attribute must be a double digit (i.e. single digits must be padded with a 0 in front). \nThe " +
            "minute attribute is also optional, and should it be omitted, it will automatically set the minute to " +
            "be 0. \nBoth the 12-h format and the 24-h format are valid time formats for this program.\n" +
            "\n" +
            "Regarding delimiters, the program will only consider \':\' and \'.\' as valid delimiters for time.\n" +
            "Delimiters are optional and may be omitted.\n" +
            "\n" +
            "Lastly, the am-pm marker is an optional attribute.\nShould it be omitted, it will automatically " +
            "be assumed the time to follow the 24-h format.\n" +
            "\n" +
            "An exhaustive list of the time formats is given below for your reference.\n" +
            TAB + "h:mma, H:mma, H:mm,\n" +
            TAB + "h.mma, H.mma, H.mm,\n" +
            TAB + "hmma, Hmma, Hmm,\n" +
            TAB + "ha, Ha, H\n";

    public static final String TIME_SPECIFIER_HELP =
            "Time Specifier Formats\n" +
            "The time specifier is used in conjunction with a date to define the time period to filter tasks in " +
            "the Task List. \nThe following words are considered valid time specifiers in the LumiChat program.\n" +
            "- on - on the specified date\n" +
            "- after or a - after the specified date\n" +
            "- before or b - before the specified date" + NEW_PARAGRAPH +
            "The time specifier is optional and if omitted, is set to on.\n";

}
