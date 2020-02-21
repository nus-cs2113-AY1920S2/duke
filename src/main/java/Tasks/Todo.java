package Tasks;

public class Todo extends Task{
    public Todo(String action) {
        super(action);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    @Override
    public String toFile(){
        return "T-" + super.toFile();
    }

}