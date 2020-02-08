public class ToDo extends TaskManager {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}