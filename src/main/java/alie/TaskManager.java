package alie;

import alie.task.Task;

import java.util.ArrayList;

public class TaskManager {
    protected static final String INDENTATION = "      ";
    protected static final String MORE_INDENTATION = "        ";
    protected ArrayList<Task> taskList = new ArrayList<>();


    /*public TaskManager() {
        this.taskList = new Task[SIZE_LIMIT];
    }*/

    public void addNewTask(Task newTask) {
        taskList.add(newTask);
        int taskId = taskList.size() - 1;
        System.out.println(INDENTATION + taskList.get(taskId).justAddedText(taskId));
    }

    public void printAllTasksAdded() {
        if (taskList.size() == 0) {
            System.out.println("No tasks added yet");
        } else {
            System.out.println (INDENTATION + "Here are the tasks in your list:");
            int counter = 1;
            for (Task task : taskList) {
                System.out.printf("%s %d.%s", INDENTATION, counter,
                        task.getTaskInfo() + System.lineSeparator());
                counter += 1;
            }
        }
    }

    public void markTaskCompleted(int index) {
        taskList.set(index, taskList.get(index).setTaskCompleted(taskList.get(index)));
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + "[✓] " + taskList.get(index).getName());
    }

    public void deleteTask(int index) {
        System.out.println(INDENTATION + "Noted. I've removed this task:" + System.lineSeparator()
                + MORE_INDENTATION + taskList.get(index).getTaskInfo() + System.lineSeparator()
                + INDENTATION + "Now you have " + (taskList.size() - 1) + " " + "tasks in the " +
                "list.");
        taskList.remove(index);
    }
}
