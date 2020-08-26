package com.cognixia.jump.controller;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)

public class UserControllerTest {
    private final String STARTING_URI = "http://localhost:8080/userApi";

    @MockBean
    private UserRepository repo;

    UserController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllUsers() throws Exception {
        String uri = STARTING_URI + "/allUsers";
        List<User> users = Arrays.asList(
                new User(1L, "dav@hello.edu", "123", "Dav", "Mor", 1L, User.Role.ROLE_USER),
                new User(2L, "bav@hello.edu", "123", "bav", "Mor", 2L, User.Role.ROLE_USER)
        );

        mockMvc.perform( get(uri))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testUserById() throws Exception {
        String uri = STARTING_URI + "/users/{id}";

        long id = 1;
        long addId = 1;
        User user = new User(id, "davmorales@ucdavis.edu", "123", "David", "Morales", addId, User.Role.ROLE_USER);

        when( repo.findById(user.getId()) ).thenReturn(Optional.of(user));

        mockMvc.perform( get(uri, id) )
                .andExpect( status().isOk() )
                .andExpect( content().contentType(MediaType.APPLICATION_JSON) );

        verify(repo, times(1)).findById(id);
        verifyNoMoreInteractions(repo);
    }

}