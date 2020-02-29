package Duke.Parser;


public class Parser {

    public static String getCommandWord(String userInput) {
        return userInput.strip().split(" ")[0];
    }

}