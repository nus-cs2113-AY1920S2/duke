public class Parser {

    public int parseTaskIndex(String[] words, int taskListSize) throws InvalidTaskIndexException {
        String taskString = words[1];
        int taskIndex = Integer.parseInt(taskString) - 1;
        if ((taskIndex < 0) || (taskIndex >= taskListSize)) {
            throw new InvalidTaskIndexException();
        }
        return taskIndex;
    }

    public String parseToDo(String[] words) throws EmptyTaskException {
        if (words.length < 2) {
            throw new EmptyTaskException();
        }
        return words[1];
    }

    public String[] parseEvent(String[] words) throws InvalidEventException {
        if ((words.length < 2) || !words[1].contains(" /at ")) {
            throw new InvalidEventException();
        }
        String[] eventWords = words[1].split(" /at ", 2); // Split task and date/time
        return eventWords;
    }

    public String[] parseDeadline(String[] words) throws InvalidDeadlineException{
        if ((words.length < 2) || !words[1].contains(" /by ")) {
            throw new InvalidDeadlineException();
        }
        String[] deadlineWords = words[1].split(" /by ", 2); // Split task and date/time
        return deadlineWords;
    }

    public void parseDone(String[] words) throws EmptyTaskException {
        if (words.length < 2) {
            throw new EmptyTaskException();
        }
    }

    public void parseDelete(String[] words) throws EmptyTaskException {
        if (words.length < 2) {
            throw new EmptyTaskException();
        }
    }
}
