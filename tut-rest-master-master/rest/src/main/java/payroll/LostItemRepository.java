package payroll;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface LostItemRepository extends JpaRepository<LostItem, Long> {

    List<LostItem> findAllByBrandLikeAndCategoryLikeAndColorLikeAllIgnoreCase
            (String strLostBrand, String strLostCategory, String strLostColor);
}



