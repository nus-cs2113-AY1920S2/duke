import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> taskList = new ArrayList<Task>();
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
        taskList.add(new Task(text));
        doLine();
        System.out.println("added: " +text);
        doLine();
    }

    public static void getList(){
        doLine();
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i< taskList.size(); i++){
            System.out.println("\t"+(i+1)+".["+ taskList.get(i).getStatusIcon()+"] "+ taskList.get(i).getDescription() );
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
            }else if(line.equals("list")) {
                getList();
            }else if(line.split(" ")[0].equals("done")){
                if(line.split(" ")[1].matches("-?\\d+")){
                    try {
                        taskList.get(Integer.parseInt(line.split(" ")[1]) - 1).markAsDone();
                        doLine();
                        System.out.println("\tNice! I've marked this task as done:");
                        System.out.println("\t\t[" + taskList.get(Integer.parseInt(line.split(" ")[1]) - 1).getStatusIcon() + "] " + taskList.get(Integer.parseInt(line.split(" ")[1]) - 1).getDescription());
                    } catch (Exception e){
                        doLine();
                        System.out.println("Index Out of Bounds. Please input within List range.");
                        doLine();
                    }
                }else{
                    doLine();
                    System.out.println("You have entered incorrect input. Please try again!");
                    doLine();
                }
            }else{
                addList(line);
            }

        }

    }
}
