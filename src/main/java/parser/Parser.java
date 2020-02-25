package parser;

public class Parser {
	
	public String parse(String input) {
		String cmd = input.split(" ")[0];
        return cmd; 
	}
	
}
