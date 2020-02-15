package Duke;

import java.util.ArrayList;

public class Event extends Task {

    public static final String TYPE_EVENT = "E";
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Event(String description, String date){
        super(description);
        this.date = date;
    }

    @Override
    public String getTaskType(){
        return TYPE_EVENT;
    }

    @Override
    public void printAddDetails(int taskCounter) {
        System.out.println("The following task has been added:\n[" + getTaskType() +"][" +
                            getStatusIcon() + "] " + description + " (at: " + date + ")\n");

        System.out.println("You've got " + taskCounter + " task(s) in the list!\n");
    }

    @Override
    public void printListDetails(int count) {
        System.out.println("["+ getTaskType() + "][" + super.getStatusIcon() + "] " +
                            count + ". " + description + " (by: " + date + ")");
    }

}
