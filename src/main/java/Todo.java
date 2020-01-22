public class Todo {
    private String name;
    private boolean done;

    public Todo(String name) {
        this.done = false;
        this.name = name;
    }
    private String isDone() {
        return this.done ? "[✓] " : "[✗] ";
    }
    public void setDone() {
        this.done = true;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + "[✓] " + this.name);
    }
    public String listMe() {
        return isDone() + this.name;
    }
}
