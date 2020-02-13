public class ToDo extends Task {

    public static final String TYPE_TODO = "T";

    public ToDo(String description){
        super(description);
    }

    @Override
    public String getTaskType(){
        return TYPE_TODO;
    }

    @Override
    public void printListDetails(int taskCounter) {
        System.out.println("["+ getTaskType() + "][" + getStatusIcon() + "] " + taskCounter + ". " + getDescription());
    }

}
