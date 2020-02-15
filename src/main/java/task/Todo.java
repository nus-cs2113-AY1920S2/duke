package task;

public class Todo extends Task {

    public Todo(String description, int index) {
        super(description,index);
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
        return "\n\t" + super.toString() + status + " " + description + "\t";
    }
}