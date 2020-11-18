package com.polytechmtp.covidalert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polytechmtp.covidalert.controllers.UsersController;
import com.polytechmtp.covidalert.models.User;
import com.polytechmtp.covidalert.repositories.UserRepository;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@SpringBootTest
class UsersTests {

	private MockMvc mockMvc;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UsersController usersController;



	@BeforeEach
	public void setup() {
		System.out.println("Passe dans le setup");
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
	}


	@Test
	public void testList() throws Exception {
		List<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());

		when(userRepository.findAll()).thenReturn((List) users);
		mockMvc.perform(get("/api/v1/users")).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void testGetOne() throws Exception {
		long id = 1;
		User user = new User(
				1,
				"Jean",
				"Castex",
				"test@test.fr",
				"00000000000",
				"abc",
				null
		);

		when(userRepository.getOne(id)).thenReturn(user);
		when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));
		mockMvc.perform(get("/api/v1/users/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", isA(LinkedHashMap.class)))

				.andExpect(jsonPath("$.*").exists())
				.andExpect(jsonPath("$.*", notNullValue()))
				.andExpect(jsonPath("$.*", isA(JSONArray.class)))
				.andExpect(jsonPath("$.user_id", is(1)))
				.andExpect(jsonPath("$.first_name", is("Jean")))
				.andExpect(jsonPath("$.last_name", is("Castex")))
				.andExpect(jsonPath("$.email", is("test@test.fr")))
				.andExpect(jsonPath("$.phone_number", is("00000000000")))
				.andExpect(jsonPath("$.password", is("abc")));
	}



	@Test
	public void createUser() throws Exception {


		User user = new User(
				1,
				"Jean",
				"Castex",
				"test@test.fr",
				"00000000000",
				"abc",
				null
		);

		String userString = "{ \"" +
				"first_name\":\"Jean\", " +
				"\"last_name\":\"Castex\", " +
				"\"email\":\"test@test.fr\", " +
				"\"phone_number\":\"0000000000\", " +
				"\"password\":\"abc\" " +
				"}";

		mockMvc.perform(post("/api/v1/users")
				.content(userString)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print())
				.andExpect(status().isCreated());
	}



	@Test
	public void deleteUser() throws Exception {
		long id = 1;
		User user = new User(
				1,
				"Jean",
				"Castex",
				"test@test.fr",
				"00000000000",
				"abc",
				null
		);

		when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));

		mockMvc.perform(delete("/api/v1/users/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}


	@Test
	public void updateOne() throws Exception {
		long id = 1;
		User user = new User(
				1,
				"Jean",
				"Castex",
				"test@test.fr",
				"00000000000",
				"abc",
				null
		);

		String userString = "{ " +
				"\"user_id\":\"1\", " +
				"\"first_name\":\"Jeannot\", " +
				"\"last_name\":\"Castex\", " +
				"\"email\":\"test@test.fr\", " +
				"\"phone_number\":\"0000000000\", " +
				"\"password\":\"abc\" " +
				"}";
		System.out.println(userString);
		when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));
		when(userRepository.getOne(id)).thenReturn(user);
		mockMvc.perform(put("/api/v1/users/1")
				.content(userString)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)

		)
				.andDo(print())
				.andExpect(status().isOk());
	}
}
