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
    
    /**
     * Remove the constant Day that user or storage load up for sync purpose
     *
     * @param input the string of the day, time and other information related
     * @return string that does not contain Day constant value
     */
    public static String removeEnum(String input) {
        Day[] days = Day.values();
        for (Day day : days) {
            if (input.contains(day.toString().toLowerCase())) {
                input = input.replace(day.toString().toLowerCase(), "");
                break;
            }
        }
        return input;
    }
    
}
