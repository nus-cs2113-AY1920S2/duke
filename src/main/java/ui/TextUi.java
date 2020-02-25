package ui;

import java.io.PrintStream;
import java.util.Scanner;

import static common.Messages.MESSAGE_GREETING;
import static common.Messages.MESSAGE_GOODBYE;
import static common.Messages.line;

public class TextUi {
	private final Scanner in;
    private final PrintStream out;

	public TextUi() {
		this.in = new Scanner(System.in);
		this.out = System.out;
	}
	
	public void greet() {
		outputMessage(MESSAGE_GREETING);
	}
	
	public void goodbye() {
		outputMessage(MESSAGE_GOODBYE);
	}
	
	public void outputMessage(String... message) {
		out.println(line);
        for (String m : message) {
            out.println(m);
        }
        out.println(line);
    }
	
	public String getUserCommand() {
		return in.nextLine();
	}
}
