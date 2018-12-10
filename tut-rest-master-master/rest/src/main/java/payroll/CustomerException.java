package payroll;

class CustomerException extends RuntimeException {
	CustomerException(Long id) {
		super("Customer ID " + id + " could not be found");
	}
	CustomerException (){
		super("No customers");
	}
}
