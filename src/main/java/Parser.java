import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contain methods that parses user input into java objects and vice versa.
 */
public class Parser {

    public static ArrayList<String> convertStringToArr(String userInput , String delimiter) {
        return new ArrayList<>(Arrays.asList(userInput.split(delimiter)));
    }
    public static ArrayList<String> convertStringToArr(String userInput, String delimiter, int arrSize) {
        return new ArrayList<>(Arrays.asList(userInput.split(delimiter, arrSize)));
    }

    public static ArrayList<String> descriptionFilter(int filterIndex, ArrayList<String> userInputInArray) {
        ArrayList<String> toReturn = new ArrayList<>();
        String description = "";
        String furtherDescription = "";
        for (int i=0; i<filterIndex; i++) {
            description += userInputInArray.get(i);
            description += " ";
        }
        toReturn.add(description.substring(0, description.length()-1));
        for (int i=filterIndex+1; i < userInputInArray.size(); i++) {
            furtherDescription += userInputInArray.get(i);
            furtherDescription += " ";
        }
        toReturn.add(furtherDescription.substring(0, furtherDescription.length()-1));
        return toReturn;
    }
}
