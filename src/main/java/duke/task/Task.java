package duke.task;

public class Task {
    protected String name;
    protected String taskType;
    protected boolean isDone;

    public Task(){
        this("");
    }

    public Task(String name){
        setName(name);
        this.isDone = false;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatusIcon(){
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public String getTaskType(){
        return null;
    }

    @Override
    public String toString(){
        return getStatusIcon() + " " + name;
    }

}
