import java.util.Scanner;

public class Main {
	
	public static void readCommand(Scanner sc, Duke duke) {
		while (sc.hasNext()) {
			String command = sc.next();
			if (command.equals("bye")) {
				System.out.println("Bye. Hope to see you again soon!");
				break;
			}
			System.out.println(duke.printLine(command));
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Duke duke = new Duke();
		
		System.out.println(duke);
		readCommand(sc, duke);
		
		sc.close();
	}
}
