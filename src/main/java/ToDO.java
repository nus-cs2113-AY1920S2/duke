public class ToDO extends Task {

    // Constructor
    public ToDO(String description){
        super(getDescription(description));
        taskType = 'T';
    }

    // Returns the description after removing the prefix "todo"
    private static String getDescription(String descriptionWithTodo) {
        return  descriptionWithTodo.substring(5);
    }

    // Returns the task's type and status along with it's description as a string
    @Override
    public String getStatusWithDescription(){
        return "[" + this.taskType + "]" + super.getStatusWithDescription();
    }

}
