package payroll;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
class ExceptionsAdvice {
	@ResponseBody
	@ExceptionHandler({ EmployeeException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeExceptionHandler (EmployeeException ex){
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

	@ResponseBody
	@ExceptionHandler({MatchException.class})
	String matchExceptionHandler (MatchException ex) {return  ex.getMessage();}
}