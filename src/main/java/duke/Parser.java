package duke;

import duke.exception.InexplicitTimeDescription;

import java.util.Scanner;

public class Parser {
    private static Scanner in = new Scanner(System.in);

    public String getAndProcessInput() {
        String input = in.nextLine().trim();
        return input;
    }

    public boolean isByeCommand(String input){
        return input.toLowerCase().equals("bye");
    }

    public boolean isDoneCommand(String input){
        return input.startsWith("done");
    }

    public boolean isDeleteCommand(String input){
        return input.startsWith("delete");
    }

    public boolean isListCommand(String input){
        return input.startsWith("list");
    }

    public boolean isFindCommand(String input){
        return input.startsWith("find");
    }

    public int getTaskIndex(String input) throws NumberFormatException {
        int dividePosition = input.indexOf(" ");
        try {
            return Integer.parseInt(input.substring(dividePosition + 1));
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    public String getNewTodoName(String input) {
        input = input.replace("todo ", "");
        return input;
    }

    public String getDdlOrEventName(String input) throws InexplicitTimeDescription {
        String newName = input.replace("deadline ", "").replace("event ", "");
        try {
            int cutPosition = newName.indexOf("/");
            newName = newName.substring(0, cutPosition - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InexplicitTimeDescription();
        }
        return newName;
    }

    public String getByOrDate(String input) {
        int byBeginPosition = input.indexOf("/");
        return input.substring(byBeginPosition + 4);
    }

    public String parseTaskType(String input){
        int dividePosition = input.indexOf(" ");
        return input.substring(0,dividePosition);
    }

    public String getTargetWords(String input){
        String targetWords = "";
        try{
            int splitIndex = input.indexOf(" ");
            targetWords = input.substring(splitIndex).trim();
        } catch (StringIndexOutOfBoundsException e){
            Ui.showCannotGetTargetWordsInfo(input);
        }

        return targetWords;
    }
}
