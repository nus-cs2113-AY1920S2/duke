package duke.commands;

public class ExitCommand extends Command {
    public ExitCommand(String parameters) {
        super(parameters);
    }

    public void Execute() {
        System.out.println(" Bye. Hope to see you again soon!\n");
    }
}
