package commands;

import common.Messages;
import data.Task;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_LIST = "  %d. [%c] %s";

    public ListCommand() {

    }

    @Override
    public CommandResult execute() {

        if (duke.getTaskList().getInternalList().size()>0){
            System.out.println(Messages.DIVIDER);
        }
        for (int i = 1; i <= duke.getTaskList().getInternalList().size() ; i++) {


            System.out.println(String.format(MESSAGE_LIST,
                    i,
                    duke.getTaskList().getInternalList().get(i-1).getChar(),
                    duke.getTaskList().getInternalList().get(i-1).getTaskDescription()));
        }
        System.out.println(Messages.DIVIDER);

        //not response from TextUi
        return null;
    }

}
