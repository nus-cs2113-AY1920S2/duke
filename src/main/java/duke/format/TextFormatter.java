package duke.format;

import duke.ui.UI;

import static org.fusesource.jansi.Ansi.*;

/**
 * <h3>Text Formatter</h3>
 * The <b>Text Formatter</b> is a utility tool that formats and edits the text displayed by the <b>UI.java</b>.
 * It also contains customised emoticon faces that are commonly displayed in the feedback messages to the user.
 * @see UI
 */
public class TextFormatter {

    public static final String HAPPY_FACE = "  \u0298\u15dc\u0298 "; // ʘᗜʘ

    public static final String SHOCK_FACE = "  \u0298\u15e9\u0298\" "; // ʘᗩʘ"

    public static final String ANGRY_FACE = "  \u0ca0~\u0ca0 "; // ಠ~ಠ

    public static final String THINKING_FACE = "  \u0298o\u0298? "; // ʘoʘ?

    public static final String SAD_FACE = "\u0ca5-\u0ca5 "; // ಥ-ಥ

    public static final String CHECK_ICON = ansi().render("@|green \u2713|@").toString(); // ✓

    public static final String CROSS_ICON = ansi().render("@|red \u2718|@").toString(); // ✘


    /**
     * Formats the specified string to become bold (and green).
     *
     * @param string The string to be made bold (and green)
     * @return The string in bold (and green)
     */
    public static String toBold(String string) {
        return ansi().render("@|bold,green " + string + "|@").toString();
    }

    /**
     * Formats the specified string to become italic (and blue).
     *
     * @param string The string to be made italic (and blue)
     * @return The string in italic (and blue)
     */
    public static String toItalic(String string) {
        return ansi().render("@|italic,blue " + string + "|@").toString();
    }

    /**
     * Formats the specified string to become both bold and italic (and blue).
     * @param string The string to be made bold and italic (and blue)
     * @return The string in bold and italic (and blue)
     */
    public static String toBoldAndItalic(String string) {
        return ansi().render("@|bold,italic,blue " + string + "|@").toString();
    }

    /**
     * Creates a string containing the specified number of spaces.
     *
     * @param numberOfSpaces The number of spaces the string will have
     * @return A string containing the specified number of spaces
     */
    public static String createSpaces(int numberOfSpaces) {
        return " ".repeat(numberOfSpaces);
    }
}
