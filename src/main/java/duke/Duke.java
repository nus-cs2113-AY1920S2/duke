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
    private static TaskList tasks;
    protected static Storage storage;
    private static Parser parser;

    public Duke(String filePath) {
        tasks = new TaskList();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            storage.loadFileData(tasks);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFound();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run(){
        prepare();
        chooseOneModeAndRun();
        exit();
    }

    public static void prepare(){
        Ui.showWelcomeMessage();
    }

    public static void exit(){
        Ui.sayBye();
    }

    private static void chooseOneModeAndRun() {
        String input = parser.getAndProcessInput();
        boolean isInputValid = false;

        while (!isInputValid) {
            switch (input) {
            case "1":
                runInEchoMode();
                isInputValid = true;
                break;
            case "2":
                runInCommandMode();
                isInputValid = true;
                break;
            default:
                Ui.showUnknownModeInfo();
                input = parser.getAndProcessInput();
                break;
            }
        }
    }

    public static void runInEchoMode() {
        Ui.showEchoModeGuideInfo();
        String input = parser.getAndProcessInput();

        while (!parser.isByeCommand(input)) {
            Ui.repeatInput(input);
            input = parser.getAndProcessInput();
        }
    }

    public static void runInCommandMode() {
        Ui.showCommandModeGuideInfo();
        String input = parser.getAndProcessInput();

        while (!parser.isByeCommand(input)) {
            Ui.showSplitLine();
            parseAndExecuteCommand(input);
            Ui.showSplitLine();
            input = parser.getAndProcessInput();
        }
    }

    private static void parseAndExecuteCommand(String input) {
        if(parser.isDoneCommand(input)){
            setTaskAsDone(input);
        }else if(parser.isDeleteCommand(input)) {
            removeCertainTask(input);
        } else if(parser.isListCommand(input)){
            tasks.printTaskList();
        } else if(parser.isFindCommand(input)){
            String targetWords = parser.getTargetWords(input);
            tasks.showAllRelatedTasks(targetWords);
        } else addNewTask(input);
    }

    private static void removeCertainTask(String input) {
        try{
            int taskIndex = parser.getTaskIndex(input);
            Task cur_task = tasks.getOneTask(taskIndex-1);
            tasks.remove(taskIndex);
            storage.saveToFile(tasks);
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
            storage.saveToFile(tasks);
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
            storage.saveToFile(tasks);
            Ui.printTaskDoneInfo(cur_task);
        } catch (NumberFormatException e){
            Ui.showUnknownTaskIndexInfo();
        } catch (IndexOutOfBoundsException e){
            Ui.showTaskNotFoundInfo();
        }
    }


    private static String newTaskAndReturnName(String input, String type)
            throws UnknownCommandException, InexplicitTimeDescription {
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