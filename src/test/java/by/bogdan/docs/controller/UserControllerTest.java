package by.bogdan.docs.controller;

import by.bogdan.docs.model.User;
import by.bogdan.docs.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bogdan Shishkin
 * project: restdocs-examples
 * date/time: 10/16/2018 / 12:05 AM
 * email: bahdan.shyshkin@itechart-group.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "src/docs/asciidoc/generated")
public class UserControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private UserRepository userRepository;

    @After
    public void tearDown() {
	userRepository.removeAll();
    }

    @Test
    public void testGetUserById() throws Exception {
	UUID uuid = UUID.randomUUID();
	User bogdan = User.builder().name("Bogdan").surname("Shishkin").userId(uuid).build();
	userRepository.save(bogdan);

	ResultActions resultActions = mockMvc.perform(get("/users/{userId}", bogdan.getUserId()))
		.andDo(document("{class-name}/{method-name}"));
	resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("name").value("Bogdan"))
		.andExpect(jsonPath("surname").value("Shishkin"));

    }
}
