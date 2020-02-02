package org.hyperskill.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

}