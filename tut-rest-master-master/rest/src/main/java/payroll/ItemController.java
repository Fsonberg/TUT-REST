package payroll;

import java.util.ArrayList;
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
        ArrayList<Match> t = new ArrayList<>();
        boolean matchFound = false;

        /**
         * Forsøg på Match funktion.. virker ikke på nuværende tidspunkt.
         * Der skal oprettes flere parametre i (if) statement. --> Color, Brand m.m. Dette skal addes til en liste, som skal være retur form
         */
        for (int i = 0; i < allLost().size() ; i++) {
            Match match = new Match();
            if(savedFoundItem.getCategory().equals(allLost().get(i).getCategory())){
                System.out.println("Inde i match - category");
               // System.out.println("Category: "+allLost().get(i).getCategory());

                if(savedFoundItem.getBrand().equals(allLost().get(i).getBrand())){
                    System.out.println("inde i match - brand");
                    //System.out.println("Brand: "+allLost().get(i).getBrand());

                    if (savedFoundItem.getColor().equals(allLost().get(i).getColor())){
                        matchFound = true;
                        if (matchFound == true ){
                        System.out.println("inde i match - color");
                       // System.out.println("Color: "+allLost().get(i).getColor());
                        System.out.println("ID-LostItem: "+allLost().get(i).getId());
                        System.out.println("ID-FoundItem: "+savedFoundItem.getId());
                        match.setFoundID(savedFoundItem.getId());
                        match.setLostID(allLost().get(i).getId());
                        t.add(match);
                        return t;
                        }

                    }
                }
             }
        }
        return savedFoundItem;
    }

    /**
     * Match funktion.... virker ikke
     * @return
     */
    @GetMapping("/match")
    List<Match> match(){
        ArrayList<Match> l = new ArrayList<>();

        System.out.println("inde i match");
        for (int i = 0; i <allFound().size() ; i++) {
            for (int j = 0; j <allLost().size() ; j++) {
                Match match = new Match();
                System.out.println("inde i match - inde i løkke");
                if(allFound().get(i).getCategory().equals(allLost().get(j).getCategory())){

                   System.out.println("Inde i match - category");
                   System.out.println("Category: "+allLost().get(i).getCategory());

                    if(allFound().get(i).getBrand().equals(allLost().get(j).getBrand())){

                        System.out.println("inde i match - brand");
                        System.out.println("Brand: "+allLost().get(i).getBrand());

                        if (allFound().get(i).getColor().equals(allLost().get(j).getColor())){
                           // System.out.println("inde i match - color");
                            // System.out.println("Color: "+allLost().get(i).getColor());
                            System.out.println("ID-LostItem: "+allLost().get(j).getId());
                            System.out.println("ID-FoundItem: "+allFound().get(i).getId());

                            match.setLostID(allLost().get(j).getId());
                            match.setFoundID(allFound().get(i).getId());
                            l.add(match);
                        }
                    }
                }
            }
        }
        return l; //Giver Exception.......
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