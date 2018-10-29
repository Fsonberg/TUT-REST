package payroll;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface LostItemRepository extends JpaRepository<LostItem, Long> {


    List<LostItem> findAllByBrandAndCategoryLike(String strLostBrand, String strLostCategory);


}


