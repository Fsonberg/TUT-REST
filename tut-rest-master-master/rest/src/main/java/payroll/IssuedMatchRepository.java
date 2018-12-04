package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssuedMatchRepository extends JpaRepository<IssuedMatch, Long> {

    List<IssuedMatch> findAllByLostIDAndFoundIDAndCustomerIDAndEmpID (Long lostID, Long foundID, Long customerID, Long empID);
}
