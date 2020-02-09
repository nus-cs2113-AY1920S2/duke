package alie;

import alie.task.Deadlines;
import alie.task.Events;
import alie.task.Task;
import alie.task.ToDo;

public class TaskManager {
    protected static final int SIZE_LIMIT = 100;
    protected static final String INDENTATION = "      ";
    protected static final String TASK_NAME_DIVIDER = " ";
    protected static final String TASK_DETAILS_DIVIDER = "/";

    //Assume no more than 100 tasks given for each Manager
    protected Task[] taskList;
    protected static int taskCount;

    public TaskManager() {
        this.taskList = new Task[SIZE_LIMIT];
        this.taskCount = 0;
    }

    public void addNewTask(Task newTask) {
        taskList[taskCount] = newTask;
        System.out.println(INDENTATION +
                taskList[taskCount].justAddedText(taskCount));
        taskCount += 1;
    }

    public void printAllTasksAdded() {
        if (taskCount == 0) {
            System.out.println("No tasks added yet");
        } else {
            System.out.println (INDENTATION + "Here are the tasks in your list:");
            for (int counter = 0; counter < taskCount; counter += 1) {
                System.out.printf("%s %d.%s", INDENTATION, counter + 1,
                        taskList[counter].getTaskInfo() + System.lineSeparator());
            }
        }
    }

    public void markTaskCompleted(int index) {
        taskList[index].setIsCompleted();
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + "[✓] " + taskList[index].getName());

    }
}
