package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface LostUserRepository extends JpaRepository<Users, Long> {
    List<Users> findAllByFirstNameLikeAndLastNameLikeAndAddressLikeAndPhoneNumberLikeAllIgnoreCase(String firstName, String lastName, String address, int phoneNumber);

}
