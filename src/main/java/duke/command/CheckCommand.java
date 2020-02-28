package duke.command;

import duke.Duke;
import duke.common.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.function.ToDoubleBiFunction;

import static duke.common.Constants.TODO;

public class CheckCommand extends Command{

    private static String checkString;
    private static ArrayList<Integer> checkCount;

    public CheckCommand (String checkString) {
        this.checkString = checkString;
        checkCount = new ArrayList<>();
    }

    private Boolean isLocalDateFormat() {
        try {
            LocalDate.parse(this.checkString);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (!isLocalDateFormat()) {
            throw new DukeException("\t Please follow the format: check yyyy-mm-dd");
        }
        LocalDate date = LocalDate.parse(checkString);
        for(int i = 0; i<tasks.size(); i++) {
            if((tasks.getATask(i).getTime() == null)) { //either it is a todo event or the time does not follow standard
                continue;
            }
            if(tasks.getATask(i).getTime().equals(date)){
                checkCount.add(i);
            }
        }
        ui.showCheckTask(tasks,checkCount);
    }
}
