package Command;

/**
 * Sends the exit signal to main loop, program is exited after this command
 */
public class ExitCommand extends Command {

    /**
     * Check if exit command is given
     * @return signal to main loop that program will be terminated
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() {
        System.out.println("LISA: Bye, hope to see you again!");
    }
}
