package payroll;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface FoundItemRepository extends JpaRepository<FoundItem, Long> {
    List<FoundItem> findAllByBrandLikeAndCategoryLikeAndColorLikeAllIgnoreCase(String strFoundBrand, String strFoundCategory, String strFoundColor);
}
