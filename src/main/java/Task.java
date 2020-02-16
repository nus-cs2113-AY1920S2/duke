public abstract class Task {

    protected String taskName;
    protected boolean isDone;
    protected int taskNumber;

    public Task(String input, int taskCount) {
        this.taskNumber = taskCount;
        this.taskName = input.strip();
        this.isDone = false;
    }

    public void markTaskAsDone(int pendingTaskNumber) { //Method variable taskNumber is independent of the class variable
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[Done] " + this.taskName);
            System.out.println("Total number of incomplete tasks: " + pendingTaskNumber);
    }

    public abstract void printCreateMessage(int pendingTaskNumber);
    public abstract void printListMessage();
}