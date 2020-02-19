public class Ui {
    public Ui() {

    }

    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void showWelcome(String name) {
        showLine();
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);

        showLine();
        System.out.println("\tHello! I'm " + name);
        System.out.println("\tWhat can I do for you?");
        showLine();
    }
}