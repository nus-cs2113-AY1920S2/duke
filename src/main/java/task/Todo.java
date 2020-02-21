package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        isDone = false;
    }


    @Override
    public String toString() {
        String status = null;
        if (isDone){
            status = "[Y]";
        } else {
            status = "[N]";
        }
        return super.toString() + status + " " + description + "\t";
    }
}