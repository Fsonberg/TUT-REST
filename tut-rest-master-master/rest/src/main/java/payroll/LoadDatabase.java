package payroll;

import lombok.extern.slf4j.Slf4j; //data-logging
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    /**
     * hardcoded database for development- & test-purposes when no database is connected to the program
     */
	@Bean
	CommandLineRunner initDatabase(LostItemRepository lostRepo, FoundItemRepository foundRepo, CustomerRepository customerRepo, EmployeeRepository employeeRepo, IssuedMatchRepository issuedMatchRepo) {
		return args -> {
			//LOST REPOSITORY
			System.out.println("Preloading " + lostRepo.save(new LostItem("Phone", "Apple", "blue",13l,false)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Phone", "Apple", "black",13l,true)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Phone", "Apple", "black",14l,true)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Phone", "Apple", "black", 14l, true)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Phone", "Apple", "yellow", 15l, true)));
            System.out.println("Preloading " + lostRepo.save(new LostItem("Phone", "Android", "black",16l,true)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Clothes", "Gucci", "green", 17l,true)));

			//FOUND REPOSITORY
			System.out.println("preloading " + foundRepo.save(new FoundItem("Phone", "Apple", "black", 19l,true)));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Phone", "Apple", "white", 19l,true )));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Phone","Android", "yellow", 20l,true )));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Clothes","Gucci", "green", 21l,true)));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Phone", "Apple", "blue", 19l,false)));

			//CUSTOMER REPOSITORY
            System.out.println("preloading " + customerRepo.save(new Customer("Casper", "Christensen", "Strandvejen", "22504140", "madril4life@humus.tihi.com")));
            System.out.println("preloading " + customerRepo.save(new Customer("Frank", "Hvam", "Rådhuspladsen", "44175896", "madril4life2@humus.tihi.com")));
            System.out.println("preloading " + customerRepo.save(new Customer("Lars", "Hjortshøj", "Salsavej", "99228140", "madril4life3@humus.tihi.com")));
            System.out.println("preloading " + customerRepo.save(new Customer("Lasse", "Rimmer", "Tuborgvej", "22514240", "madril4life4@humus.tihi.com")));
            System.out.println("preloading " + customerRepo.save(new Customer("Jan", "Gintberg", "Fredriksberg Allé", "60421900", "madril4life5@humus.tihi.com")));

			//EMPLOYEE REPOSITORY
            System.out.println("preloading " + employeeRepo.save(new Employee("Frederik", "Sonberg", "Ishøj", "50520020", "sonber@hotmal.dk")));
            System.out.println("preloading " + employeeRepo.save(new Employee("Rune", "Riber", "Roskilde", "20944162", "runeriber@gmail.com")));
            System.out.println("preloading " + employeeRepo.save(new Employee("Jeppe", "Hald", "Østerbro", "78451296", "jpHaldWagner@yahoo.dk")));
            System.out.println("preloading " + employeeRepo.save(new Employee("Rasmus", "Lohse", "Roskilde", "96385214", "rlohse@bing.com")));

			//ISSUEDMATCH REPOSITORY
			System.out.println("preloading " + issuedMatchRepo.save(new Match(12l, 1l, 13l, 21l)));
		};
	}
}
