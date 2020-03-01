package duke.format;

import duke.ui.UI;

/**
 * <h3>Text Formatter</h3>
 * The <b>Text Formatter</b> is a utility tool that formats and edits the text displayed by the <b>UI</b>.
 * It also contains customised emoticon faces that are commonly displayed in the feedback messages to the user.
 *
 * @see UI
 */
public class TextFormatter {

    public final static String HAPPY_FACE = "  \u0298\u15dc\u0298 "; // ʘᗜʘ

    public final static String SHOCK_FACE = "  \u0298\u15e9\u0298\" "; // ʘᗩʘ"

    public final static String ANGRY_FACE = "  \u0ca0~\u0ca0 "; // ಠ~ಠ

    public final static String THINKING_FACE = "  \u0298o\u0298? "; // ʘoʘ?

    public final static String SAD_FACE = "\u0ca5-\u0ca5 "; // ಥ-ಥ

    /**
     * Formats the specified string to become bold.
     *
     * @param string The string to be made bold
     * @return The string in bold
     */
    public static String toBold(String string) {
        return "\033[1m" + string + "\033[0m";
    }

    /**
     * Formats the specified string to become italic.
     *
     * @param string The string to be made italic
     * @return The string in italic
     */
    public static String toItalic(String string) {
        return "\033[3m" + string + "\033[0m";
    }

    /**
     * Formats the specified string to become both bold and italic.
     * @param string The string to be made bold and italic
     * @return The string in bold and italic
     */
    public static String toBoldAndItalic(String string) {
        return "\033[1m\033[3m" + string + "\033[0m";
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
