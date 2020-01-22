public class Duke {
    public static void doGreeting(){
        String line = "_".repeat(60);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

    }
    public static void doFarewell(){
        String line = "_".repeat(60);
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
    public static void main(String[] args) {
        doGreeting();
        doFarewell();
    }
}
