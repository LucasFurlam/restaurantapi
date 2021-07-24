package one.digitalinnovation.restaurantapi.controller;

import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.restaurantapi.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.math.BigDecimal;
import java.util.Collections;

import static one.digitalinnovation.restaurantapi.utils.RestaurantUtils.asJsonString;
import static one.digitalinnovation.restaurantapi.utils.RestaurantUtils.createFakeDTO;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    private static final String RESTAURANT_API_URL_PATH = "/api/v1/restaurants";

    private MockMvc mockMvc;

    private RestaurantController restaurantController;

    @Mock
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        restaurantController = new RestaurantController(restaurantService);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testWhenPOSTIsCalledThenARestaurantShouldBeCreated() throws Exception {
        RestaurantDTO expectedRestaurantDTO = createFakeDTO();
        MessageResponseDTO expectedResponseMessage = createMessageResponse("Created restaurant with ID ", 1L);

        when(restaurantService.create(expectedRestaurantDTO)).thenReturn(expectedResponseMessage);

        mockMvc.perform(post(RESTAURANT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedRestaurantDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(expectedResponseMessage.getMessage())));
    }

    @Test
    void testWhenGETWithValidIsCalledThenAPersonShouldBeReturned() throws Exception {
        var expectedValidId = 1L;
        RestaurantDTO expectedRestaurantDTO = createFakeDTO();
        expectedRestaurantDTO.setId(expectedValidId);

        when(restaurantService.findById(expectedValidId)).thenReturn(expectedRestaurantDTO);

        mockMvc.perform(get(RESTAURANT_API_URL_PATH + "/" + expectedValidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Lucas")))
                .andExpect(jsonPath("$.freightRate").value(BigDecimal.valueOf(2.2)));
    }

//    @Test
//    void testWhenGETWithInvalidIsCalledThenAnErrorMessagenShouldBeReturned() throws Exception {
//        var expectedValidId = 1L;
//        PersonDTO expectedPersonDTO = createFakeDTO();
//        expectedPersonDTO.setId(expectedValidId);
//
//        when(personService.findById(expectedValidId)).thenThrow(PersonNotFoundException.class);
//
//        mockMvc.perform(get(PEOPLE_API_URL_PATH + "/" + expectedValidId)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testWhenGETIsCalledThenPeopleListShouldBeReturned() throws Exception {
//        var expectedValidId = 1L;
//        PersonDTO expectedPersonDTO = createFakeDTO();
//        expectedPersonDTO.setId(expectedValidId);
//        List<PersonDTO> expectedPeopleDTOList = Collections.singletonList(expectedPersonDTO);
//
//        when(personService.listAll()).thenReturn(expectedPeopleDTOList);
//
//        mockMvc.perform(get(PEOPLE_API_URL_PATH)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].firstName", is("Rodrigo")))
//                .andExpect(jsonPath("$[0].lastName", is("Peleias")));
//    }
//
//    @Test
//    void testWhenPUTIsCalledThenAPersonShouldBeUpdated() throws Exception {
//        var expectedValidId = 1L;
//        PersonDTO expectedPersonDTO = createFakeDTO();
//        MessageResponseDTO expectedResponseMessage = createMessageResponse("Person successfully updated with ID", 1L);
//
//        when(personService.update(expectedValidId, expectedPersonDTO)).thenReturn(expectedResponseMessage);
//
//        mockMvc.perform(put(PEOPLE_API_URL_PATH + "/" + expectedValidId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(expectedPersonDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message", is(expectedResponseMessage.getMessage())));
//    }
//
//    @Test
//    void testWhenDELETEIsCalledThenAPersonShouldBeDeleted() throws Exception {
//        var expectedValidId = 1L;
//
//        mockMvc.perform(delete(PEOPLE_API_URL_PATH + "/" + expectedValidId)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }

    private MessageResponseDTO createMessageResponse(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }

}
