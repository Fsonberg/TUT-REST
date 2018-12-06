package payroll;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByFirstNameLikeAndLastNameLikeAndAddressLikeAndPhoneNumberLikeAndEmailLikeAllIgnoreCase
            (String strFirstName, String strLastName, String strAddress, String strPhoneNumber, String strEmail);

}
