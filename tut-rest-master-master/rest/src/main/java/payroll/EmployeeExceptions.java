package payroll;

public class EmployeeExceptions extends RuntimeException {
    EmployeeExceptions(Long id){
        super("Employee ID " + id + " could not be found");
    }
    EmployeeExceptions (){
        super("No employees");
    }
}
