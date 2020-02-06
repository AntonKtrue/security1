package org.hyperskill.security.controller;

import org.hyperskill.security.config.BasicLoginPasswordFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BusinessControllerTest {

    @Autowired
    private MockMvc rest;

    private final static String BASE_URL = "/api/business";

    @Test
    public void getInfoTest() throws Exception {
        rest.perform(get(BASE_URL + "/info"))
                .andExpect(status().isOk());
    }

    @Test
    public void getEcho() throws Exception {
        rest.perform(post(BASE_URL + "/echo")
                .header(BasicLoginPasswordFilter.LOGIN_HEADER, "user1")
                .header(BasicLoginPasswordFilter.PASSWORD_HEADER, "password1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"text\":\"Test message\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.message").value("Test message from user1"));
    }

}