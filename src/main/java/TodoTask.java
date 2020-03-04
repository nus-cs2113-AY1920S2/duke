public class TodoTask extends Task {

    public TodoTask(TaskType taskType, boolean isDone, String taskDetails) {
        super(taskType, isDone, taskDetails);
    }

    @Override
    public void printCreateMessage() {
        System.out.println("The following task has been created:");
        System.out.println("[" + this.taskType  + "]" + "[" + convertToCheckMark(this.isDone) + "] "+ this.taskDetails);
    }

    @Override
    public void printListMessage() {
        System.out.println("[" + this.taskType  + "]" + "[" + convertToCheckMark(this.isDone) + "] "+ this.taskDetails);
    }
}