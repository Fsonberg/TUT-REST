package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface LostUserRepository extends JpaRepository<Users, Long> {
    List<Users> findAllByFirstNameLikeAndLastNameLikeAndAddressLikeAndPhoneNumberLikeAllIgnoreCase(String strFirstName, String strLastName, String strAddress, String strPhoneNumber);

}
