package TaskObjects;

public class Deadline extends Task {

    protected String by;

    public Deadline (String description, String by) {
        super(description, "D");
        this.by = by;
    }

    public void setBy(String by){
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + by + ")";
    }

    @Override
    public String stringToSave() {
        return super.stringToSave() + "|" + by;
    }
}