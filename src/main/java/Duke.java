import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {
	private final List<String> listOfText;
	
	private Duke(List<String> newListOfText) {
		this.listOfText = newListOfText;
	}
	
	public Duke() {
		this.listOfText = new ArrayList<>();
	}
	
	public Duke addText(String text) {
		this.listOfText.add(text);
		
		List<String> newListOfText = this.listOfText.stream()
				.collect(Collectors.toList());
		
		printOutput("added: " + text);
		return new Duke(newListOfText);
	}
	
	public void printList() {
		String listOfText = "";
		int serialNum = 1;
		
		for (int i = 0; i < this.listOfText.size(); i++) {
			if (i != 0) {
				listOfText += ("\n");
			}
			listOfText += (serialNum + ". " + this.listOfText.get(i));
			serialNum++;
		}
		printOutput(listOfText);		
	}
	
	public void printOutput(String line) {
		String output = ""; 
		output += ("_______________________________________________\n");
		output += ("\n" + line);
		output += ("\n_______________________________________________");
		System.out.println(output);
	}
	
	@Override
	public String toString() {
		String logo = " ____        _        \n"
	            	+ "|  _ \\ _   _| | _____ \n"
	            	+ "| | | | | | | |/ / _ \\\n"
	            	+ "| |_| | |_| |   <  __/\n"
	            	+ "|____/ \\__,_|_|\\_\\___|\n";
		
		logo += ("\nHello! I'm Duke!" 
				+ "\nWhat can I do for you today?");
		return logo;
	}
}
