import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<String> dukeList = new ArrayList<String>();
    public static void doLine(){
        String line = "_".repeat(60);
        System.out.println("\t"+line);
    }
    public static void doGreeting(){
        doLine();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        doLine();
    }
    public static void doFarewell(){
        doLine();
        System.out.println("\tBye. Hope to see you again soon!");
        doLine();
    }
    public static void addList(String text){
        dukeList.add(text);
        removeEmptyItem();
        doLine();
        System.out.println("added: " +text);
        doLine();
    }
    public static void removeEmptyItem(){
        List<String> string = new ArrayList<String>();
        string.add("");
        dukeList.removeAll(string);
    }
    public static void getList(){
        doLine();
        for(int i =0; i<dukeList.size();i++){
            System.out.println("\t"+(i+1)+". "+dukeList.get(i));
        }
        doLine();
    }
    public static void main(String[] args) {
        doGreeting();
        while(true){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equals("bye")){
                doFarewell();
                break;
            }else if(line.equals("list")){
                getList();
            }else{
                addList(line);
            }

        }

    }
}
