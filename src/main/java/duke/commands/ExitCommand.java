package duke.commands;

import duke.ui.Ui;

import java.util.regex.Pattern;

public class ExitCommand extends Command {
    public static final Pattern FORMAT = Pattern.compile("^bye\\s*", Pattern.CASE_INSENSITIVE);
    public static final String KEYWORD = "bye";
    public static final String EXAMPLE_USAGE = "bye";
    public static final String ERROR_MESSAGE = "Command needs to be in form: bye";
    public ExitCommand() {
        super(null);
    }

    public void execute() {
        Ui.sayGoodbye();
        System.exit(0);
    }
}
