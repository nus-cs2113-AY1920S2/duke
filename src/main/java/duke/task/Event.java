package duke.task;

/* a event class that can be instantiated to event object */
public class Event extends Task {
    String timeSlot;

    public Event(String command, boolean status, String timeSlot) {
        super(command, status);
        this.timeSlot = timeSlot;
    }

    /* generate description of a event task that need to be stored in the file */
    public String textToFile() {
        String text = "E | 0 | ";
        if(isDone) text = "E | 1 | ";
        text += name + " | " + timeSlot + System.lineSeparator();
        return  text;
    }

    public String print() {
        String str = "[E]";
        if(isDone) {
            str += "[\u2713]";
        } else {
            str += "[\u274c]";
        }
        return str = str + " " + name + " (at: " + timeSlot + ")";
    }
}
