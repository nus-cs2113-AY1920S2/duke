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

    public void setIsDone(){
        this.isDone = true;
    }

    public boolean getIsDone(){
        return isDone;
    }

    public static int getNumberOfTasks(){
        return numberOfTasks;
    }

}
