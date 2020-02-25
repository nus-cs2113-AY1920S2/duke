package duke.enumerations;

/**
 * Constant value for Days in a Week
 */
public enum Day {
    
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);
    
    private final int dayNumber;
    
    /**
     * Initialization of the number in brackets
     *
     * @param dayNumber the number attached to the constant
     */
    Day(int dayNumber) {
        this.dayNumber = dayNumber;
    }
    
    /**
     * Returns the constant value attached to the day
     *
     * @return value related to constant
     */
    public int getDayNumber() {
        return dayNumber;
    }
    
}
