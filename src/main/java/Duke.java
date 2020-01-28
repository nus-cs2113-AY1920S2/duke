import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");

        Scanner scan = new Scanner( System.in );
        String[] lists = new String[101];
        int count =0;
        while(true) {
            String userData = scan.nextLine();
            if(userData.toLowerCase().equals("bye")){
                break;
            }else if(userData.toLowerCase().equals("list")){
                 for (int i = 1; i <= lists.length; i++) {
                     if (lists[i-1] != null) {
                         System.out.println(i + ". " + lists[i-1]);
                     }
                }
            }else{
                lists[count] = userData;
                count +=1;
                System.out.println("added:  " +userData);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
