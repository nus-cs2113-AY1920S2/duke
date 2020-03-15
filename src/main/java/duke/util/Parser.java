package duke.util;

import duke.commands.*;
import duke.exceptions.IllegalClearException;
import duke.exceptions.IllegalDeleteException;
import duke.exceptions.IllegalDoneTaskException;
import duke.taskmanager.Deadline;
import duke.taskmanager.Event;
import duke.taskmanager.Task;
import duke.taskmanager.ToDo;

import java.io.IOException;
import java.util.List;

public class Parser {
    private static UI ui;
    private static List<Task> list;

    public Parser(UI ui, List<Task> list) {
        Parser.ui = ui;
        Parser.list = list;
    }

    /**
     * Main parser for user commands, it parses the command to its
     * corresponding action: add task, print task, mark as done,
     * delete task, find task or clear task. Then, it carries out the
     * action. It also print an error message when the user has entered
     * a wrong command at the main page.
     * @param  exeCommand   string input command by user
     * @throws IOException  when writing data to the file fails
     */
    public static void parseCommand(String exeCommand) throws IOException {
        try {
            CommandType[] commandType = CommandType.values();
            int index = Integer.parseInt(exeCommand)-1;
            CommandType command = commandType[index];
            switch (command) {
            case ADD_TASK:
                Parser.ui.printTaskType();
                parseAddCommand();
                break;
            case PRINT_TASKS:
                ListCommand.execute(list);
                break;
            case MARK_AS_DONE:
                try {
                    list = new DoneCommand().execute(list);
                } catch (IllegalDoneTaskException | IndexOutOfBoundsException e) {
                    DoneCommand.retry();
                }
                break;
            case DELETE_TASK:
                try {
                    list = new DeleteCommand().execute(list);
                } catch (IllegalDeleteException e) {
                    DeleteCommand.retry();
                }
                break;
            case FIND_TASK:
                new FindCommand().execute(list);
                break;
            case CLEAR_TASK:
                try {
                    list.clear();
                    Parser.ui.printRespondToClearTask(list);
                } catch (IllegalClearException e) {
                    Parser.ui.printClearErrorMessage();
                }
                break;
            default:
                Parser.ui.printErrorMessage();
            }
            Storage.writeData(list);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            Parser.ui.printErrorMessage();
        }
    }

    /**
     * Parser for add command, and checks whether the task
     * entered was already in the list. It parses the type
     * of task to get different information from the user
     * and print an error message when the user has entered
     * a wrong command when selecting the type.
     */
    private static void parseAddCommand() {
        String task = Parser.ui.getStringInput();
        TaskType[] taskType = TaskType.values();
        int index = Integer.parseInt(task)-1;
        TaskType selectTask = taskType[index];

        String by;
        System.out.println("    Please enter the task: ");
        task = Parser.ui.getStringInput();
        boolean isRepeat = checkRepeat(task);
        if (!isRepeat) {
            switch (selectTask) {
            case TODO:
                ToDo t = new ToDo(task);
                list.add(t);
                break;
            case DEADLINE:
                by = Parser.ui.printTaskInstruction("deadline ");
                Deadline d = new Deadline(task, by);
                list.add(d);
                break;
            case EVENT:
                by = Parser.ui.printTaskInstruction("venue ");
                Event e = new Event(task, by);
                list.add(e);
                break;
            default:
                Parser.ui.printErrorMessage();
                parseAddCommand();
            }
            Parser.ui.printRespondToAddTask(task);
        }
    }

    /**
     * This checks repeat for the input task. Returns the
     * boolean of whether the task entered was already in the
     * task list. The task argument is a String entered
     * by the user.
     * @param task the task name to be checked
     * @return     <code>true</code> if the task is already present
     *             in the task list
     *             <code>false</code> otherwise.
     */
    private static boolean checkRepeat(String task) {
        if (list!=null && !list.isEmpty()) {
            return executeCheckRepeat(task);
        }
        return false;
    }

    private static boolean executeCheckRepeat(String task) {
        for (Task i : list) {
            if (i != null && i.task.equals(task)) {
                Parser.ui.printRepeatMessage();
                return true;
            }
        }
        return false;
    }
}
