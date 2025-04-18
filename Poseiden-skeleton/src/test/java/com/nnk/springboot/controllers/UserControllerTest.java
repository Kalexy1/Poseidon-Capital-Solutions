package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setRole("USER");

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        lenient().when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
    }

    @Test
    void testHome_ShouldReturnUserListPage() {
        List<User> users = Arrays.asList(user);
        when(userService.getAllUsers()).thenReturn(users);

        String viewName = userController.home(model);

        assertEquals("user/list", viewName);
        verify(userService, times(1)).getAllUsers();
        verify(model, times(1)).addAttribute("users", users);
    }

    @Test
    void testAddUserForm_ShouldReturnAddPage() {
        String viewName = userController.addUserForm(model);

        assertEquals("user/add", viewName);
        verify(model, times(1)).addAttribute(eq("user"), any(User.class));
    }

    @Test
    void testValidate_ShouldSaveUserAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = userController.validate(user, bindingResult, model);

        assertEquals("redirect:/user/list", viewName);
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    void testValidate_ShouldReturnAddPageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = userController.validate(user, bindingResult, model);

        assertEquals("user/add", viewName);
        verify(userService, times(0)).saveUser(any(User.class));
    }

    @Test
    void testShowUpdateForm_ShouldReturnUpdatePage() {
        when(userService.getUserById(1)).thenReturn(user);

        String viewName = userController.showUpdateForm(1, model);

        assertEquals("user/update", viewName);
        verify(model, times(1)).addAttribute("user", user);
    }

    @Test
    void testUpdateUser_ShouldUpdateUserAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = userController.updateUser(1, user, bindingResult, model);

        assertEquals("redirect:/user/list", viewName);
        verify(userService, times(1)).updateUser(1, user);
    }


    @Test
    void testUpdateUser_ShouldReturnUpdatePageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = userController.updateUser(1, user, bindingResult, model);

        assertEquals("user/update", viewName);
        verify(userService, times(0)).updateUser(anyInt(), any(User.class));
    }

    @Test
    void testDeleteUser_ShouldDeleteUserAndRedirect() {
        String viewName = userController.deleteUser(1, model);

        assertEquals("redirect:/user/list", viewName);
        verify(userService, times(1)).deleteUser(1);
    }
}
