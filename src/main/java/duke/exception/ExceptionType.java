package duke.exception;

/**
 * Enumeration used to denote type of exception for DukeException.
 * <p><u>
 * The seven types of exceptions are:
 * <br></u>
 * <i>
 * 1)EmptyCommandException<br>
 * 2)InvalidCommandException<br>
 * 3)InvalidDeadlineDeclarationException<br>
 * 4)InvalidDeleteCommandException<br>
 * 5)InvalidDoneCommandException<br>
 * 6)InvalidEventDeclarationException<br>
 * 7)InvalidFindCommandException<br>
 * 8)InvalidToDoDeclarationException<br>
 * </i>
 */
public enum ExceptionType {
    EmptyCommandException, InvalidCommandException, InvalidDeadlineDeclarationException,
    InvalidDeleteCommandException, InvalidDoneCommandException, InvalidEventDeclarationException,
    InvalidFindCommandException, InvalidToDoDeclarationException
}
