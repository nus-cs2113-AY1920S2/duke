public class EventTask extends Task{

    public String eventTiming;

    public EventTask(String input, int taskNumber) {
        super(input, taskNumber);
        int positionOfEvent = input.indexOf("/");
        this.taskName = input.substring(0, positionOfEvent);
        this.taskName = this.taskName.strip();
        this.eventTiming = input.substring(positionOfEvent + 4);
        this.eventTiming = eventTiming.strip();
    }

    @Override
    public void printCreateMessage(int pendingTaskNumber) {
        System.out.println("The following task has been created:");
        System.out.println(this.taskNumber+1 + ".[E][Not Done] " + this.taskName + " (at: " + this.eventTiming + ")");
        System.out.println("Total number of incomplete tasks: " + pendingTaskNumber);
    }

    @Override
    public void printListMessage() {
        if (isDone == false) {
            System.out.println(this.taskNumber+1 + ".[E][Not Done] " + this.taskName + " (at: " + this.eventTiming + ")");
        } else {
            System.out.println(this.taskNumber+1 + ".[E][Done] " + this.taskName + " (at: " + this.eventTiming + ")");
        }
    }
}