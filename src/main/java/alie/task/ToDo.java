package alie.task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getTaskInfo() {
        return ("[T]" + super.getTaskInfo());
    }
}
