import duke.commands.TaskList;
import duke.commands.Todo;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    private static Storage storage;

    static {
        try {
            storage = new Storage("test");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Ui ui = new Ui();
    private static TaskList tasks;

    @BeforeAll
    static void setUp() throws DukeException {
        tasks = new TaskList();
        Todo todoItem = new Todo("Learn");
        todoItem.execute(tasks,ui,storage);
    }

    @Test
    public void execute_addTask_addsItemSuccessfully() {
        assertEquals("[T][ ] Learn",tasks.list.get(0).command);
    }

    @Test
    public void execute_addTask_countCorrect() {
        assertEquals(1,tasks.list.size());
    }

    @AfterAll
    static void cleanUp() {
        tasks.removeTask(0);
        storage.updateListDataOnDisk(tasks.list);
    }
}
