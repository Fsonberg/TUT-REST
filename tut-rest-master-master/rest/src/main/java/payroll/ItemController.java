package payroll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

@EnableJpaRepositories
class Config{}

@RestController
class ItemController {

    private Long activeCustomer = 13L;
    private Long activeEmp = 18L;
    private final LostItemRepository lostRepo;
    private final FoundItemRepository foundRepo;
    private final LostUserRepository userRepo;
    private final EmployeeRepository empRepo;
    private final IssuedMatchRepository issuedMatchRepo;
   // private final MatchRepository matchRepo;

    ItemController(LostItemRepository lostRepo, FoundItemRepository foundRepo,
                   LostUserRepository userRepo, EmployeeRepository empRepo,
                   IssuedMatchRepository issuedMatchRepo) {
        this.lostRepo = lostRepo;
        this.foundRepo = foundRepo;
        this.userRepo = userRepo;
        this.empRepo = empRepo;
        this.issuedMatchRepo = issuedMatchRepo;
}

    /**
     * Users Post
     */

    @PostMapping ("/users")
    Users newUser (@RequestBody Users newUser) {return userRepo.save(newUser);}


    /**
     * Users Get
     */

    @GetMapping("/users")
    List<Users> allUsers() {
        ArrayList users = new ArrayList();

        return userRepo.findAll();
    }

    @GetMapping ("/users/{id}")
    Users singleUserID (@PathVariable Long id){
        return userRepo.findById(id).orElseThrow(()-> new LostItemIdNotFoundException(id));
    }

    @GetMapping("/users/search")
    List<Users> oneOrMoreUsers (@RequestParam(value = "firstName", defaultValue = "%%")String strFirstName,
                        @RequestParam(value = "lastName", defaultValue = "%%")String strLastName,
                        @RequestParam(value = "address", defaultValue = "%%")String strAddress,
                        @RequestParam(value = "phoneNumber", defaultValue = "%%")String strPhoneNumber){

        return userRepo.findAllByFirstNameLikeAndLastNameLikeAndAddressLikeAndPhoneNumberLikeAllIgnoreCase
                (strFirstName,strLastName,strAddress,strPhoneNumber);
    }

    /**
     * FoundItem Post
     */

    @PostMapping("/foundItems")
    List newFoundItem(@RequestBody FoundItem newFoundItem) {
        FoundItem savedFoundItem = foundRepo.save(newFoundItem);
        savedFoundItem.setActive(true);
        ArrayList<Match> postMatches = new ArrayList<>();
        savedFoundItem.setEmpID(activeEmp);

        for (int i = 0; i < allLost().size(); i++) {
            if (savedFoundItem.getCategory().equals(allLost().get(i).getCategory())
                    && savedFoundItem.getBrand().equals(allLost().get(i).getBrand())
                    && savedFoundItem.getColor().equals(allLost().get(i).getColor())
                    && allLost().get(i).isActive()) {

                Match m = new Match();
                m.setFoundID(savedFoundItem.getFoundItemID());
                m.setLostID(allLost().get(i).getLostItemID());
                m.setUserID(allLost().get(i).getUserID());
                postMatches.add(m);
            }
        }
        if (postMatches.size() == 0) {
            ArrayList postedItemNoMatch = new ArrayList();

            postedItemNoMatch.add(savedFoundItem);
            return postedItemNoMatch;
        } else {
            return postMatches;
        }
    }

    /**
     * FoundItem Get
     */

    @GetMapping("/foundItems")
    List<FoundItem> allFound(){return foundRepo.findAll();}

    @GetMapping("/foundItemsA")
    List<FoundItem> allFoundActive() {
        ArrayList afa = new ArrayList();
        for (int i = 0; i < allFound().size(); i++) {
            if (allFound().get(i).isActive()) {
                afa.add(allFound().get(i));
            }
        }
        return afa;
    }

    @GetMapping("/foundItemsD")
    List<FoundItem> allFoundDisabled() {
        ArrayList afd = new ArrayList();
        for (int i = 0; i < allFound().size(); i++) {
            if (!allFound().get(i).isActive()) {
                afd.add(allFound().get(i));
            }
        }
        return afd;
    }

