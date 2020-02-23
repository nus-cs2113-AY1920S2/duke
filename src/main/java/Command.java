import java.io.File;
import java.util.List;

public abstract class Command {
    public static final int LIMIT = 2;
    public static final int LIST_INDEX = 1;
    public static final int TASK_DESCRIPTION = 1;
    public static final int TASK_DESCRIPTION_AND_DATE = 1;
    public static final int TASK_DEADLINE = 0;
    public static final int TASK_EVENT_AT = 0;
    public static final int DUKE_COMMAND = 0;

    public abstract void execute(Storage myTasks, File saveFile, List<String> commands,
                                 String command);
}
