package duke.exception;

import static duke.format.TextFormatter.*;
import static java.lang.System.lineSeparator;

public class ExceptionMessage {

    public static final String IO_ERROR_MESSAGE =
            "An unexpected " + toBold("error") + " has occurred when creating a new task list file!!"
            + lineSeparator();

    public static final String FILE_NOT_FOUND_MESSAGE =
            "No existing task list found! Starting a new empty task list..." + lineSeparator();

    public static final String CORRUPTED_FILE_MESSAGE =
            "The task list file appears to be " + toBold("corrupted") + "!" + lineSeparator() +
            "Do you want to overwrite the corrupted task list with a new " +
            toBold("empty") + " task list?" + lineSeparator();

    public static final String FILE_SAVE_ERROR_MESSAGE =
            SHOCK_FACE + "Arh!! Lumi cannot seem to save your list!!" + lineSeparator() +
            "  Do you still want to leave " + toBold("without") + " saving?" + lineSeparator();

    public final static String EMPTY_INPUT_MESSAGE =
            ANGRY_FACE + "Hey!! Lumi needs you to type " + toBold("something") + "!" + lineSeparator();

    public final static String INVALID_ACTION_MESSAGE =
            SHOCK_FACE + "Oh no!! Lumi has trouble understanding these words... Please try again!" + lineSeparator();


    public final static String INVALID_DONE_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("DONE")  + " command..." +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + createSpaces(8) +
            toBoldAndItalic("done <list number>") + lineSeparator();

    public final static String MISSING_LIST_NUMBER_MESSAGE =
            THINKING_FACE + "Hmm... Lumi needs you to put in a " + toBold("list number") + " too!" +
            lineSeparator();

    public final static String INVALID_LIST_NUMBER_MESSAGE =
            SHOCK_FACE + "Whoops!! Lumi's list does not seem to have that number!" +
            lineSeparator() + "  Lumi shall print the list for you to check again...";

    public final static String ILLEGAL_LIST_NUMBER_MESSAGE =
            ANGRY_FACE + "Hey!! Lumi's list numbers are " + toBold("NUMBERS") + "!!" +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + createSpaces(8) +
            toBoldAndItalic("done <list number>") + lineSeparator();

    public final static String INVALID_TODO_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("TODO")  + " command..." +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + createSpaces(8) +
            toBoldAndItalic("todo <task description>") + lineSeparator();

    public final static String MISSING_TODO_DESCRIPTION_MESSAGE =
            THINKING_FACE + "Hmm... Lumi needs you to put in a " +
            toBold("task description") + " too!" + lineSeparator();

    public final static String INVALID_DEADLINE_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("DEADLINE")  + " command..." +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + createSpaces(8) +
            toBoldAndItalic("deadline <task description> /by <due date>") + lineSeparator();

    public final static String MISSING_DEADLINE_DESCRIPTION_MESSAGE =
            THINKING_FACE + "Hmm... Lumi senses missing information..." + lineSeparator() +
            "  Lumi needs you to put in either a " + toBold("task description") + " or a " +
            toBold("deadline") + "!" + lineSeparator();

    public final static String INVALID_EVENT_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("EVENT")  + " command..." +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + createSpaces(8) +
            toBoldAndItalic("event <task description> /at <duration>") + lineSeparator();

    public final static String MISSING_EVENT_DESCRIPTION_MESSAGE =
            THINKING_FACE + "Hmm... Lumi senses missing information..." + lineSeparator() +
            "  Lumi needs you to put in either a " + toBold("task description") + " or a " +
            toBold("duration") + "!" + lineSeparator();

    public final static String INVALID_DELETE_FORMAT_MESSAGE =
            SHOCK_FACE + "Oops!! Lumi cannot understand this " + toItalic("DELETE")  + " command..." +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + createSpaces(8) +
            toBoldAndItalic("delete <list number>") + lineSeparator();
}
