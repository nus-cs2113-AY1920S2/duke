public class Parser {

    public String getCommand(String input) {
        return input.split(" ")[0];
    }

    public String getDescription(String input) {
        return input.split(" ")[1];
    }

    public boolean hasDescription(String input){
        return input.split(" ").length > 1;
    }

}
