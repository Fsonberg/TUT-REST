package payroll;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

	private final EmployeeRepository repository;

	EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	// tag::get-aggregate-root[]
	@GetMapping("/employees")
	Resources<Resource<LostItem>> all() {

		List<Resource<LostItem>> employees = repository.findAll().stream()
			.map(employee -> new Resource<>(employee,
				linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
			.collect(Collectors.toList());
		
		return new Resources<>(employees,
			linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping("/employees")
	LostItem newEmployee(@RequestBody LostItem newEmployee) {
		System.out.println("inside post");
		return repository.save(newEmployee);
	}

	// Single item

	// tag::get-single-item[]
	@GetMapping("/employees/{id}")
	Resource<LostItem> one(@PathVariable Long id) {
		
		LostItem employee = repository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(id));
		
		return new Resource<>(employee,
			linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
			linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
	}
	// end::get-single-item[]

	@PutMapping("/employees/{id}")
	LostItem replaceEmployee(@RequestBody LostItem newEmployee, @PathVariable Long id) {
		
		return repository.findById(id)
			.map(employee -> {
				employee.setName(newEmployee.getName());
				employee.setRole(newEmployee.getRole());
				return repository.save(employee);
			})
			.orElseGet(() -> {
				newEmployee.setId(id);
				return repository.save(newEmployee);
			});
	}

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	} // set til invalid i stedet
}
