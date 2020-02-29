package duke.parser;

import java.util.regex.Pattern;

public class CommandInfo {
    private Pattern pattern;
    private String errorMessage;
    public CommandInfo(Pattern pattern, String errorMessage) {
        this.pattern = pattern;
        this.errorMessage = errorMessage;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
