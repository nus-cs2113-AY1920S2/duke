public class Deadline extends Todo {

    protected String by;

    public Deadline(String description, String by, int index) {
        super(description,index);
        this.by = by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + by + ")";
    }
}
