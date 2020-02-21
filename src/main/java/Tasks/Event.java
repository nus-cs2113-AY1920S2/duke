package Tasks;

public class Event extends Task {
    protected String at=null;

    public Event(String action){
        super(action);
    }
    public void setAt(String at){
        this.at=at;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString()  + " (at:" + this.at + ")";
    }
    @Override
    public String toFile(){
        return "E-" + this.at + "-" + super.toFile();
    }
}
