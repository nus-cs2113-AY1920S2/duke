import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasks=new String[100];
        int taskCountor=0;
        Scanner read=new Scanner(System.in);
        System.out.println("------------***------------");
        System.out.println("Hello! I'm Momo");
        System.out.println("What can I do for you?");
        System.out.println("------------***------------");
        String command=read.nextLine();
        while(!command.equals("bye")){
            System.out.println("------------***------------");
            switch(command) {
            case "list":
                for(int i=0;i<100;i++){
                    if(tasks[i]==null){
                        break;
                    }
                    System.out.println((i+1)+": "+tasks[i]);
                }
                break;
            default:
                System.out.println("added: "+command);
                tasks[taskCountor]=command;
                taskCountor++;
            }
            System.out.println("------------***------------");
            System.out.println("Do you have any other commands? ");
            command=read.nextLine();
        }
        System.out.println("------------***------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------***------------");
    }
}
