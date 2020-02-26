package Duke;

public class Todo extends Task {

    public Todo(String description){
        super(description);
    }

    public Todo(String description,  Boolean isDone){
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toText(){
        return "T | " + (this.isDone? "1" : "0") + " | " + this.description;
    }

}
