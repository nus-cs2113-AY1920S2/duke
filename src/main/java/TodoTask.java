public class TodoTask extends Task {

    public TodoTask(String input, int taskNumber) {
        super(input, taskNumber);
    }

    @Override
    public void printCreateMessage(int pendingTaskNumber) {
        System.out.println("The following task has been created:");
        System.out.println(this.taskNumber+1 + ".[T][Not Done] " + this.taskName);
        System.out.println("Total number of incomplete tasks: " + pendingTaskNumber);
    }

    @Override
    public void printListMessage() {
        if (isDone == false) {
            System.out.println(this.taskNumber+1 + ".[T][Not Done] " + this.taskName);
        } else {
            System.out.println(this.taskNumber+1 + ".[T][Done] " + this.taskName);
        }
    }
}