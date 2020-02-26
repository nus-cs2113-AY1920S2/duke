package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * tasklist class:contains the task list and it can perform operations on task list such as addtask, deletetask etc.)
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * add a todo task into the task list
     * @param taskName task name of the todo task
     * @return a description of newly added task
     */
    public String addTodo(String taskName) {
        Todo todo = new Todo(taskName, false);
        taskList.add(todo);
        return todo.print();
    }

    /**
     * add a deadline task into the task list
     * @param taskName task name of the deadline task
     * @param deadLine deadline string of the task
     * @return a description of this newly added task
     */
    public String addDeadline(String taskName, String deadLine) {
        Deadline deadline = new Deadline(taskName, false, deadLine);
        taskList.add(deadline);
        return deadline.print();
    }

    /**
     * add a event task into the task list
     * @param taskName task name of the event task
     * @param timeSlot timeslot string of this task
     * @return a description of this newly added tasks
     */
    public String addEvent(String taskName, String timeSlot) {
        Event event = new Event(taskName, false, timeSlot);
        taskList.add(event);
        return event.print();
    }

    /**
     * list all tasks in the task list
     * @param ui Ui object that interacts with users
     */
    public void listTask(Ui ui) {
        int i = 1;
        ui.printFormatLine();
        ui.printString(Ui.LISTING);
        for(Task task: taskList) {
            ui.printString(i++ + "." + task.print());
        }
        ui.printString("Now you have " + taskList.size() + (taskList.size() > 1 ? " tasks" : " task") + " in the list");
        ui.printFormatLine();
        ui.printString("");
    }

    /**
     * mark a task in the task list as done
     * @param taskNo # of task needed to be marked
     * @return a description of this newly marked task
     */
    public String markTask(int taskNo) {
        Task task = taskList.get(taskNo-1);
        task.changeStatus(true);
        return task.print();
    }

    /**
     * delete a task in the task list
     * @param taskNo # of task needed to be deleted
     * @return a description of this deleted task
     */
    public String deleteTask(int taskNo) {
        Task task = taskList.get(taskNo-1);
        String description = task.print();
        taskList.remove(task);
        return description;
    }

    /**
     * search tasks in the task list in terms of the keyword
     * @param keyword the key word entered by user
     * @return array of strings of all matching tasks
     */
    public String[] findMatchingTasks(String keyword) {
        ArrayList<String> matchingTasks = new ArrayList<>();
        matchingTasks.add(Ui.FINDING);
        int i = 1;
        for (Task task : taskList) {
            String taskDescription = task.print();
            if (taskDescription.toLowerCase().contains(keyword.toLowerCase())) matchingTasks.add(i++ + "." + taskDescription);
        }
        return matchingTasks.toArray(new String[matchingTasks.size()]);
    }
}
