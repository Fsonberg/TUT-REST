package payroll;

import java.util.List;

import org.springframework.context.annotation.Conditional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;


//FIX MATCH MELLEM TABELLER + BRAND QUERIES

@EnableJpaRepositories
class Config{}

@RestController
class ItemController {

    private final LostItemRepository lostRepo;

    private final FoundItemRepository foundRepo;

    private final MatchRepository matchRepo;

    ItemController(LostItemRepository lostRepo, FoundItemRepository foundRepo, MatchRepository matchRepo) {
        this.lostRepo = lostRepo;
        this.foundRepo = foundRepo;
        this.matchRepo = matchRepo;
    }




    // Aggregate root

    @GetMapping("/lostItems")
    List<LostItem> allLost() {
        return lostRepo.findAll();
    }

    @PostMapping("/lostItems")
    LostItem newItem(@RequestBody LostItem newItem) {
        return lostRepo.save(newItem);
    }

    @GetMapping("/foundItems")
    List<FoundItem> allFound(){return foundRepo.findAll();}

    @PostMapping("/foundItems")
     FoundItem newFoundItem(@RequestBody FoundItem newFoundItem) {

        if(newFoundItem.getCategory() == newItem((LostItem) lostTwo("%%","%%", "%%")).getCategory()){

            return lostRepo.findAllById("%%");

        }
        return foundRepo.save(newFoundItem);}




    // Single item

    @GetMapping("/lostItems/{id}")
    LostItem one(@PathVariable Long id) {

        return lostRepo.findById(id)
                .orElseThrow(() -> new LostItemIdNotFoundException(id));
    }

    @GetMapping("/foundItems/{id}")
    FoundItem foundOne(@PathVariable Long id){

        return foundRepo.findById(id).orElseThrow(() -> new LostItemIdNotFoundException(id));
    }



   @GetMapping("/lostItems/search")
    List<LostItem> lostTwo(@RequestParam(value = "brand", defaultValue = "%%") String strLostBrand,
                           @RequestParam(value = "category", defaultValue = "%%") String strLostCategory,
                           @RequestParam(value = "color", defaultValue = "%%") String strLostColor){
// Like + %% kommer fra https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
        return lostRepo.findAllByBrandLikeAndCategoryLikeAndColorLikeAllIgnoreCase(strLostBrand, strLostCategory, strLostColor);
    }

    @GetMapping("/foundItems/search")
    List<FoundItem> foundTwo(@RequestParam(value = "brand", defaultValue = "%%") String strFoundBrand,
                             @RequestParam(value = "category", defaultValue = "%%") String strFoundCategory,
                             @RequestParam(value = "color", defaultValue = "%%") String strFoundColor){
// Like + %% kommer fra https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
        return foundRepo.findAllByBrandLikeAndCategoryLikeAndColorAllIgnoreCase(strFoundBrand, strFoundCategory,strFoundColor);


    }




    @PutMapping("/items/{id}")
    LostItem replaceItem(@RequestBody LostItem newItem, @PathVariable Long id) {

        return lostRepo.findById(id)
                .map(item -> {
                    item.setCategory(newItem.getCategory());
                    item.setBrand(newItem.getBrand());
                    return lostRepo.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return lostRepo.save(newItem);
                });
    }

    @DeleteMapping("/items/{id}")
    void deleteItem(@PathVariable Long id) {
        lostRepo.deleteById(id);
    }



}
