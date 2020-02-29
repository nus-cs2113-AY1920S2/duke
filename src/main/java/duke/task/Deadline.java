package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Deadline extends Task {
    private static int deadlineNum = 0;
    private String by;
    private LocalDate standardTime;
    private boolean isStandardTime = false;

    public Deadline(String taskName,String by){
        super(taskName);
        this.by = by;
        this.taskType = "[D]";
        deadlineNum++;
        try{
            standardTime = LocalDate.parse(by);
            isStandardTime = true;
        } catch (DateTimeParseException ignored){
        }
    }

    public Deadline(String taskName,String by,String taskStatus){
        this(taskName,by);
        this.setTaskStatus(taskStatus);
    }

    /**
     * @return total number of deadlines in the list.
     */
    public static int getDeadlineNum(){
        return deadlineNum;
    }

    public void setBy(String by) {
        this.by = by;
    }

    /**
     * @return time description of a deadline.
     * If time description is in standard form, format it pattern "MMM dd yyyy" first.
     */
    public String getBy(){
        if(isStandardTime) {
            return this.standardTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy",Locale.ENGLISH));
        } else {
            return this.by;
        }
    }

    /**
     * @return detailed information of a deadline.
     */
    @Override
    public String showTaskInfo(){
        return super.showTaskInfo()+" (by: "+this.getBy()+")";
    }
}
