package Duke;

import java.util.List;

public class Parser {

    public static String[] parseInput(String input){
        return input.split(" ");
    }

    public static String getTodoDescription(List<String> arguments){
        return String.join(" ", arguments);
    }

    public static String getDeadlineDescription(List<String> parsedList) throws DukeException{
        int i = parsedList.indexOf("/by");
        if(i == -1){
            throw new DukeException("Please enter again with the following format: D/NAME /by D/TIME");
        }
        else{
            return String.join(" ", parsedList.subList(1, i));
        }
    }

    public static String getEventDescription(List<String> parsedList) throws DukeException{
        int i = parsedList.indexOf("/at");
        if(i == -1){
            throw new DukeException("Please enter again with the following format: E/NAME /at D/TIME");
        }
        else{
            return String.join(" ", parsedList.subList(1, i));
        }
    }

    public static String getBy(List<String> parsedList) throws DukeException{
        int i = parsedList.indexOf("/by");
        if(i == -1){
            throw new DukeException("Please enter again with the following format: D/NAME /by D/TIME");
        }
        else{
            return String.join(" ", parsedList.subList(i + 1, parsedList.size()));
        }
    }

    public static String getAt(List<String> parsedList) throws DukeException{
        int i = parsedList.indexOf("/at");
        if(i == -1){
            throw new DukeException("Please enter again with the following format: E/NAME /at D/TIME");
        }
        else{
            return String.join(" ", parsedList.subList(i + 1, parsedList.size()));
        }
    }

    public static boolean isNumeric(String strNum){
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
