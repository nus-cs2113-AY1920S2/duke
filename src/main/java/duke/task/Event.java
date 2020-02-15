package duke.task;

public class Event extends Task {
    String timeSlot;

    public Event(String[] commands) {
        super(commands[0].trim());
        timeSlot = commands[1].split(" ",2)[1];
    }

    public String print() {
        String str = "[E]";
        if(isDone) {
            str += "[✓]";
        } else {
            str += "[✗]";
        }
        return str = str + " " + name + " (at: " + timeSlot + ")";
    }
}
