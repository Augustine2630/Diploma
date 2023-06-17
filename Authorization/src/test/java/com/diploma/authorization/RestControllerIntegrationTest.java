package com.diploma.authorization;

import com.diploma.authorization.controller.RegistrationController;
import com.diploma.authorization.model.User;
import com.diploma.authorization.service.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UserDetailsServiceImpl.class)
@AutoConfigureMockMvc
public class RestControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserDetailsServiceImpl service;

    @org.junit.jupiter.api.Test
    void contextLoads() {
    }
    @Test
    public void userFound() throws Exception {

        UserDetails userDetails = service.loadUserByUsername("1234");
        assert userDetails.isAccountNonExpired();
    }
}
