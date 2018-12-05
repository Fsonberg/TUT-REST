package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByFirstNameLikeAndLastNameLikeAndAddressLikeAndPhoneNumberLikeAllIgnoreCase
            (String strFirstName, String strLastName, String strAddress, int strPhoneNumber);

}
