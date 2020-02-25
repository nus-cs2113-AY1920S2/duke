package duke.command;

public class Command {
    private String nameOfCommand;
    private String Argument;

    public Command(String Command, String Args) {
        this.nameOfCommand = Command;
        this.Argument = Args;
    }

    public String getCommandName() {
        return nameOfCommand;
    }

    public void setCommandName(String commandName) {
        nameOfCommand = commandName;
    }

    public String getArgs() {
        return Argument;
    }

    public void setArgs(String args) {
        Argument = args;
    }
}