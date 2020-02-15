package duke.task;

public class Todo extends Task {

    public Todo(String command) {
        super(command);
    }

    public String print(){
        String str = "[T]";
        if(isDone) {
            str += "[✓]";
        } else {
            str += "[✗]";
        }
        return str = str + " " + name;
    }

}
