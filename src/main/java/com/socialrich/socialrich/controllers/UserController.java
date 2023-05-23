package com.socialrich.socialrich.controllers;

import com.socialrich.socialrich.constants.Constants;
import com.socialrich.socialrich.entity.RedesSociales;
import com.socialrich.socialrich.entity.User;
import com.socialrich.socialrich.exceptions.NoUserException;
import com.socialrich.socialrich.service.RedesSocialesService;
import com.socialrich.socialrich.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedesSocialesService redesSocialesService;



    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() throws NoUserException {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            log.warn(Constants.NO_USERS);
            throw new NoUserException(Constants.NO_USERS);
        }

        return ResponseEntity.ok(users);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws NoUserException {
        User user = userService.getUserById(userId);
        if (user == null) {
            log.warn(Constants.NO_USER_STRING);
            throw new NoUserException(Constants.NO_USER_STRING);
        }
        //no devolvemos las redes sociales,para eso esta el metodo /{userId}/networks
        user.setRedesSociales(null);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{userId}/networks")
    public ResponseEntity<User> getUserAndNetworksById(@PathVariable Long userId) throws NoUserException {
        User user = userService.getUserById(userId);
        if (user == null) {
            log.warn(Constants.NO_USER_STRING);
            throw new NoUserException(Constants.NO_USER_STRING);
        }
        return ResponseEntity.ok(user);
    }



    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) throws NoUserException {
        user.setId(userId);
        User updatedUser = userService.updateUser(user);
        if (updatedUser == null) {
            log.warn(Constants.NO_USER_STRING);
            throw new NoUserException(Constants.NO_USER_STRING);
        }
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/{redesSocialesId}")
    public ResponseEntity<User> updateRedSocialUser(@PathVariable Long userId, @PathVariable Long redesSocialesId) throws NoUserException {
        User user = userService.getUserById(userId);
        RedesSociales redesSociales = redesSocialesService.getRedesSocialesById(redesSocialesId);

        //compruebo cuantas redes sociales tiene
        if (user.getRedesSociales().isEmpty())
        {
            user.setRedSocialFavorita(redesSociales);
        }

        List <RedesSociales> redes = user.getRedesSociales();
        redes.add(redesSociales);

        user.setRedesSociales(redes);

        User updatedUser = userService.updateUser(user);
        if (updatedUser == null) {
            log.warn(Constants.NO_USER_STRING);
            throw new NoUserException(Constants.NO_USER_STRING);
        }
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/fav/{redesSocialesId}")
    public ResponseEntity<User> updateFavoriteRedSocialUser(@PathVariable Long userId, @PathVariable Long redesSocialesId) throws NoUserException {
        User user = userService.getUserById(userId);
        RedesSociales redesSociales = redesSocialesService.getRedesSocialesById(redesSocialesId);

        user.setRedSocialFavorita(redesSociales);

        User updatedUser = userService.updateUser(user);
        if (updatedUser == null) {
            log.warn(Constants.NO_USER_STRING);
            throw new NoUserException(Constants.NO_USER_STRING);
        }
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
