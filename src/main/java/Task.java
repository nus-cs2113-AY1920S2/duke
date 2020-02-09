public abstract class Task {
    String name;
    boolean isDone;


    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public abstract String print();
}
