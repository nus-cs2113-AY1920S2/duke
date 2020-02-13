public class Event extends Task{
    public Event(String command) {
        super("[E][✗] "
                + command.replaceFirst("event\\s","").replaceFirst("/at","(at:").trim()
                + ")");
    }
}
