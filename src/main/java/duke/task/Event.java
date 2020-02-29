package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task {
    private static int eventNum = 0;
    private String date;
    private LocalDate standardTime;
    private boolean isStandardTime = false;

    public Event(String taskName,String date){
        super(taskName);
        this.date = date;
        this.taskType = "[E]";
        eventNum++;
        try{
            standardTime = LocalDate.parse(date);
            isStandardTime = true;
        } catch (DateTimeParseException ignored){
        }
    }

    public Event(String taskName,String date,String taskStatus){
        this(taskName,date);
        this.setTaskStatus(taskStatus);
    }

    public static int getEventNum(){
        return eventNum;
    }

    /**
     * Set time description
     * @param date time description of an event
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return time description of an event.
     * If time description is in standard form, format it pattern "MMM dd yyyy" first.
     */
    public String getDate(){
        if(isStandardTime) {
            return this.standardTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy",Locale.ENGLISH));
        } else {
            return this.date;
        }
    }

    /**
     * @return detailed information of an event.
     */
    @Override
    public String showTaskInfo(){
        return super.showTaskInfo()+" (at: "+this.getDate()+")";
    }
}
