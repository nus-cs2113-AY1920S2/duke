package parser;

/**
 * The Parser class parse the data between the user input, memory file and the ArrayList.
 */
public class Parser {
    protected String input;

    /**
     * The Parser takes in a string of input and parse it to the desired form.
     * @param input the string of data being parsed
     */
    public Parser(String input){
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
