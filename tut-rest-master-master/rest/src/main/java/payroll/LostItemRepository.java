package payroll;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface LostItemRepository extends JpaRepository<LostItem, Long> {


    List<LostItem> findAllByBrandAndCategory(String strLostBrand, String strLostCategory);


}


