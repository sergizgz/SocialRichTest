package com.socialrich.socialrich;

import com.socialrich.socialrich.controllers.UserController;
import com.socialrich.socialrich.entity.User;
import com.socialrich.socialrich.exceptions.NoUserException;
import com.socialrich.socialrich.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetUserById() throws NoUserException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("john");

        Mockito.when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(userId);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUserByIdNotFound() throws NoUserException {
        Long userId = 1L;

        Mockito.when(userService.getUserById(userId)).thenReturn(null);

        ResponseEntity<User> response = userController.getUserById(userId);

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertNull(response.getBody());
    }

    @Test
    public void testGetAllUsers() throws NoUserException {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("john");
        User user2 = new User();
        user2.setId(2L);
        user2.setName("jane");
        users.add(user1);
        users.add(user2);

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(users, response.getBody());
    }

    @Test
    public void testGetAllUsersNoContent() throws NoUserException {
        List<User> users = new ArrayList<>();

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assert.assertNull(response.getBody());
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("john");

        Mockito.when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(user);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertEquals(user, response.getBody());
    }

    @Test
    public void testUpdateUser() throws NoUserException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("john");

        Mockito.when(userService.updateUser(user)).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(userId, user);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(user, response.getBody());
    }

    @Test
    public void testUpdateUserNotFound() throws NoUserException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("john");

        Mockito.when(userService.updateUser(user)).thenReturn(null);

        ResponseEntity<User> response = userController.updateUser(userId, user);

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertNull(response.getBody());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        ResponseEntity<Void> response = userController.deleteUser(userId);

        Mockito.verify(userService, Mockito.times(1)).deleteUser(userId);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assert.assertNull(response.getBody());
    }

}
