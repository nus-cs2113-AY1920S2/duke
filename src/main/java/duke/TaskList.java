package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public void listTasks(Ui ui) {
        if (tasks.size() == 0) {
            ui.printFormattedString(Ui.NO_TASKS);
        } else {
            ui.printTasks(tasks);
        }
    }

    public void checkOffTask(Ui ui, int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
        ui.printDoneTask(tasks, taskIndex);
    }

    public void deleteTask(Ui ui, int taskIndex) {
        ui.printDeletedTask(tasks, taskIndex);
    }

    public void addTodo(String description, Ui ui) {
        tasks.add(new ToDo(description));
        ui.printAddedTask(tasks);
    }

    public void addDeadline(String info, Ui ui) {
        int dividerIndex = info.indexOf("/by");
        String description = info.substring(0, (dividerIndex - 1));
        String by = info.substring(dividerIndex + 4);
        tasks.add(new Deadline(description, by));
        ui.printAddedTask(tasks);
    }

    public void addEvent(String info, Ui ui) {
        int dividerIndex = info.indexOf("/at");
        String description = info.substring(0, (dividerIndex - 1));
        String at = info.substring(dividerIndex + 4);
        tasks.add(new Event(description, at));
        ui.printAddedTask(tasks);
    }

    public void findTasks(Ui ui, String keyword) {
        ArrayList<String> foundTasks = new ArrayList<>();
        int i = 1;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(i + "." + task.toString());
            }
            i++;
        }
        if (foundTasks.size() == 0) {
            ui.printFormattedString(Ui.NO_TASKS);
        } else {
            ui.printFoundTasks(foundTasks);
        }
    }
}
