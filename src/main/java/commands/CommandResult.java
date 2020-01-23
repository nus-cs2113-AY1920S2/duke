package commands;

import ui.TextUi;

/**
 * Display the execute result, by constructing a CommandResult object,
 * parameter: Execute feedback to user
 */

public class CommandResult {

    public final String feedbackToUser;
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        TextUi.showResult(this.feedbackToUser);
    }

}
