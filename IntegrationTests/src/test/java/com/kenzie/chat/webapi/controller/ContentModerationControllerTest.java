package com.kenzie.chat.webapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.chat.webapi.IntegrationTest;
import com.kenzie.chat.webapi.controller.model.CommentCreateRequest;
import com.kenzie.chat.webapi.controller.model.UserCreateRequest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Use this class to create your integration tests for the ContentModerationController
@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContentModerationControllerTest {
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
    public void newUser_createsSpam_deactivateUser() throws Exception{
        //GIVEN
        CommentCreateRequest commentRequest = new CommentCreateRequest();
        commentRequest.setOwner(mockNeat.strings().get());
        commentRequest.setTitle(mockNeat.strings().get());
        commentRequest.setContent(mockNeat.strings().get());

        UserCreateRequest createRequest = new UserCreateRequest();
        createRequest.setUsername(commentRequest.getOwner());
        createRequest.setName(mockNeat.names().get());
        createRequest.setEmail(mockNeat.emails().get());
        queryUtility.userControllerClient.createUser(createRequest)
                .andExpect(status().isOk());

        //WHEN
        //new user exceeds limit of 3
        queryUtility.commentControllerClient.addComment(commentRequest);
        queryUtility.commentControllerClient.addComment(commentRequest);
        queryUtility.commentControllerClient.addComment(commentRequest);
        queryUtility.commentControllerClient.addComment(commentRequest);

        queryUtility.contentModerationControllerClient.checkForSpam()
                .andExpect(status().isOk());
    }

}
