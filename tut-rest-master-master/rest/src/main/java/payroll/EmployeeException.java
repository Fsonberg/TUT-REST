package payroll;

public class EmployeeException extends RuntimeException {
    EmployeeException(Long id){
        super("Employee ID " + id + " could not be found");
    }
    EmployeeException(){
        super("No employees");
    }
}
