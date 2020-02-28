package duke.tasks;
import java.time.LocalDate;

/**
 * Interface class for subclasses that have a date component.
 */
public interface  DatedEvents{

    /** This method returns a date associated with a Task.*/
    LocalDate returnDate();
}
