package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

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

    public String addTodo(String taskName) {
        Todo todo = new Todo(taskName, false);
        taskList.add(todo);
        return todo.print();
    }

    public String addDeadline(String taskName, String deadLine) {
        Deadline deadline = new Deadline(taskName, false, deadLine);
        taskList.add(deadline);
        return deadline.print();
    }

    public String addEvent(String taskName, String timeSlot) {
        Event event = new Event(taskName, false, timeSlot);
        taskList.add(event);
        return event.print();
    }

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

    public String markTask(int taskNo) {
        Task task = taskList.get(taskNo-1);
        task.changeStatus(true);
        return task.print();
    }

    public String deleteTask(int taskNo) {
        Task task = taskList.get(taskNo-1);
        String description = task.print();
        taskList.remove(task);
        return description;
    }

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
