package Duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, String status) {
        super(description);
        if (status.equals("1")) {
            this.markAsDone();
        }
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    @Override
    public String toSaveFormat() {
        return(super.toSaveFormat() + "T");
    }
}
