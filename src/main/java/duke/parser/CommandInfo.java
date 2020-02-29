package duke.parser;

import java.util.regex.Pattern;

/**
 * Class to bundle a <code>Command</code>'s regex pattern and error message
 */
public class CommandInfo {
    private Pattern pattern;
    private String errorMessage;

    /**
     * @param pattern <code>Command</code>'s regex pattern
     * @param errorMessage <code>Command</code>'s error message
     */
    public CommandInfo(Pattern pattern, String errorMessage) {
        this.pattern = pattern;
        this.errorMessage = errorMessage;
    }

    /**
     * @return <code>Command</code>'s regex pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     * @return <code>Command</code>'s error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
