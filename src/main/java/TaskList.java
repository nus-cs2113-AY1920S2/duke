import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> savedList) {
        this.taskList = savedList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public void listCommand() throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
    }

    public void markDone(int taskIndex) throws InvalidTaskIndexException {
        if ((taskIndex < 0) || (taskIndex >= taskList.size())) {
            throw new InvalidTaskIndexException();
        }
        taskList.get(taskIndex).setDone(true);
    }

    public void deleteTask(int taskIndex) throws EmptyListException, InvalidTaskIndexException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }

        taskList.remove(taskList.get(taskIndex));
    }

    public void addToDo(String toDoTask) {
        taskList.add(new ToDo(toDoTask));
    }

    public void addEvent(String[] eventWords) {
        String eventTask = eventWords[0];
        String eventAt = eventWords[1];
        taskList.add(new Event(eventTask, eventAt));
    }

    public void addDeadline(String[] deadlineWords) {
        String deadlineTask = deadlineWords[0];
        String deadlineBy = deadlineWords[1];
        taskList.add(new Deadline(deadlineTask, deadlineBy));
    }
}
