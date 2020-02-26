package Duke.parser;

import Duke.data.Storage;
import Duke.data.TaskList;
import Duke.data.objects.Task;
import Duke.data.objects.Deadline;
import Duke.data.objects.Delete;
import Duke.data.objects.Done;
import Duke.data.objects.Event;
import Duke.data.objects.Help;
import Duke.data.objects.List;
import Duke.data.objects.ToDo;
import Duke.ui.TextUi;
import java.util.ArrayList;

import static Duke.data.exceptions.DukeExceptions.printInvalidCommandException;
import static Duke.data.exceptions.DukeExceptions.printInvalidDeadlineException;


public class Parser {
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
        case "bye":
            return false;
        default:
            printInvalidCommandException();
            return true;
        }
    }

    public void executeListCommand(TextUi ui, TaskList taskList){
        ui.showTaskList(taskList);
    }
    public void executeHelpCommand(TextUi ui){
        ui.showHelpMessage();
    }
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
