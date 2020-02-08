import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDo;

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

    public void addNewTask(String taskToBeAdded) {
        // Input format 1: <task type> <task name> /XX: <task details>  (XX can be at, by)
        // Input format 2: <task type> <task name>
        int nameDividerId = taskToBeAdded.indexOf(TASK_NAME_DIVIDER);
        int detailsDividerId = taskToBeAdded.indexOf(TASK_DETAILS_DIVIDER);

        //Default is input format 1
        String taskType = taskToBeAdded.substring(0, nameDividerId).trim().toLowerCase();
        String taskName = taskToBeAdded.substring(nameDividerId+1).trim();
        String taskDetails = "";
        if (detailsDividerId != -1) {
            //for input format 2
            taskName = taskToBeAdded.substring(nameDividerId+1, detailsDividerId).trim();
            taskDetails = taskToBeAdded.substring(detailsDividerId + 4).trim();
        }

        Task newTask = null;
        if (taskType.equals("todo")) {
            newTask = new ToDo(taskName);
        } else if (taskType.equals("deadline")) {
            newTask = new Deadlines(taskName, taskDetails);
        } else if (taskType.equals("event")) {
            newTask = new Events(taskName, taskDetails);
        }

        //Ensure that newTask is initialized to one of the task types
        if (newTask != null) {
            taskList[taskCount] = newTask;
            System.out.println(INDENTATION +
                    taskList[taskCount].justAddedText(taskCount));
            taskCount += 1;
        }
    }

    public void printAllTasksAdded() {
        if (taskCount == 0) {
            System.out.println("No tasks added yet");
        } else {
            System.out.println (INDENTATION + "Here are the tasks in your list:");
            for (int counter = 0; counter < taskCount; counter += 1) {
                System.out.printf("%s %d.%s \n",
                        INDENTATION, counter + 1, taskList[counter].getTaskInfo());
            }
        }
    }

    public void markTaskCompleted(int index) {
        if (index < taskCount) {
            taskList[index].setIsCompleted();
            System.out.println(INDENTATION + "Nice! I've marked this task as done:");
            System.out.println(INDENTATION + "[✓] " + taskList[index].getName());
        } else {
            System.out.println(INDENTATION +
                    "Wrong task index provided. Please try again with valid index.");
        }
    }
}
