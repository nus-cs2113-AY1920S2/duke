import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        display adisplay = new display();

        String responde;
        Scanner in = new Scanner(System.in);
        do {
            responde = in.nextLine();
            adisplay.reply(responde);

        }while(!responde.equals("bye"));

    }
}
