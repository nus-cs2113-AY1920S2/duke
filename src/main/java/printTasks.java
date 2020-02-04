import java.util.List;
import java.util.stream.Stream;

public class printTasks extends TaskManager {
    public printTasks (List taskList) {
        super(taskList);
    }

    public void exe() {
        Stream stream = taskList.stream();
        if (taskList.isEmpty()){
            System.out.println("    Congratulations, you have cleared all the tasks!\n");
        } else {
            stream.forEach(System.out::println);
            System.out.println();
        }
    }
}
