public class TextFormatter {

    public static String toBold(String string) {
        return "\033[1m" + string + "\033[0m";
    }

    public static String toItalics(String string) {
        return "\033[3m" + string + "\033[0m";
    }

    public static String toBoldAndItalics(String string) {
        return "\033[1m\033[3m" + string + "\033[0m";
    }

    public static String createSpaces(int numberOfSpaces) {
        return " ".repeat(numberOfSpaces);
    }
}
