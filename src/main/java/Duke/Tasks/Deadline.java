package Duke.Tasks;
/**
 * This is a sub class of the Task class in Duke.
 *
 * This class has an extra value 'by' which stores
 * the timing the deadline is due by.
 */
public class Deadline extends Task{

    protected String by=null;

    public Deadline(String description){
        super(description);
    }
    public void setBy(String inBy){
        this.by=inBy;
    }
/**
 * This method prints the class in the following format:
 * [D][X] description (by: timing)
 */
    @Override
    public String toString(){
        return "[D]" + super.toString()  + " (by:" + this.by + ")";
    }
    @Override
    public String toFile(){
        return "D-" + this.by + "-" + super.toFile();
    }
}