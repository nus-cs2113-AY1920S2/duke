package duke.tasks;
/**
 * This is the parent class for all tasks in Duke
 *
 * Each Task has the following value:
 * 1) Action : description of the Task
 * 2) isDone : status of the task (Completed or Otherwise)
 *
 * Each Task also has the following behaviours:
 * 1) toString(): prints Task to output stream
 * 2) toFile() : writes the Task details to data.txt
 *
 */
public class Task {
    private String action;
    protected boolean isDone;
    public static final String STORAGE_DELIMITER= "~";
    public Task(String action){
        this.action=action;
        this.isDone=false;
    }
    /**
     * This method returns the value of isDone of the task.
     * @return This returns the value of isDone in the form of
     *         [isDone]. Unicode value of Check Mark and Ballot
     *         is used to indicate true and false value of
     *         isDone.
     */
    public String getStatus(){
        String temp=this.isDone ? "DONE" : "PENDING";
        return "[Status: " + temp + "]";
    }
    public void done(){
        this.isDone=true;
    }
    /**
     * This method prints the Task.
     */
    public String toString(){
        return getStatus()+ " " + this.action;
    }
    /**
     * This method writes the Task details to data.txt.
     * @return This returns the data to be written into
     * data.txt in String format.
     */
    public String toFile(){
        String done = this.isDone ? "Y" : "N" ;
        return String.format("%s" + STORAGE_DELIMITER + "%s", done, this.action);
    }
    public String getAction(){
        return this.action;
    }


}