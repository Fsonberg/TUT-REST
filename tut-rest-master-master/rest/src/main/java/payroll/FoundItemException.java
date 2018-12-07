package payroll;

public class FoundItemException extends RuntimeException {
    FoundItemException(Long id){
        super("FoundItem ID " + id + " could not be found");
    }
}
