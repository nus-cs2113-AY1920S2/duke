public class Task {
    protected char type;
    protected String description;
    protected boolean isDone;

    public Task(String s) {
        setDescription(s);
        setDone(false);
        setType('-');   // default type
    }

    /**
     * Returns the type of task.
     * @return the type of task
     */
    public char getType() {
        return type;
    }

    /**
     * Sets the type of task, using a single character.
     * @param type a single character that explains the type of the task
     */
    protected void setType(char type) {
        this.type = type;
    }

    /**
     * Returns the description of task.
     * @return the description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of task.
     * @param s the description of task
     */
    protected void setDescription(String s) {
        description = s;
    }

    /**
     * Returns if the task is completed.
     * @return {@code \u2713} or {@code \u2717}
     */
    public char getDone() {
        // return tick if done, cross if not done
        return isDone ? '\u2713' : '\u2717';
    }

    /**
     * Returns if the task is completed.
     * @return {@code true} or {@code false}
     */
    public boolean getDoneInBoolean() {
        return isDone;
    }

    /**
     * Sets the progression of task as {@code true} or {@code false}.
     * @param status the progression of task, {@code true} or {@code false}
     */
    public void setDone(boolean status) {
        isDone = status;
    }

    @Override
    public String toString() {
        String output = "[" + getType() + "]"
                + "[" + getDone() + "] "
                + getDescription();
        return output;
    }
}