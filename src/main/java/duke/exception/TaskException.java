package duke.exception;

import duke.task.tasktypes.Task;

public class TaskException extends Exception {

    public TaskException (String e) {
        super(e);
    }

    @Override
    public String toString() {
        return this.getLocalizedMessage();
    }


    /**
     * This exception is raised when the index is out of bounds.
     */
    public static class TaskOutOfBoundsException extends Exception {

        private int targetIndex;

        public TaskOutOfBoundsException (int targetIndex) {
            this.targetIndex = targetIndex;
        }

        public int getTargetIndex () {
            return targetIndex;
        }

    }


    /**
     * This exception is raised when the task list is empty.
     */
    public static class TaskListEmptyException extends Exception {}


    /**
     * This exception is raised when the deadline/event details are missing
     */
    public static class TaskInvalidDateException extends Exception {

        public TaskInvalidDateException () {
            super("missing task time/place details");
        }

        @Override
        public String toString () {
            return getLocalizedMessage();
        }
    }


    /**
     * This exception is raised when the task description is empty
     */
    public static class TaskEmptyDescriptionException extends Exception {

        public TaskEmptyDescriptionException () {
            super("missing description");
        }

        @Override
        public String toString () {
            return getLocalizedMessage();
        }

    }


    /**
     * This exception is raised when a task has already been marked as done before.
     */
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
