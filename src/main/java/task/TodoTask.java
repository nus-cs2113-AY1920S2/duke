package task;

import task.Task;

public class TodoTask extends Task {

    /**
     * Constructor for TodoTask class.
     * @param taskType type of task, T for todo, E for event, D for deadline
     * @param isDone the status of completion of a task
     * @param taskDetails the details of the task itself
     */
    public TodoTask(TaskType taskType, boolean isDone, String taskDetails) {
        super(taskType, isDone, taskDetails);
    }

    @Override
    public void printCreateMessage() {
        System.out.println("The following task has been created:");
        System.out.println("[" + this.taskType  + "]" + "[" + convertIsDoneDisplay(this.isDone) + "] "+ this.taskDetails);
    }

    @Override
    public void printListMessage() {
        System.out.println("[" + this.taskType  + "]" + "[" + convertIsDoneDisplay(this.isDone) + "] "+ this.taskDetails);
    }
}