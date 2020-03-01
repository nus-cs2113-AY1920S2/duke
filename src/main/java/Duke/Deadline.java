package Duke;

/**
 * Specifies the additional requirements for deadline type tasks
 */
public class Deadline extends Task {

    public static final String TYPE_DEADLINE = "D";

    public Deadline (String description, String date) {
        super(description, date);
    }

    /**
     * Retrieve the code for current type of task
     *
     * @return deadline task type code
     */
    @Override
    public String getTaskType() {
        return TYPE_DEADLINE;
    }

    @Override
    public void printAddDetails(int taskCounter) {
        System.out.println("The following task has been added:\n[" + getTaskType() +"][" +
                            super.getStatusIcon() + "] " + super.description + " (by: " + super.date + ")\n");
        System.out.println("You've got " + taskCounter + " task(s) in the list!\n");
    }

    @Override
    public void printListDetails(int count) {
        System.out.println("["+ getTaskType() + "][" + super.getStatusIcon() + "] " +
                            count + ". " + super.description + " (by: " + super.date + ")");
    }


}
