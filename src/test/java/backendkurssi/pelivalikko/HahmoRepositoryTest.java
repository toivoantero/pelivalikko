package backendkurssi.pelivalikko;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import backendkurssi.pelivalikko.domain.Ammatti;
import backendkurssi.pelivalikko.domain.AmmattiRepository;
import backendkurssi.pelivalikko.domain.Hahmo;
import backendkurssi.pelivalikko.domain.HahmoRepository;
import backendkurssi.pelivalikko.domain.Pelaaja;
import backendkurssi.pelivalikko.domain.PelaajaRepository;

//@DataJpaTest

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PelivalikkoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HahmoRepositoryTest {

	@Autowired
	private HahmoRepository repository;
	@Autowired
	private AmmattiRepository arepository;
	@Autowired
	private PelaajaRepository prepository;

	@Test
	public void findByNameShouldReturnAge() {
		List<Hahmo> hahmot = repository.findByNimi("Varis");
		assertThat(hahmot).hasSize(1);
		assertThat(hahmot.get(0).getIka()).isEqualTo(31);
	}

	@Test
	public void luoUusiHahmo() {
		Ammatti ammatti = new Ammatti("testi");
		arepository.save(ammatti);
		Hahmo hahmo = new Hahmo("Mickey", 1, 2, ammatti);
		repository.save(hahmo);
		assertThat(hahmo.getId()).isNotNull();
	}

	@Test
	public void poistaUusiHahmo() {
		List<Hahmo> hahmot = repository.findByNimi("Nausicaä");
		Hahmo hahmo = hahmot.get(0);
		repository.delete(hahmo);
		List<Hahmo> uudetHahmot = repository.findByNimi("Nausicaä");
		assertThat(uudetHahmot).hasSize(0);
	}

	@Test
	public void luoUusiPelaaja() {
		Pelaaja pelaaja = new Pelaaja("a", "b", "c");
		prepository.save(pelaaja);
		Pelaaja pelaajaRepossa = prepository.findByPelaajanimi("a");
		assertThat(pelaajaRepossa.getRole()).isEqualTo("c");
	}

}
