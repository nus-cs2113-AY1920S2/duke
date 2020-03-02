import java.text.MessageFormat;

public abstract class DeleteCommand extends Command {

    public DeleteCommand(String fullCommand) {
        this.editWord = "removed";
        this.fullCommand =  fullCommand;
    }

    public Task removeTask () {
        int number = Integer.parseInt(fullCommand.substring(7));
        Task t = TaskList.fetchTask(number-1);
        TaskList.deleteTask(t);
        return t;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Got it. I''ve {0} this task: \n{1}\n Now you have {2} task{3} in the list.",
                editWord, removeTask(), TaskList.getSize(), checkSingular());
    }

    /*@Override
    public Ui execute() {
        try {
            final Person target = getTargetPerson();
            personBook.removePerson(target);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_PERSONBOOK);
        }
    }*/

}
