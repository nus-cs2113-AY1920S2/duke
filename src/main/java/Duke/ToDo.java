package Duke;


/**
 * Specifies the additional requirements for todo type tasks
 */
public class ToDo extends Task {

    public static final String TYPE_TODO = "T";

    public ToDo(String description){
        super(description);
    }

    /**
     * Retrieve the code for current type of task
     *
     * @return deadline task type code
     */
    @Override
    public String getTaskType(){
        return TYPE_TODO;
    }

    /**
     * Prints details of task upon adding
     * Default is set for tasks without date
     *
     * @param taskCounter current index of task
     */
    @Override
    public void printListDetails(int taskCounter) {
        System.out.println("["+ getTaskType() + "][" + getStatusIcon() + "] " + taskCounter + ". " + getDescription());
    }


}
