package data.task;

import commands.CommandResult;
import common.Month;
import parser.ParseTime;
import java.time.LocalDate;

public class DeadlineTask extends Task{

    private String taskDeadline;
    private LocalDate taskDeadlineDate;
    private String taskInformation;

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

    /*
     *  returns a string taskInformation contains all essential information for the deadline task
     */
    public String getTaskInformation() {
        if (getTaskDeadlineDate()!=null){
            String timeString = String.format("%s %s %s",
                    Month.valueOf(getTaskDeadlineDate().getMonthValue()),
                    getTaskDeadlineDate().getDayOfMonth(),
                    getTaskDeadlineDate().getYear());
            taskInformation = String.format("[%c][%c] %s (%s)",
                    taskType,
                    getChar(),
                    getTaskDescription(),
                    timeString);
        } else {
            taskInformation = String.format(
                    "[%c][%c] %s (%s)",
                    taskType,
                    getChar(),
                    getTaskDescription(),
                    getTaskDeadline());
        }

        return taskInformation;
    }
}
