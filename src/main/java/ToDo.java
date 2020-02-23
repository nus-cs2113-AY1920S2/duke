public class ToDo extends Task {
    protected String icon = "[T]";

    /**
     * Constructor used when loading from the save file
     *
     * @param isDone integer representing a boolean, where 1 is done and 0 is not done, from save file.
     * @param description string read from the save file
     */
    public ToDo(int isDone, String description ) {
        super(description);

        if (isDone == 1) {
            super.markAsDone();
        }
    }

    /**
     * Constructor used for everything else
     *
     * @param description the description of the task supplied by User
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overrides the default toString so that Task gets printed in a specific format
     *
     * @return the formatted String to print
     */
    public String toString() {
        String toPrint = super.toString();
        toPrint = String.format("%s%s", this.icon, toPrint);
        return toPrint;
    }
}
