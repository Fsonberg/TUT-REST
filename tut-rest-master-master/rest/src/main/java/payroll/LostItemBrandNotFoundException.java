package payroll;

class LostItemBrandNotFoundException extends RuntimeException {

    LostItemBrandNotFoundException(Long brand) {
        super("Could not find item " + brand);

    }


}
