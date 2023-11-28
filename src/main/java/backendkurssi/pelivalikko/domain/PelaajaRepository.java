package backendkurssi.pelivalikko.domain;

import org.springframework.data.repository.CrudRepository;

import backendkurssi.pelivalikko.domain.Pelaaja;

public interface PelaajaRepository extends CrudRepository<Pelaaja, Long> {
	Pelaaja findByPelaajanimi(String pelaajanimi);
}