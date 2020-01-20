import java.util.Scanner;

public class Duke {

      static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        }

        public void updateTask(){
            this.isDone = true;
        }
    }


    public static void main(String[] args) {

        String logo = " __    __      __    __           \n"
                +     "|  | /  /___  |  | /  /___        \n"
                +     "|  |/  /  _  \\|  |/  /  _  \\    \n"
                +     "|      \\   __/|      \\   __/    \n"
                +     "|__|\\___\\ __| |__|\\___\\ __|   \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("===================================================");
        String s1 = String.format("%50s","What's your name?");
        System.out.println(s1);
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("===================================================");


        System.out.println(String.format("%50s","Hello " + name + ", Anything I can help you with? "));
        System.out.println("===================================================");

        //String[] stringArray = new String[100];
        Task[] stringArray = new Task[100];
        int counter = 0;

        while (true) {
            String input = sc.nextLine();
            String[] arrOfStr = input.split(" ");

            if (arrOfStr[0].equalsIgnoreCase("done")) {
                System.out.println(String.format("%50s","Nice! I've marked this task as done:"));
                int doneTask = Integer.parseInt(arrOfStr[1]);
                stringArray[doneTask].updateTask();
                System.out.println(String.format("%50s","["+ stringArray[doneTask].getStatusIcon() +
                        "] "+ stringArray[doneTask].description));
                System.out.println("===================================================");
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(String.format("%50s","Here are the tasks in your list:"));
                for (int i = 1; i <= counter; i++) {
                    System.out.println(String.format("%50s",i +".["+ stringArray[i].getStatusIcon() +
                            "] "+ stringArray[i].description));
                }
                System.out.println("===================================================");
            } else if (input.equalsIgnoreCase("bye")) {
                System.out.println(String.format("%50s","Bye, "+ name + ". Hope to see you again soon!"));
                System.out.println("===================================================");
                break;
            } else {
                counter ++;
                System.out.println(String.format("%50s","added: "+ input));
                stringArray[counter] = new Task(input);
                System.out.println("===================================================");
            }
        }

    }
}
