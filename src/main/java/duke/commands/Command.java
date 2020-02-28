package duke.commands;

/**
 * <h3>Command</h3>
 * A <b>Command</b> is created using the information provide by the user input. The <b>Command</b> can then be
 * <i>executed</i> to produce a <b>Command Result</b>.
 * @see CommandResult
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @return The result of the command
     */
    public abstract CommandResult execute();
}