    @GetMapping("/foundItems/{id}")
    FoundItem foundOne(@PathVariable Long id){
        return foundRepo.findById(id).orElseThrow(() -> new LostItemIdNotFoundException(id));
    }

    @GetMapping("/foundItems/search")
    List<FoundItem> foundTwo(@RequestParam(value = "brand", defaultValue = "%%") String strFoundBrand,
                             @RequestParam(value = "category", defaultValue = "%%") String strFoundCategory,
                             @RequestParam(value = "color", defaultValue = "%%") String strFoundColor){

        return foundRepo.findAllByBrandLikeAndCategoryLikeAndColorLikeAllIgnoreCase(strFoundBrand, strFoundCategory,strFoundColor);
    }

    /**
     * LostItem Post
     */

    @PostMapping("/lostItems")
    LostItem newItem(@RequestBody LostItem newItem) {
        newItem.setUserID(activeCustomer);

        return lostRepo.save(newItem);
    }

    /**
     * LostItem Get
     */

    @GetMapping("/lostItems")
    List<LostItem> allLost() {
        return lostRepo.findAll();
    }

    @GetMapping("/lostItems/{id}")
    LostItem one(@PathVariable Long id) {
        return lostRepo.findById(id)
                .orElseThrow(() -> new LostItemIdNotFoundException(id));
    }

    @GetMapping("/lostItems/search")
    List<LostItem> lostTwo(@RequestParam(value = "brand", defaultValue = "%%") String strLostBrand,
                           @RequestParam(value = "category", defaultValue = "%%") String strLostCategory,
                           @RequestParam(value = "color", defaultValue = "%%") String strLostColor){

        return lostRepo.findAllByBrandLikeAndCategoryLikeAndColorLikeAllIgnoreCase(strLostBrand, strLostCategory, strLostColor);
    }

    /**
     * Match
     */

    @GetMapping("/match")
    List<Match> matchLostFound(){
        ArrayList<Match> getMatches = new ArrayList<>();

        for (int i = 0; i <allFoundActive().size() ; i++) {
            for (int j = 0; j <allLost().size() ; j++) {
                if(allFoundActive().get(i).getCategory().equals(allLost().get(j).getCategory())
                        && allFoundActive().get(i).getBrand().equals(allLost().get(j).getBrand())
                        && allFoundActive().get(i).getColor().equals(allLost().get(j).getColor())
                        && allFoundActive().get(i).isActive()
                        && allLost().get(j).isActive()){

                    Match m = new Match();
                    System.out.print("LostItem-ID: "+allLost().get(j).getLostItemID() + " matches with ");
                    System.out.println("FoundItem-ID: "+allFound().get(i).getFoundItemID());
                    m.setLostID(allLost().get(j).getLostItemID());
                    m.setFoundID(allFoundActive().get(i).getFoundItemID());
                    m.setUserID(allLost().get(j).getUserID());
                    m.setEmpID(allFoundActive().get(i).getEmpID());
                    getMatches.add(m);
                }
            }
        }
        return getMatches; //
    }

   @PostMapping ("/issuedMatch")
    IssuedMatch ism (@RequestBody IssuedMatch ism) {
        IssuedMatch savedIssuedMatch = issuedMatchRepo.save(ism);

        savedIssuedMatch.setEmpID(activeEmp);
        for (int i = 0; i < allLost().size(); i++) {
           if (allLost().get(i).getLostItemID() == savedIssuedMatch.getLostID()) {
               allLost().get(i).setActive(false);
               //LostItem l = new LostItem();
           }
        }

        for (int i = 0; i < allFoundActive().size(); i++) {
           if (allFoundActive().get(i).getFoundItemID() == savedIssuedMatch.getFoundID()) {
               allFoundActive().get(i).setActive(false);
           }
        }

        return savedIssuedMatch;
   }

    /*
    ############################################################################################
    ############################################################################################
    ############################################ FIX EN DAG ################################################
    ############################################################################################
    ############################################################################################
    */
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
                    newItem.setLostItemID(id);
                    return lostRepo.save(newItem);
                });
    }

    @DeleteMapping("/items/{id}")
    void deleteItem(@PathVariable Long id) {
        lostRepo.deleteById(id);
    }
}
