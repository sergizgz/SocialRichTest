package com.socialrich.socialrich.controllers;

import com.socialrich.socialrich.entity.RedesSociales;
import com.socialrich.socialrich.entity.User;
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

    private static final String NO_USER_STRING = "No user with this ID on database";
    private static final String NO_USERS = "No users on database";

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            log.warn(NO_USERS);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            log.warn(NO_USER_STRING);
            return ResponseEntity.notFound().build();
        }
        //no devolvemos las redes sociales,para eso esta el metodo /{userId}/networks
        user.setRedesSociales(null);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{userId}/networks")
    public ResponseEntity<User> getUserAndNetworksById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            log.warn(NO_USER_STRING);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }



    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        User updatedUser = userService.updateUser(user);
        if (updatedUser == null) {
            log.warn(NO_USER_STRING);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/{redesSocialesId}")
    public ResponseEntity<User> updateRedSocialUser(@PathVariable Long userId, @PathVariable Long redesSocialesId) {
        User user = userService.getUserById(userId);
        RedesSociales redesSociales = redesSocialesService.getRedesSocialesById(redesSocialesId);

        //compruebo cuantas redes sociales tiene
        if (user.getRedesSociales().size() == 0)
        {
            user.setRedSocialFavorita(redesSociales);
        }

        List <RedesSociales> redes = user.getRedesSociales();
        redes.add(redesSociales);

        user.setRedesSociales(redes);

        User updatedUser = userService.updateUser(user);
        if (updatedUser == null) {
            log.warn(NO_USER_STRING);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/fav/{redesSocialesId}")
    public ResponseEntity<User> updateFavoriteRedSocialUser(@PathVariable Long userId, @PathVariable Long redesSocialesId) {
        User user = userService.getUserById(userId);
        RedesSociales redesSociales = redesSocialesService.getRedesSocialesById(redesSocialesId);

        user.setRedSocialFavorita(redesSociales);

        User updatedUser = userService.updateUser(user);
        if (updatedUser == null) {
            log.warn(NO_USER_STRING);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
