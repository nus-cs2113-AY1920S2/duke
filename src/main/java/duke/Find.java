package duke;
import duke.task.Todo;

import java.util.ArrayList;

public class Find {
    public ArrayList<Todo> results;

    public Find(String keywords) {
        results = new ArrayList<>();
        String[] buffer = keywords.split(Ui.SPACE_SYMBOL);
        for (Todo todo : Data.todos) {
            for (String buf : buffer) {
                if (todo.getDescription().contains(buf)) {
                    results.add(todo);
                    break;
                }
            }
        }
        Ui.printBreak();
        Ui.printFindStatement();
        int i = 0;
        for (Todo result : results) {
            i++;
            System.out.println("    " + i + ". " + result);
        }
        if (i == 0) {
            System.out.println("  Sorry, no results found.\n  You can try [list] to see all tasks.");
        }
    }
}
