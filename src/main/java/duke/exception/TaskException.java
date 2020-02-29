package duke.exception;

import duke.task.tasktypes.Task;

import java.lang.reflect.Executable;
import java.security.cert.Extension;

public class TaskException extends Exception {

    public TaskException (String e) {
        super(e);
    }

    @Override
    public String toString() {
        return this.getLocalizedMessage();
    }


    public static class TaskOutOfBoundsException extends Exception {

        private int targetIndex;

        public TaskOutOfBoundsException (int targetIndex) {
            this.targetIndex = targetIndex;
        }

        public int getTargetIndex () {
            return targetIndex;
        }

    }

    public static class TaskListEmptyException extends Exception {}

    public static class TaskInvalidDateException extends Exception {

        public TaskInvalidDateException () {
            super("missing task time/place details");
        }

        @Override
        public String toString () {
            return getLocalizedMessage();
        }
    }


    public static class TaskEmptyDescriptionException extends Exception {

        public TaskEmptyDescriptionException () {
            super("missing description");
        }

        @Override
        public String toString () {
            return getLocalizedMessage();
        }

    }

    public static class TaskAlreadyMarkedException extends Exception {

        private Task task;

        public TaskAlreadyMarkedException (Task task) {
            this.task = task;
        }

        public Task getTask () {
            return task;
        }

    }

}
