package duke;

import java.io.IOException;

public class FindCommand extends Command{
    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.findTask(keyWord);
    }
}
