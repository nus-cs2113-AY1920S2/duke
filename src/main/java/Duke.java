import java.util.Scanner;
import java.util.ArrayList;
import exceptions.DukeException;
import tasks.Task;

public class Duke {
    private static ArrayList<Task> taskArrList = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        boolean continueRun = true;
        String userCmd = "";
        Ui.printGreeting();

        try {
            taskArrList = Storage.loadDuke(taskArrList);

            while (continueRun) {
                System.out.println("==========================");
                System.out.println("How can I help you?");
                userCmd = sc.nextLine();

                //immediate exit if userCmd has 'bye'
                taskArrList = Parser.runParser(userCmd, taskArrList);
            }
        }
        catch (DukeException e){
            System.out.println(e +"\nPlease try again");
        }
    }
}

