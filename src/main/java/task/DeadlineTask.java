package task;

public class DeadlineTask extends Task {

    /**
     * Constructor for DeadlineTask class.
     * @param taskType type of task, T for todo, E for event, D for deadline
     * @param isDone the status of completion of a task
     * @param taskDetails the details of the task itself
     * @param taskDate the date of the task
     * @param taskTime the time of the task
     */
    public DeadlineTask(TaskType taskType, boolean isDone, String taskDetails, String taskDate, String taskTime) {
        super(taskType, isDone, taskDetails);
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }

    @Override
    public void printCreateMessage() {
        System.out.println("The following task has been created:");
        System.out.println("[" + this.taskType  + "]" + "[" + convertIsDoneDisplay(this.isDone)  + "] "+ this.taskDetails
                + " (by: " + this.taskDate + " " + this.taskTime + ")");
    }

    @Override
    public void printListMessage() {
        System.out.println("[" + this.taskType  + "]" + "[" + convertIsDoneDisplay(this.isDone)  + "] "+ this.taskDetails
                + " (by: " + this.taskDate + " " + this.taskTime + ")");
    }
}