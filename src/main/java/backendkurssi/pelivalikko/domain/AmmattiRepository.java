package backendkurssi.pelivalikko.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AmmattiRepository extends CrudRepository<Ammatti, Long> {
    List<Ammatti> findByNimike(@Param("nimike") String nimike);
    
}
