import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    public static void getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
    }

    public static String parseCommand() {
        Scanner input = new Scanner(System.in);
        String command = input.next();
        return command;
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

        String command = parseCommand();
        while (!command.equals("bye")){
            System.out.println("LISA: "+ command);
            command = parseCommand();
        }

        System.out.println("LISA: Bye, hope to see you again!");
    }
}
