package com.socialrich.socialrich.controllers;

import com.socialrich.socialrich.constants.Constants;
import com.socialrich.socialrich.dto.RedesSocialesDTO;
import com.socialrich.socialrich.dto.UserDTO;
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
    public ResponseEntity<List<UserDTO>> getAllUsers() throws NoUserException {
        List<UserDTO> usersDTO = userService.getAllUsers();
        if (usersDTO.isEmpty()) {
            log.warn(Constants.NO_USERS);
            throw new NoUserException();
        }

        return ResponseEntity.ok(usersDTO);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) throws NoUserException {
        UserDTO userDTO = userService.getUserById(userId);
        if (userDTO == null) {
            log.warn(Constants.NO_USER);
            throw new NoUserException();
        }
        //no devolvemos las redes sociales,para eso esta el metodo /{userId}/networks
        userDTO.setRedesSociales(null);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/{userId}/networks")
    public ResponseEntity<UserDTO> getUserAndNetworksById(@PathVariable Long userId) throws NoUserException {
        UserDTO userDTO = userService.getUserById(userId);
        if (userDTO == null) {
            log.warn(Constants.NO_USER);
            throw new NoUserException();
        }
        return ResponseEntity.ok(userDTO);
    }



    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        UserDTO savedUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) throws NoUserException {

        UserDTO updatedUser = userService.updateUser(userDTO,userId);
        if (updatedUser == null) {
            log.warn(Constants.NO_USER);
            throw new NoUserException();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/{redesSocialesId}")
    public ResponseEntity<UserDTO> updateRedSocialUser(@PathVariable Long userId, @PathVariable Long redesSocialesId) throws NoUserException {
        UserDTO userDTO = userService.getUserById(userId);
        RedesSocialesDTO redesSocialesDTO = redesSocialesService.getRedesSocialesById(redesSocialesId);

        //compruebo cuantas redes sociales tiene
        if (userDTO.getRedesSociales().isEmpty())
        {
            userDTO.setRedSocialFavorita(redesSocialesService.convertDTOtoEntity(redesSocialesDTO));
        }

        List <RedesSocialesDTO> redes = redesSocialesService.convertListEntitytoListDTO(userDTO.getRedesSociales());
        redes.add(redesSocialesDTO);

        userDTO.setRedesSociales(redesSocialesService.convertListDTOtoListEntity(redes));

        UserDTO updatedUser = userService.updateUser(userDTO, userId);
        if (updatedUser == null) {
            log.warn(Constants.NO_USER);
            throw new NoUserException();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/fav/{redesSocialesId}")
    public ResponseEntity<UserDTO> updateFavoriteRedSocialUser(@PathVariable Long userId, @PathVariable Long redesSocialesId) throws NoUserException {
        UserDTO userDTO = userService.getUserById(userId);
        RedesSocialesDTO redesSocialesDTO = redesSocialesService.getRedesSocialesById(redesSocialesId);

        userDTO.setRedSocialFavorita(redesSocialesService.convertDTOtoEntity(redesSocialesDTO));

        UserDTO updatedUser = userService.updateUser(userDTO, userId);
        if (updatedUser == null) {
            log.warn(Constants.NO_USER);
            throw new NoUserException();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
