public class ToDo extends Task {
    protected String icon;

    public ToDo(String description) {
        super(description);
        icon = "[T]";
    }
    public String toString() {
        String toPrint = super.toString();
        toPrint = String.format("%s %s", this.icon, toPrint);
        return toPrint;
    }
}
