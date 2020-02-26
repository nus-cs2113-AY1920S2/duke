package Duke.data.objects;

public class ToDo extends Task {
    public static final String COMMAND_WORD = "todo";
    public ToDo(String description) {
        super(description);
    }
    public String getType(){
        return "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}