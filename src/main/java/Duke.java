import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks=new Task[100];
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
                System.out.println("Here are the tasks in your list:");
                for(int i=0;i<100;i++){
                    if(tasks[i]==null){
                        break;
                    }
                    System.out.println((i+1)+": ["+tasks[i].getStatusIcon()+"] "+tasks[i].description);
                }
                break;
            default:
                if(command.matches("done \\d")){
                    String[] splitCommand=command.split(" ");
                    int taskIndex=Integer.parseInt(splitCommand[1])-1;
                    if(taskIndex<taskCountor) {
                        tasks[taskIndex].markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  ["+tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].description);
                    }else{
                        System.out.println("There is no task No."+(taskIndex+1));
                    }
                }else{
                    System.out.println("added: "+command);
                    tasks[taskCountor]=new Task(command);
                    taskCountor++;
                }
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
