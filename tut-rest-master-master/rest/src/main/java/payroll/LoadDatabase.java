package payroll;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(LostItemRepository lostRepo, FoundItemRepository foundRepo) {
		return args -> {
			//LOST REPOSITORY
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue")));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue")));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue")));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue")));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "yellow")));
            System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Android", "black")));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Clothes", "Gucci", "green	")));

			//FOUND REPOSITORY
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics", "Apple", "")));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics", "Apple", "black")));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics","Android", "yellow")));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Clothes","Gucci", "green")));

		};
	}
}
