package Duke.Command;
import Duke.Tasks.*;
import Duke.TaskList;
import Duke.Storage;
import Duke.DukeException;
import Duke.UI.Messages;
import Duke.UI.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    protected String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String getKeyword() {
        return fullCommand.substring(5);
    }

    public ArrayList<Task> searchKeyword() throws DukeException {
        String keyword = getKeyword();
        ArrayList<Task> listCopy = new ArrayList<Task>();

        for (int i = 0; i < TaskList.getSize(); ++i) {
            Task task = TaskList.fetchTask(i);
            String stringToSearch = task.action;
            if (stringToSearch.contains(keyword)) {
                listCopy.add(task);
            }
        }

        if (listCopy.size() == 0) {
            throw new DukeException();
        }

        return listCopy;
    }

    public void printOutList (ArrayList<Task> listCopy) {
        for (int j = 0; j < listCopy.size(); ++j) {
            Ui.out.println(Integer.toString(j+1) + ". " + listCopy.get(j));
        }
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            ArrayList<Task> listCopy = searchKeyword();
            ui.out.println(Messages.MESSAGE_FIND_SUCCESS);
            printOutList(listCopy);
        } catch (IndexOutOfBoundsException e) {
            ui.out.println(Messages.FIND_ERROR_MESSAGE);
        } catch (DukeException e) {
            ui.out.println(Messages.FIND_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean isExit(){
        return false;
    };

}
