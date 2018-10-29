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
			System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Apple")));
            System.out.println("Preloading " + lostRepo.save(new LostItem("Electronics", "Android")));
			System.out.println("Preloading " + lostRepo.save(new LostItem("Clothes", "Gucci")));


			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronic", "Apple")));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronic", "Apple")));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Electronic","Samsung")));
			System.out.println("preloading " + foundRepo.save(new FoundItem("Clothes","Gucci")));

		};
	}
}
