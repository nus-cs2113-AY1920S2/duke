package data.task;

import common.Messages;

public class Task {

    //protected int index;
    protected int taskIndex;
    protected String taskDescription;
    protected boolean isDone;
    protected char taskType;

    public Task() {
    }


    public Task(int taskIndex, String taskDescription) {
        this.taskIndex = taskIndex;
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public Task(int taskIndex,String taskDescription, char taskType) {
        this.taskDescription = taskDescription;
        this.taskType = taskType;
        this.isDone = false;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public char getChar(){
        char isDone;
        if (this.isDone){
            return Messages.DONE;
        } else {
            return Messages.notDONE;
        }
    }

    public char getTaskType() {
        return taskType;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public void setTaskIndex(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Returns true if both persons have the same identity fields (name and telephone).
     */
    boolean isSamePerson(Task other) {
        return (other == this)
                || (other != null
                && other.getTaskDescription().equals(this.getTaskDescription()));
    }
}
