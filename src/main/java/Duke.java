import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Display adisplay = new Display();

        String responde;
        Scanner in = new Scanner(System.in);
        do {
            responde = in.nextLine();
            adisplay.addList(responde);

        }while(!responde.equals("bye"));

    }
}
