package commands;

import java.util.ArrayList;

import common.exceptions.DukeException;
import common.tasks.Deadline;
import common.tasks.Event;
import common.tasks.Task;
import common.tasks.ToDo;

public class CommandExecution {
	public ArrayList<Task> list;
	public ArrayList<Task> removedTasks;
	
	public CommandExecution(ArrayList<Task> list, ArrayList<Task> removedTasks) {
		this.list = list;
		this.removedTasks = removedTasks;
	}

	public String makeDone(String line) throws DukeException {
        String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! Please specify a task.");
        }
        String msg = "";
        // sets a specified task as done
        int index;
        try { 
            index = Integer.valueOf(cmds[1]);
        } catch (Exception exception) {
            throw new DukeException("Oops!! Wrong format.");
        }
        if (index < 1) {
            throw new DukeException("Oops!! Invalid task.");
        } else if (index > list.size()) {
            throw new DukeException("Oops!! No such task.");
        } else if (list.get(index - 1).isDone()) {
            throw new DukeException("Oops!! Task is already completed.");
        } else {
        	Task curr = list.get(index - 1);
            curr.markAsDone();
            msg += "Nice! I've marked this task as done: " + '\n';
            msg += "    " + curr.toString();
        }
        return msg;
    }
    
    public String removeTask(String line) throws DukeException {
    	String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! Please select a task to remove.");
        }
        String msg = "";
        // sets a specified task as done
        int index;
        try { 
            index = Integer.valueOf(cmds[1]);
        } catch (Exception exception) {
            throw new DukeException("Oops!! Wrong format.");
        }
        if (index < 1) {
            throw new DukeException("Oops!! Invalid task.");
        } else if (index > list.size()) {
            throw new DukeException("Oops!! No such task.");
        } else {
        	Task curr = list.get(index - 1);
        	removedTasks.add(curr);
            msg += "Noted. I've removed this task: " + '\n';
            msg += "    " + curr.toString() + '\n';
            list.remove(index - 1);
            msg += "  Now you have " + list.size() + " tasks in the list.";
        }
        return msg;
    }
    
    public String addTodo(String line) throws DukeException {
        String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! The description of a todo cannt be empty.");
        }
        String msg = "";
        String description = line.substring(5);
        Task todo = new ToDo(description);
        list.add(todo);
        msg = outputMessage(todo);
        return msg;
    }
    
    public String addDeadline(String line) throws DukeException {
        String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! The description of a deadline cannt be empty.");
        }
        String msg = "";
        try {
            int index = line.indexOf('/');
            String description = line.substring(9, index - 1);
            String time = line.substring(index + 4);
            Task deadline = new Deadline(description, time);
            list.add(deadline);
            msg = outputMessage(deadline);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new DukeException("Oops!! Wrong format.");
        }
        return msg;
    }
    
    public String addEvent(String line) throws DukeException {
        String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! The description of an event cannot be empty.");
        }
        String msg = "";
        try {
            int index = line.indexOf('/');
            String description = line.substring(6, index - 1);
            String time = line.substring(index + 4);
            Task event = new Event(description, time);
            list.add(event);
            msg = outputMessage(event);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new DukeException("Oops!! Wrong format.");
        }
        return msg;
    }
    
    public String outputMessage(Task task) {
        String msg = "Got it. I've added this task: " + '\n';
        msg += "    " + task.toString() + '\n';
        msg += "  Now you have " + list.size() + " task(s) in the list.";
        return msg;
    }
    
    public String list() throws DukeException {
        String msg = "";
        // accesses the list
        if (list.isEmpty()) {
            throw new DukeException("Oops!! List is empty.");
        } else {
            msg += "Here are the tasks in your list:" + '\n';
            msg += '\n';
            int counter = 1;
            for (Task s : list) {
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
