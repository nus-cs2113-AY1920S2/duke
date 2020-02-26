package duke.tasks;

import duke.exceptions.BadLineFormatException;

public abstract class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        setDescription(description);
        setIsDone(false);
    }

    // getStatusIcon() is from the website
    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String toFormattedString();

    public boolean containsWord(String word) {
        return description.contains(word);
    }

    public static Task getTaskFromFormattedLine(String line) throws BadLineFormatException {
        String[] tokens = line.split(",");
        if (tokens.length == 0) {
            throw new BadLineFormatException("Empty line");
        } else if (tokens.length < 3) {
            throw new BadLineFormatException("Not enough tokens");
        } else if ((tokens[0].equals("D") || tokens[0].equals("E")) && tokens.length < 4) {
            throw new BadLineFormatException("Not enough tokens");
        } else if (!(tokens[1].equals("y") || tokens[1].equals("n"))) {
            throw new BadLineFormatException("Second token must be y or n");
        }

        boolean isDone = tokens[1].equals("y");
        switch(tokens[0]) {
        case "T":
            return new ToDo(tokens[2], isDone);
        case "D":
            return new Deadline(tokens[2], tokens[3], isDone);
        case "E":
            return new Event(tokens[2], tokens[3], isDone);
        default:
            throw new BadLineFormatException("First token must be T, D, or E");
        }
    }
}
