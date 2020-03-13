package duke.util;

public enum Split {
    /**
     * Contains constant String FORMAT, SPLIT, BLANK_LINE, SPLIT_UPPER_BOUNDARY and SPLIT_LOWER_BOUNDARY
     * such that line spacing and format is consistent.
     */

    FORMAT ("0O=-             %-100s-=O0%n"),
    SPLIT ("================================================================" +
            "========================================================="),
    BLANK_LINE ("0O=-                                                  " +
            "                                                               -=O0"),
    SPLIT_UPPER_BOUNDARY (SPLIT.getSplit() + "\n" + BLANK_LINE.getSplit()),
    SPLIT_LOWER_BOUNDARY (BLANK_LINE.getSplit() + "\n" + SPLIT.getSplit())
    ;

    public final String split;
    Split(String split) {
        this.split = split;
    }
    public String getSplit() {
        return split;
    }
}