public class DeadlineTask extends Task{

    public String deadline;

    public DeadlineTask(String input, int taskNumber) {
        super(input, taskNumber);
        int positionOfDeadline = input.indexOf("/");
        this.taskName = input.substring(0, positionOfDeadline);
        this.taskName = this.taskName.strip();
        this.deadline = input.substring(positionOfDeadline + 4);
        this.deadline = this.deadline.strip();
    }

    @Override
    public void printCreateMessage(int pendingTaskNumber) {
        System.out.println("The following task has been created:");
        System.out.println(this.taskNumber+1 + ".[D][Not Done] " + this.taskName + " (by: " + this.deadline + ")");
        System.out.println("Total number of incomplete tasks: " + pendingTaskNumber);
    }

    @Override
    public void printListMessage() {
        if (isDone == false) {
            System.out.println(this.taskNumber+1 + ".[D][Not Done] " + this.taskName + " (at: " + this.deadline + ")");
        } else {
            System.out.println(this.taskNumber+1 + ".[D][Done] " + this.taskName + " (at: " + this.deadline + ")");
        }
    }
}