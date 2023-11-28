package backendkurssi.pelivalikko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backendkurssi.pelivalikko.domain.Ammatti;
import backendkurssi.pelivalikko.domain.AmmattiRepository;
import backendkurssi.pelivalikko.domain.Hahmo;
import backendkurssi.pelivalikko.domain.HahmoRepository;
import backendkurssi.pelivalikko.domain.Pelaaja;
import backendkurssi.pelivalikko.domain.PelaajaRepository;

@SpringBootApplication
public class PelivalikkoApplication {

	private static final Logger log = LoggerFactory.getLogger(PelivalikkoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PelivalikkoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner pelivalikkoDemo(HahmoRepository repository, AmmattiRepository arepository, PelaajaRepository prepository) {
		return (args) -> {
			arepository.save(new Ammatti("Tiedustelija"));
			arepository.save(new Ammatti("Strategi"));
			arepository.save(new Ammatti("Ritari"));
			
			Pelaaja user1 = new Pelaaja("Admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			Pelaaja user2 = new Pelaaja("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			prepository.save(user1);
			prepository.save(user2);
			
			repository.save(new Hahmo(user1.getPelaajanimi(), 34, 6544, arepository.findByNimike("Ritari").get(0)));
			repository.save(new Hahmo("Varis", 31, 1098, arepository.findByNimike("Strategi").get(0)));
			repository.save(new Hahmo("Nausicaä", 49, 3226, arepository.findByNimike("Tiedustelija").get(0)));
			
			/*
			for (Hahmo book : repository.findAll()) {
				log.info(book.toString());
			}
			*/

		};
	}

}