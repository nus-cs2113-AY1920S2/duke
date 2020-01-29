import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static class Task{
        protected String action;
        protected boolean isDone;

        public Task(String action){
            this.action=action;
            this.isDone=false;
        }
        public String getStatus(){
            String temp=this.isDone ? "\u2713" : "\u2718";
            return temp;
        }
        public void done(){
            this.isDone=true;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Task> l1= new ArrayList<Task>();
        String line = "\t__________________________________________________________";
        String banner="\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                +"\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                +"\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                +"\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                +"\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        String logo = "\t~~~~~~~~~~~~~~~~~~ ____        _        ~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~|  _ \\ _   _| | _____ ~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~| | | | | | | |/ / _ \\~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~| |_| | |_| |   <  __/~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~|____/ \\__,_|_|\\_\\___|~~~~~~~~~~~~~~~~~~\n";
        System.out.println(line);
        System.out.println("\t" +"Hello from\n" + banner + logo + banner);
        System.out.println("\t" +"What can I do for you?");

        System.out.println(line);
        try {
            while(in.hasNext()){
                String userIn = in.nextLine();
                if (userIn.equals("bye")) {
                    System.out.println(line);
                    System.out.println("\tBye.Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if(userIn.equals("list")) {
                    int i=0;
                    System.out.println(line);
                    System.out.print("\tHere are the tasks in your list:\n");
                    for(i=0; i< l1.size(); i++){
                        int count =i+1;
                        Task temp=l1.get(i);
                        System.out.println("\t" +count+". " +"[" +temp.getStatus()+ "]"+ " " + temp.action);
                    }
                    System.out.println(line);
                }else if(userIn.contains("done ")) {
                    String[] temp=userIn.split(" ");
                    int number= Integer.parseInt(temp[1])-1;
                    Task tempTask=l1.get(number);
                    tempTask.done();
                    System.out.println(line);
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t ["+tempTask.getStatus()+"] "+ tempTask.action);
                    System.out.println(line);
                }else{
                    Task task = new Task(userIn);
                    System.out.println(line);
                    l1.add(task);
                    System.out.println("\t" +"added: " + userIn);
                    System.out.println(line);
                }
            }
        }
        finally{
            in.close();
        }
    }
}