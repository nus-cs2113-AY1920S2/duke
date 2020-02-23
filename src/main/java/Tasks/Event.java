package Tasks;
/**
 * This is a sub class of the Task class in Duke.
 *
 * This class has an extra value 'at' which stores
 * the timing the event will be occur.
 */
public class Event extends Task {
    protected String at=null;

    public Event(String action){
        super(action);
    }
/**
 * This method sets the value of the parameter "at".
 * @param at This is the only argument.
 */
    public void setAt(String at){
        this.at=at;
    }
/**
 * This method prints the class in the following format:
 * [E][X] description (at: timing)
 */
    @Override
    public String toString(){
        return "[E]" + super.toString()  + " (at:" + this.at + ")";
    }
    @Override
    public String toFile(){
        return "E-" + this.at + "-" + super.toFile();
    }
}
