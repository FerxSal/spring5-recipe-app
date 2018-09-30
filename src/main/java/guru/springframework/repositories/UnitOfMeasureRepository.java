package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by fernando on 30/09/18.
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {

 Optional<UnitOfMeasure> findByDescription(String description);

}
