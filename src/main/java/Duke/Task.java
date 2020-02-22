package Duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    public String markAsDone () {
        this.isDone = true;
        return ("[" + this.getStatusIcon() + "] "+ this.description);
    }

    public String toString() {
        return ("["+ this.getStatusIcon() + "] " + this.description);
    }

    public String toSaveFormat() {
        if (this.isDone == true) {
            return ("1|" + this.description+ "|");
        } else {
            return ("0|" + this.description + "|");
        }
    }

}