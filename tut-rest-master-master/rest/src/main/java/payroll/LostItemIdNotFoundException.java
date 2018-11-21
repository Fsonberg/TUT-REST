package payroll;

class LostItemIdNotFoundException extends RuntimeException {

	LostItemIdNotFoundException(Long id) {
		super("Could not find item " + id);
	}
}
