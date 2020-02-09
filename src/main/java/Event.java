public class Event extends Task {
    protected String period;

    public Event(String description,String period) {
        super(description);
        this.period=period;
    }

    @Override
    public String toString(){
        String taskType="[E]";
        String detail = taskType + " [" + getStatusIcon() + "] " + description+" (at: "+period+")";
        return detail;
    }
}
