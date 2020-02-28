package duke.task;

public class Deadline extends Task {
    private static int deadlineNum = 0;
    private String by;

    public Deadline(String taskName,String by){
        super(taskName);
        this.by = by;
        this.taskType = "[D]";
        deadlineNum++;
    }

    public Deadline(String taskName,String by,String taskStatus){
        this(taskName,by);
        this.setTaskStatus(taskStatus);
    }

    public static int getDeadlineNum(){
        return deadlineNum;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy(){
        return this.by;
    }

    @Override
    public String showTaskInfo(){
        return super.showTaskInfo()+" (by: "+this.getBy()+")";
    }
}
