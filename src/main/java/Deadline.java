public class Deadline extends Task {
    protected String icon;
    protected String deadLine;

    public Deadline(String description, String deadLine){
        super(description);
        this.deadLine = deadLine;
        icon = "[E]";
    }

    public String toString() {
        String toPrint = super.toString();
        toPrint = String.format("%s%s (by: %s)", this.icon, toPrint, this.deadLine);
        return toPrint;
    }
}
