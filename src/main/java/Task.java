public class Task {
    protected String action;
    protected String type;
    protected String timing;
    protected boolean isDone;

    public Task(String action, String type, String timing){
        this.action=action;
        this.type=type;
        this.timing=timing;
        this.isDone=false;
    }
    public String getStatus(){
        String temp=this.isDone ? "\u2713" : "\u2718";
        return temp;
    }
    public char getType(){
        char temp=this.type.charAt(0);
        temp=(char)((int)(temp)-32);
        return temp;
    }
    public String getTaskDescription(){
        return ("[" + this.getType()+ "][" + this.getStatus()+ "] "+ this.action+" " + this.timing);
    }

    public void done(){
        this.isDone=true;
    }
}
