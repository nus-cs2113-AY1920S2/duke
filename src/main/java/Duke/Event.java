package Duke;

/**
 * Specifies the additional requirements for event type tasks
 */
public class Event extends Task {

    public static final String TYPE_EVENT = "E";


    public Event(String description, String date, String time){
        super(description, date, time);
    }

    /**
     * Retrieve the code for current type of task
     *
     * @return deadline task type code
     */
    @Override
    public String getTaskType(){
        return TYPE_EVENT;
    }

    /**
     * Prints details of task upon adding
     * Default is set for tasks without date
     *
     * @param taskCounter current index of task
     */
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
