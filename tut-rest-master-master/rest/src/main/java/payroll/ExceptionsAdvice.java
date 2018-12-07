package payroll;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ExceptionsAdvice {
	@ResponseBody
	@ExceptionHandler({ EmployeeExceptions.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)

	String employeeExceptionHandler (EmployeeExceptions ex){
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler({CustomerException.class})

	String customerExceptionHandler(CustomerException ex){
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler({LostItemException.class})

	String lostItemExceptionHandler(LostItemException ex){
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler({FoundItemException.class})

	String foundItemExceptionHandler(FoundItemException ex){
		return ex.getMessage();
	}


}
