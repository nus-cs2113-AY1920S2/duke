public class Duke {
	
	public Duke() {
	}
	
	public String printLine(String line) {
		String output = ""; 
		output += ("_______________________________________________\n");
		output += ("\n" + line);
		output += ("\n_______________________________________________");
		return output;
	}
	
	@Override
	public String toString() {
		String logo = " ____        _        \n"
	            	+ "|  _ \\ _   _| | _____ \n"
	            	+ "| | | | | | | |/ / _ \\\n"
	            	+ "| |_| | |_| |   <  __/\n"
	            	+ "|____/ \\__,_|_|\\_\\___|\n";
		
		logo += ("\nHello! I'm Joseph!");
		logo += ("\nWhat can I do for you today?");
		return logo;
	}
}
