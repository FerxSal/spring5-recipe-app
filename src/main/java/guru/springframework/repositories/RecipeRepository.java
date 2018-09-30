package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fernando on 30/09/18.
 */
public interface RecipeRepository extends CrudRepository<Recipe,Long>{



}
