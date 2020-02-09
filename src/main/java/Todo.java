public class Todo extends Task{
    public Todo(String action) {
        super(action);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}