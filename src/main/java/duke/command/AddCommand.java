package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.exception.TestDukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String TODO_COMMAND_WORD = "todo";
    public static final String DEADLINE_COMMAND_WORD = "deadline";
    public static final String EVENT_COMMAND_WORD = "event";
    public AddCommand(String fullCommand, String taskType, String args) {
        super(fullCommand, taskType, args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = addTask(tasks);
        ui.printTask(newTask, tasks, "added");
        storage.save(tasks);
    }

    /**
     * Processes the todo command and gets the todo task.
     * @return a todo task the user wants to record.
     * @throws DukeException if task name missing.
     */
    public Todo processToDoDescription() throws DukeException {
        /*Process Todo Exception */
        TestDukeException testTodoException = new TestDukeException(super.fullCommand);
        testTodoException.throwToDoException();

        /*Set up a new Todo Task */
        String todoDescription = super.args;
        return new Todo(todoDescription);
    }

    /**
     * Processes the deadline command and gets the deadline task.
     * @return a deadline task the user wants to record.
     * @throws DukeException if task name or time missing.
     */
    public Deadline processDeadlineDescription() throws DukeException {
        /*Process Deadline Exception */
        TestDukeException testDeadlineException = new TestDukeException(super.fullCommand);
        testDeadlineException.throwDeadlineException();

        /*Set up a new Deadline Task */
        String[] words = super.args.split(" /by ");
        if(words.length < 2){
            throw new DukeException("Args for deadline is wrong!" + System.lineSeparator() +
                    "\tPlease input your command in the format of:" + System.lineSeparator() +
                    "\tdeadline TASK_NAME /by TIME.");
        }
        String name = words[0];
        String time = words[1];
        return new Deadline(name, time);
    }

    /**
     * Processes the event command and gets the event task.
     * @return a event task the user wants to record.
     * @throws DukeException if task name or time missing.
     */
    public Event processEventDescription() throws DukeException {
        /*Process Event Exception */
        TestDukeException testEventException = new TestDukeException(super.fullCommand);
        testEventException.throwEventException();

        /*Set up a new Event Task */
        String[] words = super.args.split(" /at ");
        if(words.length < 2){
            throw new DukeException("Args for event is wrong!" + System.lineSeparator() +
                    "\tPlease input your command in the format of" + System.lineSeparator() +
                    "\tevent TASK_NAME /at TIME.");
        }
        String name = words[0];
        String time = words[1];
        return new Event(name, time);
    }

    /**
     * Adds the task into task list.
     * @param tasks the task list.
     * @return the added task.
     * @throws DukeException if something wrong when processes the command.
     */
    public Task addTask(TaskList tasks) throws DukeException{
        Task newTask;

        boolean isToDo = super.taskType.equalsIgnoreCase("todo");
        boolean isDeadline = super.taskType.equalsIgnoreCase("deadline");
        boolean isEvent = super.taskType.equalsIgnoreCase("event");

        TestDukeException testDukeException = new TestDukeException(super.fullCommand);

        if (isToDo) {
            newTask = processToDoDescription();
        } else if (isDeadline) {
            newTask = processDeadlineDescription();
        } else if (isEvent) {
            newTask = processEventDescription();
        } else {
            testDukeException.throwTaskTypeException();
            newTask = null;
        }
        tasks.add(newTask);
        return newTask;

    }



}
