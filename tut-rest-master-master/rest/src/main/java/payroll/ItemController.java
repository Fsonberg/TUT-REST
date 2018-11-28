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
    private final LostUserRepository userRepo;
   // private final MatchRepository matchRepo;

    ItemController(LostItemRepository lostRepo, FoundItemRepository foundRepo, LostUserRepository userRepo) {
        this.lostRepo = lostRepo;
        this.foundRepo = foundRepo;
        this.userRepo = userRepo;
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

    @GetMapping("/users")
    List<Users> allUsers() {
        ArrayList users = new ArrayList();

        return userRepo.findAll();}

    @PostMapping ("/users")
    Users newUser (@RequestBody Users newUser) {return userRepo.save(newUser);}

    @GetMapping("/foundItems")
    List<FoundItem> allFound(){return foundRepo.findAll();}

    @PostMapping("/foundItems")
    List<Match> newFoundItem(@RequestBody FoundItem newFoundItem) {
        FoundItem savedFoundItem = foundRepo.save(newFoundItem);

        ArrayList<Match> postMatches = new ArrayList<>();

        for (int i = 0; i < allLost().size(); i++) {
            if (savedFoundItem.getCategory().equals(allLost().get(i).getCategory())
                    && savedFoundItem.getBrand().equals(allLost().get(i).getBrand())
                    && savedFoundItem.getColor().equals(allLost().get(i).getColor())
                    && allLost().get(i).isActive()) {

                Match m = new Match();
                m.setFoundID(savedFoundItem.getId());
                m.setLostID(allLost().get(i).getId());
                postMatches.add(m);
            }
        }
        return postMatches;
    }

    @GetMapping("/match")
    List<Match> matchLostFound(){
        ArrayList<Match> getMatches = new ArrayList<>();

        for (int i = 0; i <allFound().size() ; i++) {
            for (int j = 0; j <allLost().size() ; j++) {
                if(allFound().get(i).getCategory().equals(allLost().get(j).getCategory())
                        && allFound().get(i).getBrand().equals(allLost().get(j).getBrand())
                        && allFound().get(i).getColor().equals(allLost().get(j).getColor())
                        && allFound().get(i).isActive()
                        && allLost().get(j).isActive()){

                    Match m = new Match();
                    System.out.println("ID-LostItem: "+allLost().get(j).getId());
                    System.out.println("ID-FoundItem: "+allFound().get(i).getId());
                    m.setLostID(allLost().get(j).getId());
                    m.setFoundID(allFound().get(i).getId());
                    getMatches.add(m);
                }
            }
        }
        return getMatches; //
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
    @GetMapping("/users/{id}")
    Users usersOne(@PathVariable Long id){
       return userRepo.findById(id).orElseThrow(() -> new LostItemIdNotFoundException(id));
    }
    @GetMapping("/users/search")
    List<Users> userTwo(@RequestParam(value = "firstName", defaultValue = "%%")String strFirstName,
                        @RequestParam(value = "lastName", defaultValue = "%%")String strLastName,
                        @RequestParam(value = "address", defaultValue = "%%")String strAddress,
                        @RequestParam(value = "phoneNumber", defaultValue = "%%")String strPhoneNumber){

       return userRepo.findAllByFirstNameLikeAndLastNameLikeAndAddressLikeAndPhoneNumberLikeAllIgnoreCase(strFirstName,strLastName,strAddress,strPhoneNumber);
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
