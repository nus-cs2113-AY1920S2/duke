package TaskList;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.itemType = 'T';
    }

    @Override
    public String printObject() {
        return ("[" + itemType + "][" + getStatusIcon() +"] "+ description);
    }

    @Override
    public String createStrForSaving() {
        return itemType + " | " + convertBoolean() + " | " + description;
    }
}
