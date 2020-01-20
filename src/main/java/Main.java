import java.util.Scanner;

public class Main {
	public static int taskCounter = 1;

	public static void readInput(Scanner sc, Duke duke) {
		while(sc.hasNextLine()) {
			String input = sc.nextLine();
			String[] argsArray = input.split(" ");
			String command = argsArray[0];
			
			switch(command) {
			case "done": 
				int taskId = Integer.parseInt(argsArray[1]);
				duke = duke.completeTask(taskId);
				break;
			case "list":
				duke.printList();
				break;
			case "bye": 
				duke.printEcho("Bye. Hope to see you again soon!");
				break;
			default: 
				duke = duke.addTask(taskCounter, input);
				taskCounter++;
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
