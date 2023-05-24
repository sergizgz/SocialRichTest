package com.socialrich.socialrich.service.impl;

import com.socialrich.socialrich.dto.UserDTO;
import com.socialrich.socialrich.entity.User;
import com.socialrich.socialrich.repository.UserRepository;
import com.socialrich.socialrich.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = userRepository.save(modelMapper.map(userDTO,User.class));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()){
                return modelMapper.map(optionalUser.get(), UserDTO.class);
            }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userID) {

        User user = modelMapper.map(userDTO,User.class);
        user.setId(userID);

        Optional<User> optionalUser = userRepository.findById(user.getId());
            if (optionalUser.isPresent()){
                User existingUser = optionalUser.get();
                existingUser.setName(user.getName());
                existingUser.setSurname(user.getSurname());
                existingUser.setRedSocialFavorita(user.getRedSocialFavorita());
                User user2 = userRepository.save(existingUser);
                return modelMapper.map(user2, UserDTO.class);

            }
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO convertEntitytoDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User convertDTOtoEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public List<UserDTO> convertListEntitytoListDTO(List<User> userList) {
        return userList
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}