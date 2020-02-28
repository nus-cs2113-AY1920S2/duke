package duke;

import duke.exception.InexplicitTimeDescription;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;


public class Duke {
    private static TaskList tasks = new TaskList();
    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();

    private static final String FILE_PATH = "data/duke.txt";

    public static void main(String[] args) {
        ui.showWelcomeMessage();

        File f = new File(FILE_PATH);

        try {
            storage.loadFileData(f,tasks);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFound();
        }

        String input = parser.getAndProcessInput();
        Boolean isInputValid = Boolean.FALSE;

        chooseOneModeAndRun(input, isInputValid);
    }

    private static void chooseOneModeAndRun(String input, Boolean isInputValid) {
        while (!isInputValid) {
            switch (input) {
            case "1":
                runInEchoMode(input);
                isInputValid = Boolean.TRUE;
                break;
            case "2":
                runInCommandMode(input);
                isInputValid = Boolean.TRUE;
                break;
            default:
                Ui.showUnknownModeInfo();
                input = parser.getAndProcessInput();
                break;
            }
        }
    }

    public static void runInEchoMode(String input) {
        Ui.showEchoModeGuideInfo();
        input = parser.getAndProcessInput();

        while (!parser.isByeCommand(input)) {
            Ui.repeatInput(input);
            input = parser.getAndProcessInput();
        }

        Ui.sayBye();
    }

    public static void runInCommandMode(String input) {
        Ui.showCommandModeGuideInfo();
        input = parser.getAndProcessInput();

        while (!parser.isByeCommand(input)) {
            Ui.showSplitLine();
            parseAndExecuteCommand(input);
            Ui.showSplitLine();
            input = parser.getAndProcessInput();
        }

        Ui.sayBye();
    }

    private static void parseAndExecuteCommand(String input) {
        if(parser.isDoneCommand(input)){
            setTaskAsDone(input);
        }else if(parser.isDeleteCommand(input)) {
            removeCertainTask(input);
        } else if(parser.isListCommand(input)){
            tasks.printTaskList();
        } else addNewTask(input);
    }

    private static void removeCertainTask(String input) {
        try{
            int taskIndex = parser.getTaskIndex(input);
            Task cur_task = tasks.getOneTask(taskIndex-1);
            tasks.remove(taskIndex);
            storage.saveToFile(FILE_PATH,tasks);
            Ui.printTaskRemovedInfo(cur_task);
            Ui.showTotalTaskNum();
        } catch (NumberFormatException e){
            Ui.showUnknownTaskIndexInfo();
        } catch (IndexOutOfBoundsException e){
            Ui.showTaskNotFoundInfo();
        }
    }

    private static void addNewTask(String input) {
        try {
            String type = parser.parseTaskType(input);
            String newTaskName = newTaskAndReturnName(input, type);
            storage.saveToFile(FILE_PATH,tasks);
            Ui.showAddTaskInfo(newTaskName);
        } catch(StringIndexOutOfBoundsException e){
            Ui.showCannotResolveTaskNameInfo(input);
        } catch (UnknownCommandException e) {
            Ui.showUnknownCommandInfo();
        } catch (InexplicitTimeDescription e){
            Ui.showInexplictTimeDesCriptionInfo();
        }
    }

    private static void setTaskAsDone(String input) {
        try{
            int taskIndex = parser.getTaskIndex(input);
            Task cur_task = tasks.getOneTask(taskIndex-1);
            cur_task.setTaskStatus(Task.DONE);
            storage.saveToFile(FILE_PATH,tasks);
            Ui.printTaskDoneInfo(cur_task);
        } catch (NumberFormatException e){
            Ui.showUnknownTaskIndexInfo();
        } catch (IndexOutOfBoundsException e){
            Ui.showTaskNotFoundInfo();
        }
    }


    private static String newTaskAndReturnName(String input, String type) throws UnknownCommandException, InexplicitTimeDescription {
        String newTaskName = "";
        switch (type) {
        case "todo":
            newTaskName = parser.getNewTodoName(input);
            tasks.append(new ToDo(newTaskName));
            break;
        case "deadline":
            try {
                newTaskName = parser.getDdlOrEventName(input);
            } catch (InexplicitTimeDescription e) {
                throw e;
            }
            String by = parser.getByOrDate(input);
            tasks.append(new Deadline(newTaskName, by));
            break;
        case "event":
            try {
                newTaskName = parser.getDdlOrEventName(input);
            } catch (InexplicitTimeDescription e) {
                throw e;
            }
            String date = parser.getByOrDate(input);
            tasks.append(new Event(newTaskName, date));
            break;
        default:
            throw new UnknownCommandException();
        }
        return newTaskName;
    }

}