package payroll;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ItemController {

    private final LostItemRepository repository;
    private final FoundItemRepository foundrepo;

    ItemController(FoundItemRepository foundrepo) {this.foundrepo = foundrepo;}
    ItemController(LostItemRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    // tag::get-aggregate-root[]

    ////////////////////////////////////////////////////// GETMAPPING FOR LOSTITEMS/////////////////////////////////////////////////////////////////////////
    @GetMapping("/lostItems")
    Resources<Resource<LostItem>> allLost() {

        List<Resource<LostItem>> lostItems = repository.findAll().stream()
                .map(item -> new Resource<>(item,
                        linkTo(methodOn(ItemController.class).one(item.getId())).withSelfRel(),
                        linkTo(methodOn(ItemController.class).allLost()).withRel("lostItems")))
                .collect(Collectors.toList());

        return new Resources<>(lostItems,
                linkTo(methodOn(ItemController.class).allLost()).withSelfRel());
    }

    //////////////////////////////////////////////////GETMAPPING FOR FOUNDITEMS///////////////////////////////////////////////////////////////////

    @GetMapping("/foundItems")
    Resources<Resource<LostItem>> allFound() {

        List<Resource<LostItem>> foundItems = repository.findAll().stream()
                .map(item -> new Resource<>(item,
                        linkTo(methodOn(ItemController.class).one(item.getId())).withSelfRel(),
                        linkTo(methodOn(ItemController.class).allFound()).withRel("lostItems")))
                .collect(Collectors.toList());

        return new Resources<>(foundItems,
                linkTo(methodOn(ItemController.class).allFound()).withSelfRel());

    }


    // end::get-aggregate-root[]
    //////////////////////////////////////////////////// POSTMAPPING LOSTITEMS /////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/lostItems")
    LostItem newItem(@RequestBody LostItem newItem) {
        System.out.println("inside post");
        return repository.save(newItem);
    }

    // Single item

    // tag::get-single-item[]
    @GetMapping("/lostItems/{id}")
    Resource<LostItem> one(@PathVariable Long id) {

        LostItem item = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)); //Exception skal ændres..!

        return new Resource<>(item,
                linkTo(methodOn(ItemController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ItemController.class).allLost()).withRel("lostItems"));
    }

    ////////////////////////////////////////////////////////POSTMAPPING FOUNDITEMS/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/foundItems")
    FoundItem newFoundItem(@RequestBody FoundItem newFoundItem) {
        System.out.println("inside post");
        return foundrepo.save(newFoundItem);
    }

    // Single item

    // tag::get-single-item[]
    @GetMapping("/foundItems/{id}")
    Resource<FoundItem> two(@PathVariable Long id) {

        FoundItem item = foundrepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)); //Exception skal ændres..!

        return new Resource<>(item,
                linkTo(methodOn(ItemController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ItemController.class).allFound()).withRel("foundItems"));
    }















    

    // end::get-single-item[]

    @PutMapping("/lostItems/{id}")
    LostItem replaceItem(@RequestBody LostItem newItem, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setCategory(newItem.getCategory());
                    employee.setBrand(newItem.getBrand());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return repository.save(newItem);
                });
    }

    @DeleteMapping("/lostItems/{id}")
    void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    } // set til invalid i stedet
}
