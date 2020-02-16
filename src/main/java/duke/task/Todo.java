package duke.task;

public class Todo extends Task {

    public Todo(String command) {
        super(command);
    }
    public Todo(String command, boolean status) {super(command, status);};

    public String textToFile() {
        String text = "T | 0 | " + name + System.lineSeparator();
        if(isDone) text.replace("| 0 |", "| 1 |");
        return text;
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
