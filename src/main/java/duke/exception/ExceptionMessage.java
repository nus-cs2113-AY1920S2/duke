package duke.exception;

import duke.format.TextFormatter;

import static java.lang.System.lineSeparator;

public class ExceptionMessage {

    public final static String INVALID_ACTION_MESSAGE =
            TextFormatter.SHOCK_FACE +
            "Oh no!! Lumi has trouble understanding these words... Please try again!\n";

    public final static String INVALID_DONE_FORMAT_MESSAGE =
            TextFormatter.SHOCK_FACE +
            "Oops!! Lumi cannot understand this " + TextFormatter.toItalic("DONE")  + " command..." +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + TextFormatter.createSpaces(8) +
            TextFormatter.toBoldAndItalic("done <list number>") + lineSeparator();

    public final static String MISSING_LIST_NUMBER_MESSAGE =
            TextFormatter.THINKING_FACE +
            "Hmm... Lumi needs you to put in a " + TextFormatter.toBold("list number") + " too!" +
            lineSeparator();

    public final static String INVALID_LIST_NUMBER_MESSAGE =
            TextFormatter.SHOCK_FACE + "Whoops!! Lumi's list does not seem to have that number!" +
            lineSeparator() + "  Lumi shall print the list for you to check again...";

    public final static String ILLEGAL_LIST_NUMBER_MESSAGE =
            TextFormatter.ANGRY_FACE +
            "Hey!! Lumi's list numbers are " + TextFormatter.toBold("NUMBERS") + "!!" +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + TextFormatter.createSpaces(8) +
            TextFormatter.toBoldAndItalic("done <list number>") + lineSeparator();

    public final static String INVALID_TODO_FORMAT_MESSAGE =
            TextFormatter.SHOCK_FACE +
            "Oops!! Lumi cannot understand this " + TextFormatter.toItalic("TODO")  + " command..." +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + TextFormatter.createSpaces(8) +
            TextFormatter.toBoldAndItalic("todo <task description>") + lineSeparator();

    public final static String MISSING_TODO_DESCRIPTION_MESSAGE =
            TextFormatter.THINKING_FACE + "Hmm... Lumi needs you to put in a " +
            TextFormatter.toBold("task description") + " too!" + lineSeparator();

    public final static String INVALID_DEADLINE_FORMAT_MESSAGE =
            TextFormatter.SHOCK_FACE +
            "Oops!! Lumi cannot understand this " + TextFormatter.toItalic("DEADLINE")  + " command..." +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + TextFormatter.createSpaces(8) +
            TextFormatter.toBoldAndItalic("deadline <task description> /by <due date>") + lineSeparator();

    public final static String MISSING_DEADLINE_DESCRIPTION_MESSAGE =
            TextFormatter.THINKING_FACE + "Hmm... Lumi senses missing information..." + lineSeparator() +
            "  Lumi needs you to put in either a " + TextFormatter.toBold("task description") + " or a " +
            TextFormatter.toBold("deadline") + "!" + lineSeparator();

    public final static String INVALID_EVENT_FORMAT_MESSAGE =
            TextFormatter.SHOCK_FACE +
            "Oops!! Lumi cannot understand this " + TextFormatter.toItalic("EVENT")  + " command..." +
            lineSeparator() + "  Lumi needs you to follow this format:" +
            lineSeparator() + TextFormatter.createSpaces(8) +
            TextFormatter.toBoldAndItalic("event <task description> /at <duration>") + lineSeparator();

    public final static String MISSING_EVENT_DESCRIPTION_MESSAGE =
            TextFormatter.THINKING_FACE + "Hmm... Lumi senses missing information..." + lineSeparator() +
            "  Lumi needs you to put in either a " + TextFormatter.toBold("task description") + " or a " +
            TextFormatter.toBold("duration") + "!" + lineSeparator();

    public final static String INVALID_DELETE_FORMAT_MESSAGE =
            TextFormatter.SHOCK_FACE +
                    "Oops!! Lumi cannot understand this " + TextFormatter.toItalic("DELETE")  + " command..." +
                    lineSeparator() + "  Lumi needs you to follow this format:" +
                    lineSeparator() + TextFormatter.createSpaces(8) +
                    TextFormatter.toBoldAndItalic("delete <list number>") + lineSeparator();
}
