package payroll;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;

//
// When a new entity is created, all entity parameters must be fill (!=null) else throw new exception
//

@RestController
class ItemController {

    private Long activeCustomer = 13L;
    private Long activeEmp = 18L;
    private final LostItemRepository lostRepo;
    private final FoundItemRepository foundRepo;
    private final CustomerRepository customerRepo;
    private final EmployeeRepository employeeRepo;
    private final IssuedMatchRepository issuedMatchRepo;

    ItemController(LostItemRepository lostRepo, FoundItemRepository foundRepo,
                   CustomerRepository customerRepo, EmployeeRepository employeeRepo,
                   IssuedMatchRepository issuedMatchRepo) {
        this.lostRepo = lostRepo;
        this.foundRepo = foundRepo;
        this.customerRepo = customerRepo;
        this.employeeRepo = employeeRepo;
        this.issuedMatchRepo = issuedMatchRepo;
}

    /**
     * phoneNumber as string is a problem because:
     * By default what ever you pass to the controller is treated as String and converted to respective types.
     * So even default values need to be set as String.
     * https://stackoverflow.com/questions/47813925/how-to-give-default-value-as-integer-in-requestparam
     */

    /**
     * Employee Post
     * Creates a new employee
     */
    @PostMapping ("/newEmployee")
    Employee newEmployee (@RequestBody Employee newEmployee) {return employeeRepo.save(newEmployee);}

    /**
     * Employee Gets
     * 1. get all employees
     * 2. get a single employee by specifying ID
     * 3. get all employees that matches a specific search parameter
     */
    @GetMapping("/employees")
    List<Employee> allEmployees() {
        if(employeeRepo.findAll().size() > 0){
            return employeeRepo.findAll() ;
        } else {
            throw new EmployeeException();
        }
    }

    @GetMapping ("/employee/{id}")
    Employee singleEmployeeID (@PathVariable Long id){
        return employeeRepo.findById(id).orElseThrow(()-> new EmployeeException(id));
    }

    @GetMapping("/employees/search")
    List<Employee> oneOrMoreEmployee (@RequestParam(value = "firstName", defaultValue = "%%")String strFirstName,
                                      @RequestParam(value = "lastName", defaultValue = "%%")String strLastName,
                                      @RequestParam(value = "address", defaultValue = "%%")String strAddress,
                                      @RequestParam(value = "phoneNumber", defaultValue = "%%") String strPhoneNumber,
                                      @RequestParam(value = "email", defaultValue = "%%") String strEmail) {

        return employeeRepo.findAllByFirstNameLikeAndLastNameLikeAndAddressLikeAndPhoneNumberLikeAndEmailLikeAllIgnoreCase
                (strFirstName,strLastName,strAddress,strPhoneNumber, strEmail);
    }

    /**
     * Customer Post
     * Creates a new customer
     */

    @PostMapping ("/newCustomer")
    Customer newCustomer (@RequestBody Customer newCustomer) {return customerRepo.save(newCustomer);}

    /**
     * Customer Get
     * 1. get all customers
     * 2. get a single customer by specifying ID
     * 3. get all customers that matches a specific search parameter
     */

    @GetMapping("/customers")
    List<Customer> allCustomers() {
        if(customerRepo.findAll().size() > 0){
            return customerRepo.findAll();
        } else {
            throw new CustomerException();
        }
    }

    @GetMapping ("/customer/{id}")
    Customer singleCustomerID (@PathVariable Long id){
        return customerRepo.findById(id).orElseThrow(()-> new CustomerException(id));
    }

    @GetMapping("/customers/search")
    List<Customer> oneOrMoreCustomers (@RequestParam(value = "firstName", defaultValue = "%%")String strFirstName,
                                    @RequestParam(value = "lastName", defaultValue = "%%")String strLastName,
                                    @RequestParam(value = "address", defaultValue = "%%")String strAddress,
                                    @RequestParam(value = "phoneNumber", defaultValue = "%%") String strPhoneNumber,
                                    @RequestParam(value = "email", defaultValue = "%%") String strEmail){

        return customerRepo.findAllByFirstNameLikeAndLastNameLikeAndAddressLikeAndPhoneNumberLikeAndEmailLikeAllIgnoreCase
                (strFirstName,strLastName,strAddress,strPhoneNumber, strEmail);
    }


