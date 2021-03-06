package payroll;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IssuedMatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByLostItemIDLikeAndFoundItemIDLikeAndCustomerIDLikeAndEmpIDLikeAllIgnoreCase
            (Long lostID, Long foundID, Long customerID, Long empID);
}
