import java.util.Scanner;

public class Duke {
    private static int listCounter = 0;
    private static String[] listToDo = new String[102];

    // let statement be printed at center
    public static void print(String str) {
        int left = (60 - str.length()) / 2;
        int right = 60 - left - str.length();
        String repeatedChar = " ";
        String buff = "\t║" + repeatedChar.repeat(Math.max(0, left)) +
                str + repeatedChar.repeat(Math.max(0, right - 1)) + "║";
        System.out.println(buff);
    }

    public static void greet(){
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print("  _   _    _   _ U _____ u             ");
        print(" |'| |'|U |\"|u| |\\| ___\"|/    ___      ");
        print("/| |_| |\\\\| |\\| | |  _|\"     |_\"_|     ");
        print("U|  _  |u | |_| | | |___      | |      ");
        print(" |_| |_| <<\\___/  |_____|   U/| |\\u    ");
        print(" //   \\\\(__) )(   <<   >>.-,_|___|_,-. ");
        print("(_\") (\"_)   (__) (__) (__)\\_)-' '-(_/  ");
        print("");
        print("Hello! I'm your chatbot - Huei.");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void echo(String cmd){
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print(cmd);
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void add(String cmd){
        listToDo[listCounter] = cmd;
        listCounter++;
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print("Added: " + cmd);
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
    }

    public static void list(){
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        int eventCounter = 1;
        for(String event: listToDo){
            if(event == null){
                break;
            }
            print(eventCounter + ". " + event);
            eventCounter++;
        }
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
    }

    public static void exit(){
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print("Bye! See you next time :)");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.exit(0);
    }

    public static void main(String[] args) {
        greet();

        // read command-line input
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();

        while(!cmd.equals("bye")){
            if(cmd.equals("list")){
                list();
            } else {
                add(cmd);
            }
            cmd = scanner.nextLine();
        }
        exit();
    }
}
