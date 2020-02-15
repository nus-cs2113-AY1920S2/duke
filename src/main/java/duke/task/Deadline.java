package duke.task;

public class Deadline extends Task {
    String deadline;

    public Deadline(String[] commands) {
        super(commands[0].trim());
        deadline = commands[1].split(" ",2)[1];
    }

    public String print() {
        String str = "[D]";
        if(isDone) {
            str += "[✓]";
        } else {
            str += "[✗]";
        }
        return str = str + " " + name + " (by: " + deadline + ")";

    }
}
