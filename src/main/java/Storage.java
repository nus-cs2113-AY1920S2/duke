import java.io.IOException;

public class Storage {
    private String filepath;
    private FileIO file;
    private TaskList tasks;

    public Storage(String filepath, TaskList tasks) {
        this.filepath = filepath;
        this.file = new FileIO("./data/duke.txt", tasks);
        this.tasks = tasks;
    }

    /**
     * Load all content to tasks.
     */
    public void load() {
        file.readAll(tasks);
    }

    /**
     * Store all content from tasks.
     */
    public void store() throws IOException {
        file.writeAll(tasks);
    }

    public void close() throws IOException {
        file.close();
    }
}