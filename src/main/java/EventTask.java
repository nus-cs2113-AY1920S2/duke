public class EventTask extends Task{


    public EventTask(TaskType taskType, boolean isDone, String taskDetails, String taskDate, String taskTime) {
        super(taskType, isDone, taskDetails);
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }

    @Override
    public void printCreateMessage() {
        System.out.println("The following task has been created:");
        System.out.println("[" + this.taskType  + "]" + "[" + convertToCheckMark(this.isDone)  + "] "+ this.taskDetails + " (at: " + this.taskDate + " " + this.taskTime + ")");
    }

    @Override
    public void printListMessage() {
        System.out.println("[" + this.taskType  + "]" + "[" + convertToCheckMark(this.isDone)  + "] "+ this.taskDetails + " (at: " + this.taskDate + " " + this.taskTime + ")");
    }
}