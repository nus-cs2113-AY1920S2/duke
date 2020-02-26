package ui;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

import commands.Command;
import common.exceptions.DukeException;
import data.TaskList;

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
	
	public void greet(TaskList tasks) throws DukeException, IOException {
		outputMessage(MESSAGE_GREETING);
		Command startingCommand = new Command("show_upcoming", Optional.of("0"));
		tasks.executeCommand(this, null, startingCommand);
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
