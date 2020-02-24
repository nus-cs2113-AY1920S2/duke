public class Ui {
    private static final String logo = "  ____        _        \n"
            + " |  _ \\ _   _| | _____ \n"
            + " | | | | | | | |/ / _ \\\n"
            + " | |_| | |_| |   <  __/\n"
            + " |____/ \\__,_|_|\\_\\___|\n";


    public void showWelcomeMessage() {
        printLine();
        System.out.println(logo);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public void printLine() {
        for (int i = 0; i < 60; i += 1) {
            System.out.print("_");
        }
        System.out.println();
    }

    public void exitFromApp() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
        System.exit(0);
    }

    public void showLoadingError() {
        System.out.println("IO Exception occurred");
    }
}
