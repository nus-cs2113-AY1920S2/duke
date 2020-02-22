package duke;

import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.taskmanager.Deadline;
import duke.taskmanager.Event;
import duke.taskmanager.TaskManager;
import duke.taskmanager.ToDo;

import java.util.List;

public class Parser {
    public static final String ADD_TASK = "1";
    public static final String PRINT_TASKS = "2";
    public static final String MARK_AS_DONE = "3";
    public static final String DELETE_TASK = "4";
    public static final String FIND_TASK = "5";
    public static final String EXIT_COMMAND = "6";
    public static final String TODO = "1";
    public static final String DEADLINE = "2";
    public static final String EVENT = "3";
    public static UI ui;
    public static void parseCommand() {
        String exeCommand = ui.getStringInput();
        while (!exeCommand.equals(EXIT_COMMAND)) {
            Parser.parseCommandType(exeCommand); //to select the exeType and execute it
            ui.printExeType(); //user guide after execution of command
            exeCommand = ui.getStringInput(); //get the next command
        }
    }
    public static void parseCommandType(String exeCommand) {
        switch (exeCommand) {
        case ADD_TASK:
            ui.printTaskType();
            parseAddCommand();
            break;
        case PRINT_TASKS:
            ui.printTasks();
            break;
        case MARK_AS_DONE:
            DoneCommand doneCommand = new DoneCommand();
            doneCommand.execute();
            break;
        case DELETE_TASK:
            ui.printDelete();
            String task = ui.getStringInput();
            int index = ui.getIntegerInput();
            DeleteCommand delete = new DeleteCommand(task, index);
            delete.execute();
            break;
        case FIND_TASK:
            FindCommand findCommand = new FindCommand();
            findCommand.execute();
            break;
        }
    }

    private static void parseAddCommand() {
        String taskType; //taskType = ToDoo||Event||Deadline
        taskType = ui.getStringInput();
        String task, by;
        System.out.println("    Please enter the task: ");
        task = ui.getStringInput();
        boolean isRepeat = false;
        List<TaskManager> list = Tasklist.getTasks();
        for (TaskManager i : list) {
            if (i != null && i.task.equals(task)) {
                System.out.println("    The is a repeated task.");
                isRepeat = true;
                break;
            }
        }
        if (!isRepeat) {
            switch (taskType) {
            case TODO:
                ToDo t = new ToDo(task);
                Tasklist.add(t);
                break;
            case DEADLINE:
                System.out.println("    Please enter the deadline of your task: ");
                by = ui.getStringInput();
                Deadline d = new Deadline(task, by);
                Tasklist.add(d);
                break;
            case EVENT:
                System.out.println("    Please enter the venue of your task: ");
                by = ui.getStringInput();
                Event e = new Event(task, by);
                Tasklist.add(e);
                break;
            default:
                System.out.println("    Sorry,we do not understand your command. " +
                        "Please follow the instructions below.");
                parseAddCommand();
            }
            ui.printRespondToAddTask(task);
            Storage.writeData(Tasklist.getTasks());
        }
    }

}
