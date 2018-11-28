package payroll;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(LostItemRepository lostRepo, FoundItemRepository foundRepo, LostUserRepository userRepo) {
		return args -> {
			//LOST REPOSITORY
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue","Jens" ,false)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue", "Jens",false)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue", "Jens" ,false)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "blue", "Jens", false)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple", "yellow", "Gert", true)));
            System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Android", "black","Kurt", true)));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Clothes", "Gucci", "green", "Grethe", true)));

			//FOUND REPOSITORY
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics", "Apple", "", true)));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics", "Apple", "black", true )));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics","Android", "yellow", true )));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Clothes","Gucci", "green", true)));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronics", "Apple", "blue", true)));

			//USER REPOSITORY
			System.out.println("preloading " + userRepo.save(new Users("Frederik", "Sonberg", "Ishøj", "50520020", "getGit@iasjd.aijs")));
			System.out.println("preloading " + userRepo.save(new Users("Rune", "Riber", "Roskilde", "1823713913", "getGit@iasjd.aijs")));
		};
	}
}
