package payroll;

public class MatchException extends RuntimeException{
    MatchException (Long id) {
        super("Issued Match ID " + id + " could not be found");
    }

    MatchException() {
        super(
                "There is no current matches"
        );
    }
}
