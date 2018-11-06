package payroll;

import java.util.List;

import org.springframework.context.annotation.Conditional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;


//FIX MATCH MELLEM TABELLER

@EnableJpaRepositories
class Config{}

@RestController
class ItemController {

    private final LostItemRepository lostRepo;

    private final FoundItemRepository foundRepo;

   // private final MatchRepository matchRepo;

    ItemController(LostItemRepository lostRepo, FoundItemRepository foundRepo) {
        this.lostRepo = lostRepo;
        this.foundRepo = foundRepo;

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
        FoundItem savedFoundItem = foundRepo.save(newFoundItem);


        /**
         * Forsøg på Match funktion.. virker ikke på nuværende tidspunkt. evt HJÆLP RUBY.
         */
        for (int i = 0; i < allLost().size() ; i++) {
            if(savedFoundItem.getCategory().equals(allLost().get(i).getCategory())){

                System.out.println("Inde i match - category");
            }
        }
        return savedFoundItem;
    }

    // Single item

    /**
     * @param id
     * @return
     */
    @GetMapping("/lostItems/{id}")
    LostItem one(@PathVariable Long id) {

        return lostRepo.findById(id)
                .orElseThrow(() -> new LostItemIdNotFoundException(id));
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/foundItems/{id}")
    FoundItem foundOne(@PathVariable Long id){

        return foundRepo.findById(id).orElseThrow(() -> new LostItemIdNotFoundException(id));
    }

    /**
     * @param strLostBrand
     * @param strLostCategory
     * @param strLostColor
     * @return
     * Like + %% kommer fra https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
     */

   @GetMapping("/lostItems/search")
    List<LostItem> lostTwo(@RequestParam(value = "brand", defaultValue = "%%") String strLostBrand,
                           @RequestParam(value = "category", defaultValue = "%%") String strLostCategory,
                           @RequestParam(value = "color", defaultValue = "%%") String strLostColor){

        return lostRepo.findAllByBrandLikeAndCategoryLikeAndColorLikeAllIgnoreCase(strLostBrand, strLostCategory, strLostColor);
    }

    /**
     * @param strFoundBrand
     * @param strFoundCategory
     * @param strFoundColor
     * @return
     * // Like + %% kommer fra https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
     */

    @GetMapping("/foundItems/search")
    List<FoundItem> foundTwo(@RequestParam(value = "brand", defaultValue = "%%") String strFoundBrand,
                             @RequestParam(value = "category", defaultValue = "%%") String strFoundCategory,
                             @RequestParam(value = "color", defaultValue = "%%") String strFoundColor){

        return foundRepo.findAllByBrandLikeAndCategoryLikeAndColorAllIgnoreCase(strFoundBrand, strFoundCategory,strFoundColor);


    }




    @PutMapping("/items/{id}")
    LostItem replaceItem(@RequestBody LostItem newItem, @PathVariable Long id) {

        return lostRepo.findById(id)
                .map(item -> {
                    item.setCategory(newItem.getCategory());
                    item.setBrand(newItem.getBrand());
                    item.setColor(newItem.getColor());
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
