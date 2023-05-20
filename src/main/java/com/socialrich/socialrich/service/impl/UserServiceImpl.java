package com.socialrich.socialrich.service.impl;

import com.socialrich.socialrich.entity.User;
import com.socialrich.socialrich.repository.UserRepository;
import com.socialrich.socialrich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()){
                return optionalUser.get();
            }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
            if (optionalUser.isPresent()){
                User existingUser = optionalUser.get();
                existingUser.setName(user.getName());
                existingUser.setSurname(user.getSurname());
                existingUser.setRedSocialFavorita(user.getRedSocialFavorita());
                return userRepository.save(existingUser);

            }
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}