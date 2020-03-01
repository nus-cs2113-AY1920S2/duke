package duke;

import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;



public class Command {
    public static void handleCommand(String line, String[] words, ArrayList<Task> taskList) throws EmptyTaskListException, EmptyDoneException, EmptyDeleteException, EmptyFindException, UnknownWordException, EmptyTodoException, EmptyDeadlineException, EmptyEventException {
        if (line.equals("list")) {
            if (taskList.isEmpty()) {
                throw new EmptyTaskListException();
            }
            UI.printListMessage(taskList);
        } else if (words[0].equals("done")) {
            if (taskList.isEmpty()) {
                throw new EmptyTaskListException();
            }
            if (words.length < 2) {
                throw new EmptyDoneException();
            }
            int taskNum = Integer.parseInt(words[1]);
            Task t = taskList.get(taskNum - 1);
            t.markAsDone();
            UI.printDoneMessage(t);
        } else if (words[0].equals("find")) {
            if (words.length < 2 || words[1].isBlank()) {
                throw new EmptyFindException();
            }
            int findCount = 0;
            for (Task t : taskList) {
                String taskDescription = t.getDescription();
                if(t.toString().toLowerCase().contains(words[1].toLowerCase())){
                    findCount++;
                    if(findCount==1){
                        UI.printFoundMessage();
                    }
                    UI.printFoundTask(t, findCount);
                }
            }
            if(findCount==0){
                UI.printNotFoundMessage();
            }else{
                System.out.println("\t____________________________________________________________\n");
            }
        } else if (words[0].equals("delete")) {
            if (taskList.isEmpty()) {
                throw new EmptyTaskListException();
            }
            if (words.length < 2) {
                throw new EmptyDeleteException();
            }
            int taskNum = Integer.parseInt(words[1]);
            Task t = taskList.get(taskNum - 1);
            taskList.remove(t);
            UI.printDeleteMessage(t, taskList);
        } else if (words[0].equals("todo") || words[0].equals("deadline") || words[0].equals("event")) {
            String taskType = words[0];
            Task t = new Task(line);
            switch (taskType) {
                case "todo":
                    if (words.length < 2 || words[1].isBlank()) {
                        throw new EmptyTodoException();
                    }
                    t = new Todo(words[1]);
                    break;
                case "deadline":
                    if (words.length < 2 ) {
                        throw new EmptyDeadlineException();
                    }
                    String[] deadlineWords = Parser.splitDeadline(words[1]);
                    if (deadlineWords[0].isBlank() || deadlineWords[1].isBlank()) {
                        throw new EmptyDeadlineException();
                    }
                    t = new Deadline(deadlineWords[0], deadlineWords[1]);
                    break;
                case "event":
                    if (words.length < 2 ) {
                        throw new EmptyEventException();
                    }
                    String[] eventWords = Parser.splitEvent(words[1]);
                    if (eventWords[0].isBlank() || eventWords[1].isBlank()) {
                        throw new EmptyEventException();
                    }
                    t = new Event(eventWords[0], eventWords[1]);
                    break;
            }
            taskList.add(t);
            UI.printAddTaskMessage(t, taskList);
        } else {
            throw new UnknownWordException();
        }
    }
}