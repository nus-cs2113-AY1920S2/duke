package duke.enumerations;

/**
 * Constant value for Days in a Week
 */
public enum Month {
    Jan("01"), Feb("02"), Mar("03"), Apr("04"), May("05"), Jun("06"), Jul("07"), Aug("08"), Sep("09"), Oct("10"),
    Nov("11"), Dec("12");
    
    private String number;
    
    /**
     * Initialization of the string number in brackets
     *
     * @param number the number attached to the constant
     */
    Month(String number) {
        this.number = number;
    }
    
    /**
     * Returns the constant string value attached to the day
     *
     * @return value related to constant
     */
    public String getNumber() {
        return number;
    }
    
}
