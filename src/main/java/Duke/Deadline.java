package Duke;


public class Deadline extends Task {

    public static final String TYPE_DEADLINE = "D";
    private String date;

    public Deadline (String description, String date) {
        super(description);
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String getTaskType() {
        return TYPE_DEADLINE;
    }

    @Override
    public void printAddDetails(int taskCounter) {
        System.out.println("The following task has been added:\n[" + getTaskType() +"][" +
                            super.getStatusIcon() + "] " + super.description + " (by: " + date + ")\n");
        System.out.println("You've got " + taskCounter + " task(s) in the list!\n");
    }

    @Override
    public void printListDetails(int count) {
        System.out.println("["+ getTaskType() + "][" + super.getStatusIcon() + "] " +
                            count + ". " + super.description + " (by: " + date + ")");
    }

}
