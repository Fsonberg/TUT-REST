package payroll;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongToIntFunction;

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
    List<Users> allUsers() {return userRepo.findAll();}

    @GetMapping ("/users/{id}")
    Users singleUserID (@PathVariable Long id){
        // FIX EXEPTIONS!!!
        // FIX EXEPTIONS!!!
        // FIX EXEPTIONS!!!
        // FIX EXEPTIONS!!!
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
        ArrayList<Match> postMatches = new ArrayList<>();
        savedFoundItem.setActive(true);
        savedFoundItem.setEmpID(activeEmp);

        for (int i = 0; i < allFoundActive().size(); i++) {
            if (savedFoundItem.getCategory().equals(allLostActive().get(i).getCategory())
                    && savedFoundItem.getBrand().equals(allLostActive().get(i).getBrand())
                    && savedFoundItem.getColor().equals(allLostActive().get(i).getColor())
                    && allLostActive().get(i).isActive()) {

                Match m = new Match();
                m.setFoundID(savedFoundItem.getFoundItemID());
                m.setLostID(allLostActive().get(i).getLostItemID());
                m.setUserID(allLostActive().get(i).getUserID());
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

        return foundRepo.findAllByBrandLikeAndCategoryLikeAndColorLikeAllIgnoreCase
                (strFoundBrand, strFoundCategory,strFoundColor);
    }

    /**
     * LostItem Post
     */

    @PostMapping("/lostItems")
    LostItem newItem(@RequestBody LostItem newItem) {
        newItem.setUserID(activeCustomer);
        newItem.setActive(true);

        return lostRepo.save(newItem);
    }

    /**
     * LostItem Get
     */

    @GetMapping("/lostItems")
    List<LostItem> allLost() {
        return lostRepo.findAll();
    }

    @GetMapping("/lostItemsA")
    List<LostItem> allLostActive() {
        ArrayList<LostItem> ala = new ArrayList<>();
        for (int i = 0; i < allLost().size(); i++) {
            if (allLost().get(i).isActive()) {
                ala.add(allLost().get(i));
            }
        }
        return ala;
    }

    @GetMapping("/lostItemsD")
    List<LostItem> allLostDisabled() {
        ArrayList<LostItem> ald = new ArrayList<>();
        for (int i = 0; i < allLost().size(); i++) {
            if (!allLost().get(i).isActive()) {
                ald.add(allLost().get(i));
            }
        }
        return ald;
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

        return lostRepo.findAllByBrandLikeAndCategoryLikeAndColorLikeAllIgnoreCase
                (strLostBrand, strLostCategory, strLostColor);
    }

    /**
     * Match
     */

    @GetMapping("/match")
    List<Match> matchLostFound(){
        ArrayList<Match> getMatches = new ArrayList<>();

        for (int i = 0; i <allFoundActive().size() ; i++) {
            for (int j = 0; j <allLostActive().size() ; j++) {
                if(allFoundActive().get(i).getCategory().equals(allLostActive().get(j).getCategory())
                        && allFoundActive().get(i).getBrand().equals(allLostActive().get(j).getBrand())
                        && allFoundActive().get(i).getColor().equals(allLostActive().get(j).getColor())
                        && allFoundActive().get(i).isActive()
                        && allLostActive().get(j).isActive()){

                    Match m = new Match();
                    System.out.println();
                    System.out.print("LostItem-ID: "+allLostActive().get(j).getLostItemID() + " matches with ");
                    System.out.println("FoundItem-ID: "+allFoundActive().get(i).getFoundItemID());
                    m.setLostID(allLostActive().get(j).getLostItemID());
                    m.setFoundID(allFoundActive().get(i).getFoundItemID());
                    m.setUserID(allLostActive().get(j).getUserID());
                    m.setEmpID(allFoundActive().get(i).getEmpID());
                    getMatches.add(m);
                }
            }
        }
        return getMatches; //
    }
/*

  */
   @PostMapping ("/issueAMatch")
    IssuedMatch issueAMatch(@RequestBody IssuedMatch issueAMatch) {
        IssuedMatch savedIssuedMatch = issueAMatch;

        savedIssuedMatch.setEmpID(activeEmp);
        for (int i = 0; i < allLostActive().size(); i++) {
           if (savedIssuedMatch.getLostID().equals(allLostActive().get(i).getLostItemID())) {
               allLostActive().get(i).setActive(false);
           }
        }

        for (int i = 0; i < allFoundActive().size(); i++) {
           if (savedIssuedMatch.getFoundID().equals(allFoundActive().get(i).getFoundItemID())) {
               allFoundActive().get(i).setActive(false);
           }
        }
        return issuedMatchRepo.save(savedIssuedMatch);
   }

   @GetMapping ("/issuedMatches")
   List<IssuedMatch> issuedMatches() {return issuedMatchRepo.findAll(); }

    /*
    ############################################################################################
    ############################################################################################
    ############################################ FIX EN DAG ####################################
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
