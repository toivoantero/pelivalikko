package backendkurssi.pelivalikko.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import backendkurssi.pelivalikko.domain.Hahmo;

@RepositoryRestResource
public interface HahmoRepository extends CrudRepository<Hahmo, Long> {
	List<Hahmo> findByNimi(@Param("nimi") String nimi);
	List<Hahmo> findByKokemuspisteet(@Param("kokemuspisteet") int kokemuspisteet);
    
}
