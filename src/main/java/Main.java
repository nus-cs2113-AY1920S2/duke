import java.util.Scanner;

public class Main {
	
	public static void readInput(Scanner sc, Duke duke) {
		while (sc.hasNext()) {
			String input = sc.nextLine();
			
			switch(input) {
			case "list":
				duke.printList();
				break;
			case "bye": 
				duke.printOutput("Bye. Hope to see you again soon!");
				break;
			default: 
				duke.addText(input);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Duke duke = new Duke();
		
		System.out.println(duke);
		readInput(sc, duke);
		
		sc.close();
	}
}
