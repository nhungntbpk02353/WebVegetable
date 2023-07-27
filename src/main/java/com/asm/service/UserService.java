package com.asm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.Repository.UserRepository;
import com.asm.domain.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public void addUser(User user) {
        userRepository.save(user);
    }
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
    
    
}
