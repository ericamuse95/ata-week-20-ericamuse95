package com.kenzie.chat.webapi.controller;

import com.kenzie.chat.usersystem.UserDto;
import com.kenzie.chat.webapi.IntegrationTest;
import com.kenzie.chat.webapi.controller.model.CommentCreateRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.chat.webapi.controller.model.UserCreateRequest;
import com.kenzie.chat.webapi.service.CommentService;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();
    private final MockNeat mockNeat = MockNeat.threadLocal();
    private QueryUtility queryUtility;

    @BeforeAll
    public void setup() {
        queryUtility = new QueryUtility(mvc);
    }

    @Test
    public void can_create_comment() throws Exception {
        // Fix this test

        // GIVEN
        CommentCreateRequest commentRequest = new CommentCreateRequest();
        UserCreateRequest createRequest = new UserCreateRequest();
        commentRequest.setOwner(mockNeat.strings().get());
        commentRequest.setTitle(mockNeat.strings().get());
        commentRequest.setContent(mockNeat.strings().get());
        createRequest.setUsername(commentRequest.getOwner());

        queryUtility.userControllerClient.createUser(createRequest)
                .andExpect(status().isOk());

        // WHEN
        queryUtility.commentControllerClient.addComment(commentRequest)
        // THEN
                .andExpect(status().isOk());
    }

    // Add additional tests here
    @Test
    public void deactivated_user_leaves_comment() throws Exception {
        //GIVEN
        CommentCreateRequest commentRequest = new CommentCreateRequest();
        UserCreateRequest createRequest = new UserCreateRequest();
        commentRequest.setOwner(mockNeat.strings().get());
        commentRequest.setTitle(mockNeat.strings().get());
        commentRequest.setContent(mockNeat.strings().get());
        createRequest.setUsername(commentRequest.getOwner());

        //WHEN
        //deactivated user tries to leave comment
        mvc.perform(
                get("/deactivate/{username}", createRequest.getUsername())
                        .accept(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isNotFound());
    }

}

