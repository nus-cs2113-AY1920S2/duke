import java.util.Scanner;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    static String[] tasks = new String[100];
    static int taskCounter = 0;

    public static void getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
    }

    public static void printList() {
        int count = 1;
        System.out.println("Listing tasks below:");
        for (String task : tasks) {
            if (task == null){
                break;
            }
            System.out.println(count + ". " + task);
            count++;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        String logo =   "  ,--,       ,---,   .--.--.       ,---,        \n"
                        + ",--.'|    ,`--.' |  /  /    '.    '  .' \\       \n"
                        + "|  | :    |   :  : |  :  /`. /   /  ;    '.     \n"
                        + ":  : '    :   |  ' ;  |  |--`   :  :       \\    \n"
                        + "|  ' |    |   :  | |  :  ;_     :  |   /\\   \\   \n"
                        + "'  | |    '   '  ;  \\  \\    `.  |  :  ' ;.   : \n"
                        + "|  | :    |   |  |   `----.   \\ |  |  ;/  \\   \\ \n"
                        + "'  : |__  '   :  ;   __ \\  \\  | '  :  | \\  \\ ,' \n"
                        + "|  | '.'| |   |  '  /  /`--'  / |  |  '  '--'   \n"
                        + ";  :    ; '   :  | '--'.     /  |  :  : \n"
                        + "|  ,   /  ;   |.'    `--'---'   |  | ,'\n"
                        + "---`-'   '---'                 `--''   \n";

        System.out.println("\n" + logo + "\nYour Lifestyle Scheduling Assistant\n");
        System.out.println("____________________________________________________________\n\nCurrent time: ");
        getDateTime();
        System.out.println("____________________________________________________________");



        Scanner input = new Scanner(System.in);
        String userCommand = input.nextLine();

        while (!userCommand.equals("bye")){
            //System.out.println("LISA: "+ userCommand);

            switch (userCommand) {
            case "list":
                printList();
                break;
            default:
                tasks[taskCounter++] = userCommand;
                System.out.println("added: " + userCommand);
            }

            userCommand = input.nextLine();
        }

        System.out.println("LISA: Bye, hope to see you again!");
    }
}
