package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/* tasklist class:contains the task list and it can perform operations on task list such as addtask, deletetask etc. */
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

    /* add a todo task into the task list */
    public String addTodo(String taskName) {
        Todo todo = new Todo(taskName, false);
        taskList.add(todo);
        return todo.print();
    }

    /* add a deadline task into the task list */
    public String addDeadline(String taskName, String deadLine) {
        Deadline deadline = new Deadline(taskName, false, deadLine);
        taskList.add(deadline);
        return deadline.print();
    }

    /* add a event task into the task list */
    public String addEvent(String taskName, String timeSlot) {
        Event event = new Event(taskName, false, timeSlot);
        taskList.add(event);
        return event.print();
    }

    /* list all tasks in the task list */
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

    /* mark a task in the task list as done */
    public String markTask(int taskNo) {
        Task task = taskList.get(taskNo-1);
        task.changeStatus(true);
        return task.print();
    }

    /* delete a task in the task list */
    public String deleteTask(int taskNo) {
        Task task = taskList.get(taskNo-1);
        String description = task.print();
        taskList.remove(task);
        return description;
    }
}
