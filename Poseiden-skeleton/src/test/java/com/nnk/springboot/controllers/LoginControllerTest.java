package com.nnk.springboot.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "testUser", roles = {"USER"})
    @Test
    public void testErrorPage() throws Exception {
        mockMvc.perform(get("/error"))
               .andExpect(MockMvcResultMatchers.status().isOk()) 
               .andExpect(MockMvcResultMatchers.view().name("403")) 
               .andExpect(MockMvcResultMatchers.model().attribute("errorMsg", "Vous n'êtes pas autorisé à accéder à cette page."));
    }
}
