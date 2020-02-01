public class Event extends Task {

    // Stores information about period of task
    private String period;

    // Overloaded Constructor
    public Event(String descriptionWithPeriod){
        super(getDescription(descriptionWithPeriod));
        taskType = 'E';
        this.period = getPeriod(descriptionWithPeriod);
    }

    // Abstracts out the description from the user given input and returns it
    private static String getDescription(String descriptionWithPeriod) {
        descriptionWithPeriod = descriptionWithPeriod.substring(6);
        String[] split = descriptionWithPeriod.split("/",2);
        return split[0];
    }

    // Abstracts out the period from the user given input and returns it
    private static String getPeriod(String descriptionWithPeriod) {
        descriptionWithPeriod = descriptionWithPeriod.substring(6);
        String[] split = descriptionWithPeriod.split("/",2);
        return (split[1].split(" ",2))[1];
    }

    // Returns the period in required format
    public String getPeriod(){
        return "(at: " + period + ")";
    }

    // Returns the task's type and status along with it's description as a string
    @Override
    public String getStatusWithDescription(){
        return "[" + this.taskType + "]" + super.getStatusWithDescription() + getPeriod();
    }
}
