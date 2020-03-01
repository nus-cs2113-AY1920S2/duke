package duke.exception;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoCommand;
import duke.commands.DueCommand;
import duke.format.DateTime;
import duke.ui.UI;


import static duke.format.TextFormatter.ANGRY_FACE;
import static duke.format.TextFormatter.SHOCK_FACE;
import static duke.format.TextFormatter.THINKING_FACE;
import static duke.format.TextFormatter.createSpaces;
import static duke.format.TextFormatter.toBold;
import static duke.format.TextFormatter.toBoldAndItalic;
import static duke.format.TextFormatter.toItalic;

/**
 * Collection of <code>Exception</code> messages to be displayed by the {@link UI}.
 * @see UI
 */
public class ExceptionMessages {
    private static final String TAB = createSpaces(4);

    public static final String IO_ERROR_MESSAGE =
            "An unexpected " + toBold("error") + " has occurred when creating a new task list file!!\n";

    public static final String FILE_NOT_FOUND_MESSAGE =
            "No existing task list found! Starting a new empty task list...\n";

    public static final String CORRUPTED_FILE_MESSAGE =
            "The task list file appears to be " + toBold("corrupted") + "\n";

    public static final String FILE_SAVE_ERROR_MESSAGE =
            SHOCK_FACE + "Arh!! Lumi cannot seem to save your list!!\n" +
            "Do you still want to leave " + toBold("without") + " saving?\n";

    public final static String INPUT_LENGTH_EXCEEDED_MESSAGE =
            SHOCK_FACE + "Aye!! Lumi only lets you enter " +
            toBold("less than 50 characters") + "!\n";

    public final static String EMPTY_INPUT_MESSAGE =
            ANGRY_FACE + "Hey!! Lumi needs you to type " + toBold("something") + "!\n";

    public final static String INVALID_ACTION_MESSAGE =
            SHOCK_FACE + "Oh no!! Lumi has trouble understanding these words... Please try again!\n";

    public final static String INVALID_DONE_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("DONE")  + " command...\n" +
            "Lumi needs you to follow this format:\n" + TAB + toBoldAndItalic("done <list number>\n");

    public final static String MISSING_LIST_NUMBER_MESSAGE =
            THINKING_FACE + "Hmm... Lumi needs you to put in a " + toBold("list number") + " too!\n";

    public final static String INVALID_LIST_NUMBER_MESSAGE =
            SHOCK_FACE + "Whoops!! Lumi's list does not seem to have that number!\n" +
            "Lumi shall print the list for you to check again...";

    public final static String ILLEGAL_LIST_NUMBER_MESSAGE =
            ANGRY_FACE + "Hey!! Lumi's list numbers are " + toBold("NUMBERS") + "!!\n" +
            "Lumi needs you to follow this format:\n" + TAB +
            toBoldAndItalic(DoCommand.FORMAT) + "\n";

    public final static String INVALID_TODO_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("TODO")  + " command...\n" +
            "Lumi needs you to follow this format:\n" + TAB +
            toBoldAndItalic(AddToDoCommand.FORMAT) + "\n";

    public final static String MISSING_TODO_DESCRIPTION_MESSAGE =
            THINKING_FACE + "Hmm... Lumi needs you to put in a " +
            toBold("task description") + " too!\n";

    public final static String INVALID_DEADLINE_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("DEADLINE")  + " command...\n" +
            "Lumi needs you to follow this format:\n" + TAB +
            toBoldAndItalic(AddDeadlineCommand.FORMAT) + "\n";

    public final static String MISSING_DEADLINE_INFORMATION_MESSAGE =
            THINKING_FACE + "Hmm... Lumi senses missing information...\n" +
            "Lumi needs you to put in either a " + toBold("task description") + " or a " +
            toBold("deadline") + "!\n";

    public final static String INVALID_EVENT_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("EVENT")  + " command...\n" +
            "Lumi needs you to follow this format:\n" + TAB +
            toBoldAndItalic(AddEventCommand.FORMAT) + "\n";

    public final static String MISSING_EVENT_INFORMATION_MESSAGE =
            THINKING_FACE + "Hmm... Lumi senses missing information...\n" +
            "Lumi needs you to put in either a " + toBold("task description") + " or a " +
            toBold("duration") + "!\n";

    public final static String MISSING_SEARCH_WORD_MESSAGE =
            THINKING_FACE + "Hmm... Lumi needs you to put in a " +
            toBold("search word") + " too!\n";

    public final static String MISSING_DATE_FILTER_MESSAGE =
            THINKING_FACE + "Hmm... Lumi senses missing information...\n" +
            "Lumi needs you to follow this format:\n" + TAB +
            toBoldAndItalic(DueCommand.FORMAT) + "\n";

    public final static String INVALID_DUE_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("DUE")  + " command...\n" +
            "Lumi needs you to follow this format:\n" + TAB +
            toBoldAndItalic(DueCommand.FORMAT) + "\n";

    public final static String INVALID_DATETIME_FORMAT_MESSAGE =
            SHOCK_FACE + "Em... Lumi cannot understand this " + toBold("datetime")  + " given...\n" +
            "Lumi needs you to follow this datetime format:\n" + TAB +
            toBoldAndItalic(DateTime.FORMAT) + "\n";

    public final static String INVALID_DATE_FORMAT_MESSAGE =
            SHOCK_FACE + "Em... Lumi cannot understand this " + toBold("date")  + " given...\n" +
            "Lumi needs you to follow this datetime format:\n" + TAB +
            toBoldAndItalic(DateTime.FORMAT) + "\n";

    public final static String INVALID_TIME_FORMAT_MESSAGE =
            SHOCK_FACE + "Em... Lumi cannot understand this " + toBold("time")  + " given...\n" +
            "Lumi needs you to follow this datetime format:\n" + TAB +
            toBoldAndItalic(DateTime.FORMAT) + "\n";

    public final static String INVALID_DELETE_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("DELETE")  + " command...\n" +
            "Lumi needs you to follow this format:\n" + TAB +
            toBoldAndItalic(DeleteCommand.FORMAT) + "\n";
}
