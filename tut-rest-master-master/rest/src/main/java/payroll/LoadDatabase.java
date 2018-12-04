package payroll;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(LostItemRepository lostRepo, FoundItemRepository foundRepo, CustomerRepository customerRepo) {
		return args -> {
			//LOST REPOSITORY
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue",true)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue",true)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue",true)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue", true)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "yellow", true)));
            System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Android", "black",true)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Clothes", "Gucci", "green", true)));

			//FOUND REPOSITORY
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics", "Apple", "blue", true)));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics", "Apple", "black", true )));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics","Android", "yellow", true )));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Clothes","Gucci", "green", true)));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics", "Apple", "blue", false)));

			//CUSTOMER REPOSITORY
			System.out.println("preloading " + customerRepo.save(new Customer("Frederik", "Sonberg", "Ishøj", 50520020, "sonber@tændstik.dk")));
			System.out.println("preloading " + customerRepo.save(new Customer("Rune", "Riber", "Roskilde", 20944162, "runeriber@gmail.com")));
		};
	}
}
