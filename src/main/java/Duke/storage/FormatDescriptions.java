package duke.storage;

import duke.exceptions.WhitespaceExceptions;

public class FormatDescriptions {

    private String taskSymbol;
    private String description;
    private String timeDate;

    public String getTaskSymbol() {
        return taskSymbol;
    }

    public String getDescription() {
        return description;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void format(String newLine) throws WhitespaceExceptions {
        String[] strArr;
        if (newLine.contains("(by:")) {
            String newline2 = newLine.replace("(by:", ":");
            strArr = newline2.split(":");
            description = strArr[0].trim();
            timeDate = strArr[1].replace(")", "").trim();
            taskSymbol = "D|";
        } else if (newLine.contains("(at:")) {
            String newline2 = newLine.replace("(at:", ":");
            strArr = newline2.split(":");
            description = strArr[0].trim();
            timeDate = strArr[1].replace(")", "").trim();
            taskSymbol = "E|";
        } else {
            description = newLine.trim();
            timeDate = "";
            taskSymbol = "T|";
        }
        if (description.isBlank()) {
            throw new WhitespaceExceptions();
        }

    }
}
