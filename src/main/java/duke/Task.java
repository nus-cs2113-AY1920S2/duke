package duke;
/**
 * Task item class of the Duke project
 *  Has three extensions
 */
public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markDone(){
        this.isDone = true;
    }

    public String displayTask(){
        return this.description;
    }

    public String toString(){
        return "[" + getStatusIcon() + "]" + this.description ;
    }

    public String showSearch() { return "[" + getStatusIcon() + "]" + this.description ;}


}