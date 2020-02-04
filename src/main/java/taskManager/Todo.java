package taskManager;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.itemType = 'T';
    }

    @Override
    public String printObject() {
        return ("[" + itemType + "][" + getStatusIcon() +"] "+ description);
    }
}
