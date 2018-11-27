package payroll;

import org.h2.engine.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface LostUserRepository extends JpaRepository<User, String> {
    List<User> findAllBy

}
