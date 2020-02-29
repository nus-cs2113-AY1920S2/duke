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

    /**
     * @param taskIndex index of the wanted task.
     * @return a Task instance.
     */
    public Task getOneTask(int taskIndex){
        return taskList.get(taskIndex);
    }

    public static int getLenOfList(){
        return taskList.size();
    }

    /**
     * Append a new task to the tail of a TaskList(tasks).
     * @param task
     */
    public void append(Task task){
        taskList.add(task);
    }

    /**
     * Remove a task from the list with its index.
     * @param taskIndex the index of the task to be removed.
     */
    public void remove(int taskIndex){
        taskList.remove(taskIndex-1);
        Task.setTaskNum(Task.getTaskNum()-1);
    }

    /**
     * Print detailed info of all tasks in the TaskList(tasks).
     */
    public void printTaskList(){
        int index = 0;
        for(Task task:taskList){
            index++;
            String prefix = "    "+index+".";
            System.out.println(prefix+task.showTaskInfo());
        }
    }

    /**
     * Print all the tasks in the TaskList(tasks) that contain certain keywords.
     * @param targetWords keywords supplied by user
     */
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

    /**
     * @param input user's command to add a new task.
     * @param type  the type of the tobe-added task.
     * @return description(name) of the new task.
     * @throws UnknownCommandException if user's command cannot be parsed legally.
     * @throws InexplicitTimeDescription if Event or Deadline isn't with correct time description.
     */
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

    /**
     * Parse original input and figure out the type of the task to be added.
     * Add a new task to the TaskList(tasks) and Save changes to the file.
     * @param input user's command to add a new task
     * @param storage instance of Storage class, helps to save changes into file.
     */
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

    /**
     * Parse user's original input and delete the task according to taskIndex.
     * Save the modifications to the file.
     * @param input user's input with the form "delete TASK_INDEX".
     * @param storage instance of Storage class, helps to save changes into file.
     */
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

    /**
     * Parse original input and get the index of the referred task.
     * Set the corresponding task in TaskList(tasks) as "Done".
     * Save changes to the file
     * @param input user's command to set a task as "Done" with its index;
     * @param storage instance of Storage class, helps to save changes into file.
     */
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
