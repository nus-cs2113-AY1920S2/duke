package data.task;

import parser.ParseTime;
import java.time.LocalDate;

public class DeadlineTask extends Task{

    private String taskDeadline;
    private LocalDate taskDeadlineDate;

    public DeadlineTask() {
    }

    public DeadlineTask(String taskDescription, String taskDeadline) {
        super(taskDescription, 'D');
        this.taskDeadline = taskDeadline;
        taskDeadlineDate = ParseTime.parseStringToLocalTime(this.taskDeadline);
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public LocalDate getTaskDeadlineDate() {
        return taskDeadlineDate;
    }
}
