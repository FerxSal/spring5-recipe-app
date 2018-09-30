package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by fernando on 30/09/18.
 */
public interface CategoryRepository extends CrudRepository<Category,Long>{

  Optional<Category> findByDescription(String description);



}
