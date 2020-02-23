package duke;

import duke.taskmanager.TaskManager;

class TaskList {
    protected final int TASK_NUMBER = 100;
    public TaskManager[] tasks;
    public TaskList() {
        this.tasks = new TaskManager[TASK_NUMBER];
    }
}
