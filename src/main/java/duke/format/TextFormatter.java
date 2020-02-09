package duke.format;

public class TextFormatter {

    public final static String HAPPY_FACE = "  \u0298\u15dc\u0298 "; // ʘᗜʘ

    public final static String SHOCK_FACE = "  \u0298\u15e9\u0298\" "; // ʘᗩʘ"

    public final static String ANGRY_FACE = "  \u0ca0~\u0ca0 "; // ಠ~ಠ

    public final static String THINKING_FACE = "  \u0298o\u0298? "; // ʘoʘ?

    public final static String SAD_FACE = "  \u0ca5-\u0ca5 "; // ಥ-ಥ

    public static String toBold(String string) {
        return "\033[1m" + string + "\033[0m";
    }

    public static String toItalic(String string) {
        return "\033[3m" + string + "\033[0m";
    }

    public static String toBoldAndItalic(String string) {
        return "\033[1m\033[3m" + string + "\033[0m";
    }

    public static String createSpaces(int numberOfSpaces) {
        return " ".repeat(numberOfSpaces);
    }
}
