package payroll;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(LostItemRepository repository, FoundItemRepository foundrepo) {
		return args -> {
			System.out.println("Preloading " + repository.save(new LostItem("Electronics", "Apple")));
			System.out.println("Preloading " + repository.save(new LostItem("Clothes", "Gucci")));


			System.out.println("preloading " + foundrepo.save(new FoundItem("Electronic", "Apple")));

		};
	}
}
