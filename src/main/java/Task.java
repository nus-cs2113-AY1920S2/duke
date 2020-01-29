public class Task {
    protected String action;
    protected boolean isDone;

    public Task(String action){
        this.action=action;
        this.isDone=false;
    }
    public String getStatus(){
        String temp=this.isDone ? "\u2713" : "\u2718";
        return temp;
    }
    public void done(){
        this.isDone=true;
    }
}
