package src.main.java;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "[T][" + getStatusIcon() + "] " + description;
    }

}
