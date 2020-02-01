import java.util.Scanner;
import duke.Duke;
import duke.exception.DukeException;

public class Main {

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