    /**
     * FoundItem Post
     * Creates a new found item, and searches through all found items, label active
     */

    @PostMapping("/newFoundItem")
    List newFoundItem(@RequestBody FoundItem newFoundItem) {
        newFoundItem.setActive(true);
        newFoundItem.setEmpID(activeEmp);
        FoundItem savedFoundItem = foundRepo.save(newFoundItem);
        ArrayList<Match> postMatches = new ArrayList<>();

        for (int i = 0; i < allFoundActive().size(); i++) {
            if (savedFoundItem.getCategory().equals(allLostActive().get(i).getCategory())
                    && savedFoundItem.getBrand().equals(allLostActive().get(i).getBrand())
                    && savedFoundItem.getColor().equals(allLostActive().get(i).getColor())
                    && allLostActive().get(i).isActive()) {

                Match m = new Match();
                m.setFoundItemID(savedFoundItem.getFoundItemID());
                m.setLostItemID(allLostActive().get(i).getLostItemID());
                m.setCustomerID(allLostActive().get(i).getCustomerID());
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
     * 1. get all found items
     * 2. get all found items labeled active
     * 3. get all found items labeled disabled
     * 4. get a single found item by specifying ID
     * 5. get all found items that matches a specific search parameter
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

    @GetMapping("/foundItem/{id}")
    FoundItem foundOne(@PathVariable Long id){
        return foundRepo.findById(id).orElseThrow(() -> new FoundItemException(id));
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
     * Creates a new lost item, and labels it as active and sets its customerID to the current user
     */

    @PostMapping("/newLostItem")
    LostItem newItem(@RequestBody LostItem newItem) {
        newItem.setCustomerID(activeCustomer);
        newItem.setActive(true);

        return lostRepo.save(newItem);
    }

    /**
     * LostItem Get
     * 1. get all lost items
     * 2. get all lost items labeled active
     * 3. get all lost items labeled disabled
     * 4. get a single lost item by specifying ID
     * 5. get all lost items that matches a specific search parameter
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

    @GetMapping("/lostItem/{id}")
    LostItem lostOne (@PathVariable Long id) {
        return lostRepo.findById(id)
                .orElseThrow(() -> new LostItemException(id));
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
     * 1.   (GetCall) runs though the list of all active found items and checks if there is an exact match of each item
     *      in the list of all active lost items
     * 2.   (PostCall) takes a lost item id and a found Item id. - then creates a new match object which stores
     *      these ID's, the ID of the user who matches the lost item and the ID of the active employee
     * 3.   (GetCall) get all issued matches
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
                    m.setLostItemID(allLostActive().get(j).getLostItemID());
                    m.setFoundItemID(allFoundActive().get(i).getFoundItemID());
                    m.setCustomerID(allLostActive().get(j).getCustomerID());
                    m.setEmpID(allFoundActive().get(i).getEmpID());
                    getMatches.add(m);
                }
            }
        }
        if (getMatches.size() == 0) {
            throw new MatchException();
        } else {
            return getMatches;
        }
    }

   @PostMapping ("/issueAMatch")
   Match issueAMatch(@RequestBody Match issueAMatch) {
        Match savedMatch = issueAMatch;

        savedMatch.setEmpID(activeEmp);
        for (int i = 0; i < allLostActive().size(); i++) {
           if (savedMatch.getLostItemID().equals(allLostActive().get(i).getLostItemID())) {
               savedMatch.setCustomerID(allLostActive().get(i).getCustomerID());
               allLostActive().get(i).setActive(false);
           }
        }

        for (int i = 0; i < allFoundActive().size(); i++) {
           if (savedMatch.getFoundItemID().equals(allFoundActive().get(i).getFoundItemID())) {
               allFoundActive().get(i).setActive(false);
           }
        }
        return issuedMatchRepo.save(savedMatch);
   }

   @GetMapping ("/issuedMatches")
   List<Match> issuedMatches() {return issuedMatchRepo.findAll(); }

   @GetMapping ("/issuedMatches/search")
    List<Match> issuedMatches(@RequestParam(value = "foundItemID", defaultValue = "%%") String foundID,
                             @RequestParam(value = "lostItemID", defaultValue = "%%") String lostID,
                             @RequestParam(value = "customerID", defaultValue = "%%") String customerID,
                             @RequestParam(value = "empID", defaultValue = "%%") String empID){

        List<Match> convertedIssuedMatches = new ArrayList<Match>();
        Long fID = Long.parseLong(foundID);
        Long lID = Long.parseLong(lostID);
        Long cID = Long.parseLong(customerID);
        Long eID = Long.parseLong(empID);

        convertedIssuedMatches = issuedMatchRepo
                .findAllByLostItemIDLikeAndFoundItemIDLikeAndCustomerIDLikeAndEmpIDLikeAllIgnoreCase
                (fID, lID, cID, eID);
       return convertedIssuedMatches;
   }

    /**
     * putMapping
     * allows to change one or more information's associated with the specified ID of a
     * lost/found item or a customer/employee
     */
    @PutMapping("/lostItem/{id}")
    LostItem replaceItem(@RequestBody LostItem newItem, @PathVariable Long id) {
        return lostRepo.findById(id)

                .map(item -> {
                    if (newItem.getCategory() != null) {
                        item.setCategory(newItem.getCategory());
                    }
                    if (newItem.getBrand() != null) {
                        item.setBrand(newItem.getBrand());
                    }
                    if (newItem.getColor() != null) {
                        item.setColor(newItem.getColor());
                    }
                    if (newItem.getCustomerID() != null) {
                        item.setCustomerID(newItem.getCustomerID());
                    }
                    return lostRepo.save(item);
                }).orElseThrow(()-> new LostItemException(id));
    }

    @PutMapping("/foundItem/{id}")
    FoundItem replaceItem(@RequestBody FoundItem newItem, @PathVariable Long id) {
        return foundRepo.findById(id)

                .map(item -> {
                    if (newItem.getCategory() != null) {
                        item.setCategory(newItem.getCategory());
                    }
                    if (newItem.getBrand() != null) {
                        item.setBrand(newItem.getBrand());
                    }
                    if (newItem.getColor() != null) {
                        item.setColor(newItem.getColor());
                    }
                    if (newItem.getEmpID() != null) {
                        item.setEmpID(newItem.getEmpID());
                    }
                    return foundRepo.save(item);
                }).orElseThrow(()-> new FoundItemException(id));
    }

    @PutMapping("/customer/{id}")
     Customer replaceCustomerInfo(@RequestBody Customer newCustomer, @PathVariable Long id) {
        return customerRepo.findById(id)
                .map(customer -> {
                    if (newCustomer.getFirstName() != null) {
                        customer.setFirstName(newCustomer.getFirstName());
                    }
                    if (newCustomer.getLastName() != null) {
                        customer.setLastName(newCustomer.getLastName());
                    }
                    if (newCustomer.getAddress() != null) {
                        customer.setAddress(newCustomer.getAddress());
                    }
                    if (newCustomer.getPhoneNumber() != null) {
                        customer.setPhoneNumber(newCustomer.getPhoneNumber());
                    }
                    if (newCustomer.getEmail() != null) {
                        customer.setEmail(newCustomer.getEmail());
                    }
                    return customerRepo.save(customer);
                }).orElseThrow(()-> new CustomerException(id));
    }

    @PutMapping("/employee/{id}")
    Employee replaceEmployeeInfo(@RequestBody Employee newEmployee, @PathVariable Long id) {

            return employeeRepo.findById(id)
                    .map(employee -> {
                        if (newEmployee.getFirstName() != null ) {
                            employee.setFirstName(newEmployee.getFirstName());
                        }
                        if (newEmployee.getLastName() != null) {
                            employee.setLastName(newEmployee.getLastName());
                        }
                        if (newEmployee.getAddress() != null) {
                            employee.setAddress(newEmployee.getAddress());
                        }
                        if (newEmployee.getPhoneNumber() != null) {
                            employee.setPhoneNumber(newEmployee.getPhoneNumber());
                        }
                        if (newEmployee.getEmail() != null) {
                            employee.setEmail(newEmployee.getEmail());
                        }
                        return employeeRepo.save(employee);
                    }).orElseThrow(()-> new EmployeeException(id));
    }

    @PutMapping("/issuedMatches/{id}")
    Match replaceIssuedMatchInfo(@RequestBody Match newMatch, @PathVariable Long id) {
        return issuedMatchRepo.findById(id)
                .map(match -> {
                    if (newMatch.getFoundItemID() != null) {
                        match.setFoundItemID(newMatch.getFoundItemID());
                    }
                    if (newMatch.getLostItemID() != null) {
                        match.setLostItemID(newMatch.getLostItemID());
                    }
                    if (newMatch.getCustomerID() != null) {
                        match.setCustomerID(newMatch.getCustomerID());
                    }
                    if (newMatch.getEmpID() != null) {
                        match.setEmpID(newMatch.getEmpID());
                    }
                    return issuedMatchRepo.save(match);
                }).orElseThrow(()-> new MatchException(id));
    }

    /**
     * allows to delete a lost/found item or a customer/employee,
     * associated with the specified ID
     */

    @DeleteMapping("/deleteLostItem/{id}")
    String deleteLostItem(@PathVariable Long id) {
        try {

            LostItem tmpLostItem = lostRepo.findById(id).get();
            lostRepo.deleteById(id);
            String s = "Lost item: " + tmpLostItem.getCategory() + ", " + tmpLostItem.getBrand() +
                    ", " + tmpLostItem.getColor() + "; with id " + tmpLostItem.getLostItemID() + " has been deleted";
            return s;
        } catch (Exception e) {
            return new LostItemException(id).getMessage();
        }
    }

    @DeleteMapping("/deleteFoundItem/{id}")
    String deleteFoundItem(@PathVariable Long id) {
        try {

            FoundItem tmpFoundItem = foundRepo.findById(id).get();
            foundRepo.deleteById(id);
            String s = "Found item: " + tmpFoundItem.getCategory() + ", " + tmpFoundItem.getBrand() +
                    ", " + tmpFoundItem.getColor() + "; with id " + tmpFoundItem.getFoundItemID() + " has been deleted";
            return s;
        } catch (Exception e) {
            return new FoundItemException(id).getMessage();
        }
    }

    @DeleteMapping("/deleteCustomer/{id}")
    String deleteCustomer(@PathVariable Long id) {
        try {
            Customer tmpCustomer = customerRepo.findById(id).get();
            customerRepo.deleteById(id);
            String s = "Customer " + tmpCustomer.getFirstName()+ " " + tmpCustomer.getLastName()
                    + " with id: " + id + " has been deleted";
            return s;
        } catch (Exception e) {
            return new CustomerException(id).getMessage();
        }
    }


    @DeleteMapping("/deleteEmployee/{id}")
    String deleteEmployee(@PathVariable Long id) {
        try {
            Employee tmpEmployee = employeeRepo.findById(id).get();
            employeeRepo.deleteById(id);
            String s = "Employee " + tmpEmployee.getFirstName() + " " + tmpEmployee.getLastName()
                    + " with id: " + id + " has been deleted";
            return s;

        } catch (Exception e) {
            return new EmployeeException(id).getMessage();
        }
    }
}