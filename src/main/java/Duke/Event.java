package Duke;


public class Event extends Task {

    public static final String TYPE_EVENT = "E";

    public void setDate(String date) {
        super.date = date;
    }

    public Event(String description, String date){
        super(description, date);
    }

    @Override
    public String getTaskType(){
        return TYPE_EVENT;
    }

    @Override
    public void printAddDetails(int taskCounter) {
        System.out.println("The following task has been added:\n[" + getTaskType() +"][" +
                            getStatusIcon() + "] " + super.description + " (at: " + super.date + ")\n");

        System.out.println("You've got " + taskCounter + " task(s) in the list!\n");
    }

    @Override
    public void printListDetails(int count) {
        System.out.println("["+ getTaskType() + "][" + super.getStatusIcon() + "] " +
                            count + ". " + super.description + " (by: " + super.date + ")");
    }

}
