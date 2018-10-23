package guru.springframework.controllers;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by fernando on 20/10/18.
 */
public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    IndexController indexController;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);


    }


    @Test
    public void testMockMVC() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));

    }


    @Test
    public void getIndexPage() throws Exception {


        Set<Recipe> recipes = new HashSet<>();

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setCookTime(34);
        recipe.setDifficulty(Difficulty.MODERATE);

        recipes.add(recipe);
        recipes.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = indexController.getIndexPage(model);
        assertEquals("index",viewName);
        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2,setInController.size());

    }


}