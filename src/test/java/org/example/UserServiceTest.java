package org.example;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        // Start mocks
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toRegisterUserSuccessfully() {
        User user = new User("Chloe", "password", "chloe@email.com");

        when(userService.registerUser(user)).thenReturn(true);
        boolean result = userService.registerUser(user);
        assertTrue(result);
        verify(userService).registerUser(user);
        System.out.println("Registration successful");
    }

    @Test
    public void shouldNotRegisterUserIfUsernameAlreadyExists() {
        User user = new User("Chloe", "password", "c@email.com");

        when(userService.registerUser(user)).thenReturn(false);
        boolean result = userService.registerUser(user);
        assertFalse(result);
        verify(userService).registerUser(user);
        System.out.println("Username already exists!");
    }

    @Test
    public void toRegisterUserWithEmptyUsername() {
        User user = new User("", "password", "c@email.com");

        when(userService.registerUser(user)).thenReturn(false);
        boolean result = userService.registerUser(user);
        assertFalse(result);
        verify(userService).registerUser(user);
        System.out.println("Registration with empty username failed!");
    }

    @Test
    public void shouldLoginUserSuccessfully() {
        User user = new User("Chloe", "password", "c@email.com");

        when(userService.loginUser("Chloe", "password")).thenReturn(user);
        User loggedInUser = userService.loginUser("Chloe", "password");
        assertNotNull(loggedInUser);
        assertEquals(user, loggedInUser);
        verify(userService).loginUser("Chloe", "password");
        System.out.println("Login successful");
    }

    @Test
    public void shouldNotLoginUserIfUsernameNotFound() {
        when(userService.loginUser("notFound", "password1234")).thenReturn(null);
        User loggedInUser = userService.loginUser("notFound", "password1234");
        assertNull(loggedInUser);
        verify(userService).loginUser("notFound", "password1234");
        System.out.println("Not logged in, Username not found!");
    }

    @Test
    public void shouldNotLoginUserWithWrongPassword() {
        User user = new User("Chloe", "password", "c@email.com");

        when(userService.loginUser("Chloe", "wrongPassword")).thenReturn(null);
        User loggedInUser = userService.loginUser("Chloe", "wrongPassword");
        assertNull(loggedInUser);
        verify(userService).loginUser("Chloe", "wrongPassword");
        System.out.println("Not logged in, Wrong password!");
    }

    @AfterClass
    public static void tearDownClass() {
        // Clean up
    }

    @After
    public void tearDown() {
        // Clean up
    }
}


