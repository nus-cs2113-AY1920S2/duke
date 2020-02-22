package duke.task;

public class Event extends Task {
    private static int eventNum = 0;
    private String date;

    public Event(String taskName){
        this(taskName,"Not set yet");
    }

    public Event(String taskName,String date){
        super(taskName);
        this.date = date;
        this.taskType = "[E]";
        eventNum++;
    }

    public static int getEventNum(){
        return eventNum;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    @Override
    public String showTaskInfo(){
        return super.showTaskInfo()+" (at: "+this.getDate()+")";
    }
}
