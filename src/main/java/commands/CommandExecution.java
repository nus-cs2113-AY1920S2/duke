package commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import common.exceptions.DukeException;
import common.tasks.Deadline;
import common.tasks.Event;
import common.tasks.Task;
import common.tasks.ToDo;

public class CommandExecution {
	public ArrayList<Task> tasks;
	public ArrayList<Task> removedTasks;
	
	public CommandExecution(ArrayList<Task> tasks, ArrayList<Task> removedTasks) {
		this.tasks = tasks;
		this.removedTasks = removedTasks;
	}
	
<<<<<<< HEAD
	public String findTask(String line) throws DukeException {
		ArrayList<Task> foundTasks = new ArrayList<>();
		String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! Please specify a task.");
        }
        String msg = "";
        String toFind = line.substring(5);
        for (Task task : list) {
        	if (task.getDescription().contains(toFind)) {
        		foundTasks.add(task);
        	}
        }
        msg += list(foundTasks);
        return msg;
	}

	public String makeDone(String line) throws DukeException {
        String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! Please specify a task.");
        }
=======
	public String clearAll() {
		tasks.removeAll(tasks);
		String msg = "All tasks have been cleared.";
		return msg;
	}
	
	public String showUpcoming(Command command) throws DukeException {
		CommandValidation.validate(command, "show");
		Optional<String> commandDescription = command.getDescription();
		int days = Integer.valueOf(commandDescription.get());
		ArrayList<Task> upcomingTasks = new ArrayList<>();
		LocalDate today = LocalDate.now();
		for (Task task : tasks) {
			if (task.getOptionalDate().isPresent()) {
				LocalDate date = task.getDate();
				Period period = Period.between(today, date);
				if (period.getDays() <= days && period.getDays() >= 0) {
					upcomingTasks.add(task);
				}
			}
		}
		Collections.sort(upcomingTasks);
		String msg = listUpcoming(upcomingTasks, days);
		return msg;
	}
	
	public String listUpcoming(ArrayList<Task> list, int days) {
>>>>>>> master
        String msg = "";
        // accesses the list
        if (list.isEmpty()) {
        	if (days == 0) {
        		msg = "Great! No upcoming tasks today! :)";
        	} else {
        		msg = "Great! No upcoming tasks within " + days + " day(s)! :)";
        	}
        } else {
        	if (days == 0) {
        		msg = "Here are the upcoming tasks today:" + '\n';
        	} else {
        		msg = "Here are the upcoming tasks within " + days + " day(s):" + '\n';
        	}
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
	
	public String showOverdue(Command command) {
		ArrayList<Task> overdueTasks = new ArrayList<>();
		LocalDate today = LocalDate.now();
		for (Task task : tasks) {
			if (task.getOptionalDate().isPresent()) {
				LocalDate date = task.getDate();
				if (date.isBefore(today) && !task.isDone()) {
					overdueTasks.add(task);
				}
			}
		}
		Collections.sort(overdueTasks);
		String msg = listOverdue(overdueTasks);
		return msg;
	}
	
	public String listOverdue(ArrayList<Task> list) {
        String msg = "";
        // accesses the list
        if (list.isEmpty()) {
        	msg = "Great! No uncompleted overdue tasks! :)";
        } else {
        	msg = "Here are the uncompleted overdue tasks:" + '\n';
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
	
	public String removeCompleted() {
    	ArrayList<Task> completedTasks = new ArrayList<>();
    	for (Task task : tasks) {
			if (task.isDone()) {
				completedTasks.add(task);
				removedTasks.add(task);
			}
		}
    	tasks.removeAll(completedTasks);
    	String msg = "All completed tasks have been removed";
    	return msg;
    }
	
	// removes both completed and uncompleted!
	public String removePast() {
		ArrayList<Task> pastTasks = new ArrayList<>();
		LocalDate today = LocalDate.now();
		for (Task task : tasks) {
			if (task.getOptionalDate().isPresent()) {
				LocalDate date = task.getDate();
				if (date.isBefore(today)) {
					pastTasks.add(task);
				}
			}
		}
		tasks.removeAll(pastTasks);
    	String msg = "All past tasks have been removed";
    	return msg;
	}

	public String makeDone(Command command) throws DukeException {
		CommandValidation.validate(command, "done", tasks);
		Optional<String> commandDescription = command.getDescription();
		String msg = "";
		// sets a specified task as done
		int index = Integer.valueOf(commandDescription.get());
		Task curr = tasks.get(index - 1);
		curr.markAsDone();
		msg += "Nice! I've marked this task as done: " + '\n';
		msg += "    " + curr.toString();
		return msg;
	}
    
    public String removeTask(Command command) throws DukeException {
    	CommandValidation.validate(command, "remove", tasks);
    	Optional<String> commandDescription = command.getDescription();
        String msg = "";
		int index = Integer.valueOf(commandDescription.get());
		Task curr = tasks.get(index - 1);
		removedTasks.add(curr);
		msg += "Noted. I've removed this task: " + '\n';
		msg += "    " + curr.toString() + '\n';
		tasks.remove(index - 1);
		msg += "  Now you have " + tasks.size() + " tasks in the tasks.";
        return msg;
    }
    
    public String addTodo(Command command) throws DukeException {
    	CommandValidation.validate(command, "todo");
    	Optional<String> commandDescription = command.getDescription();
        String msg = "";
        String description = commandDescription.get();
        Task todo = new ToDo(description);
        tasks.add(todo);
        msg = outputMessage(todo);
        return msg;
    }
    
    public String addDeadline(Command command) throws DukeException {
    	CommandValidation.validate(command, "deadline");
    	Optional<String> commandDescription = command.getDescription();
    	Optional<LocalDate> date = command.getDate();
    	Optional<LocalTime> time = command.getTime();
		String description = commandDescription.get();
		Task deadline = new Deadline(description, date, time);
		tasks.add(deadline);
		String msg = outputMessage(deadline);
		return msg;
    }
    
<<<<<<< HEAD
    public String list(ArrayList<Task> tasks) throws DukeException {
        String msg = "";
        // accesses the list
        if (list.isEmpty()) {
            throw new DukeException("Oops!! List is empty.");
        } else {
            msg += "Here are the found tasks:" + '\n';
            msg += '\n';
            int counter = 1;
            for (Task s : tasks) {
                msg += "    " + counter + ". " + s.toString();
                counter++;
                msg += '\n';
            }
        }
        return msg;
=======
    public String addEvent(Command command) throws DukeException {
    	CommandValidation.validate(command, "event");
    	Optional<String> commandDescription = command.getDescription();
    	Optional<LocalDate> date = command.getDate();
    	Optional<LocalTime> time = command.getTime();
		String description = commandDescription.get();
		Task event = new Event(description, date, time);
		tasks.add(event);
		String msg = outputMessage(event);
		return msg;
>>>>>>> master
    }
    
    public String outputMessage(Task task) {
        String msg = "Got it. I've added this task: " + '\n';
        msg += "    " + task.toString() + '\n';
        msg += "  Now you have " + tasks.size() + " task(s) in the list.";
        return msg;
    }

}
