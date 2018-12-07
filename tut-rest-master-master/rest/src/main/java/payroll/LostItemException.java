package payroll;

public class LostItemException extends RuntimeException {
    LostItemException(Long id){
        super("LostItem ID " + id + " could not be found");
    }
}
