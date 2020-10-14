package Duke.parser;

import Duke.data.Storage;
import Duke.data.TaskList;
import Duke.data.objects.*;
import Duke.ui.TextUi;
import java.util.ArrayList;

import static Duke.data.exceptions.DukeExceptions.printInvalidCommandException;
import static Duke.data.exceptions.DukeExceptions.printInvalidDeadlineException;


public class Parser {
    /**
     * Executes the command and returns a boolean whether the while loop continues.
     *
     * @param ui - TextUi object
     *           taskList - TaskList object
     *           String - String command Line
     * @return boolean
     */
    public Boolean execute(TextUi ui, TaskList taskList, String userInput) {
        String[] commands = userInput.trim().split(" ", 2);  // split the input into command and arguments
        if (commands.length == 0) {
            printInvalidCommandException();
        }
        final String commandWord = commands[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {
        case Deadline.COMMAND_WORD:
            executeDeadlineCommand(ui, taskList, arguments);
            return true;
        case Event.COMMAND_WORD:
            executeEventCommand(ui, taskList, arguments);
            return true;
        case ToDo.COMMAND_WORD:
            executeToDoCommand(ui, taskList, arguments);
            return true;
        case Delete.COMMAND_WORD:
            executeDeleteCommand(ui, taskList, arguments);
            return true;
        case Done.COMMAND_WORD:
            executeDoneCommand(ui, taskList, arguments);
            return true;
        case List.COMMAND_WORD:
            executeListCommand(ui, taskList);
            return true;
        case Help.COMMAND_WORD:
            executeHelpCommand(ui);
            return true;
        case Find.COMMAND_WORD:
            executeFindCommand(ui, taskList, arguments);
            return true;
        case "bye":
            return false;
        default:
            printInvalidCommandException();
            return true;
        }
    }
    /**
     * Executes showTaskList from TextUi object to print out Task List items.
     *
     * @param ui - TextUi object
     *           taskList - TaskList object
     */
    public void executeListCommand(TextUi ui, TaskList taskList){
        ui.showTaskList(taskList);
    }
    /**
     * Executes showHelpMessage from TextUi object to print out all Functions usage guide.
     *
     * @param ui - TextUi object
     */
    public void executeHelpCommand(TextUi ui){
        ui.showHelpMessage();
    }
    /**
     * Adds Task object into TaskList
     *
     * @param taskList - TaskList object
     *                 task - Task Object
     */
    public void addTask(TaskList taskList, Task task){
        ArrayList<Task> taskArrayList = taskList.getList();
        taskArrayList.add(task);
    }

    private void executeDeadlineCommand(TextUi ui, TaskList taskList, String arguments){
        try{
            String[] argumentArray = arguments.split(" /by ");
            addTask(taskList, new Deadline(argumentArray[0], argumentArray[1]));
            ui.showAddTask(taskList);
            Storage.saveList(taskList);
        }catch(ArrayIndexOutOfBoundsException err){
            printInvalidDeadlineException();
        }

    }
    private void executeFindCommand(TextUi ui, TaskList taskList, String arguments){
        ArrayList<Task> taskArrayList = taskList.getList();
        ArrayList<Task> tempFindList = new ArrayList<Task>();
        for(Task a : taskArrayList){
            if(a.getDescription().toLowerCase().contains(arguments.toLowerCase())){
                tempFindList.add(a);
            }
        }
        ui.showFindList(tempFindList);
    }
    private void executeEventCommand(TextUi ui, TaskList taskList, String arguments){
        String[] argumentArray = arguments.split(" /at ");
        addTask(taskList, new Event(argumentArray[0], argumentArray[1]));
        ui.showAddTask(taskList);
        Storage.saveList(taskList);
    }
    private void executeToDoCommand(TextUi ui, TaskList taskList, String arguments){
        addTask(taskList, new ToDo(arguments));
        ui.showAddTask(taskList);
        Storage.saveList(taskList);
    }
    private void executeDeleteCommand(TextUi ui, TaskList taskList, String arguments){
        ArrayList<Task> taskArrayList = taskList.getList();
        int delNumber = Integer.parseInt(arguments)-1;
        ui.showDeleteTask(taskList, delNumber);
        taskArrayList.remove(delNumber);
        Storage.saveList(taskList);
    }
    private void executeDoneCommand(TextUi ui, TaskList taskList, String arguments){
        ArrayList<Task> taskArrayList = taskList.getList();
        int taskNumber = Integer.parseInt(arguments)-1;
        taskArrayList.get(taskNumber).markAsDone();
        ui.showDoneTask(taskList, taskNumber);
        Storage.saveList(taskList);
    }

}
