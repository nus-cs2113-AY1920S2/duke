public class display {
    public display(){
        System. out. print(System. lineSeparator());
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
    }

    public static void reply(String line){
        if (line.equals("bye")){
            bye();
        }
        else{
            System.out.println("\t" + line);
        }
    }

    public static void bye(){
        System.out.println("\tBye. Hope to see you again soon!");
    }


}
