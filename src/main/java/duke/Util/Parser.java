package duke.Util;

import duke.commands.*;
import duke.taskmanager.Deadline;
import duke.taskmanager.Event;
import duke.taskmanager.Tasks;
import duke.taskmanager.ToDo;

import java.io.IOException;
import java.util.List;

public class Parser {
    public static final String TODO = "1";
    public static final String DEADLINE = "2";
    public static final String EVENT = "3";
    private static UI ui;
    private static List<Tasks> list = Tasklist.getTasks();
    public Parser(UI ui) {
        Parser.ui = ui;
    }

    public void parseCommand(String exeCommand) throws IOException {
        CommandType commandType = CommandType.valueOf(exeCommand);
        switch (commandType) {
        case ADD_TASK:
            Parser.ui.printTaskType();
            parseAddCommand();
            Storage.writeData(list);
            break;
        case PRINT_TASKS:
            ListCommand listCommand = new ListCommand();
            listCommand.execute();
            break;
        case MARK_AS_DONE:
            DoneCommand doneCommand = new DoneCommand(ui);
            doneCommand.execute();
            int indexDoneTask = doneCommand.getIndexOfTask();
            saveDoneList(indexDoneTask);
            break;
        case DELETE_TASK:
            DeleteCommand delete = new DeleteCommand(ui);
            delete.execute();
            break;
        case FIND_TASK:
            FindCommand findCommand = new FindCommand(ui);
            findCommand.execute();
            break;
        default:
            System.out.println("    Wrong command. Please try again.");
        }
    }

    private static void parseAddCommand() {
        String task = Parser.ui.getStringInput();
        TaskType taskType = TaskType.valueOf(task);
        String by;
        System.out.println("    Please enter the task: ");
        task = Parser.ui.getStringInput();
        boolean isRepeat = checkRepeat(task);
        if (!isRepeat) {
            switch (taskType) {
            case TODO:
                ToDo t = new ToDo(task);
                Tasklist.add(t);
                break;
            case DEADLINE:
                System.out.println("    Please enter the deadline of your task: ");
                by = Parser.ui.getStringInput();
                Deadline d = new Deadline(task, by);
                Tasklist.add(d);
                break;
            case EVENT:
                System.out.println("    Please enter the venue of your task: ");
                by = Parser.ui.getStringInput();
                Event e = new Event(task, by);
                Tasklist.add(e);
                break;
            default:
                System.out.println("    Sorry,we do not understand your command. " +
                        "Please follow the instructions below.");
                parseAddCommand();
            }
            Parser.ui.printRespondToAddTask(task);
        }
    }

    private static boolean checkRepeat(String task) {
        if (list!=null && !list.isEmpty()) {
            for (Tasks i : list) {
                if (i != null && i.task.equals(task)) {
                    System.out.println("    The is a repeated task.");
                    return true;
                }
            }
        }
        return false;
    }

    private static void saveDoneList(int indexDoneTask) {
        Tasks task = list.get(indexDoneTask);
        task.markAsDone();
        list.set(indexDoneTask, task);
        Storage.writeData(list);
        Parser.ui.clearInput();
    }
}
