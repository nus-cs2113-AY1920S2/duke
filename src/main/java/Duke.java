import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________");
        System.out.println("Hello,I'm little pepper. Your personal sweetheart.");
        System.out.println("What can I do for you? You can choose two model:");
        System.out.println("1.echo mode\n2.command mode");
        System.out.println("________________________________");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        Boolean inputNotValid = Boolean.TRUE;
        while(inputNotValid) {
            switch(input.trim()){
            case "1":
                echoMode(input,in);
                inputNotValid = Boolean.FALSE;
                break;
            case "2":
                commandMode(input,in);
                inputNotValid = Boolean.FALSE;
                break;
            default:
                System.out.println("Unknown mode, please try again.");
                input = in.nextLine();
            }
        }
    }

    public static void echoMode(String input,Scanner in){
        input = in.nextLine().trim().toLowerCase();
        while(!(input.equals("bye"))){
            System.out.println("    ________________________________");
            System.out.println("    "+input);
            System.out.println("    ________________________________");
            input = in.nextLine();
        }
        sayBye();
    }

    public static void commandMode(String input,Scanner in){
        TaskList taskList = new TaskList();
        int taskNum = 0;
        input = in.nextLine().trim().toLowerCase();
        while(!input.equals("bye")){
            System.out.println("    ________________________________");
            if(input.startsWith("done")){
                int dividePosition = input.indexOf(" ");
                int taskIndex = Integer.parseInt(input.substring(dividePosition+1));
                Task cur_task = taskList.getOneTask(taskIndex-1);
                cur_task.setTaskStatus("[\u2713]");
                System.out.println("    Congratulations! You have just finished this task:");
                System.out.println("    "+cur_task.getTaskStatus()+cur_task.getTaskName());
            }
            else if(!input.equals("list")) {
                input = input.replaceAll("i want to ","");
                System.out.println("    added: "+input);
                taskList.append(new Task(input));
            }
            else
                taskList.printTaskList();
            System.out.println("    ________________________________");
            input = in.nextLine().trim().toLowerCase();
        }
        sayBye();
    }

    
    public static void sayBye(){
        System.out.println("    ________________________________");
        System.out.println("    Don't leave me alone! Please come back soon!");
        System.out.println("    ________________________________");
    }

}
