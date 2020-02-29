package duke;

import duke.exception.InexplicitTimeDescription;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;


public class TaskList {
    private static ArrayList<Task> taskList;
    private static Parser parser = new Parser();

    public TaskList(){
        taskList = new ArrayList<>();
    }

    public Task getOneTask(int taskIndex){
        return taskList.get(taskIndex);
    }

    public static int getLenOfList(){
        return taskList.size();
    }

    public void append(Task task){
        taskList.add(task);
    }

    public void remove(int taskIndex){
        taskList.remove(taskIndex-1);
        Task.setTaskNum(Task.getTaskNum()-1);
    }

    public void printTaskList(){
        int index = 0;
        for(Task task:taskList){
            index++;
            String prefix = "    "+index+".";
            System.out.println(prefix+task.showTaskInfo());
        }
    }

    public void showAllRelatedTasks(String targetWords){
        int index = 0;

        for(Task task:taskList){
            if(!task.showTaskInfo().contains(targetWords)) continue;
            index++;
            String prefix = "    "+index+".";
            if(index==1) System.out.println("    Here are the matching tasks in your list:");
            System.out.println(prefix+task.showTaskInfo());
        }

        if(index==0) System.out.println("    No matching tasks found!");
    }

    public String newTaskAndReturnName(String input, String type)
            throws UnknownCommandException, InexplicitTimeDescription {
        String newTaskName = "";
        switch (type) {
        case "todo":
            newTaskName = parser.getNewTodoName(input);
            this.append(new ToDo(newTaskName));
            break;
        case "deadline":
            try {
                newTaskName = parser.getDdlOrEventName(input);
            } catch (InexplicitTimeDescription e) {
                throw e;
            }
            String by = parser.getByOrDate(input);
            this.append(new Deadline(newTaskName, by));
            break;
        case "event":
            try {
                newTaskName = parser.getDdlOrEventName(input);
            } catch (InexplicitTimeDescription e) {
                throw e;
            }
            String date = parser.getByOrDate(input);
            this.append(new Event(newTaskName, date));
            break;
        default:
            throw new UnknownCommandException();
        }
        return newTaskName;
    }

    public void addNewTaskAndSave(String input,Storage storage) {
        try {
            String type = parser.parseTaskType(input);
            String newTaskName = this.newTaskAndReturnName(input,type);
            storage.saveToFile(this);
            Ui.showAddTaskInfo(newTaskName);
        } catch(StringIndexOutOfBoundsException e){
            Ui.showCannotResolveTaskNameInfo(input);
        } catch (UnknownCommandException e) {
            Ui.showUnknownCommandInfo();
        } catch (InexplicitTimeDescription e){
            Ui.showInexplictTimeDesCriptionInfo();
        }
    }

    public void removeCertainTaskAndSave(String input,Storage storage) {
        try{
            int taskIndex = parser.getTaskIndex(input);
            Task cur_task = this.getOneTask(taskIndex-1);
            this.remove(taskIndex);
            storage.saveToFile(this);
            Ui.printTaskRemovedInfo(cur_task);
            Ui.showTotalTaskNum();
        } catch (NumberFormatException e){
            Ui.showUnknownTaskIndexInfo();
        } catch (IndexOutOfBoundsException e){
            Ui.showTaskNotFoundInfo();
        }
    }

    public void setTaskAsDoneAndSave(String input,Storage storage) {
        try{
            int taskIndex = parser.getTaskIndex(input);
            Task cur_task = this.getOneTask(taskIndex-1);
            cur_task.setTaskStatus(Task.DONE);
            storage.saveToFile(this);
            Ui.printTaskDoneInfo(cur_task);
        } catch (NumberFormatException e){
            Ui.showUnknownTaskIndexInfo();
        } catch (IndexOutOfBoundsException e){
            Ui.showTaskNotFoundInfo();
        }
    }
}
