public class Task {

    protected boolean isDone;
    protected String type;
    protected String description;
    protected String by;


    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
        this.by = "";
    }

    public String getDescription() { return description; }

    public String getBy(){ return by; }

    public String getType(){ return type; }

    public void markDone(){ this.isDone = true; }

    public void setBy(String by){ this.by = "  (" + by + ")"; }

    public String getStatusIcon() {
//        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "[Y]" : "[N]"); //return tick or X symbols
    }

}