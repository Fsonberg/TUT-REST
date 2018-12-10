package payroll;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByFirstNameLikeAndLastNameLikeAndAddressLikeAndPhoneNumberLikeAndEmailLikeAllIgnoreCase
            (String strFirstName, String strLastName, String strAddress, String strPhoneNumber, String strEmail);
}
