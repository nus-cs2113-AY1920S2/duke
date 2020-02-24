package TaskObject;

public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + type + "]" + "[" +getStatusIcon() + "]" + description;
    }

    public String stringToSave(){
        return type + "|" + (isDone?"1":"0") + "|" + description;
    }
}
