package duke.commands;

import duke.exceptions.TimeMissingException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static duke.utils.Constants.CHECK_MARKER;

public class CheckCommand extends Command {
    private String description;
    private TaskList taskList;
    
    public CheckCommand(String description) {
        this.description = description;
        this.taskList = new TaskList();
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TimeMissingException {
        String[] taskOn = description.split(CHECK_MARKER);
        
        // "", "date"
        if (taskOn.length != 2) {
            throw new TimeMissingException();
        }
        
        String on = taskOn[1].trim();

        LocalDate date = LocalDate.parse(on);
        System.out.println(date);
        
        for (int i = 1; i <= tasks.getListSize(); i++) {
            Task task = tasks.getTask(i);
            System.out.println(task.getDate());
            
            if (task.getDate() == null) {
                continue;
            }
            
            if (task.getDate().equals(date)) {
                taskList.addTask(task);
            }
        }

        System.out.println(String.format("Here are the tasks on %s:", 
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.UK))));
        ui.listTasks(taskList);
    }
}
