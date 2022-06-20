package com.recipesMS.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipesMS.controller.RecipesController;
import com.recipesMS.model.Recipe;
import com.recipesMS.pojo.RecipeRequest;
import com.recipesMS.service.RecipesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RecipesController.class, ReciepesWebControllerTest.RecipeConfiguration.class })
public class ReciepesWebControllerTest {

    @Configuration
    static class RecipeConfiguration {

        @Bean
        public RecipesService recipesService() throws Exception {
            RecipesService recipesService = mock(RecipesService.class);
            when(recipesService.addRecipe(any(Recipe.class))).thenReturn(Utility.getRecipeDetails());
            return recipesService;
        }
    }

    private HandlerMethodArgumentResolver mockHandlerMethodArgumentResolver() {

        return new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.getParameter().getType() == RecipeRequest.class;
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                RecipeRequest recipeRequest = new RecipeRequest();
                Recipe recipe = Utility.getRecipeDetails();
                recipeRequest.setRecipe(recipe);
                return recipeRequest;
            }
        };
    }

    @Autowired
    public RecipesController recipesController;

    MockMvc mockMvc;
    private RecipeRequest recipeRequest;

    @BeforeEach
    void setUp() {
        recipeRequest = new RecipeRequest();
        Recipe recipe = Utility.getRecipeDetails();
        recipeRequest.setRecipe(recipe);

        mockMvc = MockMvcBuilders.standaloneSetup(recipesController)
                .setCustomArgumentResolvers(mockHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void addRecipe() throws Exception {
        String json = new ObjectMapper().writeValueAsString(recipeRequest);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addRecipe")
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(mvcResult -> {
                    String response = mvcResult.getResponse().getContentAsString();
                    response.equals(json);
                });
    }

}
