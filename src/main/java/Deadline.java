public class Deadline extends Task{

    protected String by=null;

    public Deadline(String description){
        super(description);
    }
    public void setBy(String inBy){
        this.by=inBy;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString()  + " (by:" + this.by + ")";
    }
}