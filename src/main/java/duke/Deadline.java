package duke;

public class Deadline extends Task implements DatedEvents {
    protected String date;

    public Deadline(String description, String by) {
        super(description);
        this.date = by;
    }

    public String returnDate(){
        return this.date;
    }

    @Override
    public String getTypeIcon() {
        return ("[D]");
    }


}
