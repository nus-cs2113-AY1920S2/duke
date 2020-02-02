public class Event extends Task {

    protected String at;

    public Event(String description, String at){
        super(description,"E");
        this.at = at;
    }

    public void setAt(String at){
        this.at = at;
    }

    public String getAt(){
        return at;
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + at + ")";
    }
}
