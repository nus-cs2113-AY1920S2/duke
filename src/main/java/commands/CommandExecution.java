package commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import common.exceptions.DukeException;
import common.tasks.Deadline;
import common.tasks.Event;
import common.tasks.Task;
import common.tasks.ToDo;

import static common.HelpPage.HelpMessage;

/**
 * Handles all the execution and validation of commands.
 */
public class CommandExecution {
    public ArrayList<Task> tasks;
    public ArrayList<Task> removedTasks;
    
    /**
     * Constructor for CommandExecution class.
     * 
     * @param tasks List of tasks that references TaskList.
     * @param removedTasks List of removed tasks that references TaskList.
     */
    public CommandExecution(ArrayList<Task> tasks, ArrayList<Task> removedTasks) {
        this.tasks = tasks;
        this.removedTasks = removedTasks;
    }
    
    /**
     * Returns a help message detailing all accepted commands and formats.
     * 
     * @return HelpMessage
     */
    public String getHelp() {
    	return HelpMessage;
    }
    
    /**
     * Searches the current tasks for a particular substring and outputs a list of
     * all tasks that contain the substring.
     * 
     * @param command Command containing the substring to be found.
     * @return msg String representation of a list of all tasks containing the substring.
     * @throws DukeException if the command is invalid.
     */
    public String findTask(Command command) throws DukeException {
        CommandValidation.validate(command, "find");
        ArrayList<Task> foundTasks = new ArrayList<>();
        Optional<String> commandDescription = command.getDescription();
        String toFind = commandDescription.get();
        for (Task task : tasks) {
            if (task.getDescription().contains(toFind)) {
                foundTasks.add(task);
            }
        }
        String msg = listFound(foundTasks);
        return msg;
    }
    
    /**
     * Formats the list of found tasks into a proper chatbot reply.
     * 
     * @param list List of found tasks.
     * @return msg Properly formatted chatbot reply.
     */
    public String listFound(ArrayList<Task> list) {
        String msg = "";
        // accesses the list
        if (list.isEmpty()) {
            msg = "No matching tasks found!";
        } else {
            msg = "Here are the matching tasks in your list:" + '\n';
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
    
    /**
     * Clear all tasks, including stored tasks.
     * @return msg Chatbot reply acknowledging that the command has been executed.
     */
    public String clearAll() {
        tasks.removeAll(tasks);
        String msg = "All tasks have been cleared.";
        return msg;
    }
    
    /**
     * Shows upcoming tasks within the number of days specified in the input command,
     * and outputs a list of all such tasks.
     * 
     * @param command Command containing the timeframe in days.
     * @return msg String representation of a list of all upcoming tasks in the time frame.
     * @throws DukeException if the command is invalid.
     */
    public String showUpcoming(Command command) throws DukeException {
        CommandValidation.validate(command, "show");
        Optional<String> commandDescription = command.getDescription();
        int days = Integer.valueOf(commandDescription.get());
        ArrayList<Task> upcomingTasks = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Task task : tasks) {
            if (task.getOptionalDate().isPresent()) {
                LocalDate date = task.getDate();
                long period = today.until(date, ChronoUnit.DAYS);
                if (period <= days && period >= 0) {
                    upcomingTasks.add(task);
                }
            }
        }
        Collections.sort(upcomingTasks);
        String msg = listUpcoming(upcomingTasks, days);
        return msg;
    }
    
    /**
     * Formats the list of upcoming tasks into a proper chatbot reply.
     * 
     * @param list List of upcoming tasks.
     * @param days Timeframe of upcoming tasks.
     * @return msg Properly formatted chatbot reply.
     */
    public String listUpcoming(ArrayList<Task> list, int days) {
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
    
    /**
     * Shows all overdue tasks that have not been completed, and outputs 
     * a list of all such tasks.
     * 
     * @param command Command to be executed.
     * @return msg String representation of a list of all overdue tasks.
     */
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
    
    /**
     * Formats the list of overdue tasks into a proper chatbot reply.
     * 
     * @param list List of overdue tasks.
     * @return msg Properly formatted chatbot reply.
     */
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
    
    /**
     * Remove all completed tasks.
     * 
     * @return msg Chatbot reply acknowledging that the command has been executed.
     */
    public String removeCompleted() {
        ArrayList<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isDone()) {
                completedTasks.add(task);
                removedTasks.add(task);
            }
        }
        tasks.removeAll(completedTasks);
        String msg = "All completed tasks have been removed.";
        return msg;
    }
    
    /**
     * Removes all tasks that have passed, regardless of if the task has been
     * completed or not.
     * 
     * @return msg Chatbot reply acknowledging that the command has been executed.
     */
    public String removePast() {
        ArrayList<Task> pastTasks = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Task task : tasks) {
            if (task.getOptionalDate().isPresent()) {
                LocalDate date = task.getDate();
                if (date.isBefore(today)) {
                    pastTasks.add(task);
                    removedTasks.add(task);
                }
            }
        }
        tasks.removeAll(pastTasks);
        String msg = "All past tasks have been removed.";
        return msg;
    }
    
    /**
     * Sets an undone task as done.
     * 
     * @param command Command to be executed.
     * @return msg Chatbot reply acknowledging that the command has been executed.
     * @throws DukeException if the command is invalid.
     */
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
    
    /**
     * Removes the selected task.
     * 
     * @param command Command to be executed.
     * @return msg Chatbot reply acknowledging that the command has been executed.
     * @throws DukeException if the command is invalid.
     */
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
    
    /**
     * Adds a todo task.
     * 
     * @param command Command to be executed.
     * @return msg Chatbot reply acknowledging that the command has been executed.
     * @throws DukeException if the command is invalid.
     */
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
    
    /**
     * Adds a deadline task.
     * 
     * @param command Command to be executed.
     * @return msg Chatbot reply acknowledging that the command has been executed.
     * @throws DukeException if the command is invalid.
     */
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
    
    /**
     * Adds an event task.
     * 
     * @param command Command to be executed.
     * @return msg Chatbot reply acknowledging that the command has been executed.
     * @throws DukeException if the command is invalid.
     */
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
    }
    
    /**
     * Properly formats the chatbot reply when adding tasks.
     * 
     * @param task Task to be added
     * @return Properly formatted chatbot reply.
     */
    public String outputMessage(Task task) {
        String msg = "Got it. I've added this task: " + '\n';
        msg += "    " + task.toString() + '\n';
        msg += "  Now you have " + tasks.size() + " task(s) in the list.";
        return msg;
    }
    
}
