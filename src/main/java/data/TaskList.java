package data;

import java.io.IOException;
import java.util.ArrayList;

import common.exceptions.DukeException;
import common.tasks.Task;
import storage.Storage;
import ui.TextUi;
import commands.CommandExecution;

public class TaskList {
	private ArrayList<Task> tasks;
    private ArrayList<Task> removedTasks;
    private CommandExecution commandExecution;
    private boolean isExit;
    
    public TaskList(Storage storage) {
    	this.tasks = storage.list;
    	this.removedTasks = new ArrayList<>();
    	this.commandExecution = new CommandExecution(tasks, removedTasks);
    	this.isExit = false;
    }
    
    public boolean isTerminated() {
    	return this.isExit;
    }
    
    public void executeCommand(TextUi ui, Storage storage, String cmd, String input) throws DukeException, IOException {
    	String msg = "  ";
        try {
            switch (cmd) {
                case "bye":
                    // exits the program
                	this.isExit = true;
                	return;
                case "list":
                    msg += list();
                    break;
                case "show_deleted":
                	msg += showRemoved();
                	break;
                case "delete":
                	msg += commandExecution.removeTask(input);
                	storage.writeToFile(tasks);
                	break;
                case "done":
                    msg += commandExecution.makeDone(input);
                    storage.writeToFile(tasks);
                    break;
                case "todo":
                    msg += commandExecution.addTodo(input);
                    storage.writeToFile(tasks);
                    break;
                case "deadline":
                    msg += commandExecution.addDeadline(input);
                    storage.writeToFile(tasks);
                    break;
                case "event":
                    msg += commandExecution.addEvent(input);
                    storage.writeToFile(tasks);
                    break;
                default:
                    throw new DukeException("Oops!! Unknown Command.");
            }
        } catch (DukeException exception) {
            msg += exception.getMessage();
        }
        ui.outputMessage(msg);
    }
    
    public String list() throws DukeException {
        String msg = "";
        // accesses the list
        if (tasks.isEmpty()) {
            throw new DukeException("Oops!! List is empty.");
        } else {
            msg += "Here are the tasks in your list:" + '\n';
            msg += '\n';
            int counter = 1;
            for (Task s : tasks) {
                msg += "    " + counter + ". " + s.toString();
                counter++;
                msg += '\n';
            }
        }
        return msg;
    }
    
    public String showRemoved() throws DukeException {
    	String msg = "";
        // accesses the list
        if (removedTasks.isEmpty()) {
            throw new DukeException("Oops!! No removed tasks.");
        } else {
            msg += "Here are the tasks that have been removed:" + '\n';
            msg += '\n';
            int counter = 1;
            for (Task s : removedTasks) {
                msg += "    " + counter + ". " + s.toString();
                counter++;
                msg += '\n';
            }
        }
        return msg;
    }
    
}
