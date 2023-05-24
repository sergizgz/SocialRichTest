package com.socialrich.socialrich.service;

import com.socialrich.socialrich.dto.UserDTO;
import com.socialrich.socialrich.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO userDTO, Long userId);

    void deleteUser(Long userId);

    UserDTO convertEntitytoDTO(User user);

    User convertDTOtoEntity(UserDTO userDTO);

    List<UserDTO> convertListEntitytoListDTO (List<User> userList);
}
