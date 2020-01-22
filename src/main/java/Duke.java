import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> l1= new ArrayList<String>();
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
                    for(i=0; i< l1.size(); i++){
                        int count =i+1;
                        System.out.println("\t" +count+". "+l1.get(i));
                    }
                    System.out.println(line);
                }else{

                    System.out.println(line);
                    l1.add(userIn);
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
