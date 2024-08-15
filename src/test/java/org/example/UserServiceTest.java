package org.example;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {
    @Mock
    private UserService userService;

    @Before
    public void setup() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toRegisterUserSuccessfully() {
        User user = new User("newUser", "password123", "newuser@example.com");

        when(userService.registerUser(user)).thenReturn(true);
        boolean result = userService.registerUser(user);
        assertTrue(result);
        verify(userService).registerUser(user);
    }

    @Test
    public void shouldNotRegisterUserIfUsernameAlreadyExists() {
        User user = new User("existingUser", "password123", "existing@example.com");
        when(userService.registerUser(user)).thenReturn(false);
        boolean result = userService.registerUser(user);
        assertFalse(result);
        verify(userService).registerUser(user);
    }

    @Test
    public void toRegisterUserWithEmptyUsername() {
        User user = new User("", "password123", "emptyuser@example.com");
        boolean result = userService.registerUser(user);
        assertFalse(result);
    }

    @Test
    public void shouldLoginUserSuccessfully() {
        User user = new User("loginUser", "password123", "login@example.com");
        when(userService.loginUser("loginUser", "password123")).thenReturn(user);
        User loggedInUser = userService.loginUser("loginUser", "password123");
        assertNotNull(loggedInUser);
        assertEquals(user, loggedInUser);
        verify(userService).loginUser("loginUser", "password123");
    }

    @Test
    public void shouldNotLoginUserIfUsernameNotFound() {
        when(userService.loginUser("unknownUser", "password123")).thenReturn(null);
        User loggedInUser = userService.loginUser("unknownUser", "password123");
        assertNull(loggedInUser);
        verify(userService).loginUser("unknownUser", "password123");
    }

    @Test
    public void shouldNotLoginUserWithWrongPassword() {
        User user = new User("loginUser", "password123", "login@example.com");
        when(userService.loginUser("loginUser", "wrongPassword")).thenReturn(null);
        User loggedInUser = userService.loginUser("loginUser", "wrongPassword");
        assertNull(loggedInUser);
        verify(userService).loginUser("loginUser", "wrongPassword");
    }

    @AfterClass
    public static void tearDownClass() {
        // Clean up resources if needed
    }

    @After
    public void tearDown() {
        // Clean up resources if needed
    }
}





