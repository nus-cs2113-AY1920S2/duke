public class ExceptionMessage {

    final static String shockIcon = "  \u0298\u15e9\u0298\" "; // ʘᗩʘ"

    final static String angryIcon = "  \u0ca0~\u0ca0 "; // ಠ~ಠ

    final static String INVALID_ACTION_MESSAGE =
            shockIcon + "Oh no!! Lumi has trouble understanding these words... Please try again!\n";

    final static String INVALID_DONE_FORMAT_MESSAGE =
            shockIcon + "Oops!! Lumi cannot understand this " + TextFormatter.toItalics("DONE")  + " command..." +
            System.lineSeparator() + "  Lumi needs you to follow this format:" +
            System.lineSeparator() + TextFormatter.createSpaces(8) + "done <list number>\n";

    final static String MISSING_LIST_NUMBER_MESSAGE =
            shockIcon + "Hmm... Lumi needs you to put in a list number too!\n";

    final static String INVALID_LIST_NUMBER_MESSAGE =
            shockIcon + "Whoops!! Lumi's list does not seem to have that number!" +
            System.lineSeparator() + "  Lumi shall print the list for you to check again...\n";

    final static String ILLEGAL_LIST_NUMBER_MESSAGE =
            angryIcon + "Hey!! Lumi's list numbers are " + TextFormatter.toBold("numbers") + "!!" +
            System.lineSeparator() + "  Lumi needs you to follow this format:" +
            System.lineSeparator() + TextFormatter.createSpaces(8) + "done <list number>\n";
}
