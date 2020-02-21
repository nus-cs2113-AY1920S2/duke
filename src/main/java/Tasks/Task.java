package Tasks;
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

    public Task(String action){
        this.action=action;
        this.isDone=false;
    }
    /**
     * This method returns the status of the task.
     */
    public String getStatus(){
        String temp=this.isDone ? "\u2713" : "\u2718";
        return "[" + temp + "]";
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
     */
    public String toFile(){
        String done = this.isDone ? "Y" : "N" ;
        return done + "-" + action;
    }
    public String getAction(){
        return this.action;
    }


}