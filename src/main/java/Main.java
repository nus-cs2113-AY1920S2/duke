import java.util.Scanner;

public class Main {

    /*
    public static void readInput(Scanner sc, Duke duke) {
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] argsArray = input.split(" ");
            String command = argsArray[0];

            switch(command) {
            case "todo": 
                duke = duke.processToDosInput(input).addToDos();
                Task.taskCounter++;
                break;
            case "deadline":
                duke = duke.processDeadlinesInput(input).addDeadlines();
                Task.taskCounter++;
                break;
            case "event": 
                duke = duke.processEventsInput(input).addEvents();
                Task.taskCounter++;
                break;
            case "done": 
                duke = duke.completeTask(Integer.parseInt(argsArray[1]));
                break;
            case "list":
                duke.printList();
                break;
            case "bye": 
                duke.printOutput("Bye. Hope to see you again soon!");
                break;
            default:
                duke.printOutput(input);
            }
        }
    }
    */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        System.out.println(duke);
        
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                duke = duke.runCommand(input);
            } catch (DukeException e) {
                System.err.println(e);
            }
        }

        sc.close();
    }
}
