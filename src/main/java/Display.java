public class Display {
    private static String[] tasks = new String[100];
    private static int taskCount = 0;
    public Display(){
        System. out. print(System. lineSeparator());
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println();
    }

    public void addList(String line){
        if (line.equals("bye")){
            System.out.println("\tBye. Hope to see you again soon!");
        }
        else if (line.equals("list")){
            int currentCount = 0;
            while(currentCount < taskCount){
                System.out.println("\t" + Integer.toString(currentCount+1) + ". " + tasks[currentCount]);
                currentCount++;
            }
            System.out.println();
        }
        else{
            tasks[taskCount] = line;
            taskCount++;
            System.out.println("\tadded: " + line);
            System.out.println();
        }
    }


}
