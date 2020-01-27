public class Task {
    private String name;
    private int id;
    private boolean isDone;
    private static int numberOfTasks = 0;

    public Task(){
        this("");
    }

    public Task(String name){
        setName(name);
        id = numberOfTasks;
        this.isDone = false;
    }

    public void setName(String name){
        this.name = name;
        numberOfTasks++;
    }

    public String getName(){
        return name;
    }

    public int getID(){
        return id;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatusIcon(){
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public static int getNumberOfTasks(){
        return numberOfTasks;
    }

}
