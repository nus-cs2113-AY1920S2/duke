package duke.storage;

import duke.exceptions.WhitespaceExceptions;

public class FormatDescriptions {

    private String taskSymbol;
    private String description;
    private String timeDate;

    /**
     * Getter for Task Symbol
     *
     * @return
     */
    public String getTaskSymbol() {
        return taskSymbol;
    }

    /**
     * Getter for Descriptions
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for time and date
     *
     * @return
     */
    public String getTimeDate() {
        return timeDate;
    }

    /**
     * Method to format the tasks descriptions before storing into tasklist.txt
     *
     * @param newLine String containing the task full descriptions
     * @throws WhitespaceExceptions
     */
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
