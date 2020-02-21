package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    String command;

    public AddCommand(String parameters, String command) {
        super(parameters);
        this.command = command;
    }

    @Override
    public void Execute(TaskList tasks) {
        Task new_task;
        int findSeparator = 0;
        try {
            switch (this.command) {
            case ("todo"):
                new_task = new Todo(this.parameters);
                tasks.add(new_task);
                break;
            case ("deadline"):
                findSeparator = this.parameters.indexOf('/');
                new_task = new Deadline(this.parameters.substring(0, findSeparator), this.parameters.substring(findSeparator + 1));
                tasks.add(new_task);
                break;
            case ("event"):
                findSeparator = this.parameters.indexOf('/');
                new_task = new Event(this.parameters.substring(0, findSeparator), this.parameters.substring(findSeparator + 1));
                tasks.add(new_task);
                break;
            }
            Ui.printAddMessage(tasks.getSize());
            super.Execute(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Task description or date field is empty.");
        } catch (DukeException e) {
            System.out.println("Task description or date field is empty.");
        } catch (DateTimeParseException e){
            System.out.println("Please enter the date as: yyyy-mm-dd!");
        }
    }

}
