package payroll;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

@RestController
class ItemController {

    private final LostItemRepository lostRepo;

    private final FoundItemRepository foundRepo;

    ItemController(LostItemRepository lostRepo, FoundItemRepository foundRepo) {
        this.lostRepo = lostRepo;
        this.foundRepo = foundRepo;
    }




    // Aggregate root

    // tag::get-aggregate-root[]

    ////////////////////////////////////////////////////// GETMAPPING FOR LOSTITEMS/////////////////////////////////////////////////////////////////////////
    @GetMapping("/lostItems")
    Resources<Resource<LostItem>> allLost() {

        List<Resource<LostItem>> lostItems = lostRepo.findAll().stream()
                .map(item -> new Resource<>(item,
                        linkTo(methodOn(ItemController.class).one(item.getId())).withSelfRel(),
                        linkTo(methodOn(ItemController.class).allLost()).withRel("lostItems")))
                .collect(Collectors.toList());



        return new Resources<>(lostItems,
                linkTo(methodOn(ItemController.class).allLost()).withSelfRel());
    }

    //////////////////////////////////////////////////GETMAPPING FOR FOUNDITEMS///////////////////////////////////////////////////////////////////

    @GetMapping("/foundItems")
    Resources<Resource<FoundItem>> allFound() {

        List<Resource<FoundItem>> foundItems = foundRepo.findAll().stream()
                .map(item -> new Resource<>(item,
                        linkTo(methodOn(ItemController.class).two(item.getId())).withSelfRel(),
                        linkTo(methodOn(ItemController.class).allFound()).withRel("foundItems")))
                .collect(Collectors.toList());

        return new Resources<>(foundItems,
                linkTo(methodOn(ItemController.class).allFound()).withSelfRel());

    }


    // end::get-aggregate-root[]
    //////////////////////////////////////////////////// POSTMAPPING LOSTITEMS /////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/lostItems")
    LostItem newItem(@RequestBody LostItem newItem) {
        System.out.println("inside post");
        return lostRepo.save(newItem);
    }

    // Single item

    // tag::get-single-item[]
    @GetMapping("/lostItems/{id}")
    Resource<LostItem> one(@PathVariable Long id) {

        LostItem item = lostRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)); //Exception skal ændres..!

        return new Resource<>(item,
                linkTo(methodOn(ItemController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ItemController.class).allLost()).withRel("lostItems"));
    }

    ////////////////////////////////////////////////////////POSTMAPPING FOUNDITEMS/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/foundItems")
    FoundItem newFoundItem(@RequestBody FoundItem newFoundItem) {
        System.out.println("inside post");
        return foundRepo.save(newFoundItem);
    }

    // Single item

    // tag::get-single-item[]
    @GetMapping("/foundItems/{id}")
    Resource<FoundItem> two(@PathVariable Long id) {

        FoundItem item = foundRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)); //Exception skal ændres..!

        return new Resource<>(item,
                linkTo(methodOn(ItemController.class).two(id)).withSelfRel(),
                linkTo(methodOn(ItemController.class).allFound()).withRel("foundItems"));
    }

    @GetMapping("/foundItems/brand/")
    Resource<FoundItem> getItem (@PathVariable String strBrand){

        FoundItem foundItem = foundRepo.findByBrand(strBrand)
                .orElseThrow(()-> new EmployeeNotFoundException(new Long(0)));



        return new Resource<>(foundItem);
    }

   /* @RequestMapping(value="foundItems/search/brand/", method = RequestMethod.GET)
    Resource<FoundItem> getItem(@RequestParam("brand") String strBrand){


        FoundItem item = foundRepo.findByBrand(strBrand).get();
                //.orElseThrow(() -> new EmployeeNotFoundException(new Long(0))); //Exception skal ændres..!

            return new Resource<>(item,linkTo(methodOn(ItemController.class).getItem(strBrand)).withSelfRel(),
                linkTo(methodOn(ItemController.class).allFound()).withRel("brand"));

    } */









//////////////////////////////////////////////////////////// PUTMAPPING LOSTITEMS////////////////////////////////////////////////////////////////////////////////////





    // end::get-single-item[]

    @PutMapping("/lostItems/{id}")
    LostItem replaceItem(@RequestBody LostItem newItem, @PathVariable Long id) {

        return lostRepo.findById(id)
                .map(employee -> {
                    employee.setCategory(newItem.getCategory());
                    employee.setBrand(newItem.getBrand());
                    return lostRepo.save(employee);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return lostRepo.save(newItem);
                });
    }

    @DeleteMapping("/lostItems/{id}")
    void deleteItem(@PathVariable Long id) {
        lostRepo.deleteById(id);
    } // set til invalid i stedet
}
