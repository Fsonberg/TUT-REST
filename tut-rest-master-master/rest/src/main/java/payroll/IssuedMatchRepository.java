package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssuedMatchRepository extends JpaRepository<IssuedMatch, Long> {

    List<IssuedMatch> findAllByLostIDAndFoundIDAndUserIDAndEmpID (Long lostID, Long foundID, Long userID, Long empID);
}
