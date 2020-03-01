package Duke;


public class Event extends Task {

    public static final String TYPE_EVENT = "E";

    public Event(String description, String date, String time){
        super(description, date, time);
    }

    @Override
    public String getTaskType(){
        return TYPE_EVENT;
    }

    @Override
    public void printAddDetails(int taskCounter) {
        System.out.println("The following task has been added:\n[" + getTaskType() +"][" +
                            getStatusIcon() + "] " + super.description + " (by: " + super.date + " at " + time +")");

        System.out.println("You've got " + taskCounter + " task(s) in the list!\n");
    }

    @Override
    public void printListDetails(int count) {
        System.out.println("["+ getTaskType() + "][" + super.getStatusIcon() + "] " +
                            count + ". " + super.description + " (by: " + super.date + " at " + time +")");
    }

}
