package commands;

import ui.TextUi;

/** Display the execute result, by constructing a CommandResult object **/
public class CommandResult {

    public final String feedbackToUser;

    /**
     * @parameter: Execute feedback to user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        TextUi.showResult(this.feedbackToUser);
    }

}
