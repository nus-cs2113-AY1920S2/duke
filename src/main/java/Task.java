public abstract class Task {

    protected TaskType taskType;
    protected boolean isDone;

    protected String taskDetails;
    protected String taskDate;
    protected String taskTime;

    public Task(TaskType taskType, boolean isDone, String taskDetails) {
        this.taskType = taskType;
        this.isDone = isDone;
        this.taskDetails = taskDetails;
    }

    public void markTaskAsDone(int numberOfIncompleteTasks) {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            this.printListMessage();
            System.out.println("Total number of incomplete tasks: " + numberOfIncompleteTasks);
    }

    public String convertToCheckMark(boolean isDone) {
        String isDoneDisplay;
        if (isDone) {
            int checkMark = 0x2713;
            isDoneDisplay = Character.toString((char)checkMark);
        } else {
            int checkMark = 0x10035;
            isDoneDisplay = Character.toString((char)checkMark);
        }
        return isDoneDisplay;
    }

    public abstract void printCreateMessage();
    public abstract void printListMessage();
}